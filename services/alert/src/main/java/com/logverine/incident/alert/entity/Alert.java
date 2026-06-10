package com.logverine.incident.alert.entity;

import java.time.LocalDateTime;

public class Alert {

    private Long id;
    private String source;
    private String severity;
    private String message;
    private AlertStatus status;
    private LocalDateTime createdAt;

    public Alert(Long id,
                 String source,
                 String severity,
                 String message,
                 AlertStatus status,
                 LocalDateTime createdAt) {
        this.id = id;
        this.source = source;
        this.severity = severity;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }

    public AlertStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}