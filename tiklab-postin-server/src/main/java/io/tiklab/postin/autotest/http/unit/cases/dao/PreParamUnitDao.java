package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.http.unit.cases.entity.PreParamUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.PreParamUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 前置操作 数据访问
 */
@Repository
public class PreParamUnitDao {

    private static Logger logger = LoggerFactory.getLogger(PreParamUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建前置操作
     * @param preParamUnitEntity
     * @return
     */
    public String createPreParamUnit(PreParamUnitEntity preParamUnitEntity) {
        return jpaTemplate.save(preParamUnitEntity,String.class);
    }

    /**
     * 更新前置操作
     * @param preParamUnitEntity
     */
    public void updatePreParamUnit(PreParamUnitEntity preParamUnitEntity){
        jpaTemplate.update(preParamUnitEntity);
    }

    /**
     * 删除前置操作
     * @param id
     */
    public void deletePreParamUnit(String id){
        jpaTemplate.delete(PreParamUnitEntity.class,id);
    }

    /**
     * 通过条件删除前置操作
     * @param deleteCondition
     */
    public void deletePreParamUnitList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找前置操作
     * @param id
     * @return
     */
    public PreParamUnitEntity findPreParamUnit(String id){
        return jpaTemplate.findOne(PreParamUnitEntity.class,id);
    }

    /**
    * 查找所有前置操作
    * @return
    */
    public List<PreParamUnitEntity> findAllPreParamUnit() {
        return jpaTemplate.findAll(PreParamUnitEntity.class);
    }

    /**
     * 根据查询参数查找前置操作列表
     * @param preParamUnitQuery
     * @return
     */
    public List<PreParamUnitEntity> findPreParamUnitList(PreParamUnitQuery preParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PreParamUnitEntity.class)
                .eq("apiUnitId", preParamUnitQuery.getApiUnitId())
                .orders(preParamUnitQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, PreParamUnitEntity.class);
    }

    /**
     * 根据查询参数按分页查找前置操作列表
     * @param preParamUnitQuery
     * @return
     */
    public Pagination<PreParamUnitEntity> findPreParamUnitPage(PreParamUnitQuery preParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PreParamUnitEntity.class)
                .eq("apiUnitId", preParamUnitQuery.getApiUnitId())
                .pagination(preParamUnitQuery.getPageParam())
                .orders(preParamUnitQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, PreParamUnitEntity.class);
    }

    public int bigSort(String apiUnitId) {
        try {
            String sql = "SELECT COALESCE(MAX(sort), 0) FROM autotest_action_pre WHERE api_unit_id = ?";
            Integer result = jpaTemplate.getJdbcTemplate().queryForObject(sql, Integer.class, apiUnitId);
            return result != null ? result : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 批量更新排序
     * @param apiUnitId API单元ID
     * @param deletedSort 被删除的排序值
     */
    public void updateSortAfterDelete(String apiUnitId, int deletedSort) {
        try {
            String sql = "UPDATE autotest_action_pre SET sort = sort - 1 WHERE api_unit_id = ? AND sort > ?";
            jpaTemplate.getJdbcTemplate().update(sql, apiUnitId, deletedSort);
        } catch (Exception e) {
            logger.error("更新前置操作单元排序失败", e);
        }
    }

    /**
     * 批量更新排序（拖拽排序）
     * @param apiUnitId API单元ID
     * @param oldSort 原排序值
     * @param newSort 新排序值
     */
    public void updateSortAfterDrag(String apiUnitId, int oldSort, int newSort) {
        try {
            if (oldSort < newSort) {
                // 向下拖拽：将oldSort+1到newSort之间的记录排序值减1
                String sql = "UPDATE autotest_action_pre SET sort = sort - 1 WHERE api_unit_id = ? AND sort > ? AND sort <= ?";
                jpaTemplate.getJdbcTemplate().update(sql, apiUnitId, oldSort, newSort);
            } else if (oldSort > newSort) {
                // 向上拖拽：将newSort到oldSort-1之间的记录排序值加1
                String sql = "UPDATE autotest_action_pre SET sort = sort + 1 WHERE api_unit_id = ? AND sort >= ? AND sort < ?";
                jpaTemplate.getJdbcTemplate().update(sql, apiUnitId, newSort, oldSort);
            }
        } catch (Exception e) {
            logger.error("拖拽排序更新失败", e);
        }
    }
}