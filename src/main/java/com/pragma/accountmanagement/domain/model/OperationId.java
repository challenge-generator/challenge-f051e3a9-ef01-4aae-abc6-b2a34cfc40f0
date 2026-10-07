package com.pragma.accountmanagement.domain.model;

import java.util.Objects;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;

public final class OperationId {
    private final String value;

    private OperationId(String value) {
        this.value = value;
    }

    public static OperationId generate() {
        return new OperationId(UUID.randomUUID().toString());
    }

    public static OperationId from(@NotNull String value) {
        Objects.requireNonNull(value, "OperationId value cannot be null");
        if (!value.matches("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")) {
            throw new IllegalArgumentException("Invalid OperationId format");
        }
        return new OperationId(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationId that = (OperationId) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}