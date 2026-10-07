package com.pragma.accountmanagement.infrastructure.rest;




import com.pragma.accountmanagement.domain.port.in.DeleteAccountCommand;
import com.pragma.accountmanagement.domain.port.in.ModifyAccountCommand;
import com.pragma.accountmanagement.domain.port.in.CreateAccountCommand;
import com.pragma.accountmanagement.domain.model.OperationId;
import com.pragma.accountmanagement.domain.port.in.CreateAccountPort;
import com.pragma.accountmanagement.domain.port.in.DeleteAccountPort;
import com.pragma.accountmanagement.domain.port.in.ModifyAccountPort;
import com.pragma.accountmanagement.infrastructure.rest.dto.AccountResponse;
import com.pragma.accountmanagement.infrastructure.rest.dto.CreateAccountRequest;
import com.pragma.accountmanagement.infrastructure.rest.dto.ModifyAccountRequest;
import com.pragma.accountmanagement.infrastructure.persistence.mapper.AccountMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Controlador REST para la gestión de cuentas.
 * Expone los endpoints para crear, modificar, consultar y eliminar cuentas.
 * Utiliza WebFlux para manejo reactivo de solicitudes.
 */
@RestController
@RequestMapping("/api/v1/accounts")
@Validated
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    private final CreateAccountPort createAccountPort;
    private final ModifyAccountPort modifyAccountPort;
    private final DeleteAccountPort deleteAccountPort;
    private final AccountMapper accountMapper;

    public AccountController(
            CreateAccountPort createAccountPort,
            ModifyAccountPort modifyAccountPort,
            DeleteAccountPort deleteAccountPort,
            AccountMapper accountMapper) {
        this.createAccountPort = createAccountPort;
        this.modifyAccountPort = modifyAccountPort;
        this.deleteAccountPort = deleteAccountPort;
        this.accountMapper = accountMapper;
    }

    /**
     * Crea una nueva cuenta en el sistema.
     *
     * @param request Datos de la cuenta a crear
     * @return Respuesta con la cuenta creada
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<AccountResponse>> createAccount(
            @Valid @RequestBody CreateAccountRequest request) {
        log.info("Recibida solicitud de creación de cuenta para cliente: {}", request.getClientId());
        
        OperationId operationId = OperationId.generate();
        CreateAccountPort.CreateAccountCommand command = accountMapper.toCommand(request);
        
        return createAccountPort.createAccount(command, operationId)
                .map(account -> {
                    AccountResponse response = accountMapper.toResponse(account);
                    log.info("Cuenta creada exitosamente con ID: {} para operación: {}", 
                            account.getAccountId(), operationId.getValue());
                    return ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body(response);
                });
    }

    /**
     * Consulta una cuenta por su identificador.
     *
     * @param accountId Identificador de la cuenta
     * @return Respuesta con los datos de la cuenta
     */
    @GetMapping(value = "/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AccountResponse>> getAccount(
            @PathVariable @NotBlank String accountId) {
        log.info("Consultando cuenta con ID: {}", accountId);
        
        return modifyAccountPort.findByAccountId(accountId)
                .map(account -> {
                    AccountResponse response = accountMapper.toResponse(account);
                    log.info("Cuenta encontrada: {}", accountId);
                    return ResponseEntity.ok(response);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Lista todas las cuentas de un cliente específico.
     *
     * @param clientId Identificador del cliente
     * @return Lista de cuentas del cliente
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<AccountResponse> getAccountsByClient(
            @RequestParam @NotBlank String clientId) {
        log.info("Listando cuentas para el cliente: {}", clientId);
        
        return modifyAccountPort.findByClientId(clientId)
                .map(accountMapper::toResponse)
                .doOnComplete(() -> log.info("Listado de cuentas completado para cliente: {}", clientId));
    }

    /**
     * Lista todas las cuentas activas del sistema.
     *
     * @return Lista de cuentas activas
     */
    @GetMapping(value = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<AccountResponse> getActiveAccounts() {
        log.info("Listando cuentas activas");
        
        return modifyAccountPort.findActiveAccounts()
                .map(accountMapper::toResponse)
                .doOnComplete(() -> log.info("Listado de cuentas activas completado"));
    }

    /**
     * Modifica los datos de una cuenta existente.
     *
     * @param accountId Identificador de la cuenta
     * @param request   Datos a modificar
     * @return Respuesta con la cuenta modificada
     */
    @PutMapping(value = "/{accountId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AccountResponse>> modifyAccount(
            @PathVariable @NotBlank String accountId,
            @Valid @RequestBody ModifyAccountRequest request) {
        log.info("Solicitud de modificación de cuenta: {}", accountId);
        
        OperationId operationId = OperationId.generate();
        ModifyAccountPort.ModifyAccountCommand command = accountMapper.toCommand(accountId, request);
        
        return modifyAccountPort.modifyAccount(accountId, command, operationId)
                .map(account -> {
                    AccountResponse response = accountMapper.toResponse(account);
                    log.info("Cuenta modificada exitosamente: {} para operación: {}", 
                            accountId, operationId.getValue());
                    return ResponseEntity.ok(response);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Actualiza parcialmente una cuenta (PATCH).
     *
     * @param accountId Identificador de la cuenta
     * @param request   Datos parciales a actualizar
     * @return Respuesta con la cuenta actualizada
     */
    @PatchMapping(value = "/{accountId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AccountResponse>> patchAccount(
            @PathVariable @NotBlank String accountId,
            @Valid @RequestBody ModifyAccountRequest request) {
        log.info("Solicitud de actualización parcial de cuenta: {}", accountId);
        
        OperationId operationId = OperationId.generate();
        ModifyAccountPort.ModifyAccountCommand command = accountMapper.toCommand(accountId, request);
        
        return modifyAccountPort.patchAccount(accountId, command, operationId)
                .map(account -> {
                    AccountResponse response = accountMapper.toResponse(account);
                    log.info("Cuenta actualizada parcialmente: {} para operación: {}", 
                            accountId, operationId.getValue());
                    return ResponseEntity.ok(response);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Elimina una cuenta del sistema.
     *
     * @param accountId Identificador de la cuenta a eliminar
     * @return Respuesta sin contenido si la eliminación fue exitosa
     */
    @DeleteMapping("/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteAccount(
            @PathVariable @NotBlank String accountId) {
        log.info("Solicitud de eliminación de cuenta: {}", accountId);
        
        OperationId operationId = OperationId.generate();
        DeleteAccountPort.DeleteAccountCommand command = new DeleteAccountPort.DeleteAccountCommand(accountId);
        
        return deleteAccountPort.deleteAccount(command, operationId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .onErrorResume(e -> {
                    log.error("Error al eliminar cuenta: {}", accountId, e);
                    return Mono.just(ResponseEntity.notFound().build());
                });
    }

    /**
     * Obtiene el saldo de una cuenta específica.
     *
     * @param accountId Identificador de la cuenta
     * @return Saldo de la cuenta
     */
    @GetMapping(value = "/{accountId}/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<BalanceResponse>> getAccountBalance(
            @PathVariable @NotBlank String accountId) {
        log.info("Consultando saldo de cuenta: {}", accountId);
        
        return modifyAccountPort.findByAccountId(accountId)
                .map(account -> {
                    BalanceResponse response = new BalanceResponse(
                            account.getAccountId(),
                            account.getBalance(),
                            account.getCurrency(),
                            account.getLastUpdated()
                    );
                    log.info("Saldo consultado para cuenta: {} = {}", accountId, account.getBalance());
                    return ResponseEntity.ok(response);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * DTO para respuestas de saldo.
     */
    public static class BalanceResponse {
        private final String accountId;
        private final BigDecimal balance;
        private final String currency;
        private final LocalDateTime lastUpdated;

        public BalanceResponse(String accountId, BigDecimal balance, String currency, LocalDateTime lastUpdated) {
            this.accountId = accountId;
            this.balance = balance;
            this.currency = currency;
            this.lastUpdated = lastUpdated;
        }

        public String getAccountId() {
            return accountId;
        }

        public BigDecimal getBalance() {
            return balance;
        }

        public String getCurrency() {
            return currency;
        }

        public LocalDateTime getLastUpdated() {
            return lastUpdated;
        }
    }
}