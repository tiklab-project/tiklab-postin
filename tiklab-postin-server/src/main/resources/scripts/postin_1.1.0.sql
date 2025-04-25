
-- ---------------------------
-- 环境中服务地址表
-- ----------------------------
CREATE TABLE postin_env_server(
        id VARCHAR(12) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        url VARCHAR(256),
        env_id VARCHAR(12)
);

-- ---------------------------
-- 环境中变量表
-- ----------------------------
CREATE TABLE postin_env_variable(
        id VARCHAR(12) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        value VARCHAR(256),
        description VARCHAR(256),
        env_id VARCHAR(12)
);


-- ---------------------------
-- 将环境表中的URL数据迁移到环境服务表中，设置名称为"默认服务"
-- ---------------------------
INSERT INTO postin_env_server (id, name, url, env_id)
SELECT
    MD5(id || now()::text)::varchar(12) as id,
    '默认服务' as name,
    url,
    id as env_id
FROM postin_environment
WHERE url IS NOT NULL AND url != '';

-- ---------------------------
-- 修改环境表结构，删除URL字段
-- ---------------------------
ALTER TABLE postin_environment
DROP COLUMN url;