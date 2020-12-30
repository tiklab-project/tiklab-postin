CREATE TABLE apibox_method(
        id VARCHAR(32) PRIMARY KEY,
        category_id VARCHAR(32),
        name VARCHAR(64) NOT NULL,
        request_type VARCHAR(32) NOT NULL,
        path VARCHAR(256) NOT NULL,
        desc VARCHAR(256),
        sort int
);