package com.logverine.incident.common.kafka.event;

import com.logverine.incident.common.kafka.enums.Priority;

import java.time.LocalDateTime;

public record AlertCreatedEvent(
        Long alertId,
        String source,
        Priority priority,
        String message,
        LocalDateTime createdAt
) {
}