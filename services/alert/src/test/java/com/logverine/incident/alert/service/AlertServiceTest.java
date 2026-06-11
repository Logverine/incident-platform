package com.logverine.incident.alert.service;

import com.logverine.incident.alert.dto.AlertResponse;
import com.logverine.incident.alert.dto.CreateAlertRequest;
import com.logverine.incident.alert.entity.Alert;
import com.logverine.incident.alert.repository.AlertJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
}