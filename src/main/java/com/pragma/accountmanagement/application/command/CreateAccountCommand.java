package com.pragma.accountmanagement.application.command;

import com.pragma.accountmanagement.domain.model.AccountType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class CreateAccountCommand {

    @NotBlank(message = "El ID del cliente no puede estar vacío")
    @Size(max = 50, message = "El ID del cliente no puede exceder 50 caracteres")
    private String clientId;

    @NotNull(message = "El tipo de cuenta no puede ser nulo")
    private AccountType accountType;

    @NotNull(message = "El saldo inicial no puede ser nulo")
    @DecimalMin(value = "0.0", message = "El saldo inicial no puede ser negativo")
    private BigDecimal initialBalance;

    @NotBlank(message = "La moneda no puede estar vacía")
    @Size(min = 3, max = 3, message = "La moneda debe tener exactamente 3 caracteres (ej. USD, EUR)")
    private String currency;

    @NotBlank(message = "El identificador de operación no puede estar vacío")
    private String operationId;

    public CreateAccountCommand() {
    }

    public CreateAccountCommand(String clientId, AccountType accountType, BigDecimal initialBalance,
                                String currency, String operationId) {
        this.clientId = clientId;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.currency = currency;
        this.operationId = operationId;
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

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    @Override
    public String toString() {
        return "CreateAccountCommand{" +
                "clientId='" + clientId + '\'' +
                ", accountType=" + accountType +
                ", initialBalance=" + initialBalance +
                ", currency='" + currency + '\'' +
                ", operationId='" + operationId + '\'' +
                '}';
    }
}