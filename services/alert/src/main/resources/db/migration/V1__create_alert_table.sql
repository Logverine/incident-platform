CREATE TABLE alerts (
                        id BIGSERIAL PRIMARY KEY,
                        source VARCHAR(100) NOT NULL,
                        severity VARCHAR(20) NOT NULL,
                        message VARCHAR(500) NOT NULL,
                        status VARCHAR(20) NOT NULL,
                        created_at TIMESTAMP NOT NULL
);