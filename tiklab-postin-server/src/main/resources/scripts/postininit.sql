
-- 之前在pcs中
-- 系统级
-- 初始话管理员角色表
INSERT INTO `pcs_prc_role` (`id`, `name`, `description`, `grouper`, `type`,  `scope`, `tag`,`default_role`,`business_type`) VALUES ('1', '管理员角色', NULL, 'system', '1',  1, 1, 1);
-- 初始话普通色表
INSERT INTO `pcs_prc_role` (`id`, `name`, `description`, `grouper`, `type`,  `scope`,`tag`,`default_role`,`business_type`) VALUES ('2', '普通角色', NULL, 'system', '1',  1, 0, 0);
-- 管理员角色金和admin用户绑定 表
INSERT INTO `pcs_prc_role_user` (`id`, `role_id`, `tag`,`tag_value`) VALUES ('1', '1', 1, '111111');


-- 项目级
-- 初始话管理员角色表
INSERT INTO `pcs_prc_role` (`id`, `name`, `description`, `grouper`, `type`,  `scope`, `tag`,`default_role`,`business_type`) VALUES ('3', '项目管理员', NULL, 'system', '2',  1, 1, 1);
-- 初始话普通色表
INSERT INTO `pcs_prc_role` (`id`, `name`, `description`, `grouper`, `type`,  `scope`, `tag`,`default_role`,`business_type`) VALUES ('4', '项目普通角色', NULL, 'system', '2',  1, 0, 0);

-- 项目管理员角色金和admin用户绑定 表
INSERT INTO `pcs_prc_role_user` (`id`, `role_id`, `tag`,`tag_value`) VALUES ('2', '3', 1, '111111');

-- 初始化admin角色平台的功能点关联数据
-- 用户模块 和 admin用户
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('1de76c3f04a1c5b62b1bff44065b34f7', '1', '5fb7863b09a8d0c99cef173e18106fb3');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('c88134f7af2c99626e62ca498d6c3216', '1', '57a3bcd1e5fedd77824359d06b06fe49');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('640892e368a7d9b05f1b1e88abcda964', '1', '428be660dea3db2a2c5a613420b3ead7');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('4d62ceac4ea82c6eb1a81d4b13c4d7d1', '1', 'dd81bdbb52bca933d1e7336f9c877f8e');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('6ae50d02d4ed17f971bc6c61c0bb67ea', '1', '9633d947588684a5881ccff9eaa3aba0');

-- 部门模块 和 admin用户
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('35a204b61b65576b34a507fb5fab96f4', '1', '6b61fbe5091a8e04d2b016f15d598f1f');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('6d56ee9b2518fd0403ffcd5b0478c298', '1', '9c99b8a096c8788bc27be5122d0700e8');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('95418992b599c9fdedb92f22deb76d97', '1', 'cb954a7c0be3b37fcc96ec023924262c');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('0ca3c8625274a9a0fb0996a7b2ca40c8', '1', '325c2503007fd5127baca9d7618e8291');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('f8438284b4abb8cfcc01074e7c5c66b3', '1', 'e8bf9843bc9da8d3c4c33e31174496b3');

-- 用户目录 和 admin用户
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('e64e932f43a121ffbc19f3846ec6de0d', '1', '585d26bcbdf3047e502e4aa51c816090');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('ae443de5001adc8d498cb02456d332bd', '1', '043e412151db118d27f2ab60d8ff73a0');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('d026551cfc33d036de61208a413a9607', '1', '925371be8ec674a06613bf8e37ec356c');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('c5fe246887fa2115ec1cb8a07a77aef5', '1', '890e7d41decf71cfe3b0e80b0c4179cf');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('70978ef543639b3d373ac70071a16903', '1', '447d9998fc00fe64c96c6f09f0d41c32');

-- pcs ---end

--初始化的空间的角色
INSERT INTO `pcs_ucc_dm_user` (`id`, `domain_id`, `user_id`) VALUES ('5c56ca9a24afaba76ceef3173348f2b8', 'bd26c6ec5c6e12fd1082772362e096a8', '111111');

INSERT INTO `pcs_prc_dm_role` (`id`, `domain_id`, `role_id`) VALUES ('1bd0aa5c5f07932e2a23bb198e0efe7a', 'bd26c6ec5c6e12fd1082772362e096a8', '3');
INSERT INTO `pcs_prc_dm_role` (`id`, `domain_id`, `role_id`) VALUES ('adf4c4ca696d1bdea7b67a5666becc5e', 'bd26c6ec5c6e12fd1082772362e096a8', '4');


--功能点
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('03e4b44977b5403a8bc99b1492534e2d', '删除空间', 'workspaceDelete', NULL, 31, '2');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('06efc60a2f96ef583637d0eccf44b6d7', '消息管理', 'MSG_Notice', '305864b7559f0a81b500dc93521cab07', 33, '1');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('2a0018f77b4fc3a97a19a304d4bb5001', '发送方式', 'MSG_SendType', '305864b7559f0a81b500dc93521cab07', 32, '1');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('305864b7559f0a81b500dc93521cab07', '消息中心', 'MessageCenter', NULL, 4, '1');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('3ece7f7c704bf1117ff0126009f8cb11', '安全', 'security', NULL, 12, '1');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('4372581c8396e0736acff5d268ea7267', '代办管理', 'TODO', NULL, 1, '1');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('613e2055f04c59f9161d5b23abc39b0b', '插件管理', 'plugin', NULL, 8, '1');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('6d584487ba058a0bf02713635a84807d', '系统权限中心', 'systemPrivilege', NULL, 9, '1');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('957cb2bb99c30b504cefcc4dbaa0824d', '项目权限中心', 'projectPrivilege', NULL, 11, '1');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('a70e6efaafb67d4182a205f27e4a8492', '日志', 'log', '3ece7f7c704bf1117ff0126009f8cb11', 13, '1');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('c1fe847f5116a1c143ea96c15afefa4e', '我的待办', 'myTodo', '4372581c8396e0736acff5d268ea7267', 1, '1');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('cced804414d6c10c26183c83a1a886bd', '代办列表', 'todoList', '4372581c8396e0736acff5d268ea7267', 1, '1');

INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('12a9d8f5a8a743517be67d54854cf2a1', '1', '3ece7f7c704bf1117ff0126009f8cb11');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('1ae8800b8d14f3e78615132b96a7fd35', '1', 'cced804414d6c10c26183c83a1a886bd');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('40c042bcc344338e30a1a5623d2e9ca6', '1', 'c1fe847f5116a1c143ea96c15afefa4e');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('44f04ef91694c31c8671cd4dc97b6875', '60f6f2812935fbe0026f480b13bec400', '03e4b44977b5403a8bc99b1492534e2d');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('543e535f14db21b4df542527c5cc0f9a', '1', '4372581c8396e0736acff5d268ea7267');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('5b56bc2527502eb3b825a6086282a6f5', '1', '305864b7559f0a81b500dc93521cab07');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('5c5c647d30289a278f9396a73c7a6524', '1', '06efc60a2f96ef583637d0eccf44b6d7');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('7a86c353f526b7c2cb5cb953fae86974', '1', '2a0018f77b4fc3a97a19a304d4bb5001');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('b9026e176ce8eac5236d7ea5c43e69b2', '1', '957cb2bb99c30b504cefcc4dbaa0824d');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('c2a6385e01b6dc073b67b8c2260c3f26', '1', 'a70e6efaafb67d4182a205f27e4a8492');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('c31825ad3cdd4babc3b552ec0897db98', '2', '03e4b44977b5403a8bc99b1492534e2d');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('cf517ede114bb1a1daddd8feca60108c', '1', '6d584487ba058a0bf02713635a84807d');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('eebeab2837593efbaff611420e815f13', '1', '613e2055f04c59f9161d5b23abc39b0b');


--日志--
INSERT INTO `pcs_op_log_template` (`id`, `title`, `content`, `link`, `bgroup`) VALUES ('LOG_CODE', '日志通用模板', '<div>\n<span style=\"\n    color: #4581b7;\n\">${user}</span> <span>${actionType} </span><span>${mode}： </span><img src=\"${images}\" alt=\"\" width=\"16px\" height=\"16px\"/><span style=\"\nmargin: 0 5px;\n    font-weight: 600;\n    color: #4b768a;\n\">${name}</span></div>', '/workspacePage', 'postin');

INSERT INTO `pcs_op_log_type` (`id`, `name`, `bgroup`) VALUES ('CREATE_TYPE', '新增', 'postin');
INSERT INTO `pcs_op_log_type` (`id`, `name`, `bgroup`) VALUES ('DELETE_TYPE', '删除', 'postin');
INSERT INTO `pcs_op_log_type` (`id`, `name`, `bgroup`) VALUES ('UPDATE_TYPE', '更新', 'postin');

--消息--
INSERT INTO `pcs_mec_message_template` (`id`, `msg_type_id`, `msg_send_type_id`, `title`, `content`, `link`, `bgroup`) VALUES ('7c8fb5b038cb5a1d8b3efc4ded140535', 'WORKSPACE_CREATE_TYPE', 'email', '邮箱空间创建模板', '<div style=\"\n    display: flex;\n    align-items: center;\n\"> <span style=\"\n    color: #6c8ca0;\n    margin: 0 5px 0 0;\n\">${userName}</span>创建了:<img src=\"${images}\" alt=\"\" width=\"16px\" height=\"16px\" style=\"\n    margin: 0 5px;\"/><span  style=\"font-weight:600;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;\">${name}</span></div>', NULL, 'postin');
INSERT INTO `pcs_mec_message_template` (`id`, `msg_type_id`, `msg_send_type_id`, `title`, `content`, `link`, `bgroup`) VALUES ('WORKSPACE_CREATE', 'WORKSPACE_CREATE_TYPE', 'site', '空间创建通知', '<div style=\"\n    display: flex;\n    align-items: center;\n\"> <span style=\"\n    color: #6c8ca0;\n    margin: 0 5px 0 0;\n\">${userName}</span>创建了:<img src=\"${images}\" alt=\"\" width=\"16px\" height=\"16px\" style=\"\n    margin: 0 5px;\"/><span  style=\"font-weight:600;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;width: 120px;\">${name}</span></div>', '/workspacePage', 'postin');
INSERT INTO `pcs_mec_message_template` (`id`, `msg_type_id`, `msg_send_type_id`, `title`, `content`, `link`, `bgroup`) VALUES ('WORKSPACE_CREATE_DD', 'WORKSPACE_CREATE_TYPE', 'dingding', '钉钉空间创建', '**空间创建** \n\n**${userName}**  创建了   **${name}**\n\n![workspaceImg](${images})\n\n', NULL, 'postin');
INSERT INTO `pcs_mec_message_template` (`id`, `msg_type_id`, `msg_send_type_id`, `title`, `content`, `link`, `bgroup`) VALUES ('WORKSPACE_CREATE_WX', 'WORKSPACE_CREATE_TYPE', 'qywechat', NULL, '**空间创建**  \n\n**${userName}**  创建了   **${name}**\n', NULL, 'postin');

INSERT INTO `pcs_mec_message_notice` (`id`, `message_type_id`, `type`, `bgroup`, `message_send_type_id`) VALUES ('MESSAGE_NOTICE_ID', 'WORKSPACE_CREATE_TYPE', 1, 'postin', 'dingding,email,qywechat,site');

INSERT INTO `pcs_mec_message_notice_connect` (`id`, `message_notice_id`, `type`, `type_value`) VALUES ('afc42ebf4469a2e93f143f6bddea97d3', 'MESSAGE_NOTICE_ID', 6, '111111');
INSERT INTO `pcs_mec_message_notice_connect` (`id`, `message_notice_id`, `type`, `type_value`) VALUES ('df4d54143130adecbfac8a805307444c', 'MESSAGE_NOTICE_ID', 7, '1');

INSERT INTO `pcs_mec_message_type` (`id`, `name`, `description`, `bgroup`) VALUES ('WORKSPACE_CREATE_TYPE', '创建空间通知', NULL, 'postin');


