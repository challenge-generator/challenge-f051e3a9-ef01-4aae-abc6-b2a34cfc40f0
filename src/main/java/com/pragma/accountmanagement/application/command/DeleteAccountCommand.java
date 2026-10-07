package com.pragma.accountmanagement.application.command;

import jakarta.validation.constraints.NotBlank;

public final class DeleteAccountCommand {

    @NotBlank(message = "El ID de cuenta es obligatorio para eliminación")
    private final String accountId;

    private final String reason;

    private DeleteAccountCommand(Builder builder) {
        this.accountId = builder.accountId;
        this.reason = builder.reason;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getReason() {
        return reason;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String accountId;
        private String reason;

        private Builder() {
        }

        public Builder accountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder reason(String reason) {
            this.reason = reason;
            return this;
        }

        public DeleteAccountCommand build() {
            return new DeleteAccountCommand(this);
        }
    }
}