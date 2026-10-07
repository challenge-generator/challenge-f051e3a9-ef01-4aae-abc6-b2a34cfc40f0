package com.pragma.accountmanagement.application.service;

import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.Account.AccountStatus;
import com.pragma.accountmanagement.domain.model.Account.AccountType;
import com.pragma.accountmanagement.domain.model.OperationId;
import com.pragma.accountmanagement.domain.port.in.CreateAccountPort;
import com.pragma.accountmanagement.domain.port.in.CreateAccountPort.CreateAccountCommand;
import com.pragma.accountmanagement.domain.port.in.DeleteAccountPort;
import com.pragma.accountmanagement.domain.port.in.DeleteAccountPort.DeleteAccountCommand;
import com.pragma.accountmanagement.domain.port.in.ModifyAccountPort;
import com.pragma.accountmanagement.domain.port.in.ModifyAccountPort.ModifyAccountCommand;
import com.pragma.accountmanagement.domain.port.out.NotificationPort;
import com.pragma.accountmanagement.domain.port.out.ValidationPort;
import com.pragma.accountmanagement.infrastructure.exception.AccountOperationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para AccountService")
class AccountServiceTest {

    @Mock
    private CreateAccountPort createAccountPort;

    @Mock
    private ModifyAccountPort modifyAccountPort;

    @Mock
    private DeleteAccountPort deleteAccountPort;

    @Mock
    private NotificationPort notificationPort;

    @Mock
    private ValidationPort validationPort;

    @InjectMocks
    private AccountService accountService;

    private Account testAccount;
    private CreateAccountCommand createCommand;
    private OperationId operationId;

    @BeforeEach
    void setUp() {
        operationId = OperationId.generate();
        testAccount = Account.builder()
                .accountId("ACC-001")
                .clientId("CLI-001")
                .accountType(AccountType.SAVINGS)
                .balance(new BigDecimal("1000.00"))
                .currency("USD")
                .createdAt(LocalDateTime.now())
                .lastUpdated(LocalDateTime.now())
                .status(AccountStatus.ACTIVE)
                .operationId(operationId.getValue())
                .build();

        createCommand = new CreateAccountCommand(
                "CLI-001",
                AccountType.SAVINGS,
                new BigDecimal("1000.00"),
                "USD"
        );
    }

    @Test
    @DisplayName("Debe crear una cuenta exitosamente cuando la validación pasa")
    void createAccount_Success_WhenValidationPasses() {
        when(validationPort.validateClient(anyString())).thenReturn(Mono.just(true));
        when(createAccountPort.createAccount(any(CreateAccountCommand.class), any(OperationId.class)))
                .thenReturn(Mono.just(testAccount));
        when(notificationPort.sendNotification(any(Account.class)))
                .thenReturn(Mono.empty());

        StepVerifier.create(accountService.createAccount(createCommand))
                .expectNext(testAccount)
                .verifyComplete();

        verify(validationPort).validateClient("CLI-001");
        verify(createAccountPort).createAccount(createCommand, any(OperationId.class));
        verify(notificationPort).sendNotification(testAccount);
    }

    @Test
    @DisplayName("Debe fallar al crear cuenta cuando la validación del cliente falla")
    void createAccount_Fails_WhenClientValidationFails() {
        when(validationPort.validateClient(anyString())).thenReturn(Mono.just(false));

        StepVerifier.create(accountService.createAccount(createCommand))
                .expectError(AccountOperationException.class)
                .verify();

        verify(validationPort).validateClient("CLI-001");
        verify(createAccountPort, never()).createAccount(any(), any());
        verify(notificationPort, never()).sendNotification(any());
    }

    @Test
    @DisplayName("Debe modificar una cuenta exitosamente")
    void modifyAccount_Success() {
        ModifyAccountCommand modifyCommand = new ModifyAccountCommand(
                "ACC-001",
                new BigDecimal("2000.00"),
                AccountStatus.ACTIVE
        );

        when(validationPort.validateAccount(anyString())).thenReturn(Mono.just(true));
        when(modifyAccountPort.modifyAccount(any(ModifyAccountCommand.class), any(OperationId.class)))
                .thenReturn(Mono.just(testAccount));
        when(notificationPort.sendNotification(any(Account.class)))
                .thenReturn(Mono.empty());

        StepVerifier.create(accountService.modifyAccount(modifyCommand))
                .expectNext(testAccount)
                .verifyComplete();

        verify(validationPort).validateAccount("ACC-001");
        verify(modifyAccountPort).modifyAccount(modifyCommand, any(OperationId.class));
    }

    @Test
    @DisplayName("Debe eliminar una cuenta exitosamente")
    void deleteAccount_Success() {
        DeleteAccountCommand deleteCommand = new DeleteAccountCommand("ACC-001");

        when(validationPort.validateAccount(anyString())).thenReturn(Mono.just(true));
        when(deleteAccountPort.deleteAccount(any(DeleteAccountCommand.class), any(OperationId.class)))
                .thenReturn(Mono.empty());
        when(notificationPort.sendNotification(any(Account.class)))
                .thenReturn(Mono.empty());

        StepVerifier.create(accountService.deleteAccount(deleteCommand))
                .verifyComplete();

        verify(validationPort).validateAccount("ACC-001");
        verify(deleteAccountPort).deleteAccount(deleteCommand, any(OperationId.class));
    }

    @Test
    @DisplayName("Debe propagar errores del puerto de creación")
    void createAccount_PropagatesErrorFromPort() {
        when(validationPort.validateClient(anyString())).thenReturn(Mono.just(true));
        when(createAccountPort.createAccount(any(), any(OperationId.class)))
                .thenReturn(Mono.error(new AccountOperationException("Error de base de datos")));

        StepVerifier.create(accountService.createAccount(createCommand))
                .expectError(AccountOperationException.class)
                .verify();
    }
}