DELETE FROM pcs_op_log;
DELETE FROM pcs_op_log_type;

INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('LOG_TYPE_CREATE_ID', '新增了空间', 'postin');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('LOG_TYPE_UPDATE_ID', '更新了空间', 'postin');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('LOG_TYPE_DELETE_ID', '删除了空间', 'postin');


