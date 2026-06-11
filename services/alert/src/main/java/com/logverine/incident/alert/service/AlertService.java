package com.logverine.incident.alert.service;

import com.logverine.incident.alert.dto.AlertResponse;
import com.logverine.incident.alert.dto.CreateAlertRequest;
import com.logverine.incident.alert.entity.Alert;

import java.time.LocalDateTime;

import com.logverine.incident.alert.repository.AlertJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    private final AlertJpaRepository alertRepository;

    public AlertService(AlertJpaRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public AlertResponse createAlert(CreateAlertRequest request) {

        Alert alert = Alert.builder()
                .source(request.source())
                .severity(request.severity())
                .message(request.message())
                .status("CREATED")
                .createdAt(LocalDateTime.now())
                .build();
        alertRepository.save(alert);

        return new AlertResponse(
                alert.getId(),
                alert.getSource(),
                alert.getSeverity(),
                alert.getMessage(),
                alert.getStatus(),
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
                        alert.getStatus(),
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
                alert.getStatus(),
                alert.getCreatedAt().toString()
        );
    }
}