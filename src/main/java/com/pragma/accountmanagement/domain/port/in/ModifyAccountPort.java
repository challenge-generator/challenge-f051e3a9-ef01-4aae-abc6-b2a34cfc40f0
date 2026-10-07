package com.pragma.accountmanagement.domain.port.in;



import com.pragma.accountmanagement.domain.model.AccountStatus;
import com.pragma.accountmanagement.domain.model.AccountType;
import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.OperationId;
import reactor.core.publisher.Mono;

public interface ModifyAccountPort {
    
    Mono<Account> modifyAccount(ModifyAccountCommand command, OperationId operationId);
    
    final class ModifyAccountCommand {
        private final String accountId;
        private final String clientId;
        private final Account.AccountType accountType;
        private final java.math.BigDecimal balance;
        private final String currency;
        private final Account.AccountStatus status;
        
        private ModifyAccountCommand(Builder builder) {
            this.accountId = builder.accountId;
            this.clientId = builder.clientId;
            this.accountType = builder.accountType;
            this.balance = builder.balance;
            this.currency = builder.currency;
            this.status = builder.status;
        }
        
        public String getAccountId() {
            return accountId;
        }
        
        public String getClientId() {
            return clientId;
        }
        
        public Account.AccountType getAccountType() {
            return accountType;
        }
        
        public java.math.BigDecimal getBalance() {
            return balance;
        }
        
        public String getCurrency() {
            return currency;
        }
        
        public Account.AccountStatus getStatus() {
            return status;
        }
        
        public static Builder builder() {
            return new Builder();
        }
        
        public static final class Builder {
            private String accountId;
            private String clientId;
            private Account.AccountType accountType;
            private java.math.BigDecimal balance;
            private String currency;
            private Account.AccountStatus status;
            
            private Builder() {}
            
            public Builder accountId(String accountId) {
                this.accountId = accountId;
                return this;
            }
            
            public Builder clientId(String clientId) {
                this.clientId = clientId;
                return this;
            }
            
            public Builder accountType(Account.AccountType accountType) {
                this.accountType = accountType;
                return this;
            }
            
            public Builder balance(java.math.BigDecimal balance) {
                this.balance = balance;
                return this;
            }
            
            public Builder currency(String currency) {
                this.currency = currency;
                return this;
            }
            
            public Builder status(Account.AccountStatus status) {
                this.status = status;
                return this;
            }
            
            public ModifyAccountCommand build() {
                return new ModifyAccountCommand(this);
            }
        }
    }
}