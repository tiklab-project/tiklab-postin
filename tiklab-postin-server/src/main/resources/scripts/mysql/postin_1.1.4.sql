-- 创建 认证 表
CREATE TABLE postin_http_auth(
        id VARCHAR(32) PRIMARY KEY,
        api_id VARCHAR(32),
        type VARCHAR(12)
);

-- 创建 自定义认证 表
CREATE TABLE postin_http_auth_apikey(
        id VARCHAR(32) PRIMARY KEY,
        api_id VARCHAR(32),
        apikey_in VARCHAR(8),
        name VARCHAR(128),
        value text
);
-- 创建 BearerToken认证 表
CREATE TABLE postin_http_auth_bearer(
        id VARCHAR(32) PRIMARY KEY,
        api_id VARCHAR(32),
        name VARCHAR(32),
        value text
);

-- 将表示字段修改为TEXT
ALTER TABLE postin_env_variable MODIFY COLUMN value TEXT;