CREATE TABLE apibox_apix(
        id VARCHAR(40) PRIMARY KEY,
        category_id VARCHAR(40),
        name VARCHAR(64) NOT NULL,
        protocol_type VARCHAR(32) NOT NULL,
        request_type VARCHAR(32) NOT NULL,
        path VARCHAR(256) NOT NULL,
        create_user VARCHAR(30),
        update_user VARCHAR(30),
        create_time timestamp,
        update_time timestamp,
        status_id VARCHAR(32),
        executor_id VARCHAR(32),
        description VARCHAR(256),
        sort int
);

CREATE TABLE apibox_api_http(
        id VARCHAR(40) PRIMARY KEY,
        apix_id VARCHAR(40)
);

CREATE TABLE apibox_request_header(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        header_name VARCHAR(64) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);
CREATE TABLE apibox_query_param(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);
CREATE TABLE apibox_request_body(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        body_type VARCHAR(32) NOT NULL
);
CREATE TABLE apibox_form_param(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(256),
        sort int
);
CREATE TABLE apibox_form_urlencoded(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);
CREATE TABLE apibox_json_param(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        parent_id VARCHAR(40),
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int

);
CREATE TABLE apibox_raw_param(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        raw VARCHAR(2048) NOT NULL,
        type VARCHAR(32) NOT NULL
);
CREATE TABLE apibox_pre_script(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        script VARCHAR(2048) NOT NULL
);
CREATE TABLE apibox_after_script(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        script VARCHAR(2048) NOT NULL
);
CREATE TABLE apibox_response_header(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        header_name VARCHAR(64) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);
CREATE TABLE apibox_response_result(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        result_type VARCHAR(32) NOT NULL
);
CREATE TABLE apibox_json_response(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        parent_id VARCHAR(40),
        property_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int

);
CREATE TABLE apibox_raw_response(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        raw VARCHAR(2048) NOT NULL,
        type VARCHAR(32) NOT NULL
);
CREATE TABLE apibox_binary_param(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        filename VARCHAR(64) NOT NULL
);