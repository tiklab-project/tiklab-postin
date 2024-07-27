INSERT INTO  postin_workspace  ( id ,  workspace_name ,  description ,  user_id ,  visibility ,  icon_url ) VALUES ('bd26c6ec5c6e', '默认空间', NULL, '111111', 1, '/images/pi1.png');

-- INSERT INTO  postin_environment  VALUES ('f25d692b439b', 'test', 'http://postin-ce.thoughtware.cn', '2022-7-28 06:07:28', NULL, 'bd26c6ec5c6e');

INSERT INTO  postin_category  ( id ,  name ,  workspace_id ,  parent_category_id ) VALUES ('86e78329ea11', 'test', 'bd26c6ec5c6e', NULL);
INSERT INTO  postin_category  ( id ,  name ,  workspace_id ,  parent_category_id ) VALUES ('a8ead30da71c', '默认分组', 'bd26c6ec5c6e', NULL);

INSERT INTO  postin_apix  ( id ,  category_id ,  name ,  protocol_type ,  method_type ,  create_user ,  update_user ,  create_time ,  update_time ,  status_id ,  executor_id ,  description ,  workspace_id ,  version ,  api_uid ) VALUES ('219512b6cb74', 'a8ead30da71c', 'name', 'http', 'post', '111111', '111111', '2022-09-24 09:17:01', '2022-09-24 09:20:28', 'developmentid', NULL, NULL, 'bd26c6ec5c6e', NULL, NULL);
INSERT INTO  postin_apix  ( id ,  category_id ,  name ,  protocol_type ,  method_type ,  create_user ,  update_user ,  create_time ,  update_time ,  status_id ,  executor_id ,  description ,  workspace_id ,  version ,  api_uid ) VALUES ('f483d630bafa', 'a8ead30da71c', 'test', 'http', 'post', '111111', NULL, '2022-09-19 07:36:53', NULL, 'developmentid', NULL, NULL, 'bd26c6ec5c6e', NULL, NULL);

INSERT INTO  postin_api_http  VALUES ('219512b6cb74', '219512b6cb74', 'post', '/iam/passport/member/login');
INSERT INTO  postin_api_http  VALUES ('f483d630bafa', 'f483d630bafa', 'post', '/passport/login');

INSERT INTO  postin_http_request  ( id ,  http_id ,  body_type ,  pre_script ,  after_script ) VALUES ('219512b6cb74', '219512b6cb74', 'raw', '', '');
INSERT INTO  postin_http_request  ( id ,  http_id ,  body_type ,  pre_script ,  after_script ) VALUES ('f483d630bafa', 'f483d630bafa', 'raw', '', '');

INSERT INTO  postin_http_request_raw  VALUES ('219512b6cb74', '219512b6cb74', '{"account":"18783894551","password":"123456"}', 'application/json');
INSERT INTO  postin_http_request_raw  VALUES ('f483d630bafa', 'f483d630bafa', '{"account": "admin","password": "12345","userType": "1"}', 'application/json');

INSERT INTO  postin_http_response  ( id ,  http_id ,  json_text ,  http_code ,  name ,  data_type ,  raw_text ,  create_time ) VALUES ('219512b6cb78', '219512b6cb74', '{"type":"object","title":"title","properties":{"field_0_277":{"type":"boolean","properties":{}}}}', 200, '成功', 'json', NULL, '2022-12-08 13:41:04');
INSERT INTO  postin_http_response  ( id ,  http_id ,  json_text ,  http_code ,  name ,  data_type ,  raw_text ,  create_time ) VALUES ('219512b6cb76', 'f483d630bafa', '{"type":"object","title":"title","properties":{"field_0_277":{"type":"boolean","properties":{}}}}', 200, '成功', 'json', NULL, '2022-12-08 13:41:04');


INSERT INTO  postin_http_mock  VALUES ('4dbfdd774c9c', '219512b6cb74', '登录失败', NULL, '111111', '2022-7-28 00:00:00', 1);
INSERT INTO  postin_http_mock_request  ( id ,  mock_id ,  body_type ) VALUES ('4dbfdd774c9c', '4dbfdd774c9c', 'json');
INSERT INTO  postin_http_mock_request_json  VALUES ('9a24d52f1e82', '4dbfdd774c9c', 'password', '123', 0);
INSERT INTO  postin_http_mock_response  ( id ,  mock_id ,  http_code ,  body_type ) VALUES ('4dbfdd774c9c', '4dbfdd774c9c', '400','json');
INSERT INTO  postin_http_mock_response_result  VALUES ('4dbfdd774c9c', '4dbfdd774c9c', '{"status":"error"}');

INSERT INTO  postin_http_mock  VALUES ('4dbfdd774c95', 'f483d630bafa', '登录失败', NULL, '111111', '2022-7-28 00:00:00', 1);
INSERT INTO  postin_http_mock_request  ( id ,  mock_id ,  body_type ) VALUES ('4dbfdd774c95', '4dbfdd774c95', 'json');
INSERT INTO  postin_http_mock_request_json  VALUES ('9624d52f1e82', '4dbfdd774c95', 'password', '123', 0);
INSERT INTO  postin_http_mock_response  ( id ,  mock_id ,  http_code ,  body_type ) VALUES ('4dbfdd774c95', '4dbfdd774c95', '400','json');
INSERT INTO  postin_http_mock_response_result  VALUES ('4dbfdd774c95', '4dbfdd774c95', '{"status":"error"}');

INSERT INTO  postin_api_status  ( id ,  color ,  name ,  type ,  workspace_id ) VALUES ('publishid', 'green', '已发布', 'system', NULL);
INSERT INTO  postin_api_status  ( id ,  color ,  name ,  type ,  workspace_id ) VALUES ('designId', 'purple', '设计中', 'system', NULL);
INSERT INTO  postin_api_status  ( id ,  color ,  name ,  type ,  workspace_id ) VALUES ('developmentid', 'orange', '开发中', 'system', NULL);
INSERT INTO  postin_api_status  ( id ,  color ,  name ,  type ,  workspace_id ) VALUES ('testid', 'cyan', '测试', 'system', NULL);
INSERT INTO  postin_api_status  ( id ,  color ,  name ,  type ,  workspace_id ) VALUES ('completeid', 'lime', '完成', 'system', NULL);
INSERT INTO  postin_api_status  ( id ,  color ,  name ,  type ,  workspace_id ) VALUES ('maintainid', 'red', '维护', 'system', NULL);



