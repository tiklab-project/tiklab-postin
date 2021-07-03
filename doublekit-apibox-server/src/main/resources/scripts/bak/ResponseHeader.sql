CREATE TABLE apibox_response_header(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        header_name VARCHAR(64) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        eg VARCHAR(128),
        sort int
);