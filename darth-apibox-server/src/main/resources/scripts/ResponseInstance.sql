CREATE TABLE apibox_response_instance(
        id VARCHAR(32) PRIMARY KEY,
        instance_id VARCHAR(32) NOT NULL,
        response_header VARCHAR(2048) NOT NULL,
        response_body VARCHAR(2048) NOT NULL
);