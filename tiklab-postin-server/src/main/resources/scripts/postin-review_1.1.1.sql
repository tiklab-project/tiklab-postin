-- ee
-- 创建评审表
CREATE TABLE postin_review(
    id VARCHAR(12) PRIMARY KEY,
    workspace_id VARCHAR(32),
    name VARCHAR(128),
    description TEXT,
    status int,
    type int,
    create_user_id VARCHAR(32),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    start_time TIMESTAMP,
    end_time TIMESTAMP
);

-- 评审关联的接口
CREATE TABLE postin_review_apis(
    id VARCHAR(12) PRIMARY KEY,
    review_id VARCHAR(12),
    api_id VARCHAR(32),
    result int,
    comment TEXT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);