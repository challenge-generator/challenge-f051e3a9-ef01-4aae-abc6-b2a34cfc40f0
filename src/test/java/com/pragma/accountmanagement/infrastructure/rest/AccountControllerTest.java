package com.pragma.accountmanagement.infrastructure.rest;

import com.pragma.accountmanagement.application.service.AccountService;
import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.Account.AccountStatus;
import com.pragma.accountmanagement.domain.model.Account.AccountType;
import com.pragma.accountmanagement.infrastructure.rest.dto.AccountResponse;
import com.pragma.accountmanagement.infrastructure.rest.dto.CreateAccountRequest;
import com.pragma.accountmanagement.infrastructure.rest.dto.ModifyAccountRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@WebFluxTest(AccountController.class)
@DisplayName("Pruebas de integración para AccountController")
class AccountControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AccountService accountService;

    @Test
    @DisplayName("POST /api/cuentas debe crear una cuenta exitosamente")
    void createAccount_Returns201_WhenSuccessful() {
        CreateAccountRequest request = new CreateAccountRequest();
        request.setClientId("CLI-001");
        request.setAccountType(AccountType.SAVINGS.name());
        request.setBalance(new BigDecimal("1000.00"));
        request.setCurrency("USD");

        Account createdAccount = createTestAccount();
        when(accountService.createAccount(any())).thenReturn(Mono.just(createdAccount));

        webTestClient.post()
                .uri("/api/cuentas")
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(request))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(AccountResponse.class)
                .value(response -> {
                    assert response != null;
                    assert response.getAccountId().equals("ACC-001");
                    assert response.getClientId().equals("CLI-001");
                });
    }

    @Test
    @DisplayName("POST /api/cuentas debe retornar 400 cuando la validacion falla")
    void createAccount_Returns400_WhenValidationFails() {
        CreateAccountRequest request = new CreateAccountRequest();
        request.setClientId("");
        request.setAccountType(AccountType.SAVINGS.name());
        request.setBalance(new BigDecimal("-100.00"));

        webTestClient.post()
                .uri("/api/cuentas")
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(request))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("PUT /api/cuentas/{accountId} debe modificar una cuenta exitosamente")
    void modifyAccount_Returns200_WhenSuccessful() {
        String accountId = "ACC-001";
        ModifyAccountRequest request = new ModifyAccountRequest();
        request.setBalance(new BigDecimal("2000.00"));
        request.setStatus(AccountStatus.ACTIVE.name());

        Account modifiedAccount = createTestAccount();
        modifiedAccount = Account.builder()
                .accountId(accountId)
                .clientId("CLI-001")
                .accountType(AccountType.SAVINGS)
                .balance(new BigDecimal("2000.00"))
                .currency("USD")
                .createdAt(modifiedAccount.getCreatedAt())
                .lastUpdated(LocalDateTime.now())
                .status(AccountStatus.ACTIVE)
                .operationId(modifiedAccount.getOperationId())
                .build();

        when(accountService.modifyAccount(any())).thenReturn(Mono.just(modifiedAccount));

        webTestClient.put()
                .uri("/api/cuentas/{accountId}", accountId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(request))
                .exchange()
                .expectStatus().isOk()
                .expectBody(AccountResponse.class)
                .value(response -> {
                    assert response != null;
                    assert response.getBalance().compareTo(new BigDecimal("2000.00")) == 0;
                });
    }

    @Test
    @DisplayName("DELETE /api/cuentas/{accountId} debe eliminar una cuenta exitosamente")
    void deleteAccount_Returns204_WhenSuccessful() {
        String accountId = "ACC-001";
        when(accountService.deleteAccount(any())).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/api/cuentas/{accountId}", accountId)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    @DisplayName("GET /api/cuentas/{accountId} debe retornar una cuenta existente")
    void getAccount_Returns200_WhenExists() {
        String accountId = "ACC-001";
        Account account = createTestAccount();
        when(accountService.getAccount(accountId)).thenReturn(Mono.just(account));

        webTestClient.get()
                .uri("/api/cuentas/{accountId}", accountId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AccountResponse.class)
                .value(response -> {
                    assert response != null;
                    assert response.getAccountId().equals(accountId);
                });
    }

    @Test
    @DisplayName("GET /api/cuentas/{accountId} debe retornar 404 cuando no existe")
    void getAccount_Returns404_WhenNotExists() {
        String accountId = "NON-EXISTENT";
        when(accountService.getAccount(accountId)).thenReturn(Mono.empty());

        webTestClient.get()
                .uri("/api/cuentas/{accountId}", accountId)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @DisplayName("POST /api/cuentas debe manejar errores internos del servidor")
    void createAccount_Returns500_WhenInternalError() {
        CreateAccountRequest request = new CreateAccountRequest();
        request.setClientId("CLI-001");
        request.setAccountType(AccountType.SAVINGS.name());
        request.setBalance(new BigDecimal("1000.00"));
        request.setCurrency("USD");

        when(accountService.createAccount(any()))
                .thenReturn(Mono.error(new RuntimeException("Internal server error")));

        webTestClient.post()
                .uri("/api/cuentas")
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(request))
                .exchange()
                .expectStatus().is5xxServerError();
    }

    private Account createTestAccount() {
        return Account.builder()
                .accountId("ACC-001")
                .clientId("CLI-001")
                .accountType(AccountType.SAVINGS)
                .balance(new BigDecimal("1000.00"))
                .currency("USD")
                .createdAt(LocalDateTime.now())
                .lastUpdated(LocalDateTime.now())
                .status(AccountStatus.ACTIVE)
                .operationId("OP-001")
                .build();
    }
}