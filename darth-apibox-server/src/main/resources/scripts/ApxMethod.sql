CREATE TABLE apibox_method(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        parent_category_id VARCHAR(32),
        sort int
);