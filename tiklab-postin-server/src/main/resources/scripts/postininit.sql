
INSERT INTO `pcs_prc_role` VALUES ('3b4eee0e8752fe8299e4f1df080c01a8', '默认角色', NULL, 'custom', '1', 'postin');

INSERT INTO `pcs_prc_role_user` VALUES ('4e1fe7b1dc57e35acf27bb223abd08ac', '3b4eee0e8752fe8299e4f1df080c01a8', '111111', 'postin');

INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`,`bgroup`) VALUES ('12b085bb37913b5f7feb32da5ed260bb', '角色管理', 'SysRoleSystem', '6d584487ba058a0bf02713635a84807d', 1, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`,`bgroup`) VALUES ('136c10de265146b40621babdb88fdcca', '消息模板管理', 'MessageTemplate', '305864b7559f0a81b500dc93521cab07', 2, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`,`bgroup`) VALUES ('2eca8165ccd6a0c33d1bd411662617ef', '消息类型管理', 'SysMessageType', '305864b7559f0a81b500dc93521cab07', 3, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`,`bgroup`) VALUES ('305864b7559f0a81b500dc93521cab07', '消息中心', 'MessageCenter', NULL, 4, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`,`bgroup`) VALUES ('45cc48e39fb855de5923cf5d735247c8', '功能管理', 'SysFeatruestem', '6d584487ba058a0bf02713635a84807d', 5, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`,`bgroup`) VALUES ('4ab56fedc91484eb91d71a894b16349d', '功能管理', 'SysFeatrueProject', '957cb2bb99c30b504cefcc4dbaa0824d', 6, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`,`bgroup`) VALUES ('5d46c6e4f019a45edef7b427db5a732a', '角色管理', 'SysRoleProject', '957cb2bb99c30b504cefcc4dbaa0824d', 7, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`,`bgroup`) VALUES ('613e2055f04c59f9161d5b23abc39b0b', '插件管理', 'plugin', NULL, 8, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`,`bgroup`) VALUES ('6d584487ba058a0bf02713635a84807d', '系统权限中心', 'SysPrisystem', NULL, 9, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`,`bgroup`) VALUES ('76608490874eed4ef820d84d114ec780', '发送方式管理', 'SysMessageSendType', '305864b7559f0a81b500dc93521cab07', 10, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`,`bgroup`) VALUES ('957cb2bb99c30b504cefcc4dbaa0824d', '项目权限中心', 'SysPriProject', NULL, 11, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`,`bgroup`) VALUES ('9bab146ee19fd1ce65d769966ebed231', 'licence管理', 'licence', NULL, 12, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`,`bgroup`) VALUES ('cdfc6bae3a807ec26c1ef0b934abcd49', '消息管理', 'MessageManagement', '305864b7559f0a81b500dc93521cab07', 13, '1', 'postin');

INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`,`bgroup`) VALUES ('0072401117976f6a8053b1e7b42aa6f4', '3b4eee0e8752fe8299e4f1df080c01a8', '9bab146ee19fd1ce65d769966ebed231', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`,`bgroup`) VALUES ('06c247eb47b78f48bea9f533aae1af1b', '3b4eee0e8752fe8299e4f1df080c01a8', '5d46c6e4f019a45edef7b427db5a732a', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`,`bgroup`) VALUES ('09da2455882b59728b88d0c3d3f8c3eb', '3b4eee0e8752fe8299e4f1df080c01a8', '305864b7559f0a81b500dc93521cab07', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`,`bgroup`) VALUES ('24a4cd0e5a98749d86c7bcad4603e263', '3b4eee0e8752fe8299e4f1df080c01a8', '76608490874eed4ef820d84d114ec780', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`,`bgroup`) VALUES ('42971cf33ad9f3de063faea9b9e7bd50', '3b4eee0e8752fe8299e4f1df080c01a8', '45cc48e39fb855de5923cf5d735247c8', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`,`bgroup`) VALUES ('48374461682d57724c4f39669604b2aa', '3b4eee0e8752fe8299e4f1df080c01a8', '613e2055f04c59f9161d5b23abc39b0b', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`,`bgroup`) VALUES ('5b593764e330b9175f0f54cc6db12301', '3b4eee0e8752fe8299e4f1df080c01a8', '957cb2bb99c30b504cefcc4dbaa0824d', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`,`bgroup`) VALUES ('7c91528eecdacf903f8ecd4abf1a3b23', '3b4eee0e8752fe8299e4f1df080c01a8', '136c10de265146b40621babdb88fdcca', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`,`bgroup`) VALUES ('8d88693f0b8905eda9b1fd2dc500d3e6', '3b4eee0e8752fe8299e4f1df080c01a8', '4ab56fedc91484eb91d71a894b16349d', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`,`bgroup`) VALUES ('8ec23e0d7461583dd8f79693a478d362', '3b4eee0e8752fe8299e4f1df080c01a8', '2eca8165ccd6a0c33d1bd411662617ef', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`,`bgroup`) VALUES ('b339dca335842c625d62dabc9b95c964', '3b4eee0e8752fe8299e4f1df080c01a8', 'cdfc6bae3a807ec26c1ef0b934abcd49', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`,`bgroup`) VALUES ('b6f258ea5853c4947bf16acd0556a75c', '3b4eee0e8752fe8299e4f1df080c01a8', '12b085bb37913b5f7feb32da5ed260bb', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`,`bgroup`) VALUES ('ca2d83007900f0d9dde9b2a22131ddfd', '3b4eee0e8752fe8299e4f1df080c01a8', '6d584487ba058a0bf02713635a84807d', 'postin');


INSERT INTO `pcs_mec_message_send_type` VALUES ('942591eeee4a3100f2ea2cb871539c65', '站内信', 'site', NULL);

INSERT INTO `pcs_mec_message_template` VALUES ('5ea6acf0883ead208d8e75f031ca94df', '接口空间创建通知模板', '2ba9b35136183577a6973fbf900d5b4b', '942591eeee4a3100f2ea2cb871539c65', '空间创建通知', 1, '你创建了一个空间！', NULL, '/workspacepage/apis/detail/interface/detail');
INSERT INTO `pcs_mec_message_template` VALUES ('60ea6c910b092a0e68bd5a55fddcdc46', '角色添加成员站内消息模板', '2ba9b35136183577a6973fbf900d5b3b', '942591eeee4a3100f2ea2cb871539c65', '角色通知', 1, '用户${userName} ,邀请${users}用户到\"${roleName}\"角色中。', NULL, '/system/role');

INSERT INTO `pcs_mec_message_type` VALUES ('2ba9b35136183577a6973fbf900d5b3b', '角色通知', NULL);
INSERT INTO `pcs_mec_message_type` VALUES ('2ba9b35136183577a6973fbf900d5b4b', '创建空间通知', NULL);
