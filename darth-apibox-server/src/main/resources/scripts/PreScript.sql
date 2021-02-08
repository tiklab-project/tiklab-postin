CREATE TABLE apibox_pre_script(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        script VARCHAR(2048) NOT NULL
);