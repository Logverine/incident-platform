package com.logverine.incident.incident.service;

import com.logverine.incident.common.kafka.event.AlertCreatedEvent;
import com.logverine.incident.incident.dto.request.IncidentRequest;
import com.logverine.incident.incident.dto.request.UpdateIncidentRequest;
import com.logverine.incident.incident.dto.response.IncidentResponse;
import com.logverine.incident.incident.entity.Incident;
import com.logverine.incident.incident.enums.IncidentStatus;
import com.logverine.incident.incident.enums.Priority;
import com.logverine.incident.incident.exception.IncidentNotFoundException;
import com.logverine.incident.incident.mapper.IncidentMapper;
import com.logverine.incident.incident.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.logverine.incident.incident.enums.Priority.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final IncidentMapper incidentMapper;


    @Transactional
    public void createFromAlert(AlertCreatedEvent event) {

        if (incidentRepository.existsByAlertId(event.alertId())) {
            log.warn("Incident already exists for alertId={}", event.alertId());
            return;
        }

        Incident incident = Incident.builder()
                .alertId(event.alertId())
                .title(event.message())
                .description(event.message())
                .source(event.source())
                .priority(Priority.valueOf(event.priority().name()))
                .status(IncidentStatus.OPEN)
                .build();

        incidentRepository.save(incident);

        log.info("Created incident for alertId={}", event.alertId());
    }

    public IncidentResponse createIncident(IncidentRequest request) {

        Incident incident = Incident.builder()
                .title(request.title())
                .description(request.description())
                .priority(request.priority())
                .source(request.source())
                .status(IncidentStatus.OPEN)
                .build();

        Incident saved = incidentRepository.save(incident);

        return incidentMapper.toResponse(saved);
    }

    public IncidentResponse getIncidentById(UUID id) {

        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() ->
                        new IncidentNotFoundException("Incident not found with id: " + id));

        return incidentMapper.toResponse(incident);
    }

    public Page<IncidentResponse> getAllIncidents(Pageable pageable) {

        return incidentRepository.findAll(pageable)
                .map(incidentMapper::toResponse);
    }

    public IncidentResponse updateIncident(UUID id, UpdateIncidentRequest request) {

        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() ->
                        new IncidentNotFoundException("Incident not found with id: " + id));

        incident.setTitle(request.title());
        incident.setDescription(request.description());
        incident.setPriority(request.priority());
        incident.setStatus(request.status());
        incident.setAssignee(request.assignee());

        Incident updated = incidentRepository.save(incident);

        return incidentMapper.toResponse(updated);
    }

    public void deleteIncident(UUID id) {

        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() ->
                        new IncidentNotFoundException("Incident not found with id: " + id));

        incidentRepository.delete(incident);
    }

    public Page<IncidentResponse> getAllIncidents(
            IncidentStatus status,
            Priority priority,
            String title,
            Pageable pageable) {

        Page<Incident> incidents;

        if (title != null && !title.isBlank()) {
            incidents = incidentRepository
                    .findByTitleContainingIgnoreCase(title, pageable);
        } else if (status != null && priority != null) {
            incidents = incidentRepository
                    .findByStatusAndPriority(status, priority, pageable);
        } else if (status != null) {
            incidents = incidentRepository
                    .findByStatus(status, pageable);
        } else if (priority != null) {
            incidents = incidentRepository
                    .findByPriority(priority, pageable);
        } else {
            incidents = incidentRepository
                    .findAll(pageable);
        }
        return incidents.map(incidentMapper::toResponse);
    }

}