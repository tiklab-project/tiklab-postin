
-- 系统级
-- 初始话管理员角色表
INSERT INTO `pcs_prc_role` (`id`, `name`, `description`, `grouper`, `type`,  `scope`, `default_role`,`business_type`) VALUES ('1', '管理员角色', NULL, 'system', '1', 1, 0, 1);
-- 初始话普通色表
INSERT INTO `pcs_prc_role` (`id`, `name`, `description`, `grouper`, `type`,  `scope`, `default_role`,`business_type`) VALUES ('2', '普通角色', NULL, 'system', '1', 1,  1, 0);
-- 管理员角色金和admin用户绑定 表
-- INSERT INTO `pcs_prc_role_user` (`id`, `role_id`, `user_id`) VALUES ('13141341', '1', '111111');

-- 项目级
-- 初始话管理员角色表
INSERT INTO `pcs_prc_role` (`id`, `name`, `description`, `grouper`, `type`,  `scope`, `default_role`,`business_type`) VALUES ('3', '项目管理员', NULL, 'system', '2', 1, 0, 1);
-- 初始话普通色表
INSERT INTO `pcs_prc_role` (`id`, `name`, `description`, `grouper`, `type`,  `scope`, `default_role`,`business_type`) VALUES ('4', '项目普通角色', NULL, 'system', '2', 1, 1, 0);
-- 项目管理员角色金和admin用户绑定 表
-- INSERT INTO `pcs_prc_role_user` (`id`, `role_id`, `tag`,`tag_value`) VALUES ('2', '3', 1, '111111');


-- 初始化admin角色平台的功能点关联数据
-- 用户模块 和 admin用户
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('1de76c3f04a', '1', '5fb7863b09a8');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('c88134f7af2', '1', '57a3bcd1e5fe');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('640892e368a', '1', '428be660dea3');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('4d62ceac4ea', '1', 'dd81bdbb52bc');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('6ae50d02d4e', '1', '9633d9475886');

-- 部门模块 和 admin用户
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('35a204b61b6', '1', '6b61fbe5091a8');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('6d56ee9b251', '1', '9c99b8a096c87');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('95418992b59', '1', 'cb954a7c0be3b');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('0ca3c862527', '1', '325c2503007fd');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('f8438284b4a', '1', 'e8bf9843bc9da');

-- 用户目录 和 admin用户
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('e64e932f43a', '1', '585d26bcbdf3');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('ae443de5001', '1', '043e412151db');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('d026551cfc3', '1', '925371be8ec6');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('c5fe246887f', '1', '890e7d41decf');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('70978ef5436', '1', '447d9998fc00');

-- pcs ---end

--功能点
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('03e4b44977b', '删除空间', 'workspaceDelete', NULL, 31, '2');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('06efc60a2f9', '消息管理', 'MSG_Notice', '305864b7559', 33, '1');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('2a0018f77b4', '发送方式', 'MSG_SendType', '305864b7559', 32, '1');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('305864b7559', '消息中心', 'MessageCenter', NULL, 4, '1');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('3ece7f7c704', '安全', 'security', NULL, 12, '1');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('4372581c839', '代办管理', 'TODO', NULL, 1, '1');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('613e2055f04', '插件管理', 'plugin', NULL, 8, '1');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('6d584487ba0', '系统权限中心', 'systemPrivilege', NULL, 9, '1');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('957cb2bb99c', '项目权限中心', 'projectPrivilege', NULL, 11, '1');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('a70e6efaafb', '日志', 'log', '3ece7f7c704', 13, '1');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('c1fe847f511', '我的待办', 'myTodo', '4372581c839', 1, '1');
INSERT INTO `pcs_prc_function` (`id`, `name`, `code`, `parent_function_id`, `sort`, `type`) VALUES ('cced804414d', '代办列表', 'todoList', '4372581c839', 1, '1');

INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('12a9d8f5a8a7', '1', '3ece7f7c704');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('1ae8800b8d14', '1', 'cced804414d');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('40c042bcc344', '1', 'c1fe847f511');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('543e535f14db', '1', '4372581c839');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('5b56bc252750', '1', '305864b7559');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('5c5c647d3028', '1', '06efc60a2f9');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('7a86c353f526', '1', '2a0018f77b4');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('b9026e176ce8', '1', '957cb2bb99c');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('c2a6385e01b6', '1', 'a70e6efaafb');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('c31825ad3cdd', '2', '03e4b44977b');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('cf517ede114b', '1', '6d584487ba0');
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('eebeab283759', '1', '613e2055f04');


--日志--
INSERT INTO `pcs_op_log_template` (`id`, `title`, `content`, `link`, `bgroup`, `abstract_content`) VALUES ('LOG_CODE', '日志通用模板', '<div>\n<span style=\"\n    color: #4581b7;\n\">${user}</span> <span>${actionType} </span><span>${mode}： </span><img src=\"${images}\" alt=\"\" width=\"16px\" height=\"16px\"/><span style=\"\nmargin: 0 5px;\n    font-weight: 600;\n    color: #4b768a;\n\">${name}</span></div>', '/workspacePage', 'postin','管理员创建了默认空间');

INSERT INTO `pcs_op_log_type` (`id`, `name`, `bgroup`) VALUES ('CREATE_TYPE', '新增', 'postin');
INSERT INTO `pcs_op_log_type` (`id`, `name`, `bgroup`) VALUES ('DELETE_TYPE', '删除', 'postin');
INSERT INTO `pcs_op_log_type` (`id`, `name`, `bgroup`) VALUES ('UPDATE_TYPE', '更新', 'postin');

--消息--
INSERT INTO `pcs_mec_message_template` (`id`, `msg_type_id`, `msg_send_type_id`, `title`, `content`, `link`, `bgroup`) VALUES ('7c8fb5b0', 'WS_MS_TYPE', 'email', '邮箱空间创建模板', '<div style=\"\n    display: flex;\n    align-items: center;\n\"> <span style=\"\n    color: #6c8ca0;\n    margin: 0 5px 0 0;\n\">${userName}</span>创建了:<img src=\"${images}\" alt=\"\" width=\"16px\" height=\"16px\" style=\"\n    margin: 0 5px;\"/><span  style=\"font-weight:600;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;\">${name}</span></div>', NULL, 'postin');
INSERT INTO `pcs_mec_message_template` (`id`, `msg_type_id`, `msg_send_type_id`, `title`, `content`, `link`, `bgroup`) VALUES ('WS_CREATE', 'WS_MS_TYPE', 'site', '空间创建通知', '<div style=\"\n    display: flex;\n    align-items: center;\n\"> <span style=\"\n    color: #6c8ca0;\n    margin: 0 5px 0 0;\n\">${userName}</span>创建了:<img src=\"${images}\" alt=\"\" width=\"16px\" height=\"16px\" style=\"\n    margin: 0 5px;\"/><span  style=\"font-weight:600;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;width: 120px;\">${name}</span></div>', '/workspacePage', 'postin');
INSERT INTO `pcs_mec_message_template` (`id`, `msg_type_id`, `msg_send_type_id`, `title`, `content`, `link`, `bgroup`) VALUES ('WS_CREATE_DD', 'WS_MS_TYPE', 'dingding', '钉钉空间创建', '**空间创建** \n\n**${userName}**  创建了   **${name}**\n\n![workspaceImg](${images})\n\n', NULL, 'postin');
INSERT INTO `pcs_mec_message_template` (`id`, `msg_type_id`, `msg_send_type_id`, `title`, `content`, `link`, `bgroup`) VALUES ('WS_CREATE_WX', 'WS_MS_TYPE', 'qywechat', NULL, '**空间创建**  \n\n**${userName}**  创建了   **${name}**\n', NULL, 'postin');

INSERT INTO `pcs_mec_message_notice` (`id`, `message_type_id`, `type`, `bgroup`, `message_send_type_id`) VALUES ('MSG_NOTICEID', 'WS_MS_TYPE', 1, 'postin', 'dingding,email,qywechat,site');

INSERT INTO `pcs_mec_message_notice_connect_user` (`id`, `message_notice_id`, `user_id`) VALUES ('afc42e5554', 'MSG_NOTICEID', '111111');
INSERT INTO `pcs_mec_message_notice_connect_role` (`id`, `message_notice_id`, `role_id`) VALUES ('df4d545645', 'MSG_NOTICEID', '1');

INSERT INTO `pcs_mec_message_type` (`id`, `name`, `description`, `bgroup`) VALUES ('WS_MS_TYPE', '创建空间通知', NULL, 'postin');



--初始化的空间的角色
INSERT INTO `pcs_prc_role` (`id`, `name`, `description`, `grouper`, `type`, `scope`, `business_type`, `default_role`) VALUES ('custom3', '项目管理员', NULL, 'system', '2', 2, 1, 0);
INSERT INTO `pcs_prc_role` (`id`, `name`, `description`, `grouper`, `type`, `scope`, `business_type`, `default_role`) VALUES ('custom4', '项目普通角色', NULL, 'system', '2', 2, 0, 1);
INSERT INTO `pcs_prc_role_function` (`id`, `role_id`, `function_id`) VALUES ('7a06707984c0', 'custom3', '03e4b44977b');

INSERT INTO `pcs_ucc_dm_user`  (`id`, `domain_id`, `user_id`, `type`)  VALUES ('5c56ca9a24a', 'bd26c6ec5c6e', '111111', 0);
INSERT INTO `pcs_prc_dm_role_user` (`id`, `dmRole_id`, `domain_id`, `user_id`) VALUES ('36388488b', '1bd0aa5c5f', 'bd26c6ec5c6e', '111111');

INSERT INTO `pcs_prc_dm_role` (`id`, `domain_id`, `role_id`, `business_type`) VALUES ('1bd0aa5c5f', 'bd26c6ec5c6e', 'custom3', 1);
INSERT INTO `pcs_prc_dm_role` (`id`, `domain_id`, `role_id`, `business_type`) VALUES ('adf4c4ca69', 'bd26c6ec5c6e', 'custom4', 0);

