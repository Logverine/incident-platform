package com.logverine.incident.incident.repository;

import com.logverine.incident.incident.entity.Incident;
import com.logverine.incident.incident.enums.IncidentStatus;
import com.logverine.incident.common.kafka.enums.Priority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, UUID> {
    Page<Incident> findByStatus(
            IncidentStatus status,
            Pageable pageable);

    Page<Incident> findByPriority(
            Priority priority,
            Pageable pageable);

    Page<Incident> findByStatusAndPriority(
            IncidentStatus status,
            Priority priority,
            Pageable pageable);

    Page<Incident> findByTitleContainingIgnoreCase(
            String title,
            Pageable pageable);

    boolean existsByAlertId(Long alertId);
}