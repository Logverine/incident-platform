package com.logverine.incident.incident.dto.request;

import com.logverine.incident.incident.enums.IncidentStatus;
import com.logverine.incident.incident.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateIncidentRequest(

        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotNull
        Priority priority,

        String assignee,

        @NotNull
        IncidentStatus status
) {
}