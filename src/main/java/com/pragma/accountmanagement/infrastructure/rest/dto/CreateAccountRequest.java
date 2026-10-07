package com.pragma.accountmanagement.infrastructure.rest.dto;

import com.pragma.accountmanagement.domain.model.Account.AccountStatus;
import com.pragma.accountmanagement.domain.model.Account.AccountType;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * DTO de solicitud para la creación de cuentas.
 * Utiliza Jakarta Validation para garantizar la integridad de los datos
 * recibidos en las peticiones de creación.
 */
public class CreateAccountRequest {

    @NotBlank(message = "El identificador del cliente es obligatorio")
    @Size(min = 1, max = 50, message = "El identificador del cliente debe tener entre 1 y 50 caracteres")
    private String clientId;

    @NotNull(message = "El tipo de cuenta es obligatorio")
    private AccountType accountType;

    @NotNull(message = "El saldo inicial es obligatorio")
    @DecimalMin(value = "0.0", message = "El saldo inicial no puede ser negativo")
    @Digits(integer = 15, fraction = 2, message = "El saldo inicial debe tener como máximo 15 enteros y 2 decimales")
    private BigDecimal initialBalance;

    @NotBlank(message = "La moneda es obligatoria")
    @Size(min = 3, max = 3, message = "El código de moneda debe tener exactamente 3 caracteres (ISO 4217)")
    @Pattern(regexp = "^[A-Z]{3}$", message = "El código de moneda debe ser un código ISO 4217 válido en mayúsculas")
    private String currency;

    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String description;

    @Size(max = 100, message = "El identificador de operación no puede exceder 100 caracteres")
    private String operationId;

    public CreateAccountRequest() {
    }

    public CreateAccountRequest(String clientId, AccountType accountType, BigDecimal initialBalance, 
                                 String currency, String description, String operationId) {
        this.clientId = clientId;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.currency = currency;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    @Override
    public String toString() {
        return "CreateAccountRequest{" +
                "clientId='" + clientId + '\'' +
                ", accountType=" + accountType +
                ", initialBalance=" + initialBalance +
                ", currency='" + currency + '\'' +
                ", description='" + (description != null ? description.substring(0, Math.min(description.length(), 50)) + "..." : null) + '\'' +
                ", operationId='" + operationId + '\'' +
                '}';
    }
}