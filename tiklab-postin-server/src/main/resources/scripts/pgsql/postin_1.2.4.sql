--  autotest_case_step_common 添加 name 字段
ALTER TABLE autotest_case_step_common ADD COLUMN name varchar(128);
ALTER TABLE autotest_case_step_instance_common ADD COLUMN name varchar(128);

