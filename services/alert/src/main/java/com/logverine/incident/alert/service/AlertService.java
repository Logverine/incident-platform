package com.logverine.incident.alert.service;

import com.logverine.incident.alert.dto.AlertResponse;
import com.logverine.incident.alert.dto.CreateAlertRequest;
import com.logverine.incident.alert.entity.Alert;
import com.logverine.incident.alert.entity.AlertStatus;

import java.time.LocalDateTime;

import com.logverine.incident.alert.repository.AlertRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AlertService {

    private final AlertRepository alertRepository;
    private final AtomicLong idGenerator = new AtomicLong();

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public AlertResponse createAlert(CreateAlertRequest request) {

        Alert alert = new Alert(
                idGenerator.incrementAndGet(),
                request.source(),
                request.severity(),
                request.message(),
                AlertStatus.CREATED,
                LocalDateTime.now()
        );
        alertRepository.save(alert);

        return new AlertResponse(
                alert.getId(),
                alert.getSource(),
                alert.getSeverity(),
                alert.getMessage(),
                alert.getStatus().name(),
                alert.getCreatedAt().toString()
        );
    }

    public List<AlertResponse> getAllAlerts() {

        return alertRepository.findAll()
                .stream()
                .map(alert -> new AlertResponse(
                        alert.getId(),
                        alert.getSource(),
                        alert.getSeverity(),
                        alert.getMessage(),
                        alert.getStatus().name(),
                        alert.getCreatedAt().toString()
                ))
                .toList();
    }

    public AlertResponse getAlertById(Long id) {

        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Alert not found with id: " + id
                ));

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