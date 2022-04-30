CREATE TABLE apibox_workspace(
        id VARCHAR(32) PRIMARY KEY,
        workspace_name VARCHAR(64) NOT NULL,
        description VARCHAR(64),
        user_id VARCHAR(32)
);
CREATE TABLE apibox_category(
        id VARCHAR(48) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        workspace_id VARCHAR(32),
        parent_category_id VARCHAR(48),
        sort int
);
CREATE TABLE apibox_environment(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        url VARCHAR(256) NOT NULL,
        sort int,
        create_time timestamp,
        update_time timestamp
);
CREATE TABLE apibox_apix(
        id VARCHAR(40) PRIMARY KEY,
        category_id VARCHAR(40),
        name VARCHAR(64) NOT NULL,
        protocol_type VARCHAR(32) NOT NULL,
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

CREATE TABLE apibox_testcase(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
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


CREATE TABLE apibox_mock(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
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

CREATE TABLE stru_subject(
        id VARCHAR(32) PRIMARY KEY,
        coding VARCHAR (64),
        name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        create_user varchar(30),
        create_time timestamp ,
        update_time timestamp

);
CREATE TABLE stru_json(
        id VARCHAR(32) PRIMARY KEY,
        subject_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        parent_id VARCHAR(32)
);
CREATE TABLE stru_enum(
        id VARCHAR(32) PRIMARY KEY,
        subject_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128)
);

CREATE TABLE apibox_method_status(
        id VARCHAR(32) PRIMARY KEY,
        code VARCHAR(32),
        name VARCHAR(64),
        type VARCHAR(32)
);
INSERT INTO apibox_method_status values     ("publishid","publish","已发布","system"),
                                            ("designid","design","设计中","system"),
                                            ("developmentid","development","开发中","system"),
                                            ("testid","test","测试","system"),
                                            ("completeid","complete","完成","system"),
                                            ("maintainid","maintain","维护","system");