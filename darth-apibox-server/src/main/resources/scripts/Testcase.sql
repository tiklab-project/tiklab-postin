CREATE TABLE apibox_testcase(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        name VARCHAR(64) NOT NULL,
        base_url VARCHAR(128)
);