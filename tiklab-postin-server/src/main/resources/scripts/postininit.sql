
INSERT INTO `pcs_prc_role` VALUES ('postinId', 'admin', NULL, 'system', '1', 'postin');

INSERT INTO `pcs_prc_role_user` VALUES ('4e1fe7b1dc57e35acf27bb223abd08ac', 'postinId', '111111', 'postin');


INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('305864b7559f0a81b500dc93521cab07', '消息中心', 'MessageCenter', NULL, 4, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('3ece7f7c704bf1117ff0126009f8cb11', '安全', 'security', NULL, 12, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('4372581c8396e0736acff5d268ea7267', '代办管理', 'TODO', NULL, 1, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('613e2055f04c59f9161d5b23abc39b0b', '插件管理', 'plugin', NULL, 8, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('6d584487ba058a0bf02713635a84807d', '系统权限中心', 'systemPrivilege', NULL, 9, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('957cb2bb99c30b504cefcc4dbaa0824d', '项目权限中心', 'projectPrivilege', NULL, 11, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('a70e6efaafb67d4182a205f27e4a8492', '日志', 'log', '3ece7f7c704bf1117ff0126009f8cb11', 13, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('c1fe847f5116a1c143ea96c15afefa4e', '我的待办', 'myTodo', '4372581c8396e0736acff5d268ea7267', 1, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('cced804414d6c10c26183c83a1a886bd', '代办列表', 'todoList', '4372581c8396e0736acff5d268ea7267', 1, '1', 'postin');


INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('09da2455882b59728b88d0c3d3f8c3eb', 'postinId', '305864b7559f0a81b500dc93521cab07', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('5c55572546bfebc4c87537af8e149e46', 'postinId', '4372581c8396e0736acff5d268ea7267', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('8994cf8571bd424007f8f9ec3ceae250', 'postinId', '45a0c077bd1ebc2cc53bbb81ae915a8e', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('48374461682d57724c4f39669604b2aa', 'postinId', '613e2055f04c59f9161d5b23abc39b0b', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('ca2d83007900f0d9dde9b2a22131ddfd', 'postinId', '6d584487ba058a0bf02713635a84807d', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('5b593764e330b9175f0f54cc6db12301', 'postinId', '957cb2bb99c30b504cefcc4dbaa0824d', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('6dc316292a4797c4932f7490f6eb394a', 'postinId', 'c1fe847f5116a1c143ea96c15afefa4e', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('268c9ec17db6726736403526b1fab3ce', 'postinId', 'cced804414d6c10c26183c83a1a886bd', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('c6662a396e043075ca69c781829ece8f', 'postinId', '3ece7f7c704bf1117ff0126009f8cb11', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('6b1c6b8c37a95fb3116606e3f891e287', 'postinId', 'a70e6efaafb67d4182a205f27e4a8492', 'postin');

INSERT INTO `pcs_op_log_template` (`id`, `title`, `content`, `link`, `bgroup`) VALUES ('c072fe18d74bc3893ac323cf8319d8b3', '空间', '用户<span>${user}</span> ${actionType} 了 ${name}', NULL, 'postin');

INSERT INTO `pcs_mec_message_send_type` VALUES ('942591eeee4a3100f2ea2cb871539c65', '站内信', 'site', NULL,"postin");

INSERT INTO `pcs_mec_message_template` VALUES ('5ea6acf0883ead208d8e75f031ca94df', '接口空间创建通知模板', '2ba9b35136183577a6973fbf900d5b4b', '942591eeee4a3100f2ea2cb871539c65', '空间创建通知', 1, '你创建了一个空间！', NULL, '/workspacepage/apis/detail/interface/detail',"postin");
INSERT INTO `pcs_mec_message_template` VALUES ('60ea6c910b092a0e68bd5a55fddcdc46', '角色添加成员站内消息模板', '2ba9b35136183577a6973fbf900d5b3b', '942591eeee4a3100f2ea2cb871539c65', '角色通知', 1, '用户${userName} ,邀请${users}用户到\"${roleName}\"角色中。', NULL, '/system/role',"postin");

INSERT INTO `pcs_mec_message_type` VALUES ('2ba9b35136183577a6973fbf900d5b3b', '角色通知', NULL,"postin");
INSERT INTO `pcs_mec_message_type` VALUES ('2ba9b35136183577a6973fbf900d5b4b', '创建空间通知', NULL,"postin");
