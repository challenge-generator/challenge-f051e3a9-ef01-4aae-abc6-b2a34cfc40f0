package com.pragma.accountmanagement.application.service;

import com.pragma.accountmanagement.application.command.CreateAccountCommand;
import com.pragma.accountmanagement.application.command.DeleteAccountCommand;
import com.pragma.accountmanagement.application.command.ModifyAccountCommand;
import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.OperationId;
import com.pragma.accountmanagement.domain.port.in.CreateAccountPort;
import com.pragma.accountmanagement.domain.port.in.DeleteAccountPort;
import com.pragma.accountmanagement.domain.port.in.ModifyAccountPort;
import com.pragma.accountmanagement.domain.port.out.AccountPersistencePort;
import com.pragma.accountmanagement.domain.port.out.NotificationPort;
import com.pragma.accountmanagement.domain.port.out.ValidationPort;
import com.pragma.accountmanagement.infrastructure.exception.AccountOperationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);
    private static final String SERVICE_NAME = "AccountService";

    private final CreateAccountPort createAccountPort;
    private final ModifyAccountPort modifyAccountPort;
    private final DeleteAccountPort deleteAccountPort;
    private final AccountPersistencePort accountPersistencePort;
    private final NotificationPort notificationPort;
    private final ValidationPort validationPort;

    public AccountService(
            CreateAccountPort createAccountPort,
            ModifyAccountPort modifyAccountPort,
            DeleteAccountPort deleteAccountPort,
            AccountPersistencePort accountPersistencePort,
            NotificationPort notificationPort,
            ValidationPort validationPort) {
        this.createAccountPort = Objects.requireNonNull(createAccountPort, "CreateAccountPort no puede ser null");
        this.modifyAccountPort = Objects.requireNonNull(modifyAccountPort, "ModifyAccountPort no puede ser null");
        this.deleteAccountPort = Objects.requireNonNull(deleteAccountPort, "DeleteAccountPort no puede ser null");
        this.accountPersistencePort = Objects.requireNonNull(accountPersistencePort, "AccountPersistencePort no puede ser null");
        this.notificationPort = Objects.requireNonNull(notificationPort, "NotificationPort no puede ser null");
        this.validationPort = Objects.requireNonNull(validationPort, "ValidationPort no puede ser null");
        log.info("{} inicializado con todos los puertos inyectados", SERVICE_NAME);
    }

    public Mono<Account> createAccount(CreateAccountCommand command) {
        Objects.requireNonNull(command, "El comando de creación no puede ser null");
        log.info("Iniciando creación de cuenta para cliente: {} con operación: {}", 
                command.getClientId(), command.getOperationId());
        
        return validationPort.validateClientExists(command.getClientId())
                .then(validationPort.validateAccountType(command.getAccountType()))
                .then(Mono.defer(() -> {
                    OperationId operationId = OperationId.generate();
                    return createAccountPort.createAccount(command, operationId)
                            .flatMap(account -> {
                                log.info("Cuenta {} creada exitosamente para cliente: {}", 
                                        account.getAccountId(), account.getClientId());
                                return notificationPort.sendAccountCreatedNotification(account)
                                        .thenReturn(account);
                            })
                            .onErrorResume(error -> {
                                log.error("Error al crear cuenta para cliente {}: {}", 
                                        command.getClientId(), error.getMessage());
                                return Mono.error(new AccountOperationException(
                                        "Error al crear cuenta: " + error.getMessage(), error));
                            });
                }));
    }

    public Mono<Account> modifyAccount(ModifyAccountCommand command) {
        Objects.requireNonNull(command, "El comando de modificación no puede ser null");
        log.info("Iniciando modificación de cuenta: {} con operationId generado", command.getAccountId());
        
        return accountPersistencePort.findByAccountId(command.getAccountId())
                .switchIfEmpty(Mono.error(new AccountOperationException(
                        "Cuenta no encontrada: " + command.getAccountId())))
                .flatMap(existingAccount -> {
                    log.debug("Cuenta {} encontrada, procediendo con modificación", 
                            command.getAccountId());
                    return validationPort.validateClientExists(command.getClientId())
                            .then(validationPort.validateAccountType(command.getAccountType()))
                            .then(Mono.defer(() -> {
                                OperationId operationId = OperationId.generate();
                                return modifyAccountPort.modifyAccount(command, operationId)
                                        .flatMap(modifiedAccount -> {
                                            log.info("Cuenta {} modificada exitosamente", 
                                                    modifiedAccount.getAccountId());
                                            return notificationPort.sendAccountModifiedNotification(modifiedAccount)
                                                    .thenReturn(modifiedAccount);
                                        })
                                        .onErrorResume(error -> {
                                            log.error("Error al modificar cuenta {}: {}", 
                                                    command.getAccountId(), error.getMessage());
                                            return Mono.error(new AccountOperationException(
                                                    "Error al modificar cuenta: " + error.getMessage(), error));
                                        });
                            }));
                });
    }

    public Mono<Void> deleteAccount(DeleteAccountCommand command) {
        Objects.requireNonNull(command, "El comando de eliminación no puede ser null");
        log.info("Iniciando eliminación de cuenta: {} por razón: {}", 
                command.getAccountId(), command.getReason());
        
        return accountPersistencePort.findByAccountId(command.getAccountId())
                .switchIfEmpty(Mono.error(new AccountOperationException(
                        "Cuenta no encontrada para eliminación: " + command.getAccountId())))
                .flatMap(existingAccount -> {
                    log.debug("Cuenta {} encontrada, verificando dependencias antes de eliminación", 
                            command.getAccountId());
                    return validationPort.validateAccountCanBeDeleted(command.getAccountId())
                            .then(deleteAccountPort.deleteAccount(command)
                                    .doOnSuccess(unused -> {
                                        log.info("Cuenta {} eliminada exitosamente", command.getAccountId());
                                    })
                                    .flatMap(account -> notificationPort.sendAccountDeletedNotification(
                                            account.getAccountId(), command.getReason()))
                                    .then())
                                    .onErrorResume(error -> {
                                        log.error("Error al eliminar cuenta {}: {}", 
                                                command.getAccountId(), error.getMessage());
                                        return Mono.error(new AccountOperationException(
                                                "Error al eliminar cuenta: " + error.getMessage(), error));
                                    });
                });
    }

    public Mono<Account> findAccountById(String accountId) {
        Objects.requireNonNull(accountId, "El ID de cuenta no puede ser null");
        log.debug("Buscando cuenta por ID: {}", accountId);
        
        return accountPersistencePort.findByAccountId(accountId)
                .doOnSuccess(account -> {
                    if (account != null) {
                        log.debug("Cuenta {} encontrada", accountId);
                    } else {
                        log.warn("Cuenta {} no encontrada", accountId);
                    }
                });
    }

    public Mono<Account> findAccountByClientId(String clientId) {
        Objects.requireNonNull(clientId, "El ID de cliente no puede ser null");
        log.debug("Buscando cuentas para cliente: {}", clientId);
        
        return accountPersistencePort.findByClientId(clientId)
                .single()
                .doOnSuccess(account -> log.debug("Cuenta encontrada para cliente: {}", clientId))
                .onErrorResume(error -> Mono.error(new AccountOperationException(
                        "Error al buscar cuenta por cliente: " + error.getMessage(), error)));
    }
}