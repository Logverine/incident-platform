package com.logverine.incident.common.kafka.event;

import java.time.LocalDateTime;

public record AlertCreatedEvent(
        Long alertId,
        String source,
        String severity,
        String message,
        LocalDateTime createdAt
) {
}