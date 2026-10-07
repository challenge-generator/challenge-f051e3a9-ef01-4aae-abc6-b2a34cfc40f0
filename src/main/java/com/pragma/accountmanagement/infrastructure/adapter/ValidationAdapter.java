package com.pragma.accountmanagement.infrastructure.adapter;



import com.pragma.accountmanagement.domain.model.AccountType;
import com.pragma.accountmanagement.domain.model.Builder;
import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.port.out.ValidationPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

@Slf4j
@Component
public class ValidationAdapter implements ValidationPort {

    private final WebClient validationWebClient;
    
    @Value("${app.validation.external.timeout-ms:3000}")
    private int validationTimeoutMs;
    
    @Value("${app.validation.external.enabled:true}")
    private boolean externalValidationEnabled;
    
    private static final Set<String> SUPPORTED_CURRENCIES = Set.of("USD", "EUR", "GBP", "JPY", "COP", "MXN");
    private static final Set<String> VALID_ACCOUNT_TYPES = Set.of("SAVINGS", "CHECKING", "INVESTMENT");
    private static final BigDecimal MIN_INITIAL_BALANCE = new BigDecimal("100.00");
    private static final BigDecimal MAX_INITIAL_BALANCE = new BigDecimal("1000000.00");
    private static final Pattern CLIENT_ID_PATTERN = Pattern.compile("^[A-Z0-9]{6,20}$");
    private static final Pattern ACCOUNT_ID_PATTERN = Pattern.compile("^ACC-[0-9]{10}$");

    public ValidationAdapter(WebClient.Builder webClientBuilder) {
        this.validationWebClient = webClientBuilder
                .baseUrl("http://validation-service:8080")
                .build();
    }

    @Override
    public Mono<Boolean> validateAccountData(Account account) {
        log.debug("Validando datos de cuenta: {}", account.getAccountId());
        
        if (account == null) {
            log.warn("Cuenta nula recibida para validación");
            return Mono.just(false);
        }
        
        if (!validateAccountId(account.getAccountId())) {
            log.warn("AccountId inválido: {}", account.getAccountId());
            return Mono.just(false);
        }
        
        if (!validateClientId(account.getClientId())) {
            log.warn("ClientId inválido: {}", account.getClientId());
            return Mono.just(false);
        }
        
        if (!validateAccountType(account.getAccountType())) {
            log.warn("AccountType inválido: {}", account.getAccountType());
            return Mono.just(false);
        }
        
        if (!validateBalance(account.getBalance())) {
            log.warn("Balance inválido: {}", account.getBalance());
            return Mono.just(false);
        }
        
        if (!validateCurrency(account.getCurrency())) {
            log.warn("Currency inválida: {}", account.getCurrency());
            return Mono.just(false);
        }
        
        if (externalValidationEnabled) {
            return performExternalValidation(account)
                    .subscribeOn(Schedulers.boundedElastic())
                    .timeout(Duration.ofMillis(validationTimeoutMs))
                    .onErrorResume(e -> {
                        log.error("Error en validación externa: {}. Usando validación local.", e.getMessage());
                        return Mono.just(true);
                    });
        }
        
        return Mono.just(true);
    }

    @Override
    public Mono<Boolean> validateClientEligibility(String clientId) {
        log.debug("Validando elegibilidad del cliente: {}", clientId);
        
        if (clientId == null || clientId.isBlank()) {
            return Mono.just(false);
        }
        
        if (!validateClientId(clientId)) {
            return Mono.just(false);
        }
        
        if (externalValidationEnabled) {
            return validationWebClient.get()
                    .uri("/api/v1/clients/{clientId}/eligibility", clientId)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .map(response -> {
                        Object eligible = response.get("eligible");
                        return eligible instanceof Boolean && (Boolean) eligible;
                    })
                    .timeout(Duration.ofMillis(validationTimeoutMs))
                    .onErrorResume(e -> {
                        log.error("Error al validar elegibilidad del cliente {}: {}", clientId, e.getMessage());
                        return Mono.just(true);
                    });
        }
        
        return Mono.just(true);
    }

    @Override
    public Mono<Boolean> validateAccountStatusTransition(String currentStatus, String newStatus) {
        log.debug("Validando transición de estado de {} a {}", currentStatus, newStatus);
        
        if (currentStatus == null || newStatus == null) {
            log.warn("Estado nulo recibido en transición");
            return Mono.just(false);
        }
        
        boolean validTransition = switch (currentStatus) {
            case "ACTIVE" -> Set.of("ACTIVE", "SUSPENDED", "CLOSED").contains(newStatus);
            case "SUSPENDED" -> Set.of("ACTIVE", "CLOSED").contains(newStatus);
            case "CLOSED" -> "CLOSED".equals(newStatus);
            default -> false;
        };
        
        if (!validTransition) {
            log.warn("Transición inválida de {} a {}", currentStatus, newStatus);
            return Mono.just(false);
        }
        
        return Mono.just(true);
    }

    @Override
    public Mono<Boolean> validateOperationIdempotency(String operationId, String operationType) {
        log.debug("Validando idempotencia de operación: {} tipo: {}", operationId, operationType);
        
        if (operationId == null || operationId.isBlank()) {
            return Mono.just(false);
        }
        
        if (operationType == null || operationType.isBlank()) {
            return Mono.just(false);
        }
        
        if (externalValidationEnabled) {
            return validationWebClient.get()
                    .uri("/api/v1/operations/{operationId}/exists", operationId)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .map(response -> {
                        Object exists = response.get("exists");
                        return !(exists instanceof Boolean && (Boolean) exists);
                    })
                    .timeout(Duration.ofMillis(validationTimeoutMs))
                    .onErrorResume(e -> {
                        log.error("Error al validar idempotencia {}: {}", operationId, e.getMessage());
                        return Mono.just(true);
                    });
        }
        
        return Mono.just(true);
    }

    private boolean validateAccountId(String accountId) {
        return accountId != null && ACCOUNT_ID_PATTERN.matcher(accountId).matches();
    }

    private boolean validateClientId(String clientId) {
        return clientId != null && CLIENT_ID_PATTERN.matcher(clientId).matches();
    }

    private boolean validateAccountType(Object accountType) {
        if (accountType == null) {
            return false;
        }
        String typeName = accountType.toString();
        return VALID_ACCOUNT_TYPES.contains(typeName);
    }

    private boolean validateBalance(BigDecimal balance) {
        if (balance == null) {
            return false;
        }
        return balance.compareTo(BigDecimal.ZERO) >= 0 && 
               balance.compareTo(new BigDecimal("999999999.99")) <= 0;
    }

    private boolean validateCurrency(String currency) {
        return currency != null && SUPPORTED_CURRENCIES.contains(currency.toUpperCase());
    }

    private Mono<Boolean> performExternalValidation(Account account) {
        return validationWebClient.post()
                .uri("/api/v1/accounts/validate")
                .bodyValue(Map.of(
                        "accountId", account.getAccountId(),
                        "clientId", account.getClientId(),
                        "accountType", account.getAccountType().name(),
                        "balance", account.getBalance().toPlainString(),
                        "currency", account.getCurrency()
                ))
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    Object valid = response.get("valid");
                    return valid instanceof Boolean && (Boolean) valid;
                });
    }
}