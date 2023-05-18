-- alter table postin_environment add workspace_id VARCHAR(32);

CREATE TABLE postin_api_recent (
        id VARCHAR(32) PRIMARY KEY,
        workspace_id VARCHAR(32),
        apix_id VARCHAR(32),
        user_id VARCHAR (32),
        update_time timestamp
);
