CREATE TABLE apibox_json_param_testcase(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32) NOT NULL,
        parent_id VARCHAR(32),
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        value VARCHAR(128),
        sort int
);