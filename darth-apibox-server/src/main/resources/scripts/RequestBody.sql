CREATE TABLE apibox_request_body(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        body_type VARCHAR(32) NOT NULL
);