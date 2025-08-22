-- 删除postin_api_request 表中的 pre_script 和 after_script字段
ALTER TABLE postin_api_request DROP COLUMN pre_script;
ALTER TABLE postin_api_request DROP COLUMN after_script;

-- 前置操作
CREATE TABLE postin_api_request_pre(
        id VARCHAR(12) PRIMARY KEY,
        api_id VARCHAR(32),
        name VARCHAR(32) NOT NULL,
        type VARCHAR(12) NOT NULL,
        enabled int,
        sort int
);
--  后置操作
CREATE TABLE postin_api_request_after(
        id VARCHAR(12) PRIMARY KEY,
        api_id VARCHAR(32),
        name VARCHAR(32) NOT NULL,
        type VARCHAR(12) NOT NULL,
        enabled int,
        sort int
);

-- 前置后置中的db操作
CREATE TABLE postin_api_request_operate_db(
        id VARCHAR(12) PRIMARY KEY,
        operation_id VARCHAR(12),
        db_connect_id VARCHAR(12),
        sql_text text,
        is_console_print int
);

-- 前置后置中的db操作的提取变量列表
CREATE TABLE postin_api_request_operate_db_variable(
        id VARCHAR(12) PRIMARY KEY,
        operation_id VARCHAR(12),
        name VARCHAR(32),
        variable_type VARCHAR(12) NOT NULL,
        json_path VARCHAR(128)
);

-- 前置后置中的自定义脚本
CREATE TABLE postin_api_request_operate_script(
        id VARCHAR(12) PRIMARY KEY,
        operation_id VARCHAR(12),
        script_text text
);


-- 数据库链接管理
CREATE TABLE postin_db_connect(
        id VARCHAR(12) PRIMARY KEY,
        workspace_id VARCHAR(32),
        user_id VARCHAR(32),
        name VARCHAR(32),
        type VARCHAR(12),
        description VARCHAR(128)
);

-- 数据库链接管理配置
CREATE TABLE postin_db_connect_config(
        id VARCHAR(12) PRIMARY KEY,
        db_connect_id VARCHAR(12),
        host VARCHAR(16) NOT NULL,
        port int NOT NULL,
        user_name VARCHAR(64) NOT NULL,
        password VARCHAR(64) NOT NULL,
        db_name VARCHAR(64)
);

