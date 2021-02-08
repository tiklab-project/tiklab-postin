CREATE TABLE apibox_raw_response(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        raw VARCHAR(2048) NOT NULL
);