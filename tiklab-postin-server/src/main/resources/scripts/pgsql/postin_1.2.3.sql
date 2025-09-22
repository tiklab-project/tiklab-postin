

CREATE TABLE autotest_api_path(
        id VARCHAR(12) PRIMARY KEY,
        api_unit_id VARCHAR(32),
        data_type VARCHAR(32),
        name VARCHAR(128) NOT NULL,
        required int NOT NULL,
        description VARCHAR(512),
        value VARCHAR(1024),
        sort int
);

-- 创建 认证 表
CREATE TABLE autotest_api_auth(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32),
        type VARCHAR(12)
);

-- 创建 自定义认证 表
CREATE TABLE autotest_api_auth_apikey(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32),
        apikey_in VARCHAR(8),
        name VARCHAR(128),
        value text
);

-- 创建 BearerToken认证 表
CREATE TABLE autotest_api_auth_bearer(
        id VARCHAR(32) PRIMARY KEY,
        api_unit_id VARCHAR(32),
        name VARCHAR(32),
        value text
);

-- 将表示字段值修改为TEXT
ALTER TABLE postin_api_request_header ALTER COLUMN value TYPE TEXT;