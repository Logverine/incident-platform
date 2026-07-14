package com.logverine.incident.incident.mapper;

import com.logverine.incident.incident.dto.response.IncidentResponse;
import com.logverine.incident.incident.entity.Incident;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IncidentMapper {

    IncidentResponse toResponse(Incident incident);

}