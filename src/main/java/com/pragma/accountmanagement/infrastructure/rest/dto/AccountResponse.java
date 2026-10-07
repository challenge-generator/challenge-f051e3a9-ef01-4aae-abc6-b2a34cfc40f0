package com.pragma.accountmanagement.infrastructure.rest.dto;


import com.pragma.accountmanagement.domain.model.AccountStatus;
import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.AccountType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountResponse {

    private String accountId;
    private String clientId;
    private AccountType accountType;
    private BigDecimal balance;
    private String currency;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;
    private Account.AccountStatus status;
    private String operationId;

    public AccountResponse() {
    }

    public AccountResponse(String accountId, String clientId, AccountType accountType, BigDecimal balance,
                           String currency, LocalDateTime createdAt, LocalDateTime lastUpdated,
                           Account.AccountStatus status, String operationId) {
        this.accountId = accountId;
        this.clientId = clientId;
        this.accountType = accountType;
        this.balance = balance;
        this.currency = currency;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
        this.status = status;
        this.operationId = operationId;
    }

    public static AccountResponse fromDomain(Account account) {
        return new AccountResponse(
            account.getAccountId(),
            account.getClientId(),
            account.getAccountType(),
            account.getBalance(),
            account.getCurrency(),
            account.getCreatedAt(),
            account.getLastUpdated(),
            account.getStatus(),
            account.getOperationId()
        );
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Account.AccountStatus getStatus() {
        return status;
    }

    public void setStatus(Account.AccountStatus status) {
        this.status = status;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    @Override
    public String toString() {
        return "AccountResponse{" +
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
}