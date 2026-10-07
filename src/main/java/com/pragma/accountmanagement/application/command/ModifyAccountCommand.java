package com.pragma.accountmanagement.application.command;

import com.pragma.accountmanagement.domain.model.AccountType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public final class ModifyAccountCommand {

    @NotBlank(message = "El ID de cuenta es obligatorio")
    private final String accountId;

    @NotNull(message = "El tipo de cuenta es obligatorio")
    private final AccountType accountType;

    @NotNull(message = "El saldo es obligatorio")
    @DecimalMin(value = "0.0", message = "El saldo no puede ser negativo")
    private final BigDecimal balance;

    @NotBlank(message = "La moneda es obligatoria")
    @Size(min = 3, max = 3, message = "La moneda debe tener 3 caracteres")
    private final String currency;

    @NotBlank(message = "El ID de cliente es obligatorio")
    private final String clientId;

    private ModifyAccountCommand(Builder builder) {
        this.accountId = builder.accountId;
        this.accountType = builder.accountType;
        this.balance = builder.balance;
        this.currency = builder.currency;
        this.clientId = builder.clientId;
    }

    public String getAccountId() {
        return accountId;
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

    public String getClientId() {
        return clientId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String accountId;
        private AccountType accountType;
        private BigDecimal balance;
        private String currency;
        private String clientId;

        private Builder() {
        }

        public Builder accountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder accountType(AccountType accountType) {
            this.accountType = accountType;
            return this;
        }

        public Builder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public ModifyAccountCommand build() {
            return new ModifyAccountCommand(this);
        }
    }
}