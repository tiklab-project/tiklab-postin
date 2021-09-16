CREATE TABLE stru_json(
        id VARCHAR(32) PRIMARY KEY,
        param_name VARCHAR(64) NOT NULL,
        subject_id VARCHAR(32),
        data_type VARCHAR(32),
        required timestamp,
        description timestamp,
        parent_id timestamp
);