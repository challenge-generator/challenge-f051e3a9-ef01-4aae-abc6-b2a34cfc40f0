package com.pragma.accountmanagement.infrastructure.adapter;

import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.Account.AccountStatus;
import com.pragma.accountmanagement.domain.model.Account.AccountType;
import com.pragma.accountmanagement.domain.model.OperationId;
import com.pragma.accountmanagement.domain.port.in.CreateAccountPort.CreateAccountCommand;
import com.pragma.accountmanagement.domain.port.in.ModifyAccountPort.ModifyAccountCommand;
import com.pragma.accountmanagement.infrastructure.persistence.entity.AccountEntity;
import com.pragma.accountmanagement.infrastructure.persistence.mapper.AccountMapper;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas de integración para AccountPersistenceAdapter")
class AccountPersistenceAdapterTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountPersistenceAdapter accountPersistenceAdapter;

    private AccountEntity testEntity;
    private Account testAccount;
    private OperationId operationId;

    @BeforeEach
    void setUp() {
        operationId = OperationId.generate();
        LocalDateTime now = LocalDateTime.now();

        testEntity = AccountEntity.builder()
                .accountId("ACC-001")
                .clientId("CLI-001")
                .accountType(AccountType.SAVINGS.name())
                .balance(new BigDecimal("1000.00"))
                .currency("USD")
                .createdAt(now)
                .lastUpdated(now)
                .status(AccountStatus.ACTIVE.name())
                .operationId(operationId.getValue())
                .build();

        testAccount = Account.builder()
                .accountId("ACC-001")
                .clientId("CLI-001")
                .accountType(AccountType.SAVINGS)
                .balance(new BigDecimal("1000.00"))
                .currency("USD")
                .createdAt(now)
                .lastUpdated(now)
                .status(AccountStatus.ACTIVE)
                .operationId(operationId.getValue())
                .build();
    }

    @Test
    @DisplayName("Debe guardar una cuenta exitosamente en la base de datos")
    void save_Success() {
        when(accountMapper.toEntity(any(Account.class))).thenReturn(testEntity);
        when(accountRepository.save(any(AccountEntity.class))).thenReturn(Mono.just(testEntity));
        when(accountMapper.toDomain(any(AccountEntity.class))).thenReturn(testAccount);

        CreateAccountCommand command = new CreateAccountCommand(
                "CLI-001",
                AccountType.SAVINGS,
                new BigDecimal("1000.00"),
                "USD"
        );

        StepVerifier.create(accountPersistenceAdapter.save(command, operationId))
                .assertNext(account -> {
                    assertThat(account.getAccountId()).isEqualTo("ACC-001");
                    assertThat(account.getClientId()).isEqualTo("CLI-001");
                    assertThat(account.getBalance()).isEqualByComparingTo(new BigDecimal("1000.00"));
                })
                .verifyComplete();

        verify(accountMapper).toEntity(any(Account.class));
        verify(accountRepository).save(any(AccountEntity.class));
        verify(accountMapper).toDomain(any(AccountEntity.class));
    }

    @Test
    @DisplayName("Debe actualizar una cuenta existente")
    void update_Success() {
        when(accountRepository.findByAccountId(anyString())).thenReturn(Mono.just(testEntity));
        when(accountRepository.save(any(AccountEntity.class))).thenReturn(Mono.just(testEntity));
        when(accountMapper.toDomain(any(AccountEntity.class))).thenReturn(testAccount);

        ModifyAccountCommand command = new ModifyAccountCommand(
                "ACC-001",
                new BigDecimal("2000.00"),
                AccountStatus.ACTIVE
        );

        StepVerifier.create(accountPersistenceAdapter.update(command, operationId))
                .assertNext(account -> {
                    assertThat(account.getAccountId()).isEqualTo("ACC-001");
                    assertThat(account.getBalance()).isEqualByComparingTo(new BigDecimal("1000.00"));
                })
                .verifyComplete();

        verify(accountRepository).findByAccountId("ACC-001");
        verify(accountRepository).save(any(AccountEntity.class));
    }

    @Test
    @DisplayName("Debe eliminar una cuenta por su ID")
    void deleteByAccountId_Success() {
        when(accountRepository.deleteByAccountId(anyString())).thenReturn(Mono.empty());

        StepVerifier.create(accountPersistenceAdapter.deleteByAccountId("ACC-001"))
                .verifyComplete();

        verify(accountRepository).deleteByAccountId("ACC-001");
    }

    @Test
    @DisplayName("Debe encontrar una cuenta por su ID")
    void findByAccountId_Success() {
        when(accountRepository.findByAccountId(anyString())).thenReturn(Mono.just(testEntity));
        when(accountMapper.toDomain(any(AccountEntity.class))).thenReturn(testAccount);

        StepVerifier.create(accountPersistenceAdapter.findByAccountId("ACC-001"))
                .assertNext(account -> {
                    assertThat(account).isNotNull();
                    assertThat(account.getAccountId()).isEqualTo("ACC-001");
                })
                .verifyComplete();

        verify(accountRepository).findByAccountId("ACC-001");
    }

    @Test
    @DisplayName("Debe retornar vacio cuando la cuenta no existe")
    void findByAccountId_ReturnsEmpty_WhenNotFound() {
        when(accountRepository.findByAccountId(anyString())).thenReturn(Mono.empty());

        StepVerifier.create(accountPersistenceAdapter.findByAccountId("NON-EXISTENT"))
                .verifyComplete();

        verify(accountRepository).findByAccountId("NON-EXISTENT");
    }

    @Test
    @DisplayName("Debe verificar si existe una cuenta por su ID")
    void existsByAccountId_ReturnsTrue_WhenExists() {
        when(accountRepository.existsByAccountId(anyString())).thenReturn(Mono.just(true));

        StepVerifier.create(accountPersistenceAdapter.existsByAccountId("ACC-001"))
                .expectNext(true)
                .verifyComplete();

        verify(accountRepository).existsByAccountId("ACC-001");
    }

    @Test
    @DisplayName("Debe manejar errores de base de datos al guardar")
    void save_HandlesDatabaseError() {
        when(accountMapper.toEntity(any(Account.class))).thenReturn(testEntity);
        when(accountRepository.save(any(AccountEntity.class)))
                .thenReturn(Mono.error(new RuntimeException("Database connection failed")));

        CreateAccountCommand command = new CreateAccountCommand(
                "CLI-001",
                AccountType.SAVINGS,
                new BigDecimal("1000.00"),
                "USD"
        );

        StepVerifier.create(accountPersistenceAdapter.save(command, operationId))
                .expectError(RuntimeException.class)
                .verify();
    }
}