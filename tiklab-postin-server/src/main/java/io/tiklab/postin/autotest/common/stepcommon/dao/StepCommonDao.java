package io.tiklab.postin.autotest.common.stepcommon.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.common.stepcommon.entity.StepCommonEntity;
import io.tiklab.postin.autotest.common.stepcommon.model.StepCommonQuery;

/**
 * 公共步骤 数据访问
 */
@Repository
public class StepCommonDao {

    private static Logger logger = LoggerFactory.getLogger(StepCommonDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建公共步骤
     * @param stepCommonEntity
     * @return
     */
    public String createStepCommon(StepCommonEntity stepCommonEntity) {
        return jpaTemplate.save(stepCommonEntity,String.class);
    }

    /**
     * 更新公共步骤
     * @param stepCommonEntity
     */
    public void updateStepCommon(StepCommonEntity stepCommonEntity){
        jpaTemplate.update(stepCommonEntity);
    }

    /**
     * 更新步骤的父级ID和排序（使用原生SQL确保null值也能正确更新）
     * @param stepId 步骤ID
     * @param parentId 父级ID（可以为null）
     * @param sort 排序值
     */
    public void updateStepParentIdAndSort(String stepId, String parentId, Integer sort) {
        try {
            String sql;
            Object[] params;
            if (parentId == null) {
                sql = "UPDATE autotest_case_step_common SET parent_id = NULL, sort = ? WHERE id = ?";
                params = new Object[]{sort, stepId};
            } else {
                sql = "UPDATE autotest_case_step_common SET parent_id = ?, sort = ? WHERE id = ?";
                params = new Object[]{parentId, sort, stepId};
            }
            jpaTemplate.getJdbcTemplate().update(sql, params);
        } catch (Exception e) {
            logger.error("更新步骤父级ID和排序失败", e);
        }
    }

    /**
     * 删除公共步骤
     * @param id
     */
    public void deleteStepCommon(String id){
        jpaTemplate.delete(StepCommonEntity.class,id);
    }

    public void deleteStepCommon(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找公共步骤
     * @param id
     * @return
     */
    public StepCommonEntity findStepCommon(String id){
        return jpaTemplate.findOne(StepCommonEntity.class,id);
    }

    /**
     * 查询步骤数量
     * @param caseId
     * @return
     */
    public int findStepNum(String caseId) {
        String stepSql = "Select count(1) as total from autotest_case_step_common where case_id = '" + caseId+ "'";
        Integer stepNum = jpaTemplate.getJdbcTemplate().queryForObject(stepSql, new Object[]{}, Integer.class);

        return stepNum;
    }



    /**
    * 查找所有公共步骤
    * @return
    */
    public List<StepCommonEntity> findAllStepCommon() {
        return jpaTemplate.findAll(StepCommonEntity.class);
    }

    public List<StepCommonEntity> findStepCommonList(List<String> idList) {
        return jpaTemplate.findList(StepCommonEntity.class,idList);
    }

    /**
     * 根据查询参数查询公共步骤列表
     * @param stepCommonQuery
     * @return
     */
    public List<StepCommonEntity> findStepCommonList(StepCommonQuery stepCommonQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(StepCommonEntity.class)
                .eq("caseId",stepCommonQuery.getCaseId())
                .eq("parentId",stepCommonQuery.getParentId())
                .eq("type",stepCommonQuery.getCaseType())
                .orders(stepCommonQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, StepCommonEntity.class);
    }

    /**
     * 根据查询参数按分页查询公共步骤
     * @param stepCommonQuery
     * @return
     */
    public Pagination<StepCommonEntity> findStepCommonPage(StepCommonQuery stepCommonQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(StepCommonEntity.class)
                .eq("caseId",stepCommonQuery.getCaseId())
                .eq("parentId",stepCommonQuery.getParentId())
                .orders(stepCommonQuery.getOrderParams())
                .pagination(stepCommonQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, StepCommonEntity.class);
    }


    /**
     * 查询指定父节点下最大的排序值
     * @param caseId
     * @param parentId
     * @return
     */
    public Integer biggerSort(String caseId, String parentId) {
        String sql;
        Object[] params;

        if (parentId == null || parentId.trim().isEmpty()) {
            // parentId 为 null 或 "" 都视为没有父步骤
            sql = "SELECT COALESCE(MAX(sort), 0) FROM autotest_case_step_common WHERE case_id = ? AND (parent_id IS NULL OR parent_id = '')";
            params = new Object[]{caseId};
        } else {
            sql = "SELECT COALESCE(MAX(sort), 0) FROM autotest_case_step_common WHERE case_id = ? AND parent_id = ?";
            params = new Object[]{caseId, parentId};
        }

        Integer maxSort = jpaTemplate.getJdbcTemplate().queryForObject(sql, params, Integer.class);
        return maxSort != null ? maxSort : 0;
    }


    /**
     * 根据父级ID查询步骤列表
     * @param caseId 用例ID
     * @param parentId 父级ID
     * @return 步骤列表
     */
    public List<StepCommonEntity> findStepCommonListByParentId(String caseId, String parentId) {
        String sql;
        Object[] params;
        if (parentId != null) {
            sql = "SELECT * FROM autotest_case_step_common WHERE case_id = ? AND parent_id = ? ORDER BY sort ASC";
            params = new Object[]{caseId, parentId};
        } else {
            sql = "SELECT * FROM autotest_case_step_common WHERE case_id = ? AND parent_id IS NULL ORDER BY sort ASC";
            params = new Object[]{caseId};
        }

        return jpaTemplate.getJdbcTemplate().query(sql, params, (rs, rowNum) -> {
            StepCommonEntity entity = new StepCommonEntity();
            entity.setId(rs.getString("id"));
            entity.setCaseId(rs.getString("case_id"));
            entity.setParentId(rs.getString("parent_id"));
            entity.setType(rs.getString("type"));
            entity.setSort(rs.getInt("sort"));
            entity.setCreateTime(rs.getTimestamp("create_time"));
            return entity;
        });
    }

    /**
     * 批量更新排序（删除后）
     * @param caseId 用例ID
     * @param parentId 父级ID
     * @param deletedSort 被删除的排序值
     */
    public void updateSortAfterDelete(String caseId, String parentId, int deletedSort) {
        try {
            String sql;
            Object[] params;
            if (parentId == null || parentId.trim().isEmpty()) {
                sql = "UPDATE autotest_case_step_common SET sort = sort - 1 WHERE case_id = ? AND (parent_id IS NULL OR parent_id = '') AND sort > ?";
                params = new Object[]{caseId, deletedSort};
            } else {
                sql = "UPDATE autotest_case_step_common SET sort = sort - 1 WHERE case_id = ? AND parent_id = ? AND sort > ?";
                params = new Object[]{caseId, parentId, deletedSort};
            }
            jpaTemplate.getJdbcTemplate().update(sql, params);
        } catch (Exception e) {
            logger.error("删除后更新排序失败", e);
        }
    }

    /**
     * 批量更新排序（同级拖拽排序）
     * @param caseId 用例ID
     * @param parentId 父级ID
     * @param oldSort 原排序值
     * @param newSort 新排序值
     */
    public void updateSortAfterDrag(String caseId, String parentId, int oldSort, int newSort) {
        try {
            String sql;
            Object[] params;
            
            if (oldSort < newSort) {
                // 向下拖拽：将oldSort+1到newSort之间的记录排序值减1
                if (parentId == null || parentId.trim().isEmpty()) {
                    sql = "UPDATE autotest_case_step_common SET sort = sort - 1 WHERE case_id = ? AND (parent_id IS NULL OR parent_id = '') AND sort > ? AND sort <= ?";
                    params = new Object[]{caseId, oldSort, newSort};
                } else {
                    sql = "UPDATE autotest_case_step_common SET sort = sort - 1 WHERE case_id = ? AND parent_id = ? AND sort > ? AND sort <= ?";
                    params = new Object[]{caseId, parentId, oldSort, newSort};
                }
            } else if (oldSort > newSort) {
                // 向上拖拽：将newSort到oldSort-1之间的记录排序值加1
                if (parentId == null || parentId.trim().isEmpty()) {
                    sql = "UPDATE autotest_case_step_common SET sort = sort + 1 WHERE case_id = ? AND (parent_id IS NULL OR parent_id = '') AND sort >= ? AND sort < ?";
                    params = new Object[]{caseId, newSort, oldSort};
                } else {
                    sql = "UPDATE autotest_case_step_common SET sort = sort + 1 WHERE case_id = ? AND parent_id = ? AND sort >= ? AND sort < ?";
                    params = new Object[]{caseId, parentId, newSort, oldSort};
                }
            } else {
                // 排序值相同，无需更新
                return;
            }
            
            jpaTemplate.getJdbcTemplate().update(sql, params);
        } catch (Exception e) {
            logger.error("拖拽排序更新失败", e);
        }
    }

    /**
     * 批量更新排序（源层级：移除步骤后）
     * @param caseId 用例ID
     * @param parentId 父级ID
     * @param deletedSort 被删除的排序值
     */
    public void updateSortAfterRemoveFromLevel(String caseId, String parentId, int deletedSort) {
        try {
            String sql;
            Object[] params;
            if (parentId == null || parentId.trim().isEmpty()) {
                sql = "UPDATE autotest_case_step_common SET sort = sort - 1 WHERE case_id = ? AND (parent_id IS NULL OR parent_id = '') AND sort > ?";
                params = new Object[]{caseId, deletedSort};
            } else {
                sql = "UPDATE autotest_case_step_common SET sort = sort - 1 WHERE case_id = ? AND parent_id = ? AND sort > ?";
                params = new Object[]{caseId, parentId, deletedSort};
            }
            jpaTemplate.getJdbcTemplate().update(sql, params);
        } catch (Exception e) {
            logger.error("从层级移除后更新排序失败", e);
        }
    }

    /**
     * 批量更新排序（目标层级：插入步骤前）
     * @param caseId 用例ID
     * @param parentId 父级ID
     * @param targetSort 目标排序值
     */
    public void updateSortBeforeInsertToLevel(String caseId, String parentId, int targetSort) {
        try {
            String sql;
            Object[] params;
            if (parentId == null || parentId.trim().isEmpty()) {
                sql = "UPDATE autotest_case_step_common SET sort = sort + 1 WHERE case_id = ? AND (parent_id IS NULL OR parent_id = '') AND sort >= ?";
                params = new Object[]{caseId, targetSort};
            } else {
                sql = "UPDATE autotest_case_step_common SET sort = sort + 1 WHERE case_id = ? AND parent_id = ? AND sort >= ?";
                params = new Object[]{caseId, parentId, targetSort};
            }
            jpaTemplate.getJdbcTemplate().update(sql, params);
        } catch (Exception e) {
            logger.error("插入层级前更新排序失败", e);
        }
    }

}