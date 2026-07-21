package com.logverine.incident.alert.service;

import com.logverine.incident.alert.dto.AlertResponse;
import com.logverine.incident.alert.dto.CreateAlertRequest;
import com.logverine.incident.alert.entity.Alert;
import com.logverine.incident.alert.kafka.AlertEventPublisher;

import java.time.LocalDateTime;

import com.logverine.incident.alert.entity.AlertStatus;
import com.logverine.incident.alert.exception.AlertNotFoundException;
import com.logverine.incident.alert.repository.AlertJpaRepository;
import com.logverine.incident.common.kafka.event.AlertCreatedEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    private final AlertJpaRepository alertRepository;
    private final AlertEventPublisher alertEventPublisher;

    public AlertService(AlertJpaRepository alertRepository, AlertEventPublisher alertEventPublisher) {
        this.alertRepository = alertRepository;
        this.alertEventPublisher = alertEventPublisher;
    }

    public AlertResponse createAlert(CreateAlertRequest request) {

        Alert alert = Alert.builder()
                .source(request.source())
                .priority(request.priority())
                .message(request.message())
                .status(AlertStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();
        Alert savedAlert = alertRepository.save(alert);

        alertEventPublisher.publish(
                new AlertCreatedEvent(
                        savedAlert.getId(),
                        savedAlert.getSource(),
                        savedAlert.getPriority(),
                        savedAlert.getMessage(),
                        savedAlert.getCreatedAt()
                )
        );

        return new AlertResponse(
                alert.getId(),
                alert.getSource(),
                alert.getPriority(),
                alert.getMessage(),
                alert.getStatus().toString(),
                alert.getCreatedAt().toString()
        );
    }

    public List<AlertResponse> getAllAlerts() {

        return alertRepository.findAll()
                .stream()
                .map(alert -> new AlertResponse(
                        alert.getId(),
                        alert.getSource(),
                        alert.getPriority(),
                        alert.getMessage(),
                        alert.getStatus().toString(),
                        alert.getCreatedAt().toString()
                ))
                .toList();
    }

    public AlertResponse getAlertById(Long id) {

        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new AlertNotFoundException(
                        "Alert not found with id: " + id
                ));

        return new AlertResponse(
                alert.getId(),
                alert.getSource(),
                alert.getPriority(),
                alert.getMessage(),
                alert.getStatus().toString(),
                alert.getCreatedAt().toString()
        );
    }
}