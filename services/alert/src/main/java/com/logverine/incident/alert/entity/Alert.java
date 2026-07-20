package com.logverine.incident.alert.entity;

import com.logverine.incident.common.kafka.enums.Priority;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;

    private Priority priority;

    private String message;

    @Enumerated(EnumType.STRING)
    private AlertStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}