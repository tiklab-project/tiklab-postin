--  postin_workspace 添加 create_time 字段
ALTER TABLE postin_workspace ADD COLUMN create_time TIMESTAMP;
-- 为历史数据设置默认值（当前时间）
UPDATE postin_workspace SET create_time = '2025-01-01 00:00:00' WHERE create_time IS NULL;

