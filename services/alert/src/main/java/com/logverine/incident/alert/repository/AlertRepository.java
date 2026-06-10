package com.logverine.incident.alert.repository;

import com.logverine.incident.alert.entity.Alert;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AlertRepository {

    private final Map<Long, Alert> alerts = new ConcurrentHashMap<>();

    public Alert save(Alert alert) {
        alerts.put(alert.getId(), alert);
        return alert;
    }

    public Optional<Alert> findById(Long id) {
        return Optional.ofNullable(alerts.get(id));
    }

    public List<Alert> findAll() {
        return new ArrayList<>(alerts.values());
    }
}