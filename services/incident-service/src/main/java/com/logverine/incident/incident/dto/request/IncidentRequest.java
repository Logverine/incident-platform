package com.logverine.incident.incident.dto.request;

import com.logverine.incident.incident.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IncidentRequest(

        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "Priority is required")
        Priority priority,

        @NotBlank(message = "Source is required")
        String source

) {}