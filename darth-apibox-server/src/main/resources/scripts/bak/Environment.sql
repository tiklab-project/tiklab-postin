CREATE TABLE apibox_environment(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        url VARCHAR(256) NOT NULL,
        sort int,
        create_time timestamp,
        update_time timestamp
);