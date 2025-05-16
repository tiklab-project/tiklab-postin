-- 更新角色名称
UPDATE pcs_prc_role
SET name = CASE id
    WHEN '1' THEN '管理员'
    WHEN '2' THEN '普通用户'
    WHEN '4' THEN '项目成员'
    WHEN 'pro_111111' THEN '项目管理员'
    ELSE name
END
WHERE id IN ('1','2','4','pro_111111');

-- 更新示例项目 项目管理员 custom3
UPDATE pcs_prc_role
SET parent_id     = 'pro_111111',
    business_type =  2,
    default_role  =  2
WHERE id = 'custom3';

-- 更新示例项目 项目成员 custom4
UPDATE pcs_prc_role
SET parent_id     = '4',
    name = '项目成员'
WHERE id = 'custom4';

UPDATE pcs_prc_role
SET name = '项目管理员'
WHERE parent_id = 'pro_111111';

UPDATE pcs_prc_role
SET name = '项目成员'
WHERE parent_id = '4';


-- 分别为每个DELETE语句定义相同的CTE
WITH RECURSIVE all_roles_to_delete (id) AS (
    SELECT id FROM pcs_prc_role WHERE id = '3'
    UNION ALL
    SELECT r.id FROM pcs_prc_role r
    JOIN all_roles_to_delete artd ON r.parent_id = artd.id
)
DELETE FROM pcs_prc_dm_role
WHERE role_id IN (SELECT id FROM all_roles_to_delete);

WITH RECURSIVE all_roles_to_delete (id) AS (
    SELECT id FROM pcs_prc_role WHERE id = '3'
    UNION ALL
    SELECT r.id FROM pcs_prc_role r
    JOIN all_roles_to_delete artd ON r.parent_id = artd.id
)
DELETE FROM pcs_prc_role
WHERE id IN (SELECT id FROM all_roles_to_delete);
