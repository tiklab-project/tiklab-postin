CREATE TABLE apibox_testcase(
        id VARCHAR(40) PRIMARY KEY,
        method_id VARCHAR(40) NOT NULL,
        name VARCHAR(64) NOT NULL,
        base_url VARCHAR(128),
        request_type VARCHAR(64),
        path VARCHAR(256)
);
CREATE TABLE apibox_request_header_testcase(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32) NOT NULL,
        header_name VARCHAR(64) NOT NULL,
        value VARCHAR(256),
        sort int
);
CREATE TABLE apibox_query_param_testcase(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        value VARCHAR(128),
        sort int
);
CREATE TABLE apibox_request_body_testcase(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32) NOT NULL,
        body_type VARCHAR(32) NOT NULL
);
CREATE TABLE apibox_form_param_testcase(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        value VARCHAR(128),
        sort int
);
CREATE TABLE apibox_json_param_testcase(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32) NOT NULL,
        parent_id VARCHAR(32),
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        value VARCHAR(128),
        sort int
);
CREATE TABLE apibox_raw_param_testcase(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32) NOT NULL,
        raw VARCHAR(2048),
        type VARCHAR(32) NOT NULL
);
CREATE TABLE apibox_pre_script_testcase(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32) NOT NULL,
        script VARCHAR(2048) NOT NULL
);
CREATE TABLE apibox_after_script_testcase(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32) NOT NULL,
        script VARCHAR(2048) NOT NULL
);
CREATE TABLE apibox_assert_testcase(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32) NOT NULL,
        source int,
        property_name VARCHAR(64),
        data_type VARCHAR(32),
        comparator VARCHAR(32) NOT NULL,
        value VARCHAR(128) NOT NULL,
        sort int
);
CREATE TABLE apibox_test_instance(
        id VARCHAR(40) PRIMARY KEY,
        testcase_id VARCHAR(40) ,
        testNo int NOT NULL,
        statusCode int NOT NULL,
        result int NOT NULL,
        create_time timestamp,
        request_type VARCHAR(32)
);
CREATE TABLE apibox_request_instance(
        id VARCHAR(32) PRIMARY KEY,
        instance_id VARCHAR(32) NOT NULL,
        request_base VARCHAR(2048) NOT NULL,
        request_header VARCHAR(2048),
        request_param VARCHAR(2048)
);
CREATE TABLE apibox_response_instance(
        id VARCHAR(32) PRIMARY KEY,
        instance_id VARCHAR(32) NOT NULL,
        response_header VARCHAR(2048) NOT NULL,
        response_body VARCHAR(2048) NOT NULL
);
CREATE TABLE apibox_assert_instance(
        id VARCHAR(32) PRIMARY KEY,
        instance_id VARCHAR(32) NOT NULL,
        source int,
        property_name VARCHAR(64),
        data_type VARCHAR(32),
        comparator VARCHAR(32) NOT NULL,
        value VARCHAR(128),
        result int
);
CREATE TABLE apibox_form_urlencoded_testcase(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        value VARCHAR(128),
        sort int
);
CREATE TABLE apibox_binary_param_testcase(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32) NOT NULL,
        filename VARCHAR(64) NOT NULL
);