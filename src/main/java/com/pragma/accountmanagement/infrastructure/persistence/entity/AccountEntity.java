package com.pragma.accountmanagement.infrastructure.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table("accounts")
public class AccountEntity {

    @Id
    @Column("id")
    private Long id;

    @Column("account_id")
    private String accountId;

    @Column("client_id")
    private String clientId;

    @Column("account_type")
    private String accountType;

    @Column("balance")
    private BigDecimal balance;

    @Column("currency")
    private String currency;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("last_updated")
    private LocalDateTime lastUpdated;

    @Column("status")
    private String status;

    @Column("operation_id")
    private String operationId;

    public AccountEntity() {
    }

    public AccountEntity(Long id, String accountId, String clientId, String accountType,
                        BigDecimal balance, String currency, LocalDateTime createdAt,
                        LocalDateTime lastUpdated, String status, String operationId) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
}