--- 版本管理
CREATE TABLE postin_api_version(
    id VARCHAR(12) PRIMARY KEY,
    name VARCHAR(128),
    api_id VARCHAR(32),
    version_api_id VARCHAR(32),
    user_id VARCHAR(32),
    create_time TIMESTAMP
);
