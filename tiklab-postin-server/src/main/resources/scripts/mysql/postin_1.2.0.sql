--  autotest_case_step_common 添加 parent_id 字段
ALTER TABLE autotest_case_step_common ADD COLUMN parent_id varchar(12);

ALTER TABLE autotest_case_step_instance_common ADD COLUMN parent_id varchar(12);

-- for循环
CREATE TABLE autotest_case_step_for(
        id VARCHAR(12) PRIMARY KEY,
        case_id VARCHAR(32),
        times int,
        condition Text
);