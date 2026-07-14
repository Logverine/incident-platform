package com.logverine.incident.incident.service;

import com.logverine.incident.incident.dto.response.IncidentResponse;
import com.logverine.incident.incident.dto.request.IncidentRequest;
import com.logverine.incident.incident.entity.Incident;
import com.logverine.incident.incident.enums.IncidentStatus;
import com.logverine.incident.incident.exception.IncidentNotFoundException;
import com.logverine.incident.incident.repository.IncidentRepository;
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
class IncidentServiceTest {
}