package com.pragma.accountmanagement.infrastructure.adapter;

import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.port.out.NotificationPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationAdapter implements NotificationPort {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.kafka.topics.account-events:account-events}")
    private String accountEventsTopic;

    @Value("${app.kafka.topics.notifications:account-notifications}")
    private String notificationsTopic;

    private static final String EVENT_TYPE_ACCOUNT_CREATED = "ACCOUNT_CREATED";
    private static final String EVENT_TYPE_ACCOUNT_UPDATED = "ACCOUNT_UPDATED";
    private static final String EVENT_TYPE_ACCOUNT_DELETED = "ACCOUNT_DELETED";
    private static final String EVENT_TYPE_ACCOUNT_BALANCE_UPDATED = "ACCOUNT_BALANCE_UPDATED";

    @Override
    public Mono<Boolean> notifyAccountCreated(Account account) {
        log.info("Enviando notificación de cuenta creada: {}", account.getAccountId());
        
        Map<String, Object> event = buildBaseEvent(EVENT_TYPE_ACCOUNT_CREATED);
        event.put("accountId", account.getAccountId());
        event.put("clientId", account.getClientId());
        event.put("accountType", account.getAccountType().name());
        event.put("balance", account.getBalance().toPlainString());
        event.put("currency", account.getCurrency());
        event.put("status", account.getStatus().name());
        
        return publishEvent(accountEventsTopic, account.getAccountId(), event)
                .subscribeOn(Schedulers.boundedElastic())
                .then(publishEvent(notificationsTopic, account.getClientId(), event))
                .subscribeOn(Schedulers.boundedElastic())
                .thenReturn(true)
                .onErrorResume(e -> {
                    log.error("Error al notificar cuenta creada: {}", e.getMessage());
                    return Mono.just(false);
                });
    }

    @Override
    public Mono<Boolean> notifyAccountUpdated(Account account) {
        log.info("Enviando notificación de cuenta actualizada: {}", account.getAccountId());
        
        Map<String, Object> event = buildBaseEvent(EVENT_TYPE_ACCOUNT_UPDATED);
        event.put("accountId", account.getAccountId());
        event.put("clientId", account.getClientId());
        event.put("accountType", account.getAccountType().name());
        event.put("balance", account.getBalance().toPlainString());
        event.put("currency", account.getCurrency());
        event.put("status", account.getStatus().name());
        event.put("operationId", account.getOperationId());
        
        return publishEvent(accountEventsTopic, account.getAccountId(), event)
                .subscribeOn(Schedulers.boundedElastic())
                .then(publishEvent(notificationsTopic, account.getClientId(), event))
                .subscribeOn(Schedulers.boundedElastic())
                .thenReturn(true)
                .onErrorResume(e -> {
                    log.error("Error al notificar cuenta actualizada: {}", e.getMessage());
                    return Mono.just(false);
                });
    }

    @Override
    public Mono<Boolean> notifyAccountDeleted(String accountId, String clientId) {
        log.info("Enviando notificación de cuenta eliminada: {}", accountId);
        
        Map<String, Object> event = buildBaseEvent(EVENT_TYPE_ACCOUNT_DELETED);
        event.put("accountId", accountId);
        event.put("clientId", clientId);
        
        return publishEvent(accountEventsTopic, accountId, event)
                .subscribeOn(Schedulers.boundedElastic())
                .thenReturn(true)
                .onErrorResume(e -> {
                    log.error("Error al notificar cuenta eliminada: {}", e.getMessage());
                    return Mono.just(false);
                });
    }

    @Override
    public Mono<Boolean> notifyBalanceUpdate(String accountId, String clientId, String newBalance, String operationId) {
        log.info("Enviando notificación de actualización de balance para cuenta: {}", accountId);
        
        Map<String, Object> event = buildBaseEvent(EVENT_TYPE_ACCOUNT_BALANCE_UPDATED);
        event.put("accountId", accountId);
        event.put("clientId", clientId);
        event.put("newBalance", newBalance);
        event.put("operationId", operationId);
        
        return publishEvent(accountEventsTopic, accountId, event)
                .subscribeOn(Schedulers.boundedElastic())
                .thenReturn(true)
                .onErrorResume(e -> {
                    log.error("Error al notificar actualización de balance: {}", e.getMessage());
                    return Mono.just(false);
                });
    }

    private Map<String, Object> buildBaseEvent(String eventType) {
        Map<String, Object> event = new HashMap<>();
        event.put("eventType", eventType);
        event.put("timestamp", Instant.now().toString());
        event.put("source", "account-management-service");
        return event;
    }

    private Mono<Void> publishEvent(String topic, String key, Map<String, Object> event) {
        String eventJson = convertToJson(event);
        
        CompletableFuture<SendResult<String, String>> future = 
                kafkaTemplate.send(topic, key, eventJson);
        
        return Mono.fromFuture(future)
                .doOnSuccess(result -> log.debug("Evento publicado en {} con offset: {}", 
                        topic, result.getRecordMetadata().offset()))
                .doOnError(error -> log.error("Error al publicar evento en {}: {}", 
                        topic, error.getMessage()))
                .then();
    }

    private String convertToJson(Map<String, Object> map) {
        StringBuilder json = new StringBuilder("{");
        int count = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (count > 0) {
                json.append(",");
            }
            json.append("\"").append(entry.getKey()).append("\":");
            Object value = entry.getValue();
            if (value instanceof String) {
                json.append("\"").append(escapeJson((String) value)).append("\"");
            } else {
                json.append(value);
            }
            count++;
        }
        json.append("}");
        return json.toString();
    }

    private String escapeJson(String value) {
        return value.replace("\\", "\\\\")
                   .replace("\"", "\\\"")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }
}