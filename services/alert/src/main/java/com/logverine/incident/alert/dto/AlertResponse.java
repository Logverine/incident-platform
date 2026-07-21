package com.logverine.incident.alert.dto;

public record AlertResponse(
        Long id,
        String source,
        com.logverine.incident.common.kafka.enums.Priority severity,
        String message,
        String status,
        String createdAt
) {
}