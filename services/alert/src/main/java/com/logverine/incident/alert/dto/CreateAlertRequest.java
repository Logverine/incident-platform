package com.logverine.incident.alert.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateAlertRequest(

        @NotBlank(message = "Source is required")
        String source,

        @NotBlank(message = "Severity is required")
        String severity,

        @NotBlank(message = "Message is required")
        String message

) {
}