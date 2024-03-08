

CREATE TABLE postin_node(
        id VARCHAR(32) PRIMARY KEY,
        workspace_id VARCHAR(32),
        parent_id VARCHAR(64),
        type VARCHAR(32),
        tree_path TEXT,
        sort int,
        create_user VARCHAR(32),
        update_user VARCHAR(32),
        create_time timestamp,
        update_time timestamp
);


INSERT INTO postin_node (id, workspace_id, parent_id, type)
SELECT id, workspace_id, parent_category_id, 'category'
FROM postin_category;

INSERT INTO postin_node (id, workspace_id, parent_id,  type)
SELECT id, workspace_id, category_id,  protocol_type
FROM postin_apix;

