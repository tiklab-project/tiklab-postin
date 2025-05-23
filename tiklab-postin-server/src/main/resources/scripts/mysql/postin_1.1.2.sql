-- 创建 分享节点表
CREATE TABLE postin_share_node(
        id VARCHAR(12) PRIMARY KEY,
        node_id VARCHAR(32),
        share_id VARCHAR(32)
);

-- 删除分享表内容
DELETE FROM postin_share;
-- 新增名称
ALTER TABLE postin_share Add COLUMN name VARCHAR(128);
-- 删除targetId不为空
ALTER TABLE postin_share MODIFY COLUMN target_Id VARCHAR(32) NULL;


