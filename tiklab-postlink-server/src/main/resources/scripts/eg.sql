INSERT INTO `postlink_workspace` VALUES ('bd26c6ec5c6e12fd1082772362e096a8', '默认空间', NULL, '111111');

INSERT INTO `postlink_environment` VALUES ('f25d692b439b0d714a7c9d26182eb0fb', 'test', 'http://postlink-ce.tiklab.net', NULL, '2022-7-28 06:07:28', NULL);

INSERT INTO `postlink_category` VALUES ('7bf98c20d8af4218a99f07a7c5ec80c6', '默认分组', 'bd26c6ec5c6e12fd1082772362e096a8', NULL, NULL);

INSERT INTO `postlink_apix` VALUES ('cb32ea06b30b43f8a7354c0caf6b10f4', '7bf98c20d8af4218a99f07a7c5ec80c6', 'login', 'http', NULL, NULL, '111111', NULL, NULL, NULL, 'developmentid', NULL, NULL, 'bd26c6ec5c6e12fd1082772362e096a8', NULL, NULL);

INSERT INTO `postlink_api_http` VALUES ('cb32ea06b30b43f8a7354c0caf6b10f4', 'cb32ea06b30b43f8a7354c0caf6b10f4', 'post', '/passport/login');

INSERT INTO `postlink_request_body` VALUES ('cb32ea06b30b43f8a7354c0caf6b10f4', 'cb32ea06b30b43f8a7354c0caf6b10f4', 'raw');

INSERT INTO `postlink_raw_param` VALUES ('cb32ea06b30b43f8a7354c0caf6b10f4', 'cb32ea06b30b43f8a7354c0caf6b10f4', '{\n	\"account\": \"admin\",\n	\"password\": \"123456\",\n	\"userType\": \"1\"\n}', 'application/json');



INSERT INTO `postlink_testcase` VALUES ('b5a11dffac33b019e4fc3df942988e99', 'cb32ea06b30b43f8a7354c0caf6b10f4', '成功登录');

INSERT INTO `postlink_request_body_testcase` VALUES ('b5a11dffac33b019e4fc3df942988e99', 'b5a11dffac33b019e4fc3df942988e99', 'raw');

INSERT INTO `postlink_raw_param_testcase` VALUES ('b5a11dffac33b019e4fc3df942988e99', 'b5a11dffac33b019e4fc3df942988e99', '{\n	\"account\": \"admin\",\n	\"password\": \"123456\",\n	\"userType\": \"1\"\n}', 'application/json');


INSERT INTO `postlink_mock` VALUES ('4dbfdd774c9cd245baf2e1ead23a6a36', 'cb32ea06b30b43f8a7354c0caf6b10f4', '登录失败', NULL, '111111', '2022-7-28 00:00:00', 1);
INSERT INTO `postlink_request_body_mock` VALUES ('4dbfdd774c9cd245baf2e1ead23a6a36', '4dbfdd774c9cd245baf2e1ead23a6a36', 'json');
INSERT INTO `postlink_json_param_mock` VALUES ('9a24d52f1e82d5059c050bc7d418dea3', '4dbfdd774c9cd245baf2e1ead23a6a36', 'password', '123', 0);
INSERT INTO `postlink_response_mock` VALUES ('4dbfdd774c9cd245baf2e1ead23a6a36', '4dbfdd774c9cd245baf2e1ead23a6a36', '400');
INSERT INTO `postlink_response_result_mock` VALUES ('4dbfdd774c9cd245baf2e1ead23a6a36', '4dbfdd774c9cd245baf2e1ead23a6a36', 'json');
INSERT INTO `postlink_json_response_mock` VALUES ('4dbfdd774c9cd245baf2e1ead23a6a36', '4dbfdd774c9cd245baf2e1ead23a6a36', '{\n \"status\":\"error”\n}');
