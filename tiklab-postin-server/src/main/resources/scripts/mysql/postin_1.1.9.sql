-- 删除之前的表
DROP TABLE autotest_api_after_script;
DROP TABLE autotest_api_pre_script;

-- 前置操作
CREATE TABLE autotest_action_pre(
        id VARCHAR(12) PRIMARY KEY,
        api_unit_id VARCHAR(32),
        name VARCHAR(32) NOT NULL,
        type VARCHAR(12) NOT NULL,
        enabled int,
        sort int
);
--  后置操作
CREATE TABLE autotest_action_after(
        id VARCHAR(12) PRIMARY KEY,
        api_unit_id VARCHAR(32),
        name VARCHAR(32) NOT NULL,
        type VARCHAR(12) NOT NULL,
        enabled int,
        sort int
);

