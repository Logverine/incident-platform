package com.logverine.incident.alert.repository;

import com.logverine.incident.alert.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertJpaRepository extends JpaRepository<Alert, Long> {
}