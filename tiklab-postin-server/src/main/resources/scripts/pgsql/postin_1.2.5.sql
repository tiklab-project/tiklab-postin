--  autotest_api_perfcase 添加字段
ALTER TABLE autotest_api_perfcase ADD COLUMN ramp_up int;
ALTER TABLE autotest_api_perfcase ADD COLUMN step int;

update autotest_api_perfcase set ramp_up=0;
update autotest_api_perfcase set step=0;

CREATE TABLE autotest_api_perf_instance_report (
  id varchar(12)  NOT NULL,
  api_perf_instance_id varchar(32) ,
  timestamp TIMESTAMP,
  thread_count int,
  tps float8,
  avg_elapsed_time float8,
  error_rate varchar(8)
);