alter table apibox_test_instance drop testNo;
alter table apibox_test_instance drop request_type;
alter table apibox_test_instance rename column statusCode to status_code;
alter table apibox_test_instance add  user_id VARCHAR(32) NOT NULL;
alter table apibox_test_instance add  time int;
alter table apibox_test_instance add  size int;


alter table apibox_request_instance rename column instance_id to http_instance_id;
alter table apibox_request_instance rename column request_base to URL;
alter table apibox_request_instance rename column request_header to headers;
alter table apibox_request_instance rename column request_param to body;
alter table apibox_request_instance add  method_type VARCHAR(32);
alter table apibox_request_instance add  media_type VARCHAR(32);
alter table apibox_request_instance add  pre_script VARCHAR(2048);
alter table apibox_request_instance add  after_script VARCHAR(2048);


alter table apibox_response_instance rename column instance_id to http_instance_id;
alter table apibox_response_instance rename column response_header to headers;
alter table apibox_response_instance rename column response_body to body;


alter table apibox_assert_instance rename column instance_id to http_instance_id;
