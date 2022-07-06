alter table apibox_testcase drop base_url;
alter table apibox_testcase drop request_type;
alter table apibox_testcase drop path;
alter table apibox_api_http rename column request_type to method_type;