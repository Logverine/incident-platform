package com.logverine.incident.alert.kafka;

import com.logverine.incident.common.kafka.KafkaTopics;
import com.logverine.incident.common.kafka.event.AlertCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AlertEventPublisher {

    private static final Logger logger =
            LoggerFactory.getLogger(AlertEventPublisher.class);

    private final KafkaTemplate<String, AlertCreatedEvent> kafkaTemplate;

    public AlertEventPublisher(KafkaTemplate<String, AlertCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(AlertCreatedEvent event) {

        kafkaTemplate.send(
                KafkaTopics.ALERT_CREATED,
                event.alertId().toString(),
                event
        );

        logger.info("Published AlertCreatedEvent with alertId={}", event.alertId());
    }
}