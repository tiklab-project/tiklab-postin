INSERT INTO `postin_workspace` VALUES ('bd26c6ec5c6e12fd1082772362e096a8', '默认空间', NULL, '111111');

INSERT INTO `postin_environment` VALUES ('f25d692b439b0d714a7c9d26182eb0fb', 'test', 'http://postin-ce.tiklab.net', '2022-7-28 06:07:28', NULL);

INSERT INTO `postin_category` (`id`, `name`, `workspace_id`, `parent_category_id`) VALUES ('86e78329ea1145309b1b207ca4504cbb', 'test', 'bd26c6ec5c6e12fd1082772362e096a8', NULL);
INSERT INTO `postin_category` (`id`, `name`, `workspace_id`, `parent_category_id`) VALUES ('a8ead30da71c493789e1a7354164f4ec', '默认分组', 'bd26c6ec5c6e12fd1082772362e096a8', NULL);

INSERT INTO `postin_apix` (`id`, `category_id`, `name`, `protocol_type`, `create_user`, `update_user`, `create_time`, `update_time`, `status_id`, `executor_id`, `description`, `workspace_id`, `version`, `api_uid`) VALUES ('219512b6cb7446d0adf661607561e9a5', 'a8ead30da71c493789e1a7354164f4ec', 'name', 'http', '111111', '111111', '2022-9-24 09:17:01', '2022-9-24 09:20:28', 'developmentid', NULL, NULL, 'bb2a25071239f2323358be55844ae10b', NULL, NULL);
INSERT INTO `postin_apix` (`id`, `category_id`, `name`, `protocol_type`, `create_user`, `update_user`, `create_time`, `update_time`, `status_id`, `executor_id`, `description`, `workspace_id`, `version`, `api_uid`) VALUES ('f483d630bafa46e781c14491fad1881f', 'a8ead30da71c493789e1a7354164f4ec', 'test', 'http', '111111', NULL, '2022-9-19 07:36:53', NULL, 'developmentid', NULL, NULL, 'bb2a25071239f2323358be55844ae10b', NULL, NULL);

INSERT INTO `postin_api_http` VALUES ('219512b6cb7446d0adf661607561e9a5', '219512b6cb7446d0adf661607561e9a5', 'post', '/iam/passport/member/login');
INSERT INTO `postin_api_http` VALUES ('f483d630bafa46e781c14491fad1881f', 'f483d630bafa46e781c14491fad1881f', 'post', '/passport/login');

INSERT INTO `postin_http_request` (`id`, `http_id`, `body_type`, `pre_script`, `after_script`) VALUES ('219512b6cb7446d0adf661607561e9a5', '219512b6cb7446d0adf661607561e9a5', 'raw', '', '');
INSERT INTO `postin_http_request` (`id`, `http_id`, `body_type`, `pre_script`, `after_script`) VALUES ('f483d630bafa46e781c14491fad1881f', 'f483d630bafa46e781c14491fad1881f', 'raw', '', '');

INSERT INTO `postin_http_request_raw` VALUES ('f483d630bafa46e781c14491fad1881f', 'f483d630bafa46e781c14491fad1881f', '{\n	\"account\": \"admin\",\n	\"password\": \"12345\",\n	\"userType\": \"1\"\n}', 'application/json');
INSERT INTO `postin_http_request_raw` VALUES ('219512b6cb7446d0adf661607561e9a5', '219512b6cb7446d0adf661607561e9a5', '{\"account\":\"18783894551\",\"password\":\"123456\"}', 'application/json');

INSERT INTO `postin_http_response` VALUES ('219512b6cb7446d0adf661607561e9a5', '219512b6cb7446d0adf661607561e9a5', 'json');

INSERT INTO `postin_http_case` VALUES ('05d69a6cf4a90ff3060d871c190445fd', 'f483d630bafa46e781c14491fad1881f', '成功');
INSERT INTO `postin_http_case` VALUES ('3423f288df5dbf271c8f8533af51df86', '219512b6cb7446d0adf661607561e9a5', '成功');

INSERT INTO `postin_http_case_request` VALUES ('05d69a6cf4a90ff3060d871c190445fd', '05d69a6cf4a90ff3060d871c190445fd', 'raw', '', '');
INSERT INTO `postin_http_case_request` VALUES ('3423f288df5dbf271c8f8533af51df86', '3423f288df5dbf271c8f8533af51df86', 'raw', NULL, NULL);

INSERT INTO `postin_http_case_request_raw` VALUES ('05d69a6cf4a90ff3060d871c190445fd', '05d69a6cf4a90ff3060d871c190445fd', '{\n	\"account\": \"admin\",\n	\"password\": \"12345\",\n	\"userType\": \"1\"\n}', 'application/json');
INSERT INTO `postin_http_case_request_raw` VALUES ('3423f288df5dbf271c8f8533af51df86', '3423f288df5dbf271c8f8533af51df86', '{\"account\":\"18783894551\",\"password\":\"123456\"}', 'application/json');

INSERT INTO `postin_http_mock` VALUES ('4dbfdd774c9cd245baf2e1ead23a6a36', '219512b6cb7446d0adf661607561e9a5', '登录失败', NULL, '111111', '2022-7-28 00:00:00', 1);
INSERT INTO `postin_http_mock_request` (`id`, `mock_id`, `body_type`) VALUES ('4dbfdd774c9cd245baf2e1ead23a6a36', '4dbfdd774c9cd245baf2e1ead23a6a36', 'json');
INSERT INTO `postin_http_mock_request_json` VALUES ('9a24d52f1e82d5059c050bc7d418dea3', '4dbfdd774c9cd245baf2e1ead23a6a36', 'password', '123', 0);
INSERT INTO `postin_http_mock_response` (`id`, `mock_id`, `http_code`, `body_type`) VALUES ('4dbfdd774c9cd245baf2e1ead23a6a36', '4dbfdd774c9cd245baf2e1ead23a6a36', '400','json');
INSERT INTO `postin_http_mock_response_result` VALUES ('4dbfdd774c9cd245baf2e1ead23a6a36', '4dbfdd774c9cd245baf2e1ead23a6a36', '{\n \"status\":\"error”\n}');


INSERT INTO `postin_api_status` values     ("publishid","green","已发布","system",null),
                                            ("designId","volcano","设计中","system",null),
                                            ("developmentid","orange","开发中","system",null),
                                            ("testid","cyan","测试","system",null),
                                            ("completeid","lime","完成","system",null),
                                            ("maintainid","red","维护","system",null);
