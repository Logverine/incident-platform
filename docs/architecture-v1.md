# Architecture V1 - Incident Management Platform

## Overview

The Incident Management Platform is a cloud-native, event-driven system designed to receive operational alerts, create incidents, track incident lifecycles, and provide visibility into production issues.

The system follows a Microservices Architecture to ensure scalability, maintainability, and independent deployment of services.

---

## High Level Architecture

```text
+-------------+
|    User     |
+------+------+
       |
       v
+------------------+
|  Alert Service   |
+--------+---------+
         |
         | Publish Event
         v
+------------------+
|      Kafka       |
+--------+---------+
         |
         | Consume Event
         v
+------------------+
| Incident Service |
+--------+---------+
         |
         v
+------------------+
|   PostgreSQL     |
+------------------+
```

---

## Components

### 1. Alert Service

Responsibilities:

- Receive alerts from external systems.
- Validate incoming requests.
- Persist alert information.
- Publish alert events to Kafka.

Example Alert:

```json
{
  "source": "payment-service",
  "severity": "CRITICAL",
  "message": "Database connection timeout"
}
```

Technology:

- Java 21
- Spring Boot
- Spring Web
- Spring Validation

---

### 2. Kafka

Responsibilities:

- Decouple services.
- Provide reliable event delivery.
- Enable asynchronous communication.

Benefits:

- Alert Service remains available even if Incident Service is temporarily unavailable.
- Events can be replayed.
- Multiple consumers can process the same event.

Topic:

```text
alerts.created
```

---

### 3. Incident Service

Responsibilities:

- Consume alert events.
- Create incidents.
- Track incident lifecycle.

Incident States:

```text
OPEN
IN_PROGRESS
RESOLVED
CLOSED
```

Technology:

- Java 21
- Spring Boot
- Spring Kafka
- Spring Data JPA

---

### 4. PostgreSQL

Responsibilities:

- Store alerts.
- Store incidents.
- Provide reliable transactional storage.

Why PostgreSQL:

- ACID compliance
- Strong consistency
- Excellent Spring Boot support
- Widely used in cloud-native applications

---

## Event Flow

### Alert Creation Flow

1. User submits an alert.
2. Alert Service validates request.
3. Alert is persisted.
4. Event is published to Kafka.
5. Incident Service consumes event.
6. Incident is created.
7. Incident is stored in PostgreSQL.

---

## Architectural Decisions

### Why Microservices?

Advantages:

- Independent deployment
- Better scalability
- Clear service ownership
- Easier future expansion

Future services may include:

- Notification Service
- SLA Service
- Analytics Service
- User Service

---

### Why Event-Driven Architecture?

Without Kafka:

```text
Alert Service --> Incident Service
```

If Incident Service is unavailable, alert processing fails.

With Kafka:

```text
Alert Service --> Kafka --> Incident Service
```

Kafka acts as a buffer and guarantees event delivery when consumers become available.

---

## Non-Functional Requirements

| Requirement    | Target        |
| -------------- | ------------- |
| Availability   | 99%           |
| Response Time  | < 500 ms      |
| Event Delivery | At Least Once |
| Scalability    | Horizontal    |
| Deployment     | Docker        |
| Cloud          | AWS           |

---

## Future Enhancements

Phase 2:

- API Gateway
- JWT Authentication
- Role Based Access Control

Phase 3:

- Notification Service
- Email Integration
- SLA Tracking

Phase 4:

- Monitoring and Observability
- Prometheus
- Grafana
- OpenTelemetry

Phase 5:

- Kubernetes Deployment
- Infrastructure as Code
- Terraform

---

## Technology Stack

- Java 21
- Spring Boot 3
- PostgreSQL
- Apache Kafka
- Docker
- GitHub
- GitHub Actions
- AWS
- Prometheus
- Grafana
- OpenTelemetry

---

## Version

Architecture Version: 1.0

Status: Initial Design
