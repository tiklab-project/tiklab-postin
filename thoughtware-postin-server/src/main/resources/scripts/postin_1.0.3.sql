

CREATE TABLE postin_node(
        id VARCHAR(32) PRIMARY KEY,
        workspace_id VARCHAR(32),
        parent_id VARCHAR(32),
        name VARCHAR(1024),
        type VARCHAR(32),
        method_type VARCHAR(32),
        tree_path TEXT,
        sort int,
        create_user VARCHAR(32),
        update_user VARCHAR(32),
        create_time timestamp,
        update_time timestamp
);


INSERT INTO postin_node (id, name,workspace_id, parent_id, type)
SELECT id, name,workspace_id, parent_category_id, 'category'
FROM postin_category;

INSERT INTO postin_node (id,name, workspace_id, parent_id,type,method_type,create_user,update_user,create_time,update_time)
SELECT id,name, workspace_id, category_id,protocol_type,method_type,create_user,update_user,create_time,update_time
FROM postin_apix;

ALTER TABLE postin_category
DROP COLUMN name,
DROP COLUMN workspace_id,
DROP COLUMN parent_category_id;
