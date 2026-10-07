package com.pragma.accountmanagement.infrastructure.adapter;

import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.OperationId;
import com.pragma.accountmanagement.domain.port.out.AccountPersistencePort;
import com.pragma.accountmanagement.infrastructure.persistence.entity.AccountEntity;
import com.pragma.accountmanagement.infrastructure.persistence.mapper.AccountMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountPersistencePort {

    private final DatabaseClient databaseClient;
    private final AccountMapper accountMapper;

    private static final String INSERT_SQL = """
        INSERT INTO accounts (account_id, client_id, account_type, balance, currency, 
                             created_at, last_updated, status, operation_id)
        VALUES (:accountId, :clientId, :accountType, :balance, :currency, 
                :createdAt, :lastUpdated, :status, :operationId)
        """;

    private static final String SELECT_BY_ID_SQL = """
        SELECT account_id, client_id, account_type, balance, currency, 
               created_at, last_updated, status, operation_id
        FROM accounts 
        WHERE account_id = :accountId
        """;

    private static final String SELECT_BY_CLIENT_ID_SQL = """
        SELECT account_id, client_id, account_type, balance, currency, 
               created_at, last_updated, status, operation_id
        FROM accounts 
        WHERE client_id = :clientId
        """;

    private static final String UPDATE_SQL = """
        UPDATE accounts 
        SET client_id = :clientId, account_type = :accountType, balance = :balance, 
            currency = :currency, last_updated = :lastUpdated, status = :status, 
            operation_id = :operationId
        WHERE account_id = :accountId
        """;

    private static final String DELETE_SQL = """
        DELETE FROM accounts WHERE account_id = :accountId
        """;

    private static final String UPDATE_BALANCE_SQL = """
        UPDATE accounts 
        SET balance = :balance, last_updated = :lastUpdated, operation_id = :operationId
        WHERE account_id = :accountId
        """;

    @Override
    @CircuitBreaker(name = "accountPersistenceCircuitBreaker", fallbackMethod = "saveAccountFallback")
    @Retry(name = "accountPersistenceRetry")
    public Mono<Account> saveAccount(Account account) {
        log.info("Persistiendo cuenta con ID: {} para cliente: {}", 
                account.getAccountId(), account.getClientId());
        
        AccountEntity entity = accountMapper.toEntity(account);
        LocalDateTime now = LocalDateTime.now();
        
        return databaseClient.sql(INSERT_SQL)
                .bind("accountId", entity.getAccountId())
                .bind("clientId", entity.getClientId())
                .bind("accountType", entity.getAccountType().name())
                .bind("balance", entity.getBalance())
                .bind("currency", entity.getCurrency())
                .bind("createdAt", now)
                .bind("lastUpdated", now)
                .bind("status", entity.getStatus().name())
                .bind("operationId", entity.getOperationId())
                .fetch()
                .rowsUpdated()
                .flatMap(rows -> {
                    if (rows > 0) {
                        log.info("Cuenta {} persistida exitosamente", account.getAccountId());
                        return Mono.just(account);
                    }
                    log.error("Error al persistir cuenta {}", account.getAccountId());
                    return Mono.error(new RuntimeException("Error al persistir cuenta"));
                })
                .onErrorResume(e -> {
                    log.error("Error en persistencia de cuenta: {}", e.getMessage());
                    return Mono.error(e);
                });
    }

    private Mono<Account> saveAccountFallback(Account account, Throwable t) {
        log.error("Circuit breaker activado para saveAccount. Cuenta: {}, Error: {}", 
                account.getAccountId(), t.getMessage());
        return Mono.error(new RuntimeException("Servicio de persistencia temporalmente no disponible", t));
    }

    @Override
    @CircuitBreaker(name = "accountPersistenceCircuitBreaker", fallbackMethod = "findByIdFallback")
    @Retry(name = "accountPersistenceRetry")
    public Mono<Optional<Account>> findById(String accountId) {
        log.debug("Buscando cuenta por ID: {}", accountId);
        
        return databaseClient.sql(SELECT_BY_ID_SQL)
                .bind("accountId", accountId)
                .map((row, metadata) -> accountMapper.toDomain(
                        row.get("account_id", String.class),
                        row.get("client_id", String.class),
                        row.get("account_type", String.class),
                        row.get("balance", BigDecimal.class),
                        row.get("currency", String.class),
                        row.get("created_at", LocalDateTime.class),
                        row.get("last_updated", LocalDateTime.class),
                        row.get("status", String.class),
                        row.get("operation_id", String.class)
                ))
                .first()
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .onErrorResume(e -> {
                    log.error("Error al buscar cuenta {}: {}", accountId, e.getMessage());
                    return Mono.error(e);
                });
    }

    private Mono<Optional<Account>> findByIdFallback(String accountId, Throwable t) {
        log.error("Circuit breaker activado para findById. Cuenta: {}, Error: {}", 
                accountId, t.getMessage());
        return Mono.error(new RuntimeException("Servicio de consulta temporalmente no disponible", t));
    }

    @Override
    @CircuitBreaker(name = "accountPersistenceCircuitBreaker", fallbackMethod = "findByClientIdFallback")
    public Flux<Account> findByClientId(String clientId) {
        log.debug("Buscando cuentas para cliente: {}", clientId);
        
        return databaseClient.sql(SELECT_BY_CLIENT_ID_SQL)
                .bind("clientId", clientId)
                .map((row, metadata) -> accountMapper.toDomain(
                        row.get("account_id", String.class),
                        row.get("client_id", String.class),
                        row.get("account_type", String.class),
                        row.get("balance", BigDecimal.class),
                        row.get("currency", String.class),
                        row.get("created_at", LocalDateTime.class),
                        row.get("last_updated", LocalDateTime.class),
                        row.get("status", String.class),
                        row.get("operation_id", String.class)
                ))
                .all()
                .onErrorResume(e -> {
                    log.error("Error al buscar cuentas del cliente {}: {}", clientId, e.getMessage());
                    return Flux.error(e);
                });
    }

    private Flux<Account> findByClientIdFallback(String clientId, Throwable t) {
        log.error("Circuit breaker activado para findByClientId. Cliente: {}, Error: {}", 
                clientId, t.getMessage());
        return Flux.error(new RuntimeException("Servicio de consulta temporalmente no disponible", t));
    }

    @Override
    @CircuitBreaker(name = "accountPersistenceCircuitBreaker", fallbackMethod = "updateAccountFallback")
    @Retry(name = "accountPersistenceRetry")
    public Mono<Account> updateAccount(Account account) {
        log.info("Actualizando cuenta con ID: {}", account.getAccountId());
        
        AccountEntity entity = accountMapper.toEntity(account);
        LocalDateTime now = LocalDateTime.now();
        
        return databaseClient.sql(UPDATE_SQL)
                .bind("accountId", entity.getAccountId())
                .bind("clientId", entity.getClientId())
                .bind("accountType", entity.getAccountType().name())
                .bind("balance", entity.getBalance())
                .bind("currency", entity.getCurrency())
                .bind("lastUpdated", now)
                .bind("status", entity.getStatus().name())
                .bind("operationId", entity.getOperationId())
                .fetch()
                .rowsUpdated()
                .flatMap(rows -> {
                    if (rows > 0) {
                        log.info("Cuenta {} actualizada exitosamente", account.getAccountId());
                        return Mono.just(account);
                    }
                    log.warn("No se encontró cuenta {} para actualizar", account.getAccountId());
                    return Mono.empty();
                })
                .onErrorResume(e -> {
                    log.error("Error al actualizar cuenta {}: {}", account.getAccountId(), e.getMessage());
                    return Mono.error(e);
                });
    }

    private Mono<Account> updateAccountFallback(Account account, Throwable t) {
        log.error("Circuit breaker activado para updateAccount. Cuenta: {}, Error: {}", 
                account.getAccountId(), t.getMessage());
        return Mono.error(new RuntimeException("Servicio de actualización temporalmente no disponible", t));
    }

    @Override
    @CircuitBreaker(name = "accountPersistenceCircuitBreaker", fallbackMethod = "deleteAccountFallback")
    @Retry(name = "accountPersistenceRetry")
    public Mono<Boolean> deleteAccount(String accountId) {
        log.info("Eliminando cuenta con ID: {}", accountId);
        
        return databaseClient.sql(DELETE_SQL)
                .bind("accountId", accountId)
                .fetch()
                .rowsUpdated()
                .flatMap(rows -> {
                    if (rows > 0) {
                        log.info("Cuenta {} eliminada exitosamente", accountId);
                        return Mono.just(true);
                    }
                    log.warn("No se encontró cuenta {} para eliminar", accountId);
                    return Mono.just(false);
                })
                .onErrorResume(e -> {
                    log.error("Error al eliminar cuenta {}: {}", accountId, e.getMessage());
                    return Mono.error(e);
                });
    }

    private Mono<Boolean> deleteAccountFallback(String accountId, Throwable t) {
        log.error("Circuit breaker activado para deleteAccount. Cuenta: {}, Error: {}", 
                accountId, t.getMessage());
        return Mono.error(new RuntimeException("Servicio de eliminación temporalmente no disponible", t));
    }

    @Override
    @CircuitBreaker(name = "accountPersistenceCircuitBreaker")
    public Mono<Account> updateBalance(String accountId, BigDecimal newBalance, OperationId operationId) {
        log.info("Actualizando balance de cuenta {} a {}", accountId, newBalance);
        
        LocalDateTime now = LocalDateTime.now();
        
        return databaseClient.sql(UPDATE_BALANCE_SQL)
                .bind("accountId", accountId)
                .bind("balance", newBalance)
                .bind("lastUpdated", now)
                .bind("operationId", operationId.getValue())
                .fetch()
                .rowsUpdated()
                .flatMap(rows -> {
                    if (rows > 0) {
                        log.info("Balance de cuenta {} actualizado exitosamente", accountId);
                        return findById(accountId)
                                .flatMap(opt -> opt.map(Mono::just)
                                        .orElseGet(() -> Mono.error(new RuntimeException("Cuenta no encontrada después de actualizar"))));
                    }
                    log.warn("No se encontró cuenta {} para actualizar balance", accountId);
                    return Mono.empty();
                })
                .onErrorResume(e -> {
                    log.error("Error al actualizar balance de cuenta {}: {}", accountId, e.getMessage());
                    return Mono.error(e);
                });
    }
}