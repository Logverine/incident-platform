package com.logverine.incident.alert.service;

import com.logverine.incident.alert.dto.AlertResponse;
import com.logverine.incident.alert.dto.CreateAlertRequest;
import com.logverine.incident.alert.entity.Alert;
import com.logverine.incident.alert.entity.AlertStatus;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class AlertService {

    private final AtomicLong idGenerator = new AtomicLong();

    public AlertResponse createAlert(CreateAlertRequest request) {

        Alert alert = new Alert(
                idGenerator.incrementAndGet(),
                request.source(),
                request.severity(),
                request.message(),
                AlertStatus.CREATED,
                LocalDateTime.now()
        );

        return new AlertResponse(
                alert.getId(),
                alert.getSource(),
                alert.getSeverity(),
                alert.getMessage(),
                alert.getStatus().name(),
                alert.getCreatedAt().toString()
        );
    }
}