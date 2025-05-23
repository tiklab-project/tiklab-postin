-- 删除 postin_http_mock_response_result
DELETE FROM postin_http_mock_response_result WHERE mock_id IN ('4dbfdd774c9c', '4dbfdd774c95');

-- 删除 postin_http_mock_response
DELETE FROM postin_http_mock_response WHERE mock_id IN ('4dbfdd774c9c', '4dbfdd774c95');

-- 删除 postin_http_mock_request_json
DELETE FROM postin_http_mock_request_json WHERE mock_id IN ('4dbfdd774c9c', '4dbfdd774c95');

-- 删除 postin_http_mock_request
DELETE FROM postin_http_mock_request WHERE id IN ('4dbfdd774c9c', '4dbfdd774c95');

-- 删除 postin_http_mock
DELETE FROM postin_http_mock WHERE id IN ('4dbfdd774c9c', '4dbfdd774c95');

-- 删除 postin_http_response
DELETE FROM postin_http_response WHERE http_id IN ('219512b6cb74', 'f483d630bafa');

-- 删除 postin_http_request_raw
DELETE FROM postin_api_request_raw WHERE api_id IN ('219512b6cb74', 'f483d630bafa');

-- 删除 postin_http_request
DELETE FROM postin_api_request WHERE api_id IN ('219512b6cb74', 'f483d630bafa');

-- 删除 postin_api_http
DELETE FROM postin_api_http WHERE id IN ('219512b6cb74', 'f483d630bafa');

-- 删除 postin_apix
DELETE FROM postin_apix WHERE id IN ('219512b6cb74', 'f483d630bafa');

-- 删除 postin_category
DELETE FROM postin_category WHERE id IN ('86e78329ea11', 'a8ead30da71c');

DELETE FROM postin_node WHERE id IN ('219512b6cb74', 'f483d630bafa', '86e78329ea11', 'a8ead30da71c');

UPDATE postin_workspace SET workspace_name = '演示空间' WHERE id = 'bd26c6ec5c6e';

INSERT INTO "postin_environment" ("id", "name", "url", "workspace_id", "create_time", "update_time") VALUES ('f25d692b439b', 'mock', 'http://localhost:9300/mockx/bd26c6ec5c6e', 'bd26c6ec5c6e', '2024-11-25 14:19:19.934', NULL);
INSERT INTO "postin_node" ("id", "workspace_id", "parent_id", "name", "type", "method_type", "tree_path", "sort", "create_user", "update_user", "create_time", "update_time") VALUES ('a8ead60da71c', 'bd26c6ec5c6e', NULL, '演示分组', 'category', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "postin_node" ("id", "workspace_id", "parent_id", "name", "type", "method_type", "tree_path", "sort", "create_user", "update_user", "create_time", "update_time") VALUES ('219512b6cb75', 'bd26c6ec5c6e', 'a8ead60da71c', ' 用户登录接口', 'http', 'post', NULL, 0, '111111', '111111', '2022-09-24 00:00:00', '2024-11-25 13:17:04.74');
INSERT INTO "postin_node" ("id", "workspace_id", "parent_id", "name", "type", "method_type", "tree_path", "sort", "create_user", "update_user", "create_time", "update_time") VALUES ('5ff68dd8d905', 'bd26c6ec5c6e', 'a8ead60da71c', '获取用户信息', 'http', 'get', NULL, NULL, '111111', '111111', '2024-11-25 13:22:12.525', '2024-11-25 13:22:12.525');
INSERT INTO "postin_node" ("id", "workspace_id", "parent_id", "name", "type", "method_type", "tree_path", "sort", "create_user", "update_user", "create_time", "update_time") VALUES ('6f461bb43d5d', 'bd26c6ec5c6e', 'a8ead60da71c', '获取产品列表', 'http', 'get', NULL, NULL, '111111', '111111', '2024-11-25 13:24:41.588', '2024-11-25 13:24:41.588');
INSERT INTO "postin_node" ("id", "workspace_id", "parent_id", "name", "type", "method_type", "tree_path", "sort", "create_user", "update_user", "create_time", "update_time") VALUES ('624499ffaac4', 'bd26c6ec5c6e', 'a8ead60da71c', '创建用户', 'http', 'post', NULL, NULL, '111111', '111111', '2024-11-25 13:33:35.353', '2024-11-25 13:33:35.353');

INSERT INTO "postin_category" ("id") VALUES ('a8ead60da71c');

INSERT INTO "postin_apix" ("id", "category_id", "protocol_type", "status_id", "executor_id", "description", "workspace_id", "version", "api_uid", "path") VALUES ('219512b6cb75', 'a8ead60da71c', 'http', 'developmentid', NULL, NULL, 'bd26c6ec5c6e', NULL, NULL, '/login');
INSERT INTO "postin_apix" ("id", "category_id", "protocol_type", "status_id", "executor_id", "description", "workspace_id", "version", "api_uid", "path") VALUES ('5ff68dd8d905', 'a8ead60da71c', 'http', 'developmentid', NULL, NULL, 'bd26c6ec5c6e', NULL, NULL, '/user');
INSERT INTO "postin_apix" ("id", "category_id", "protocol_type", "status_id", "executor_id", "description", "workspace_id", "version", "api_uid", "path") VALUES ('6f461bb43d5d', 'a8ead60da71c', 'http', 'developmentid', NULL, NULL, 'bd26c6ec5c6e', NULL, NULL, '/products');
INSERT INTO "postin_apix" ("id", "category_id", "protocol_type", "status_id", "executor_id", "description", "workspace_id", "version", "api_uid", "path") VALUES ('624499ffaac4', 'a8ead60da71c', 'http', 'developmentid', NULL, NULL, 'bd26c6ec5c6e', NULL, NULL, '/user/create');

INSERT INTO "postin_api_http" ("id", "apix_id", "method_type") VALUES ('219512b6cb75', '219512b6cb75', 'post');
INSERT INTO "postin_api_http" ("id", "apix_id", "method_type") VALUES ('5ff68dd8d905', '5ff68dd8d905', NULL);
INSERT INTO "postin_api_http" ("id", "apix_id", "method_type") VALUES ('6f461bb43d5d', '6f461bb43d5d', NULL);
INSERT INTO "postin_api_http" ("id", "apix_id", "method_type") VALUES ('624499ffaac4', '624499ffaac4', NULL);

INSERT INTO "postin_api_request" ("id", "api_id", "body_type", "pre_script", "after_script") VALUES ('219512b6cb75', '219512b6cb75', 'json', '', '');
INSERT INTO "postin_api_request" ("id", "api_id", "body_type", "pre_script", "after_script") VALUES ('5ff68dd8d905', '5ff68dd8d905', 'none', NULL, NULL);
INSERT INTO "postin_api_request" ("id", "api_id", "body_type", "pre_script", "after_script") VALUES ('6f461bb43d5d', '6f461bb43d5d', 'none', NULL, NULL);
INSERT INTO "postin_api_request" ("id", "api_id", "body_type", "pre_script", "after_script") VALUES ('624499ffaac4', '624499ffaac4', 'json', NULL, NULL);

INSERT INTO "postin_api_request_header" ("id", "api_id", "workspace_id", "header_name", "required", "description", "value", "sort") VALUES ('0a589baddce9', '219512b6cb75', NULL, 'auth', 1, NULL, 'authtokenid', NULL);

INSERT INTO "postin_api_request_query" ("id", "api_id", "param_name", "required", "description", "value", "sort") VALUES ('e4bc0c8a4ed3', '5ff68dd8d905', 'userId', 1, NULL, '@id', NULL);
INSERT INTO "postin_api_request_query" ("id", "api_id", "param_name", "required", "description", "value", "sort") VALUES ('7db475eb2428', '6f461bb43d5d', 'page', 1, NULL, '1', NULL);
INSERT INTO "postin_api_request_query" ("id", "api_id", "param_name", "required", "description", "value", "sort") VALUES ('3ff2e48b9a0c', '6f461bb43d5d', 'size', 1, NULL, '10', NULL);


INSERT INTO "postin_api_request_json" ("id", "api_id", "json_text", "description") VALUES ('624499ffaac4', '624499ffaac4', '{"type":"object","properties":{"name":{"type":"string","mock":{"mock":"@name"},"description":""},"email":{"type":"string","mock":{"mock":"@email"},"description":""}},"required":["name","email"],"description":""}', NULL);
INSERT INTO "postin_api_request_json" ("id", "api_id", "json_text", "description") VALUES ('219512b6cb75', '219512b6cb75', '{"type":"object","properties":{"username":{"type":"string","mock":{"mock":"@word"},"description":""},"password":{"type":"string","mock":{"mock":"@word"},"description":""}},"required":["username","password"],"description":""}', NULL);

INSERT INTO "postin_http_response" ("id", "http_id", "create_time", "http_code", "name", "data_type", "json_text", "raw_text") VALUES ('f58b93809da7', '5ff68dd8d905', '2024-11-25 13:22:12.481', 200, '成功', 'json', '{"type":"object","properties":{"status":{"type":"string","mock":{"mock":"success"},"description":""},"data":{"type":"object","properties":{"id":{"type":"string","mock":{"mock":"@id"},"description":""},"name":{"type":"string","mock":{"mock":"@name"},"description":""},"email":{"type":"string","mock":{"mock":"@email"},"description":""}},"required":["id","name","email"],"description":""}},"required":["status","data"],"description":""}', NULL);
INSERT INTO "postin_http_response" ("id", "http_id", "create_time", "http_code", "name", "data_type", "json_text", "raw_text") VALUES ('e9548b56ba2c', '6f461bb43d5d', '2024-11-25 13:24:41.534', 200, '成功', 'json', '{"type":"object","properties":{"status":{"type":"string","mock":{"mock":"success"},"description":"状态"},"data":{"type":"object","properties":{"total":{"type":"integer","mock":{"mock":"50"},"description":"总数"},"page":{"type":"integer","mock":{"mock":"1"},"description":"页数"},"size":{"type":"integer","mock":{"mock":"10"},"description":"单页数量"},"products":{"type":"array","properties":{"ITEMS":{"type":"object","properties":{"id":{"type":"string","mock":{"mock":"@id"},"description":""},"name":{"type":"string","mock":{"mock":"@name"},"description":""}},"required":["id","name"],"description":""}},"required":["ITEMS"],"description":"产品列表"}},"required":["total","page","size","products"],"description":""}},"required":["status","data"],"description":""}', NULL);
INSERT INTO "postin_http_response" ("id", "http_id", "create_time", "http_code", "name", "data_type", "json_text", "raw_text") VALUES ('c00ff672f992', '624499ffaac4', '2024-11-25 13:33:35.321', 200, '成功', 'json', '{"type":"object","properties":{"status":{"type":"string","mock":{"mock":"success"},"description":""},"data":{"type":"object","properties":{"id":{"type":"string","mock":{"mock":"@id"},"description":""},"name":{"type":"string","mock":{"mock":"@name"},"description":""},"email":{"type":"string","mock":{"mock":"@email"},"description":""}},"required":["id","name","email"],"description":""}},"required":["status","data"],"description":""}', NULL);
INSERT INTO "postin_http_response" ("id", "http_id", "create_time", "http_code", "name", "data_type", "json_text", "raw_text") VALUES ('219512b6cb78', '219512b6cb75', '2022-12-08 13:41:04', 200, '成功', 'json', '{"type":"object","properties":{"status":{"type":"string","mock":{"mock":"success"}},"data":{"type":"object","properties":{"userId":{"type":"string","mock":{"mock":"@id"},"description":""},"name":{"type":"string","mock":{"mock":"@name"},"description":""}},"required":["userId","name"],"description":""}},"required":["status","data"],"description":""}', NULL);
