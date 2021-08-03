CREATE TABLE stru_subject(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        coding VARCHAR(32),
        create_user VARCHAR(32),
        create_time timestamp,
        update_time timestamp
);