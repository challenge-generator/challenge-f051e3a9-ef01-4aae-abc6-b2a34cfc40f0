package com.pragma.accountmanagement.domain.port.out;

import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.Account.AccountType;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

/**
 * Puerto de salida para validación de datos externos.
 * Permite al dominio validar reglas de negocio que dependen
 * de sistemas externos sin acoplar la implementación.
 */
public interface ValidationPort {

    /**
     * Valida que el cliente existe y está activo en el sistema externo.
     * @param clientId Identificador del cliente a validar
     * @return Mono que emite verdadero si el cliente es válido
     */
    Mono<Boolean> validateClientExists(String clientId);

    /**
     * Valida que el tipo de cuenta es permitido para el cliente.
     * @param clientId Identificador del cliente
     * @param accountType Tipo de cuenta solicitado
     * @return Mono que emite verdadero si el tipo es permitido
     */
    Mono<Boolean> validateAccountTypeAllowed(String clientId, AccountType accountType);

    /**
     * Valida que el saldo inicial cumple con los requisitos del tipo de cuenta.
     * @param accountType Tipo de cuenta
     * @param initialBalance Saldo inicial propuesto
     * @return Mono que emite verdadero si el saldo es válido
     */
    Mono<Boolean> validateInitialBalance(AccountType accountType, java.math.BigDecimal initialBalance);

    /**
     * Valida que la moneda es soportada por el sistema.
     * @param currency Código de moneda ISO 4217
     * @return Mono que emite verdadero si la moneda es válida
     */
    Mono<Boolean> validateCurrencySupported(String currency);

    /**
     * Valida el límite de cuentas por cliente.
     * Un cliente no puede tener más de N cuentas activas.
     * @param clientId Identificador del cliente
     * @return Mono que emite verdadero si el cliente puede abrir más cuentas
     */
    Mono<Boolean> validateAccountLimitNotExceeded(String clientId);

    /**
     * Obtiene las cuentas activas de un cliente para validación cruzada.
     * @param clientId Identificador del cliente
     * @return Flux de cuentas activas del cliente
     */
    Flux<Account> getActiveAccountsByClient(String clientId);

    /**
     * Valida la integridad de los datos de la cuenta antes de persistir.
     * @param account Cuenta a validar
     * @return Mono que completa si la validación es exitosa
     * @throws AccountOperationException si la validación falla
     */
    Mono<Void> validateAccountIntegrity(Account account);
}