INSERT INTO `apibox_workspace` VALUES ('bd26c6ec5c6e12fd1082772362e096a8', 'apibox', NULL, '111111');

INSERT INTO `apibox_environment` VALUES ('f25d692b439b0d714a7c9d26182eb0fb', 'test', 'http://apibox-ce.doublekit.net', NULL, '2022-7-28 06:07:28', NULL);

INSERT INTO `apibox_category` VALUES ('7bf98c20d8af4218a99f07a7c5ec80c6', '默认分组', 'bd26c6ec5c6e12fd1082772362e096a8', NULL, NULL);

INSERT INTO `apibox_apix` VALUES ('cb32ea06b30b43f8a7354c0caf6b10f4', '7bf98c20d8af4218a99f07a7c5ec80c6', 'name', 'http', NULL, NULL, '111111', NULL, '2022-7-28 06:06:32', NULL, 'developmentid', NULL, NULL, 'bd26c6ec5c6e12fd1082772362e096a8', NULL, NULL);

INSERT INTO `apibox_api_http` VALUES ('cb32ea06b30b43f8a7354c0caf6b10f4', 'cb32ea06b30b43f8a7354c0caf6b10f4', 'post', '/passport/login');

INSERT INTO `apibox_request_body` VALUES ('cb32ea06b30b43f8a7354c0caf6b10f4', 'cb32ea06b30b43f8a7354c0caf6b10f4', 'raw');

INSERT INTO `apibox_raw_param` VALUES ('cb32ea06b30b43f8a7354c0caf6b10f4', 'cb32ea06b30b43f8a7354c0caf6b10f4', '{\n	\"account\": \"admin\",\n	\"password\": \"123456\",\n	\"userType\": \"1\"\n}', 'application/json');
