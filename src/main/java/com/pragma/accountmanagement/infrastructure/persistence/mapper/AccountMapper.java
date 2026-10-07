package com.pragma.accountmanagement.infrastructure.persistence.mapper;



import com.pragma.accountmanagement.domain.model.AccountStatus;
import com.pragma.accountmanagement.domain.model.AccountType;
import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.infrastructure.persistence.entity.AccountEntity;

import java.time.LocalDateTime;

public class AccountMapper {

    private AccountMapper() {
    }

    public static Account toDomain(AccountEntity entity) {
        if (entity == null) {
            return null;
        }

        Account.AccountType accountTypeEnum;
        try {
            accountTypeEnum = Account.AccountType.valueOf(entity.getAccountType());
        } catch (IllegalArgumentException e) {
            accountTypeEnum = Account.AccountType.UNKNOWN;
        }

        Account.AccountStatus statusEnum;
        try {
            statusEnum = Account.AccountStatus.valueOf(entity.getStatus());
        } catch (IllegalArgumentException e) {
            statusEnum = Account.AccountStatus.UNKNOWN;
        }

        return Account.builder()
                .accountId(entity.getAccountId())
                .clientId(entity.getClientId())
                .accountType(accountTypeEnum)
                .balance(entity.getBalance())
                .currency(entity.getCurrency())
                .createdAt(entity.getCreatedAt())
                .lastUpdated(entity.getLastUpdated())
                .status(statusEnum)
                .operationId(entity.getOperationId())
                .build();
    }

    public static AccountEntity toEntity(Account account) {
        if (account == null) {
            return null;
        }

        AccountEntity entity = new AccountEntity();
        entity.setAccountId(account.getAccountId());
        entity.setClientId(account.getClientId());
        entity.setAccountType(account.getAccountType() != null ? account.getAccountType().name() : null);
        entity.setBalance(account.getBalance());
        entity.setCurrency(account.getCurrency());
        entity.setCreatedAt(account.getCreatedAt());
        entity.setLastUpdated(account.getLastUpdated());
        entity.setStatus(account.getStatus() != null ? account.getStatus().name() : null);
        entity.setOperationId(account.getOperationId());

        return entity;
    }

    public static AccountEntity toEntityWithId(Account account, Long databaseId) {
        AccountEntity entity = toEntity(account);
        entity.setId(databaseId);
        return entity;
    }

    public static void updateEntityFromDomain(AccountEntity entity, Account account) {
        if (entity == null || account == null) {
            return;
        }

        if (account.getClientId() != null) {
            entity.setClientId(account.getClientId());
        }
        if (account.getAccountType() != null) {
            entity.setAccountType(account.getAccountType().name());
        }
        if (account.getBalance() != null) {
            entity.setBalance(account.getBalance());
        }
        if (account.getCurrency() != null) {
            entity.setCurrency(account.getCurrency());
        }
        if (account.getStatus() != null) {
            entity.setStatus(account.getStatus().name());
        }
        if (account.getOperationId() != null) {
            entity.setOperationId(account.getOperationId());
        }

        entity.setLastUpdated(LocalDateTime.now());
    }

    public static boolean isValidAccountType(String accountType) {
        if (accountType == null || accountType.isBlank()) {
            return false;
        }
        try {
            Account.AccountType.valueOf(accountType);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isValidStatus(String status) {
        if (status == null || status.isBlank()) {
            return false;
        }
        try {
            Account.AccountStatus.valueOf(status);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}