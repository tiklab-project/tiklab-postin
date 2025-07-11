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
        key VARCHAR(128),
        value text
);
-- 创建 BearerToken认证 表
CREATE TABLE postin_http_auth_bearer(
        id VARCHAR(32) PRIMARY KEY,
        api_id VARCHAR(32),
        key VARCHAR(32),
        value text
);

-- 将表示字段值修改为TEXT
ALTER TABLE postin_env_variable ALTER COLUMN value TYPE TEXT;