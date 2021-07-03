CREATE TABLE apibox_method(
        id VARCHAR(32) PRIMARY KEY,
        category_id VARCHAR(32),
        name VARCHAR(64) NOT NULL,
        request_type VARCHAR(32) NOT NULL,
        path VARCHAR(256) NOT NULL,
        description VARCHAR(256),
        sort int
);
CREATE TABLE apibox_request_header(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        header_name VARCHAR(64) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        eg VARCHAR(128),
        sort int
);
CREATE TABLE apibox_query_param(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        eg VARCHAR(128),
        sort int
);
CREATE TABLE apibox_request_body(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        body_type VARCHAR(32) NOT NULL
);
CREATE TABLE apibox_form_param(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        eg VARCHAR(128),
        sort int
);
CREATE TABLE apibox_json_param(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        eg VARCHAR(128),
        sort int,
        parent_id VARCHAR(32)
);
CREATE TABLE apibox_raw_param(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        raw VARCHAR(2048) NOT NULL
);
CREATE TABLE apibox_pre_script(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        script VARCHAR(2048) NOT NULL
);
CREATE TABLE apibox_after_script(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        script VARCHAR(2048) NOT NULL
);
CREATE TABLE apibox_response_header(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        header_name VARCHAR(64) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        eg VARCHAR(128),
        sort int
);
CREATE TABLE apibox_response_result(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        result_type VARCHAR(32) NOT NULL
);
CREATE TABLE apibox_json_response(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        property_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        eg VARCHAR(128),
        sort int,
        parent_id VARCHAR(32)
);
CREATE TABLE apibox_raw_response(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        raw VARCHAR(2048) NOT NULL
);