CREATE TABLE apibox_mock(
        id VARCHAR(40) PRIMARY KEY,
        method_id VARCHAR(40) NOT NULL,
        name VARCHAR(64) NOT NULL,
        description VARCHAR(128),
        create_user VARCHAR(32),
        create_time timestamp,
        enable int NOT NULL
);
CREATE TABLE apibox_request_header_mock(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        header_name VARCHAR(64) NOT NULL,
        value VARCHAR(256),
        sort int
);
CREATE TABLE apibox_query_param_mock(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        value VARCHAR(256),
        sort int
);
CREATE TABLE apibox_request_body_mock(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        body_type VARCHAR(32) NOT NULL
);
CREATE TABLE apibox_form_param_mock(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        value VARCHAR(256),
        sort int
);
CREATE TABLE apibox_json_param_mock(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        exp VARCHAR(64) NOT NULL,
        value VARCHAR(256),
        sort int
);
CREATE TABLE apibox_response_header_mock(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        header_name VARCHAR(64),
        value VARCHAR(64),
        sort int
);
CREATE TABLE apibox_response_mock(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        http_code VARCHAR(32) NOT NULL
);
CREATE TABLE apibox_response_result_mock(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        result_type VARCHAR(32) NOT NULL
);
CREATE TABLE apibox_json_response_mock(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        result VARCHAR(2048)
);
CREATE TABLE apibox_raw_response_mock(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        result VARCHAR(2048),
        type VARCHAR(32) NOT NULL
);