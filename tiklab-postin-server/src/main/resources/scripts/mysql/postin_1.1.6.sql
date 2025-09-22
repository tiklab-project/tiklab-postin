
CREATE TABLE autotest_agent_config (
  id varchar(32) NOT NULL,
  name varchar(64),
  address varchar(256),
  status varchar(32),
  update_time timestamp,
  enable int
);

CREATE TABLE autotest_api_after_script (
  id varchar(32)  NOT NULL,
  api_unit_id varchar(32)  NOT NULL,
  script varchar(2048) 
);

CREATE TABLE autotest_api_assert (
  id varchar(32)  NOT NULL,
  api_unit_id varchar(32)  NOT NULL,
  source int,
  property_name varchar(64) ,
  data_type varchar(32) ,
  value varchar(128)  NOT NULL,
  sort int,
  comparator int
);

CREATE TABLE autotest_api_assert_instance (
  id varchar(32)  NOT NULL,
  instance_id varchar(32)  NOT NULL,
  source int,
  value varchar(128) ,
  result int,
  actual_result varchar(256) ,
  property_name varchar(256) ,
  comparator int
);

CREATE TABLE autotest_api_form (
  id varchar(32)  NOT NULL,
  api_unit_id varchar(32)  NOT NULL,
  param_name varchar(64)  NOT NULL,
  data_type varchar(32)  NOT NULL,
  description varchar(128) ,
  value varchar(128) ,
  sort int
);

CREATE TABLE autotest_api_form_urlencoded (
  id varchar(32)  NOT NULL,
  api_unit_id varchar(32)  NOT NULL,
  param_name varchar(64)  NOT NULL,
  data_type varchar(32)  NOT NULL,
  description varchar(128) ,
  value varchar(128) ,
  sort int
);

CREATE TABLE autotest_api_json (
  id varchar(32)  NOT NULL,
  api_unit_id varchar(32) ,
  schema_text varchar(2048) 
);

CREATE TABLE autotest_api_perf_instance (
  id varchar(32)  NOT NULL,
  api_perf_id varchar(32) ,
  pass_rate varchar(8) ,
  error_rate varchar(8) ,
  pass_num int,
  fail_num int,
  total int,
  create_time timestamp,
  status varchar(32) ,
  elapsed_time int
);

CREATE TABLE autotest_api_perf_step (
  id varchar(32)  NOT NULL,
  case_id varchar(32) ,
  api_perf_id varchar(32) ,
  create_time timestamp,
  thread_count int,
  execute_type int,
  execute_count int,
  case_type varchar(32) ,
  time_type varchar(12) ,
  time_count int
);

CREATE TABLE autotest_api_perf_step_instance (
  id varchar(32)  NOT NULL,
  api_perf_instance_id varchar(255) ,
  total_requests int,
  successful_requests int,
  failed_requests int,
  avg_elapsed_time float8,
  max_elapsed_time float8,
  min_elapsed_time float8,
  total_elapsed_time float8,
  tps float8,
  error_rate float8,
  percentile90 float8,
  percentile95 float8,
  percentile99 float8
);

CREATE TABLE autotest_api_perf_step_unit_instance (
  id varchar(32)  NOT NULL,
  api_perf_step_instance_id varchar(32) ,
  name varchar(256) ,
  total_requests int,
  successful_requests int,
  failed_requests int,
  avg_elapsed_time float8,
  max_elapsed_time float8,
  min_elapsed_time float8,
  total_elapsed_time float8,
  tps float8,
  error_rate float8,
  percentile90 float8,
  percentile95 float8,
  percentile99 float8
);

CREATE TABLE autotest_api_perf_testdata (
  id varchar(32)  NOT NULL,
  name varchar(64) ,
  case_id varchar(32) ,
  test_data text ,
  create_time timestamp,
  type varchar(8) 
);

CREATE TABLE autotest_api_perfcase (
  id varchar(32)  NOT NULL,
  testcase_id varchar(32) ,
  bind_case_id varchar(32) ,
  thread_count int,
  execute_type int,
  execute_count int,
  time_type varchar(12) ,
  time_count int
);

CREATE TABLE autotest_api_pre_script (
  id varchar(32)  NOT NULL,
  api_unit_id varchar(32)  NOT NULL,
  script varchar(2048) 
);

CREATE TABLE autotest_api_query (
  id varchar(32)  NOT NULL,
  api_unit_id varchar(32)  NOT NULL,
  param_name varchar(64)  NOT NULL,
  description varchar(128) ,
  value varchar(128) ,
  sort int
);

CREATE TABLE autotest_api_raw (
  id varchar(32)  NOT NULL,
  api_unit_id varchar(32)  NOT NULL,
  raw varchar(2048)  NOT NULL,
  type varchar(32)  NOT NULL
);

CREATE TABLE autotest_api_request_body (
  id varchar(32) ,
  api_unit_id varchar(32) ,
  body_type varchar(32) 
);

CREATE TABLE autotest_api_request_header (
  id varchar(32)  NOT NULL,
  api_unit_id varchar(32)  NOT NULL,
  header_name varchar(64)  NOT NULL,
  required int NOT NULL,
  description varchar(128) ,
  value varchar(128) ,
  sort int
);

CREATE TABLE autotest_api_request_instance (
  id varchar(32)  NOT NULL,
  api_unit_instance_id varchar(32) ,
  request_url text ,
  request_type varchar(32) ,
  request_header text ,
  request_param text 
);

CREATE TABLE autotest_api_response_body (
  id varchar(32)  NOT NULL,
  api_unit_id varchar(32) ,
  http_code int,
  create_time timestamp,
  name varchar(64) ,
  data_type varchar(32) ,
  json_text text ,
  raw_text text 
);

CREATE TABLE autotest_api_response_header (
  id varchar(32)  NOT NULL,
  api_unit_id varchar(32)  NOT NULL,
  header_name varchar(64)  NOT NULL,
  required int NOT NULL,
  description varchar(128) ,
  value varchar(128) ,
  sort int
);

CREATE TABLE autotest_api_response_instance (
  id varchar(32)  NOT NULL,
  api_unit_instance_id varchar(32) ,
  response_header text ,
  response_body text 
);

CREATE TABLE autotest_api_response_json (
  id varchar(32)  NOT NULL,
  api_unit_id varchar(32)  NOT NULL,
  property_name varchar(64)  NOT NULL,
  data_type varchar(32)  NOT NULL,
  required int NOT NULL,
  description varchar(128) ,
  value varchar(128) ,
  sort int,
  parent_id varchar(32) 
);

CREATE TABLE autotest_api_response_raw (
  id varchar(32)  NOT NULL,
  api_unit_id varchar(32)  NOT NULL,
  raw varchar(2048)  NOT NULL,
  type varchar(32)  NOT NULL
);

CREATE TABLE autotest_api_scene (
  id varchar(32)  NOT NULL,
  testcase_id varchar(32) 
);

CREATE TABLE autotest_api_scene_instance (
  id varchar(32)  NOT NULL,
  api_scene_id varchar(32) ,
  result varchar(8) ,
  execute_number int,
  test_number int,
  pass_number int,
  fail_number int,
  pass_rate varchar(32) ,
  elapsed_time int,
  create_user varchar(32) ,
  create_time timestamp
);

CREATE TABLE autotest_api_scene_step (
  id varchar(32)  NOT NULL,
  api_scene_id varchar(32) ,
  api_unit_id varchar(32) 
);

CREATE TABLE autotest_api_scene_step_instance_bind (
  id varchar(32)  NOT NULL,
  api_scene_instance_id varchar(32) ,
  api_unit_instance_id varchar(32) 
);

CREATE TABLE autotest_api_unit (
  id varchar(32)  NOT NULL,
  testcase_id varchar(32) ,
  path varchar(256)  NOT NULL,
  method_type varchar(64) 
);

CREATE TABLE autotest_api_unit_instance (
  id varchar(32)  NOT NULL,
  status_code varchar(32) ,
  api_unit_id varchar(32) ,
  result int,
  execute_number int,
  err_message varchar(2048) ,
  elapsed_time float8,
  create_user varchar(32) ,
  create_time timestamp
);

CREATE TABLE autotest_api_unit_instance_bind (
  id varchar(32)  NOT NULL,
  api_unit_id varchar(32) ,
  api_unit_instance_id varchar(32) ,
  create_time timestamp
);

CREATE TABLE autotest_assert_step_common (
  id varchar(32)  NOT NULL,
  step_id varchar(32) ,
  type varchar(12) ,
  create_time timestamp
);

CREATE TABLE autotest_assert_variable (
  id varchar(32) ,
  assert_id varchar(32) ,
  variable varchar(128) ,
  compare int,
  expect varchar(128) 
);

CREATE TABLE autotest_case_step_common (
  id varchar(32)  NOT NULL,
  case_id varchar(32) ,
  create_time timestamp,
  type varchar(32) ,
  sort int
);

CREATE TABLE autotest_case_step_if (
  id varchar(32)  NOT NULL,
  case_id varchar(32) ,
  relation varchar(8) 
);

CREATE TABLE autotest_case_step_if_variable (
  id varchar(32)  NOT NULL,
  step_id varchar(32) ,
  variable varchar(128) ,
  compare int,
  expect varchar(128) 
);

CREATE TABLE autotest_case_step_instance_common (
  id varchar(32)  NOT NULL,
  instance_id varchar(32) ,
  sort int,
  type varchar(32) ,
  result int
);

CREATE TABLE autotest_case_step_instance_if (
  id varchar(32)  NOT NULL,
  step_instance_id varchar(32) ,
  relation varchar(12) 
);

CREATE TABLE autotest_case_step_instance_if_variable (
  id varchar(32)  NOT NULL,
  step_instance_id varchar(32) ,
  variable varchar(128) ,
  compare int,
  expect varchar(128) ,
  result int
);

CREATE TABLE autotest_category (
  id varchar(32)  NOT NULL,
  name varchar(64)  NOT NULL,
  workspace_id varchar(32) ,
  parent_category_id varchar(32) ,
  sort int,
  description varchar(64) 
);

CREATE TABLE autotest_instance (
  id varchar(32)  NOT NULL,
  name varchar(256),
  type varchar(64)  NOT NULL,
  workspace_id varchar(32),
  content text ,
  create_time timestamp,
  create_user varchar(32) ,
  execute_number int,
  status varchar(8),
  test_plan_id varchar(32),
  case_id varchar(32)
);


CREATE TABLE autotest_testcase (
  id varchar(32)  NOT NULL,
  name varchar(64)  NOT NULL,
  category_id varchar(32),
  workspace_id varchar(32),
  api_id varchar(32),
  case_type varchar(32),
  create_user varchar(32),
  update_user varchar(32),
  create_time timestamp,
  update_time timestamp,
  description varchar(64),
  sort int,
  director varchar(32),
  priority_level int,
  status int,
  case_key varchar(32)
);


-- 计划任务
CREATE TABLE autotest_plan_quartz (
  id varchar(32)  NOT NULL,
  test_plan_id varchar(32) ,
  exe_type int,
  create_time timestamp,
  state int
);

CREATE TABLE autotest_plan_quartz_time (
  id varchar(32)  NOT NULL,
  quartz_plan_id varchar(32) ,
  cron varchar(128) ,
  exe_type int,
  time varchar(32) ,
  week int
);

CREATE TABLE autotest_test_plan (
  id varchar(32)  NOT NULL,
  name varchar(32) ,
  start_time timestamp,
  end_time timestamp,
  state int,
  principals varchar(32) ,
  workspace_id varchar(32) ,
  description varchar(64) ,
  create_time timestamp,
  update_time timestamp,
  sort int,
  api_env_id varchar(32)
);

CREATE TABLE autotest_test_plan_case_instance_bind (
  id varchar(32)  NOT NULL,
  test_plan_instance_id varchar(32) ,
  case_instance_id varchar(32) ,
  name varchar(128) ,
  test_type varchar(32) ,
  case_type varchar(32) ,
  result int,
  elapsed_time int,
  case_id varchar(32) ,
  test_plan_id varchar(32) 
);

CREATE TABLE autotest_test_plan_detail (
  id varchar(32)  NOT NULL,
  test_plan_id varchar(32) ,
  test_case_id varchar(32) ,
  status int,
  sort int
);

CREATE TABLE autotest_test_plan_instance (
  id varchar(32)  NOT NULL,
  test_plan_id varchar(32) ,
  workspace_id varchar(32) ,
  result int,
  total int,
  pass_num int,
  fail_num int,
  pass_rate varchar(8) ,
  error_rate varchar(8) ,
  create_time timestamp,
  create_user varchar(128) ,
  executable_case_num int,
  status int DEFAULT 0
);

CREATE TABLE autotest_test_report (
  id varchar(12)  NOT NULL,
  workspace_id varchar(32) ,
  name varchar(64) ,
  start_time timestamp,
  end_time timestamp,
  create_user varchar(32) ,
  description varchar(256) ,
  create_time timestamp,
  test_plan_id varchar(32) 
);




-- autotest_agent_config
INSERT INTO  autotest_agent_config  ( id ,  name ,  address ,  status ,  update_time ,  enable ) VALUES ('agent-default_localhost', 'agent-default','localhost', 'online',  null, 1);

