package com.logverine.incident.incident.controller;

import com.logverine.incident.incident.dto.request.IncidentRequest;
import com.logverine.incident.incident.dto.request.UpdateIncidentRequest;
import com.logverine.incident.incident.dto.response.IncidentResponse;
import com.logverine.incident.incident.service.IncidentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IncidentResponse createIncident(
            @Valid @RequestBody IncidentRequest request) {

        return incidentService.createIncident(request);
    }

    @GetMapping("/{id}")
    public IncidentResponse getIncidentById(
            @PathVariable("id") UUID id) {

        return incidentService.getIncidentById(id);
    }

    @GetMapping
    public List<IncidentResponse> getAllIncidents() {
        return incidentService.getAllIncidents();
    }

    @PutMapping("/{id}")
    public IncidentResponse updateIncident(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateIncidentRequest request) {
        return incidentService.updateIncident(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIncident(@PathVariable("id") UUID id) {

        incidentService.deleteIncident(id);
    }
}