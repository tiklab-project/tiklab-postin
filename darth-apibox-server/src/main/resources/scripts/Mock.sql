CREATE TABLE apibox_mock(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        name VARCHAR(64) NOT NULL,
        description VARCHAR(128)
);