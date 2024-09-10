-- ---------------------------
-- http
-- path
-- ----------------------------
CREATE TABLE postin_http_request_path(
        id VARCHAR(32) PRIMARY KEY,
        api_id VARCHAR(32),
        workspace_id VARCHAR(32),
        data_type VARCHAR(32),
        name VARCHAR(128) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        value VARCHAR(128),
        sort int
);
