CREATE TABLE apibox_after_script(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        script VARCHAR(2048) NOT NULL
);