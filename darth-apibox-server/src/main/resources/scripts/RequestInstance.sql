CREATE TABLE apibox_request_instance(
        id VARCHAR(32) PRIMARY KEY,
        instance_id VARCHAR(32) NOT NULL,
        request_base VARCHAR(2048) NOT NULL,
        request_header VARCHAR(2048) NOT NULL,
        request_param VARCHAR(2048) NOT NULL
);