CREATE TABLE postin_workspace(
        id VARCHAR(32) PRIMARY KEY,
        workspace_name VARCHAR(64) NOT NULL,
        description VARCHAR(64),
        visibility int,
        user_id VARCHAR(32)
);

CREATE TABLE postin_workspace_recent (
        id VARCHAR(32) PRIMARY KEY,
        workspace_id VARCHAR(32),
        user_id VARCHAR (32),
        update_time timestamp
);

CREATE TABLE postin_workspace_follow(
        id VARCHAR(32) PRIMARY KEY,
        workspace_id VARCHAR(32) NOT NULL,
        user_id VARCHAR(32) NOT NULL,
        create_time timestamp
);

CREATE TABLE postin_dynamic (
        id VARCHAR(32) PRIMARY KEY,
        workspace_id VARCHAR(32) NOT NULL,
        user_id VARCHAR (32) NOT NULL,
        name VARCHAR (64) NOT NULL,
        model_id VARCHAR (32) NOT NULL,
        model VARCHAR (32) NOT NULL,
        dynamic_type VARCHAR (32) NOT NULL,
        operation_time timestamp
);

CREATE TABLE postin_category(
        id VARCHAR(48) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        workspace_id VARCHAR(32),
        parent_category_id VARCHAR(48)
);

CREATE TABLE postin_environment(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        url VARCHAR(256) NOT NULL,
        create_time timestamp,
        update_time timestamp
);

CREATE TABLE postin_apix(
        id VARCHAR(40) PRIMARY KEY,
        category_id VARCHAR(40),
        name VARCHAR(64),
        protocol_type VARCHAR(32),
        method_type VARCHAR(32),
        create_user VARCHAR(30),
        update_user VARCHAR(30),
        create_time timestamp,
        update_time timestamp,
        status_id VARCHAR(32),
        executor_id VARCHAR(32),
        description VARCHAR(256),
        workspace_id VARCHAR(32),
        version VARCHAR(32),
        api_uid VARCHAR(32)
);

CREATE TABLE postin_api_http(
        id VARCHAR(40) PRIMARY KEY,
        apix_id VARCHAR(40),
        method_type VARCHAR(32),
        path VARCHAR(256)
);

CREATE TABLE postin_http_request_header(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        header_name VARCHAR(64) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);
CREATE TABLE postin_http_request_query(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);

CREATE TABLE postin_http_request(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40),
        body_type VARCHAR(32),
        pre_script VARCHAR(2048),
        after_script VARCHAR(2048)
);

CREATE TABLE postin_http_request_form(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(256),
        sort int
);
CREATE TABLE postin_http_request_urlencoded(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);
CREATE TABLE postin_http_request_json(
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

CREATE TABLE postin_http_request_raw(
        id VARCHAR(40) ,
        http_id VARCHAR(40) NOT NULL,
        raw VARCHAR(2048) NOT NULL,
        type VARCHAR(32) NOT NULL
);

CREATE TABLE postin_http_request_binary(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        filename VARCHAR(64) NOT NULL
);


CREATE TABLE postin_http_response_header(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        header_name VARCHAR(64) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);

CREATE TABLE postin_http_response(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        body_type VARCHAR(32)
);

CREATE TABLE postin_http_response_json(
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

CREATE TABLE postin_http_response_raw(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        raw VARCHAR(2048) NOT NULL,
        type VARCHAR(32) NOT NULL
);

CREATE TABLE postin_http_case(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        name VARCHAR(64)
);
CREATE TABLE postin_http_case_request_header(
        id VARCHAR(32) PRIMARY KEY,
        http_case_id VARCHAR(32) NOT NULL,
        header_name VARCHAR(64) NOT NULL,
        value VARCHAR(256),
        sort int
);
CREATE TABLE postin_http_case_request_query(
        id VARCHAR(32) PRIMARY KEY,
        http_case_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        value VARCHAR(128),
        sort int
);

CREATE TABLE postin_http_case_request(
        id VARCHAR(32) PRIMARY KEY,
        http_case_id VARCHAR(32),
        body_type VARCHAR(32),
        pre_script VARCHAR(2048),
        after_script VARCHAR(2048)
);

CREATE TABLE postin_http_case_request_form(
        id VARCHAR(32) PRIMARY KEY,
        http_case_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        value VARCHAR(128),
        sort int
);

CREATE TABLE postin_http_case_request_urlencoded(
        id VARCHAR(32) PRIMARY KEY,
        http_case_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        value VARCHAR(128),
        sort int
);

CREATE TABLE postin_http_case_request_json(
        id VARCHAR(32) PRIMARY KEY,
        http_case_id VARCHAR(32) NOT NULL,
        parent_id VARCHAR(32),
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        value VARCHAR(128),
        sort int
);

CREATE TABLE postin_http_case_request_raw(
        id VARCHAR(32) PRIMARY KEY,
        http_case_id VARCHAR(32) NOT NULL,
        raw VARCHAR(2048),
        type VARCHAR(32) NOT NULL
);

CREATE TABLE postin_http_case_request_binary(
        id VARCHAR(32) PRIMARY KEY,
        http_case_id VARCHAR(32) NOT NULL,
        filename VARCHAR(64) NOT NULL
);

CREATE TABLE postin_http_case_request_assert(
        id VARCHAR(32) PRIMARY KEY,
        http_case_id VARCHAR(32) NOT NULL,
        source int,
        property_name VARCHAR(64),
        data_type VARCHAR(32),
        comparator VARCHAR(32) NOT NULL,
        value VARCHAR(128) NOT NULL,
        sort int
);
CREATE TABLE postin_http_case_instance(
        id VARCHAR(40) PRIMARY KEY,
        http_case_id VARCHAR(40) ,
        user_id VARCHAR(32),
        status_code int,
        result int,
        create_time timestamp,
        time int,
        size int,
        error_message VARCHAR(2048)
);

CREATE TABLE postin_http_case_instance_request(
        id VARCHAR(32) PRIMARY KEY,
        http_instance_id VARCHAR(32) NOT NULL,
        URL VARCHAR(2048),
        headers VARCHAR(2048),
        body VARCHAR(2048),
        pre_script VARCHAR(2048),
        after_script VARCHAR(2048),
        method_type VARCHAR(32),
        media_type VARCHAR(32)
);

CREATE TABLE postin_http_case_instance_response(
        id VARCHAR(32) PRIMARY KEY,
        http_instance_id VARCHAR(32),
        headers VARCHAR(2048),
        body VARCHAR(2048)
);

CREATE TABLE postin_http_case_instance_assert(
        id VARCHAR(32) PRIMARY KEY,
        http_instance_id VARCHAR(32) NOT NULL,
        source int,
        property_name VARCHAR(64),
        data_type VARCHAR(32),
        comparator VARCHAR(32),
        value VARCHAR(128),
        result int
);

CREATE TABLE postin_http_mock(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        name VARCHAR(64),
        description VARCHAR(128),
        create_user VARCHAR(32),
        create_time timestamp,
        enable int
);
CREATE TABLE postin_http_mock_request_header(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        header_name VARCHAR(64) NOT NULL,
        value VARCHAR(256),
        sort int
);
CREATE TABLE postin_http_mock_request_query(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        value VARCHAR(256),
        sort int
);
CREATE TABLE postin_http_mock_request(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        body_type VARCHAR(32)
);
CREATE TABLE postin_http_mock_request_form(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        value VARCHAR(256),
        sort int
);
CREATE TABLE postin_http_mock_request_json(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        exp VARCHAR(64) NOT NULL,
        value VARCHAR(256),
        sort int
);
CREATE TABLE postin_http_mock_response_header(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        header_name VARCHAR(64),
        value VARCHAR(64),
        sort int
);

CREATE TABLE postin_http_mock_response(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        http_code VARCHAR(32),
        body_type VARCHAR(32)
);

CREATE TABLE postin_http_mock_response_result(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        result VARCHAR(2048)
);

CREATE TABLE postin_model(
        id VARCHAR(32) PRIMARY KEY,
        workspace_id VARCHAR(32),
        coding VARCHAR (64),
        name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        create_user varchar(30),
        create_time timestamp ,
        update_time timestamp

);
CREATE TABLE postin_model_json(
        id VARCHAR(32) PRIMARY KEY,
        subject_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        parent_id VARCHAR(32)
);
CREATE TABLE postin_model_enum(
        id VARCHAR(32) PRIMARY KEY,
        subject_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128)
);

CREATE TABLE postin_api_status(
        id VARCHAR(32) PRIMARY KEY,
        color VARCHAR(32),
        name VARCHAR(64),
        type VARCHAR(32),
        workspace_id VARCHAR(32)
);

