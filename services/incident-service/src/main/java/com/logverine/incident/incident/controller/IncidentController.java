package com.logverine.incident.incident.controller;

import com.logverine.incident.incident.dto.request.IncidentRequest;
import com.logverine.incident.incident.dto.request.UpdateIncidentRequest;
import com.logverine.incident.incident.dto.response.IncidentResponse;
import com.logverine.incident.incident.enums.IncidentStatus;
import com.logverine.incident.incident.service.IncidentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.logverine.incident.common.kafka.enums.Priority;

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

    @GetMapping
    public Page<IncidentResponse> getAllIncidents(

            @RequestParam(name = "status", required = false)
            IncidentStatus status,

            @RequestParam(name = "priority", required = false)
            Priority priority,

            @RequestParam(name = "title", required = false)
            String title,

            @ParameterObject Pageable pageable) {

        return incidentService.getAllIncidents(
                status,
                priority,
                title,
                pageable);
    }
}