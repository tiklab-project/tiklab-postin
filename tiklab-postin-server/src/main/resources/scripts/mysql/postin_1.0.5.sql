
INSERT INTO  postin_api_status  ( id ,  color ,  name ,  type ,  workspace_id ) VALUES ('intergratedid', 'pink', '联调', 'system', NULL);
INSERT INTO  postin_api_status  ( id ,  color ,  name ,  type ,  workspace_id ) VALUES ('errorid', 'red', '异常', 'system', NULL);
INSERT INTO  postin_api_status  ( id ,  color ,  name ,  type ,  workspace_id ) VALUES ('obsoleteid', '#aaaaaa', '废弃', 'system', NULL);


CREATE TABLE postin_statistic_trend (
    id VARCHAR(32) PRIMARY KEY,
    workspace_id VARCHAR(32),
    record_time VARCHAR(32),
    publish INT,
    design INT,
    development INT,
    intergrated INT,
    test INT,
    complete INT,
    maintain INT,
    error INT,
    obsolete INT
);
