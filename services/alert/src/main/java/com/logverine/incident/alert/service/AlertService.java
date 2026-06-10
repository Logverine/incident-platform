package com.logverine.incident.alert.service;

import com.logverine.incident.alert.dto.AlertResponse;
import com.logverine.incident.alert.dto.CreateAlertRequest;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class AlertService {

    private final AtomicLong idGenerator = new AtomicLong();

    public AlertResponse createAlert(CreateAlertRequest request) {

        Long alertId = idGenerator.incrementAndGet();

        return new AlertResponse(
                alertId,
                request.source(),
                request.severity(),
                request.message(),
                "CREATED"
        );
    }
}