package com.logverine.incident.incident.service;

import com.logverine.incident.incident.dto.request.IncidentRequest;
import com.logverine.incident.incident.dto.request.UpdateIncidentRequest;
import com.logverine.incident.incident.dto.response.IncidentResponse;
import com.logverine.incident.incident.entity.Incident;
import com.logverine.incident.incident.enums.IncidentStatus;
import com.logverine.incident.incident.exception.IncidentNotFoundException;
import com.logverine.incident.incident.mapper.IncidentMapper;
import com.logverine.incident.incident.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final IncidentMapper incidentMapper;

    public IncidentResponse createIncident(IncidentRequest request) {

        Incident incident = Incident.builder()
                .title(request.title())
                .description(request.description())
                .priority(request.priority())
                .source(request.source())
                .status(IncidentStatus.OPEN)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
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

    public List<IncidentResponse> getAllIncidents() {

        return incidentRepository.findAll()
                .stream()
                .map(incidentMapper::toResponse)
                .toList();
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
        incident.setUpdatedAt(Instant.now());

        Incident updated = incidentRepository.save(incident);

        return incidentMapper.toResponse(updated);
    }

    public void deleteIncident(UUID id) {

        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() ->
                        new IncidentNotFoundException("Incident not found with id: " + id));

        incidentRepository.delete(incident);
    }
}