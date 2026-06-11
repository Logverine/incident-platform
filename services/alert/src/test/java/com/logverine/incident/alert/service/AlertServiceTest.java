package com.logverine.incident.alert.service;

import com.logverine.incident.alert.dto.AlertResponse;
import com.logverine.incident.alert.dto.CreateAlertRequest;
import com.logverine.incident.alert.entity.Alert;
import com.logverine.incident.alert.entity.AlertStatus;
import com.logverine.incident.alert.exception.AlertNotFoundException;
import com.logverine.incident.alert.repository.AlertJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlertServiceTest {

    @Mock
    private AlertJpaRepository alertRepository;

    @InjectMocks
    private AlertService alertService;

    @Test
    void shouldCreateAlert() {

        CreateAlertRequest request =
                new CreateAlertRequest(
                        "payment-service",
                        "CRITICAL",
                        "Database timeout"
                );

        when(alertRepository.save(any(Alert.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        AlertResponse response = alertService.createAlert(request);

        assertNotNull(response);
    }

    @Test
    void shouldGetAlertById() {

        Alert alert = new Alert(
                1L,
                "payment-service",
                "CRITICAL",
                "Database timeout",
                AlertStatus.CREATED,
                LocalDateTime.now()
        );

        when(alertRepository.findById(1L))
                .thenReturn(Optional.of(alert));

        AlertResponse response =
                alertService.getAlertById(1L);

        assertNotNull(response);
        assertEquals(1L, response.id());
    }

    @Test
    void shouldThrowAlertNotFoundException() {

        when(alertRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                AlertNotFoundException.class,
                () -> alertService.getAlertById(999L)
        );
    }

    @Test
    void shouldGetAllAlerts() {

        List<Alert> alerts = List.of(
                new Alert(
                        1L,
                        "payment-service",
                        "CRITICAL",
                        "Database timeout",
                        AlertStatus.CREATED,
                        LocalDateTime.now()
                )
        );

        when(alertRepository.findAll())
                .thenReturn(alerts);

        List<AlertResponse> responses =
                alertService.getAllAlerts();

        assertEquals(1, responses.size());
    }
}