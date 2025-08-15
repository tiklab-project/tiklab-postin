--  postin_env_variable 添加 workspace_id 字段
ALTER TABLE postin_env_variable ADD COLUMN workspace_id varchar(32);

--  postin_api_request_query 添加 workspace_id 字段
ALTER TABLE postin_api_request_query ADD COLUMN workspace_id varchar(32);

-- 创建postin_basedata_body  基础数据中的body公共参数 (formdata/formurlencoded)
CREATE TABLE postin_basedata_body(
        id VARCHAR(12) PRIMARY KEY,
        workspace_id VARCHAR(32),
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);
