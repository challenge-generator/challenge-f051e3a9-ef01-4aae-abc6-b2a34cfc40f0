package com.pragma.accountmanagement.domain.port.out;


import com.pragma.accountmanagement.domain.model.AccountType;
import com.pragma.accountmanagement.domain.model.Account;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public interface AccountPersistencePort {
    
    Mono<Account> save(Account account);
    
    Mono<Account> findById(String accountId);
    
    Flux<Account> findByClientId(String clientId);
    
    Mono<Boolean> existsById(String accountId);
    
    Mono<Boolean> existsByClientIdAndAccountType(String clientId, Account.AccountType accountType);
    
    Mono<Void> deleteById(String accountId);
    
    Mono<Account> update(Account account);
    
    Mono<Long> count();
    
    Flux<Account> findAll();
    
    Mono<Account> findByOperationId(String operationId);
}