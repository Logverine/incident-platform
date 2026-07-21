package com.logverine.incident.alert.dto;

import com.logverine.incident.common.kafka.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAlertRequest(

        @NotBlank(message = "Source is required")
        String source,

        @NotNull(message = "Severity is required")
        Priority priority,

        @NotBlank(message = "Message is required")
        String message

) {
}