
INSERT INTO `pcs_prc_role` (`id`, `name`, `description`, `grouper`, `type`, `bgroup`, `scope`) VALUES ('03c60bfdf955efcca032346f5f46e967', '普通用户', NULL, 'custom', '2', 'postin', 2);
INSERT INTO `pcs_prc_role` (`id`, `name`, `description`, `grouper`, `type`, `bgroup`, `scope`) VALUES ('1', '管理员', NULL, 'system', '1', 'postin', 1);
INSERT INTO `pcs_prc_role` (`id`, `name`, `description`, `grouper`, `type`, `bgroup`, `scope`) VALUES ('2', '管理员', NULL, 'system', '2', 'postin', 1);
INSERT INTO `pcs_prc_role` (`id`, `name`, `description`, `grouper`, `type`, `bgroup`, `scope`) VALUES ('60f6f2812935fbe0026f480b13bec400', '管理员', NULL, 'custom', '2', 'postin', 2);

INSERT INTO `pcs_prc_role_user` (`id`, `role_id`, `user_id`, `bgroup`) VALUES ('f5db0ee0408da8ca2718c0fc5d1edb16', '1', '111111', 'postin');

INSERT INTO `pcs_prc_dm_role` (`id`, `domain_id`, `role_id`, `bgroup`) VALUES ('1bd0aa5c5f07932e2a23bb198e0efe7a', 'bd26c6ec5c6e12fd1082772362e096a8', '03c60bfdf955efcca032346f5f46e967', 'postin');
INSERT INTO `pcs_prc_dm_role` (`id`, `domain_id`, `role_id`, `bgroup`) VALUES ('adf4c4ca696d1bdea7b67a5666becc5e', 'bd26c6ec5c6e12fd1082772362e096a8', '60f6f2812935fbe0026f480b13bec400', 'postin');

INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('03e4b44977b5403a8bc99b1492534e2d', '删除空间', 'workspaceDelete', NULL, 31, '2', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('06efc60a2f96ef583637d0eccf44b6d7', '消息管理', 'messageManagement', '305864b7559f0a81b500dc93521cab07', 33, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('2a0018f77b4fc3a97a19a304d4bb5001', '发送方式', 'messageSendType', '305864b7559f0a81b500dc93521cab07', 32, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('305864b7559f0a81b500dc93521cab07', '消息中心', 'MessageCenter', NULL, 4, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('3d9e98da5f08886f1c0586154697725e', '删除组织', 'orga_delete_orga', 'de86f534c1a52c08ef8371576dff72e0', 22, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('3ece7f7c704bf1117ff0126009f8cb11', '安全', 'security', NULL, 12, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('42954635871b8e9be21be039ea4e599f', '添加用户', 'user_add_user', 'b38635f2a86d1cbd60b05abfd9dd55ae', 23, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('4372581c8396e0736acff5d268ea7267', '代办管理', 'TODO', NULL, 1, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('613e2055f04c59f9161d5b23abc39b0b', '插件管理', 'plugin', NULL, 8, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('648ea6d5a3a55a6a9ece5bc6400421fb', '更新', 'orga_update_orga', 'de86f534c1a52c08ef8371576dff72e0', 20, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('6d584487ba058a0bf02713635a84807d', '系统权限中心', 'systemPrivilege', NULL, 9, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('8a4ff07464db301e4a4b5fd433fee2fa', '用户目录', 'authConfig', NULL, 15, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('8af4d8664fd15e23a76630bf4783ac73', '开启', 'user_dir_open', '8a4ff07464db301e4a4b5fd433fee2fa', 28, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('8c9366a24a716f0e55cb1a32897c51f9', '添加', 'orga_add_orga', 'de86f534c1a52c08ef8371576dff72e0', 17, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('957cb2bb99c30b504cefcc4dbaa0824d', '项目权限中心', 'projectPrivilege', NULL, 11, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('983c0bcec4f2dc7c79eea8fac88e0bb8', '配置', 'user_dir_config', '8a4ff07464db301e4a4b5fd433fee2fa', 30, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('a70e6efaafb67d4182a205f27e4a8492', '日志', 'log', '3ece7f7c704bf1117ff0126009f8cb11', 13, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('b38635f2a86d1cbd60b05abfd9dd55ae', '用户', 'user', NULL, 19, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('c1fe847f5116a1c143ea96c15afefa4e', '我的待办', 'myTodo', '4372581c8396e0736acff5d268ea7267', 1, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('c3768d99a3f4a72ef426fb5e5ee53346', '添加user', 'orga_add_user', 'de86f534c1a52c08ef8371576dff72e0', 21, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('cada68b5c570415bac4bc767540f1a4b', '同步', 'user_dir_sync', '8a4ff07464db301e4a4b5fd433fee2fa', 27, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('cc59e0eae35834e6221ba74e70bc91c9', '禁止', 'user_dir_forbid', '8a4ff07464db301e4a4b5fd433fee2fa', 29, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('cced804414d6c10c26183c83a1a886bd', '代办列表', 'todoList', '4372581c8396e0736acff5d268ea7267', 1, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('de86f534c1a52c08ef8371576dff72e0', '部门', 'org', NULL, 14, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('e93441f6a9c0d26f182b3e24afee8f8d', '更新角色', 'user_update_role', 'b38635f2a86d1cbd60b05abfd9dd55ae', 26, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('f18adf67da9a3da02173673f7ffbbc30', '更新用户', 'user_update_user', 'b38635f2a86d1cbd60b05abfd9dd55ae', 24, '1', 'postin');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`, `bgroup`) VALUES ('f52325d366fcc55bc6e5ed9e95cfe82b', '删除用户', 'user_delete_user', 'b38635f2a86d1cbd60b05abfd9dd55ae', 25, '1', 'postin');


INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('01c027a0509635730e0b5f90da041941', '1', '42954635871b8e9be21be039ea4e599f', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('12a9d8f5a8a743517be67d54854cf2a1', '1', '3ece7f7c704bf1117ff0126009f8cb11', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('1a15caa5d375f9d64c840289bb360697', '1', 'b38635f2a86d1cbd60b05abfd9dd55ae', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('1ae8800b8d14f3e78615132b96a7fd35', '1', 'cced804414d6c10c26183c83a1a886bd', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('22908394991e613de39b6c13a07c627e', '1', 'cada68b5c570415bac4bc767540f1a4b', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('28cb5cd21261d39744f7d284e83b7e61', '1', 'f52325d366fcc55bc6e5ed9e95cfe82b', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('40c042bcc344338e30a1a5623d2e9ca6', '1', 'c1fe847f5116a1c143ea96c15afefa4e', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('44f04ef91694c31c8671cd4dc97b6875', '60f6f2812935fbe0026f480b13bec400', '03e4b44977b5403a8bc99b1492534e2d', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('4c89f73f72626a9bdea82993ee618610', '1', '8c9366a24a716f0e55cb1a32897c51f9', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('543e535f14db21b4df542527c5cc0f9a', '1', '4372581c8396e0736acff5d268ea7267', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('5b56bc2527502eb3b825a6086282a6f5', '1', '305864b7559f0a81b500dc93521cab07', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('5c5c647d30289a278f9396a73c7a6524', '1', '06efc60a2f96ef583637d0eccf44b6d7', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('62b81ebacfb420491c4c56ddd59b50a0', '1', '8a4ff07464db301e4a4b5fd433fee2fa', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('6afa224129db6b7e0f0dc5d47f03f8f7', '1', 'c3768d99a3f4a72ef426fb5e5ee53346', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('7a86c353f526b7c2cb5cb953fae86974', '1', '2a0018f77b4fc3a97a19a304d4bb5001', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('7eca2a208519a646f67b385d0ad00eab', '1', 'e93441f6a9c0d26f182b3e24afee8f8d', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('9221f66696277ba8ddcea71cd26d15bf', '1', '3d9e98da5f08886f1c0586154697725e', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('a763160b11dd31036886a80a42f3522e', '1', '648ea6d5a3a55a6a9ece5bc6400421fb', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('b9026e176ce8eac5236d7ea5c43e69b2', '1', '957cb2bb99c30b504cefcc4dbaa0824d', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('c10eb4229f8613c5f9763eec238ccdf8', '1', 'de86f534c1a52c08ef8371576dff72e0', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('c2a6385e01b6dc073b67b8c2260c3f26', '1', 'a70e6efaafb67d4182a205f27e4a8492', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('c31825ad3cdd4babc3b552ec0897db98', '2', '03e4b44977b5403a8bc99b1492534e2d', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('c914d8028d9dd12f611a98a208ad46e2', '1', '983c0bcec4f2dc7c79eea8fac88e0bb8', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('cf517ede114bb1a1daddd8feca60108c', '1', '6d584487ba058a0bf02713635a84807d', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('d762e40e64064ab79c1af8627a3f1183', '1', 'cc59e0eae35834e6221ba74e70bc91c9', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('eb74738584992ccc1d29abb8323f1e32', '1', '8af4d8664fd15e23a76630bf4783ac73', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('eebeab2837593efbaff611420e815f13', '1', '613e2055f04c59f9161d5b23abc39b0b', 'postin');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`, `bgroup`) VALUES ('f02e44d95ad2c5bc61ebd12a08870f51', '1', 'f18adf67da9a3da02173673f7ffbbc30', 'postin');


INSERT INTO `pcs_op_log_template` (`id`, `title`, `content`, `link`, `bgroup`) VALUES ('c072fe18d74bc3893ac323cf8319d8b3', '绌洪棿', '鐢ㄦ埛<span>${user}</span> ${actionType} 浜?${name}', NULL, 'postin');

INSERT INTO `pcs_mec_message_send_type` VALUES ('942591eeee4a3100f2ea2cb871539c65', '站内信', 'site', NULL,"postin");
INSERT INTO `pcs_mec_message_template` VALUES ('5ea6acf0883ead208d8e75f031ca94df', '空间创建通知模板', '2ba9b35136183577a6973fbf900d5b4b', '942591eeee4a3100f2ea2cb871539c65', '空间创建通知', 1, ' <img src="${images}" alt="" width="16px" height="16px" />创建了一个空间:<span  style="font-weight:600">${name}</span>', NULL, '/workspacePage',"postin");
INSERT INTO `pcs_mec_message_type` VALUES ('2ba9b35136183577a6973fbf900d5b4b', '创建空间通知', NULL,"postin");
