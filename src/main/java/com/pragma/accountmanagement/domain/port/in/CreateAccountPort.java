package com.pragma.accountmanagement.domain.port.in;


import com.pragma.accountmanagement.domain.model.AccountType;
import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.OperationId;
import reactor.core.publisher.Mono;

public interface CreateAccountPort {
    /**
     * Crea una nueva cuenta para un cliente.
     * 
     * @param command Comando que contiene los datos necesarios para crear la cuenta.
     * @param operationId Identificador único de la operación para garantizar idempotencia.
     * @return Mono que emite la cuenta creada o un error si la operación falla.
     */
    Mono<Account> createAccount(CreateAccountCommand command, OperationId operationId);
    
    /**
     * Comando que encapsula los datos necesarios para crear una cuenta.
     */
    final class CreateAccountCommand {
        private final String clientId;
        private final Account.AccountType accountType;
        private final String currency;
        private final String initialDeposit;

        public CreateAccountCommand(String clientId, Account.AccountType accountType, String currency, String initialDeposit) {
            this.clientId = clientId;
            this.accountType = accountType;
            this.currency = currency;
            this.initialDeposit = initialDeposit;
        }

        public String getClientId() {
            return clientId;
        }

        public Account.AccountType getAccountType() {
            return accountType;
        }

        public String getCurrency() {
            return currency;
        }

        public String getInitialDeposit() {
            return initialDeposit;
        }
    }
}