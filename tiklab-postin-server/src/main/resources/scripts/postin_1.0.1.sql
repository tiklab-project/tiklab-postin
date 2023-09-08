CREATE TABLE postin_db_backups (
    id VARCHAR(32) PRIMARY KEY,
    type VARCHAR(32),
    run_state VARCHAR(32),
    log text,
    scheduled VARCHAR(32) ,
    dir VARCHAR(64) ,
    create_time VARCHAR(64)
);
