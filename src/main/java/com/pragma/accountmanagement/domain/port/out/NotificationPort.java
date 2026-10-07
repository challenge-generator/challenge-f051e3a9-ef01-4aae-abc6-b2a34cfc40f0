package com.pragma.accountmanagement.domain.port.out;

import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.OperationId;
import reactor.core.publisher.Mono;

/**
 * Puerto de salida para el sistema de notificaciones.
 * Define la interfaz que el dominio usa para notificar eventos
 * relacionados con cuentas sin conocer la implementación concreta.
 */
public interface NotificationPort {

    /**
     * Notifica la creación exitosa de una cuenta.
     * @param account La cuenta creada
     * @param operationId Identificador de la operación para idempotencia
     * @return Mono vacío que indica completación exitosa
     */
    Mono<Void> notifyAccountCreated(Account account, OperationId operationId);

    /**
     * Notifica la modificación exitosa de una cuenta.
     * @param account La cuenta modificada
     * @param operationId Identificador de la operación para idempotencia
     * @return Mono vacío que indica completación exitosa
     */
    Mono<Void> notifyAccountUpdated(Account account, OperationId operationId);

    /**
     * Notifica la eliminación de una cuenta.
     * @param accountId Identificador de la cuenta eliminada
     * @param operationId Identificador de la operación para idempotencia
     * @return Mono vacío que indica completación exitosa
     */
    Mono<Void> notifyAccountDeleted(String accountId, OperationId operationId);

    /**
     * Notifica un fallo en la operación de cuenta.
     * @param operationId Identificador de la operación que falló
     * @param reason Razón del fallo
     * @return Mono vacío que indica completación exitosa
     */
    Mono<Void> notifyOperationFailed(OperationId operationId, String reason);
}