CREATE TABLE apibox_node(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        workspace_id VARCHAR(32),
        parent_category_id VARCHAR(32),
        sort int,
        nodeType int
);