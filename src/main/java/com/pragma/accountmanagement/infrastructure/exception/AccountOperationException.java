package com.pragma.accountmanagement.infrastructure.exception;

import java.util.Map;

public class AccountOperationException extends RuntimeException {

    private final ErrorType errorType;
    private final String accountId;
    private final Map<String, Object> metadata;

    public enum ErrorType {
        ACCOUNT_NOT_FOUND("La cuenta especificada no existe en el sistema"),
        DUPLICATE_ACCOUNT("Ya existe una cuenta con los mismos identificadores"),
        INVALID_ACCOUNT_STATE("La cuenta se encuentra en un estado inválido para la operación"),
        INSUFFICIENT_BALANCE("La cuenta no tiene saldo suficiente para la operación"),
        PERSISTENCE_ERROR("Error al persistir los datos de la cuenta"),
        VALIDATION_ERROR("Error de validación en los datos de la cuenta"),
        CONCURRENT_MODIFICATION("La cuenta fue modificada concurrentemente por otra operación"),
        OPERATION_CANCELLED("La operación fue cancelada debido a una falla"),
        UNKNOWN_ERROR("Error desconocido durante la operación de cuenta");

        private final String defaultMessage;

        ErrorType(String defaultMessage) {
            this.defaultMessage = defaultMessage;
        }

        public String getDefaultMessage() {
            return defaultMessage;
        }
    }

    public AccountOperationException(ErrorType errorType, String accountId) {
        super(buildMessage(errorType, accountId, null));
        this.errorType = errorType;
        this.accountId = accountId;
        this.metadata = Map.of();
    }

    public AccountOperationException(ErrorType errorType, String accountId, String customMessage) {
        super(customMessage != null ? customMessage : buildMessage(errorType, accountId, null));
        this.errorType = errorType;
        this.accountId = accountId;
        this.metadata = Map.of();
    }

    public AccountOperationException(ErrorType errorType, String accountId, Throwable cause) {
        super(buildMessage(errorType, accountId, null), cause);
        this.errorType = errorType;
        this.accountId = accountId;
        this.metadata = Map.of();
    }

    public AccountOperationException(ErrorType errorType, String accountId, Map<String, Object> metadata) {
        super(buildMessage(errorType, accountId, metadata));
        this.errorType = errorType;
        this.accountId = accountId;
        this.metadata = metadata != null ? metadata : Map.of();
    }

    public AccountOperationException(ErrorType errorType, String accountId, String customMessage, Throwable cause) {
        super(customMessage != null ? customMessage : buildMessage(errorType, accountId, null), cause);
        this.errorType = errorType;
        this.accountId = accountId;
        this.metadata = Map.of();
    }

    private static String buildMessage(ErrorType errorType, String accountId, Map<String, Object> metadata) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(errorType.name()).append("] ");
        sb.append(errorType.getDefaultMessage());
        if (accountId != null && !accountId.isBlank()) {
            sb.append(" | AccountId: ").append(accountId);
        }
        if (metadata != null && !metadata.isEmpty()) {
            sb.append(" | Metadata: ").append(metadata);
        }
        return sb.toString();
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public String getAccountId() {
        return accountId;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public boolean isRetryable() {
        return errorType == ErrorType.PERSISTENCE_ERROR ||
               errorType == ErrorType.OPERATION_CANCELLED ||
               errorType == ErrorType.CONCURRENT_MODIFICATION;
    }

    public boolean isFatal() {
        return errorType == ErrorType.ACCOUNT_NOT_FOUND ||
               errorType == ErrorType.DUPLICATE_ACCOUNT ||
               errorType == ErrorType.INVALID_ACCOUNT_STATE;
    }
}