CREATE TABLE incidents (
                           id UUID PRIMARY KEY,
                           title VARCHAR(255) NOT NULL,
                           description VARCHAR(2000) NOT NULL,
                           priority VARCHAR(20) NOT NULL,
                           status VARCHAR(20) NOT NULL,
                           assignee VARCHAR(255),
                           source VARCHAR(100) NOT NULL,
                           created_at TIMESTAMP WITH TIME ZONE NOT NULL,
                           updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);