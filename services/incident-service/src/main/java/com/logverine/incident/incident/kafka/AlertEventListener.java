package com.logverine.incident.incident.kafka;

import com.logverine.incident.common.kafka.KafkaTopics;
import com.logverine.incident.common.kafka.event.AlertCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AlertEventListener {

    @KafkaListener(
            topics = KafkaTopics.ALERT_CREATED,
            groupId = "incident-service"
    )
    public void handleAlertCreated(AlertCreatedEvent event) {

        log.info(
                "Received AlertCreatedEvent. alertId={}, source={}, severity={}",
                event.alertId(),
                event.source(),
                event.severity()
        );
    }
}