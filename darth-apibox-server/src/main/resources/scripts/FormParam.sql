CREATE TABLE apibox_form_param(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        desc VARCHAR(128),
        eg VARCHAR(128),
        sort int
);