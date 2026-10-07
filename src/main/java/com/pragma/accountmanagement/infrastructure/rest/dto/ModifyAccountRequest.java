package com.pragma.accountmanagement.infrastructure.rest.dto;


import com.pragma.accountmanagement.domain.model.AccountStatus;
import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.AccountType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class ModifyAccountRequest {

    @NotNull(message = "El tipo de cuenta no puede ser nulo")
    private AccountType accountType;

    @NotBlank(message = "La moneda no puede estar vacía")
    @Size(min = 3, max = 3, message = "La moneda debe tener exactamente 3 caracteres")
    private String currency;

    @NotNull(message = "El estado de la cuenta no puede ser nulo")
    private Account.AccountStatus status;

    @NotNull(message = "El saldo no puede ser nulo")
    @DecimalMin(value = "0.0", message = "El saldo no puede ser negativo")
    private BigDecimal balance;

    public ModifyAccountRequest() {
    }

    public ModifyAccountRequest(AccountType accountType, String currency, Account.AccountStatus status, BigDecimal balance) {
        this.accountType = accountType;
        this.currency = currency;
        this.status = status;
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Account.AccountStatus getStatus() {
        return status;
    }

    public void setStatus(Account.AccountStatus status) {
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "ModifyAccountRequest{" +
                "accountType=" + accountType +
                ", currency='" + currency + '\'' +
                ", status=" + status +
                ", balance=" + balance +
                '}';
    }
}