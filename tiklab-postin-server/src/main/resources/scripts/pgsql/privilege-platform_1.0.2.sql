-- 项目下设置的权限
update pcs_prc_function_group set function_ids='domain_user,domain_role,project_basic_info',sort=101,code='project_setting',id='project_setting' where id ='pi_setting';
update pcs_prc_function set name='删除成员' where id = 'domain_user_delete';
update pcs_prc_function set name='修改成员角色' where id = 'domain_user_update';
-- 基础信息
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_basic_info', '基础信息', 'project_basic_info', NULL, 0, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_basic_info_delete', '删除项目', 'project_basic_info_delete', 'project_basic_info', 2, '2');