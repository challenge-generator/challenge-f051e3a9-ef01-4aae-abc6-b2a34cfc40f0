package com.pragma.accountmanagement.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public final class Account {
    private final String accountId;
    private final String clientId;
    private final AccountType accountType;
    private final BigDecimal balance;
    private final String currency;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastUpdated;
    private final AccountStatus status;
    private final String operationId;

    public enum AccountType {
        SAVINGS, CHECKING, INVESTMENT
    }

    public enum AccountStatus {
        ACTIVE, BLOCKED, CLOSED
    }

    private Account(Builder builder) {
        this.accountId = builder.accountId;
        this.clientId = builder.clientId;
        this.accountType = builder.accountType;
        this.balance = builder.balance;
        this.currency = builder.currency;
        this.createdAt = builder.createdAt;
        this.lastUpdated = builder.lastUpdated;
        this.status = builder.status;
        this.operationId = builder.operationId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getAccountId() {
        return accountId;
    }

    public String getClientId() {
        return clientId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public String getOperationId() {
        return operationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountId, account.accountId) && 
               Objects.equals(operationId, account.operationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, operationId);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", accountType=" + accountType +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", createdAt=" + createdAt +
                ", lastUpdated=" + lastUpdated +
                ", status=" + status +
                ", operationId='" + operationId + '\'' +
                '}';
    }

    public static final class Builder {
        private String accountId;
        private String clientId;
        private AccountType accountType;
        private BigDecimal balance;
        private String currency;
        private LocalDateTime createdAt;
        private LocalDateTime lastUpdated;
        private AccountStatus status;
        private String operationId;

        private Builder() {
        }

        public Builder accountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder clientId(@NotNull String clientId) {
            this.clientId = Objects.requireNonNull(clientId, "clientId cannot be null");
            return this;
        }

        public Builder accountType(@NotNull AccountType accountType) {
            this.accountType = Objects.requireNonNull(accountType, "accountType cannot be null");
            return this;
        }

        public Builder balance(@NotNull @Positive BigDecimal balance) {
            this.balance = Objects.requireNonNull(balance, "balance cannot be null");
            if (balance.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("balance must be positive");
            }
            return this;
        }

        public Builder currency(@NotNull @Size(min = 3, max = 3) String currency) {
            this.currency = Objects.requireNonNull(currency, "currency cannot be null");
            if (currency.length() != 3) {
                throw new IllegalArgumentException("currency must be a 3-letter code");
            }
            return this;
        }

        public Builder createdAt(@NotNull LocalDateTime createdAt) {
            this.createdAt = Objects.requireNonNull(createdAt, "createdAt cannot be null");
            return this;
        }

        public Builder lastUpdated(@NotNull LocalDateTime lastUpdated) {
            this.lastUpdated = Objects.requireNonNull(lastUpdated, "lastUpdated cannot be null");
            return this;
        }

        public Builder status(@NotNull AccountStatus status) {
            this.status = Objects.requireNonNull(status, "status cannot be null");
            return this;
        }

        public Builder operationId(@NotNull String operationId) {
            this.operationId = Objects.requireNonNull(operationId, "operationId cannot be null");
            return this;
        }

        public Account build() {
            Objects.requireNonNull(clientId, "clientId cannot be null");
            Objects.requireNonNull(accountType, "accountType cannot be null");
            Objects.requireNonNull(balance, "balance cannot be null");
            Objects.requireNonNull(currency, "currency cannot be null");
            Objects.requireNonNull(createdAt, "createdAt cannot be null");
            Objects.requireNonNull(lastUpdated, "lastUpdated cannot be null");
            Objects.requireNonNull(status, "status cannot be null");
            Objects.requireNonNull(operationId, "operationId cannot be null");
            
            if (accountId == null) {
                this.accountId = java.util.UUID.randomUUID().toString();
            }
            
            return new Account(this);
        }
    }
}