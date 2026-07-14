package com.logverine.incident.incident.dto.response;

import com.logverine.incident.incident.enums.IncidentStatus;
import com.logverine.incident.incident.enums.Priority;

import java.time.Instant;
import java.util.UUID;

public record IncidentResponse(
        UUID id,
        String title,
        String description,
        Priority priority,
        IncidentStatus status,
        String assignee,
        String source,
        Instant createdAt,
        Instant updatedAt
) {
}