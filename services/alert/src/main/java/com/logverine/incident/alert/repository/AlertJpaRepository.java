package com.logverine.incident.alert.repository;

import com.logverine.incident.alert.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertJpaRepository extends JpaRepository<Alert, Long> {
}