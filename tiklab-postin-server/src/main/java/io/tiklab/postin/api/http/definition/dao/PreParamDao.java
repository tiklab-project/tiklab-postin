package io.tiklab.postin.api.http.definition.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.api.http.definition.entity.PreParamEntity;
import io.tiklab.postin.api.http.definition.model.PreParamQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 前置操作 数据访问
 */
@Repository
public class PreParamDao {

    private static Logger logger = LoggerFactory.getLogger(PreParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建前置操作
     * @param preParamEntity
     * @return
     */
    public String createPreParam(PreParamEntity preParamEntity) {
        return jpaTemplate.save(preParamEntity,String.class);
    }

    /**
     * 更新前置操作
     * @param preParamEntity
     */
    public void updatePreParam(PreParamEntity preParamEntity){
        jpaTemplate.update(preParamEntity);
    }

    /**
     * 删除前置操作
     * @param id
     */
    public void deletePreParam(String id){
        jpaTemplate.delete(PreParamEntity.class,id);
    }

    /**
     * 通过条件删除前置操作
     * @param deleteCondition
     */
    public void deletePreParamList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找前置操作
     * @param id
     * @return
     */
    public PreParamEntity findPreParam(String id){
        return jpaTemplate.findOne(PreParamEntity.class,id);
    }

    /**
    * 查找所有前置操作
    * @return
    */
    public List<PreParamEntity> findAllPreParam() {
        return jpaTemplate.findAll(PreParamEntity.class);
    }

    /**
     * 根据查询参数查找前置操作列表
     * @param preParamQuery
     * @return
     */
    public List<PreParamEntity> findPreParamList(PreParamQuery preParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PreParamEntity.class)
                .eq("apiId", preParamQuery.getApiId())
                .orders(preParamQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, PreParamEntity.class);
    }

    /**
     * 根据查询参数按分页查找前置操作列表
     * @param preParamQuery
     * @return
     */
    public Pagination<PreParamEntity> findPreParamPage(PreParamQuery preParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PreParamEntity.class)
                .eq("apiId", preParamQuery.getApiId())
                .pagination(preParamQuery.getPageParam())
                .orders(preParamQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, PreParamEntity.class);
    }

    public int bigSort(String apiId) {
        try {
            String sql = "SELECT COALESCE(MAX(sort), 0) FROM postin_api_request_pre WHERE api_id = ?";
            Integer result = jpaTemplate.getJdbcTemplate().queryForObject(sql, Integer.class, apiId);
            return result != null ? result : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 批量更新排序
     * @param apiId API ID
     * @param deletedSort 被删除的排序值
     */
    public void updateSortAfterDelete(String apiId, int deletedSort) {
        try {
            String sql = "UPDATE postin_api_request_pre SET sort = sort - 1 WHERE api_id = ? AND sort > ?";
            jpaTemplate.getJdbcTemplate().update(sql, apiId, deletedSort);
        } catch (Exception e) {
            logger.error("更新前置操作排序失败", e);
        }
    }

    /**
     * 批量更新排序（拖拽排序）
     * @param apiId API ID
     * @param oldSort 原排序值
     * @param newSort 新排序值
     */
    public void updateSortAfterDrag(String apiId, int oldSort, int newSort) {
        try {
            if (oldSort < newSort) {
                // 向下拖拽：将oldSort+1到newSort之间的记录排序值减1
                String sql = "UPDATE postin_api_request_pre SET sort = sort - 1 WHERE api_id = ? AND sort > ? AND sort <= ?";
                jpaTemplate.getJdbcTemplate().update(sql, apiId, oldSort, newSort);
            } else if (oldSort > newSort) {
                // 向上拖拽：将newSort到oldSort-1之间的记录排序值加1
                String sql = "UPDATE postin_api_request_pre SET sort = sort + 1 WHERE api_id = ? AND sort >= ? AND sort < ?";
                jpaTemplate.getJdbcTemplate().update(sql, apiId, newSort, oldSort);
            }
        } catch (Exception e) {
            logger.error("拖拽排序更新失败", e);
        }
    }
}