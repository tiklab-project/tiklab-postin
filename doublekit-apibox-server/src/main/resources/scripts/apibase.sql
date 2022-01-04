CREATE TABLE apibox_workspace(
        id VARCHAR(32) PRIMARY KEY,
        workspace_name VARCHAR(64) NOT NULL,
        description VARCHAR(64),
        user_id VARCHAR(32)
);
CREATE TABLE apibox_category(
        id VARCHAR(40) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        workspace_id VARCHAR(32),
        parent_category_id VARCHAR(32),
        sort int
);
CREATE TABLE apibox_environment(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        url VARCHAR(256) NOT NULL,
        sort int,
        create_time timestamp,
        update_time timestamp
);