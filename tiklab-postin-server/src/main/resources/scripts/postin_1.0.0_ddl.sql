-- ---------------------------
-- 空间表
-- @dsm.cmd.id="1001"
-- ----------------------------
CREATE TABLE postin_workspace(
        id VARCHAR(32) PRIMARY KEY,
        workspace_name VARCHAR(64) NOT NULL,
        description VARCHAR(64),
        visibility int,
        icon_url VARCHAR(256),
        user_id VARCHAR(32)
);

-- ---------------------------
-- 最近空间表
-- @dsm.cmd.id="1002"
-- ----------------------------
CREATE TABLE postin_workspace_recent (
        id VARCHAR(32) PRIMARY KEY,
        workspace_id VARCHAR(32),
        user_id VARCHAR (32),
        update_time timestamp
);

-- ---------------------------
-- 空间关注表
-- @dsm.cmd.id="1003"
-- ----------------------------
CREATE TABLE postin_workspace_follow(
        id VARCHAR(32) PRIMARY KEY,
        workspace_id VARCHAR(32) NOT NULL,
        user_id VARCHAR(32) NOT NULL,
        create_time timestamp
);

-- ---------------------------
-- 分组表
-- @dsm.cmd.id="1004"
-- ----------------------------
CREATE TABLE postin_category(
        id VARCHAR(48) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        workspace_id VARCHAR(32),
        parent_category_id VARCHAR(48)
);

-- ---------------------------
-- 环境表
-- @dsm.cmd.id="1005"
-- ----------------------------
CREATE TABLE postin_environment(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        url VARCHAR(256) NOT NULL,
        workspace_id VARCHAR(32),
        create_time timestamp,
        update_time timestamp
);

-- ---------------------------
-- 接口表  中间关联
-- @dsm.cmd.id="1006"
-- ----------------------------
CREATE TABLE postin_apix(
        id VARCHAR(40) PRIMARY KEY,
        category_id VARCHAR(40),
        name VARCHAR(64),
        protocol_type VARCHAR(32),
        method_type VARCHAR(32),
        create_user VARCHAR(32),
        update_user VARCHAR(32),
        create_time timestamp,
        update_time timestamp,
        status_id VARCHAR(32),
        executor_id VARCHAR(32),
        description VARCHAR(256),
        workspace_id VARCHAR(32),
        version VARCHAR(32),
        api_uid VARCHAR(32)
);

-- ---------------------------
-- 接口表
-- http
-- @dsm.cmd.id="1007"
-- ----------------------------
CREATE TABLE postin_api_http(
        id VARCHAR(40) PRIMARY KEY,
        apix_id VARCHAR(40),
        method_type VARCHAR(32),
        path VARCHAR(256)
);

-- ---------------------------
-- http
-- 请求头
-- @dsm.cmd.id="1008"
-- ----------------------------
CREATE TABLE postin_http_request_header(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40),
        workspace_id VARCHAR(32),
        header_name VARCHAR(64) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);

-- ---------------------------
-- http
-- query查询参数
-- @dsm.cmd.id="1009"
-- ----------------------------
CREATE TABLE postin_http_request_query(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40),
        param_name VARCHAR(64) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);

-- ---------------------------
-- http
-- 请求的公共字段表
-- @dsm.cmd.id="1010"
-- ----------------------------
CREATE TABLE postin_http_request(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40),
        body_type VARCHAR(32),
        pre_script longtext,
        after_script longtext
);

-- ---------------------------
-- http
-- form-data
-- @dsm.cmd.id="1011"
-- ----------------------------
CREATE TABLE postin_http_request_form(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40),
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(256),
        sort int
);

-- ---------------------------
-- http
-- form-url
-- @dsm.cmd.id="1012"
-- ----------------------------
CREATE TABLE postin_http_request_urlencoded(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40),
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);

-- ---------------------------
-- http
-- json
-- @dsm.cmd.id="1013"
-- ----------------------------
CREATE TABLE postin_http_request_json(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        json_text VARCHAR(2048),
        parent_id VARCHAR(40),
        param_name VARCHAR(64),
        data_type VARCHAR(32),
        required int,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int

);

-- ---------------------------
-- http
-- raw
-- @dsm.cmd.id="1014"
-- ----------------------------
CREATE TABLE postin_http_request_raw(
        id VARCHAR(40) ,
        http_id VARCHAR(40) NOT NULL,
        raw longtext NOT NULL,
        type VARCHAR(32) NOT NULL
);

-- ---------------------------
-- http
-- binary
-- @dsm.cmd.id="1015"
-- ----------------------------
CREATE TABLE postin_http_request_binary(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        filename VARCHAR(64) NOT NULL
);

-- ---------------------------
-- http
-- 响应头
-- @dsm.cmd.id="1016"
-- ----------------------------
CREATE TABLE postin_http_response_header(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        header_name VARCHAR(64) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);

-- ---------------------------
-- http
-- 响应中的公共字段
-- @dsm.cmd.id="1017"
-- ----------------------------
CREATE TABLE postin_http_response(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        create_time timestamp,
        http_code int,
        name VARCHAR(64),
        data_type VARCHAR(32),
        json_text longtext,
        raw_text longtext
);

-- ---------------------------
-- http
-- 响应中的json
-- @dsm.cmd.id="1018"
-- ----------------------------
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

-- ---------------------------
-- http
-- 响应中raw
-- @dsm.cmd.id="1019"
-- ----------------------------
CREATE TABLE postin_http_response_raw(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        raw VARCHAR(2048) NOT NULL,
        type VARCHAR(32) NOT NULL
);

-- ---------------------------
-- http 用例
-- @dsm.cmd.id="1020"
-- ----------------------------
CREATE TABLE postin_http_case(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        name VARCHAR(64)
);

-- ---------------------------
-- http 用例
-- 请求头
-- @dsm.cmd.id="1021"
-- ----------------------------
CREATE TABLE postin_http_case_request_header(
        id VARCHAR(32) PRIMARY KEY,
        http_case_id VARCHAR(32) NOT NULL,
        header_name VARCHAR(64) NOT NULL,
        value VARCHAR(256),
        sort int
);

-- ---------------------------
-- http 用例
-- query查询参数
-- @dsm.cmd.id="1022"
-- ----------------------------
CREATE TABLE postin_http_case_request_query(
        id VARCHAR(32) PRIMARY KEY,
        http_case_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        value VARCHAR(128),
        sort int
);

-- ---------------------------
-- http 用例
-- 请求的公共字段表
-- @dsm.cmd.id="1023"
-- ----------------------------
CREATE TABLE postin_http_case_request(
        id VARCHAR(32) PRIMARY KEY,
        http_case_id VARCHAR(32),
        body_type VARCHAR(32),
        pre_script VARCHAR(2048),
        after_script VARCHAR(2048)
);

-- ---------------------------
-- http 用例
-- form-data
-- @dsm.cmd.id="1024"
-- ----------------------------
CREATE TABLE postin_http_case_request_form(
        id VARCHAR(32) PRIMARY KEY,
        http_case_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        value VARCHAR(128),
        sort int
);

-- ---------------------------
-- http 用例
-- form-url
-- @dsm.cmd.id="1025"
-- ----------------------------
CREATE TABLE postin_http_case_request_urlencoded(
        id VARCHAR(32) PRIMARY KEY,
        http_case_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        value VARCHAR(128),
        sort int
);

-- ---------------------------
-- http 用例
-- json
-- @dsm.cmd.id="1026"
-- ----------------------------
CREATE TABLE postin_http_case_request_json(
        id VARCHAR(32) PRIMARY KEY,
        http_case_id VARCHAR(32) NOT NULL,
        parent_id VARCHAR(32),
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        value VARCHAR(128),
        sort int
);

-- ---------------------------
-- http 用例
-- raw
-- @dsm.cmd.id="1027"
-- ----------------------------
CREATE TABLE postin_http_case_request_raw(
        id VARCHAR(32) PRIMARY KEY,
        http_case_id VARCHAR(32) NOT NULL,
        raw VARCHAR(2048),
        type VARCHAR(32) NOT NULL
);

-- ---------------------------
-- http 用例
-- binary
-- @dsm.cmd.id="1028"
-- ----------------------------
CREATE TABLE postin_http_case_request_binary(
        id VARCHAR(32) PRIMARY KEY,
        http_case_id VARCHAR(32) NOT NULL,
        filename VARCHAR(64) NOT NULL
);

-- ---------------------------
-- http 用例
-- 断言
-- @dsm.cmd.id="1029"
-- ----------------------------
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

-- ---------------------------
-- http 用例
-- 实例
-- @dsm.cmd.id="1030"
-- ----------------------------
CREATE TABLE postin_instance(
        id VARCHAR(40) PRIMARY KEY,
        http_case_id VARCHAR(40),
        workspace_id VARCHAR(32),
        user_id VARCHAR(32),
        status_code int,
        result int,
        create_time timestamp,
        time int,
        size int,
        error_message VARCHAR(2048)
);

-- ---------------------------
-- http 用例
-- 请求实例
-- @dsm.cmd.id="1031"
-- ----------------------------
CREATE TABLE postin_instance_http_request(
        id VARCHAR(32) PRIMARY KEY,
        http_instance_id VARCHAR(32) NOT NULL,
        URL VARCHAR(2048),
        headers text,
        body longtext,
        pre_script longtext,
        after_script longtext,
        method_type VARCHAR(32),
        media_type VARCHAR(64)
);

-- ---------------------------
-- http 用例
-- 响应实例
-- @dsm.cmd.id="1032"
-- ----------------------------
CREATE TABLE postin_instance_http_response(
        id VARCHAR(32) PRIMARY KEY,
        http_instance_id VARCHAR(32),
        headers text,
        body longtext
);

-- ---------------------------
-- http 用例
-- 断言实例
-- @dsm.cmd.id="1033"
-- ----------------------------
CREATE TABLE postin_instance_http_assert(
        id VARCHAR(32) PRIMARY KEY,
        http_instance_id VARCHAR(32) NOT NULL,
        source int,
        property_name VARCHAR(64),
        data_type VARCHAR(32),
        comparator VARCHAR(32),
        value VARCHAR(128),
        result int
);

-- ---------------------------
-- http mock
-- @dsm.cmd.id="1034"
-- ----------------------------
CREATE TABLE postin_http_mock(
        id VARCHAR(40) PRIMARY KEY,
        http_id VARCHAR(40) NOT NULL,
        name VARCHAR(64),
        description VARCHAR(128),
        create_user VARCHAR(32),
        create_time timestamp,
        enable int
);

-- ---------------------------
-- http mock
-- 请求头
-- @dsm.cmd.id="1035"
-- ----------------------------
CREATE TABLE postin_http_mock_request_header(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        header_name VARCHAR(64) NOT NULL,
        value VARCHAR(256),
        sort int
);

-- ---------------------------
-- http mock
-- query查询参数
-- @dsm.cmd.id="1036"
-- ----------------------------
CREATE TABLE postin_http_mock_request_query(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        value VARCHAR(256),
        sort int
);

-- ---------------------------
-- http mock
-- 请求体的类型
-- @dsm.cmd.id="1037"
-- ----------------------------
CREATE TABLE postin_http_mock_request(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        body_type VARCHAR(32)
);

-- ---------------------------
-- http mock
-- form-data
-- @dsm.cmd.id="1038"
-- ----------------------------
CREATE TABLE postin_http_mock_request_form(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        value VARCHAR(256),
        sort int
);

-- ---------------------------
-- http mock
-- json
-- @dsm.cmd.id="1039"
-- ----------------------------
CREATE TABLE postin_http_mock_request_json(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        exp VARCHAR(64) NOT NULL,
        value VARCHAR(256),
        sort int
);

-- ---------------------------
-- http mock
-- 响应头
-- @dsm.cmd.id="1040"
-- ----------------------------
CREATE TABLE postin_http_mock_response_header(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        header_name VARCHAR(64),
        value VARCHAR(64),
        sort int
);

-- ---------------------------
-- http mock
-- 响应中的公共字段
-- @dsm.cmd.id="1041"
-- ----------------------------
CREATE TABLE postin_http_mock_response(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        http_code VARCHAR(32),
        time int,
        body_type VARCHAR(32)
);

-- ---------------------------
-- http mock
-- 响应结果
-- @dsm.cmd.id="1042"
-- ----------------------------
CREATE TABLE postin_http_mock_response_result(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        result VARCHAR(2048)
);

-- ---------------------------
-- 模型
-- @dsm.cmd.id="1043"
-- ----------------------------
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

-- ---------------------------
-- 模型
-- json
-- @dsm.cmd.id="1044"
-- ----------------------------
CREATE TABLE postin_model_json(
        id VARCHAR(32) PRIMARY KEY,
        data_structure_id VARCHAR(32) NOT NULL,
        json_text VARCHAR(2048)
);

-- ---------------------------
-- 模型
-- 枚举
-- @dsm.cmd.id="1045"
-- ----------------------------
CREATE TABLE postin_model_enum(
        id VARCHAR(32) PRIMARY KEY,
        subject_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128)
);

-- ---------------------------
-- 状态
-- @dsm.cmd.id="1046"
-- ----------------------------
CREATE TABLE postin_api_status(
        id VARCHAR(32) PRIMARY KEY,
        color VARCHAR(32),
        name VARCHAR(64),
        type VARCHAR(32),
        workspace_id VARCHAR(32)
);

-- ---------------------------
-- 分享
-- @dsm.cmd.id="1047"
-- ----------------------------
CREATE TABLE postin_share(
        id VARCHAR(32) PRIMARY KEY,
        target_Id VARCHAR(32) NOT NULL,
        target_type VARCHAR(32) NOT NULL,
        visibility int NOT NULL,
        password VARCHAR(32),
        create_time timestamp ,
        update_time timestamp
);

