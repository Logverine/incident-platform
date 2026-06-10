package com.logverine.incident.alert.dto;

public record AlertResponse(
        Long id,
        String source,
        String severity,
        String message,
        String status,
        String createdAt
) {
}