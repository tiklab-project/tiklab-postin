alter table apibox_request_header_testcase rename column testcase_id to http_case_id;
alter table apibox_query_param_testcase rename column testcase_id to http_case_id;
alter table apibox_request_body_testcase rename column testcase_id to http_case_id;
alter table apibox_form_param_testcase rename column testcase_id to http_case_id;
alter table apibox_form_urlencoded_testcase rename column testcase_id to http_case_id;
alter table apibox_json_param_testcase rename column testcase_id to http_case_id;
alter table apibox_raw_param_testcase rename column testcase_id to http_case_id;
alter table apibox_binary_param_testcase rename column testcase_id to http_case_id;
alter table apibox_pre_script_testcase rename column testcase_id to http_case_id;
alter table apibox_after_script_testcase rename column testcase_id to http_case_id;
alter table apibox_assert_testcase rename column testcase_id to http_case_id;
alter table apibox_test_instance rename column testcase_id to http_case_id;