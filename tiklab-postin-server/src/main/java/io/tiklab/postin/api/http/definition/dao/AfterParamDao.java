package io.tiklab.postin.api.http.definition.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.api.http.definition.entity.AfterParamEntity;
import io.tiklab.postin.api.http.definition.model.AfterParamQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 后置操作 数据访问
 */
@Repository
public class AfterParamDao {

    private static Logger logger = LoggerFactory.getLogger(AfterParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建后置操作
     * @param afterParamEntity
     * @return
     */
    public String createAfterParam(AfterParamEntity afterParamEntity) {
        return jpaTemplate.save(afterParamEntity,String.class);
    }

    /**
     * 更新后置操作
     * @param afterParamEntity
     */
    public void updateAfterParam(AfterParamEntity afterParamEntity){
        jpaTemplate.update(afterParamEntity);
    }

    /**
     * 删除后置操作
     * @param id
     */
    public void deleteAfterParam(String id){
        jpaTemplate.delete(AfterParamEntity.class,id);
    }

    /**
     * 通过条件删除后置操作
     * @param deleteCondition
     */
    public void deleteAfterParamList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找后置操作
     * @param id
     * @return
     */
    public AfterParamEntity findAfterParam(String id){
        return jpaTemplate.findOne(AfterParamEntity.class,id);
    }

    /**
    * 查找所有后置操作
    * @return
    */
    public List<AfterParamEntity> findAllAfterParam() {
        return jpaTemplate.findAll(AfterParamEntity.class);
    }

    /**
     * 根据查询参数查找后置操作列表
     * @param afterParamQuery
     * @return
     */
    public List<AfterParamEntity> findAfterParamList(AfterParamQuery afterParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AfterParamEntity.class)
                .eq("apiId", afterParamQuery.getApiId())
                .orders(afterParamQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, AfterParamEntity.class);
    }

    /**
     * 根据查询参数按分页查找后置操作列表
     * @param afterParamQuery
     * @return
     */
    public Pagination<AfterParamEntity> findAfterParamPage(AfterParamQuery afterParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AfterParamEntity.class)
                .eq("apiId", afterParamQuery.getApiId())
                .pagination(afterParamQuery.getPageParam())
                .orders(afterParamQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, AfterParamEntity.class);
    }


    public int bigSort(String apiId) {
        try {
            String sql = "SELECT COALESCE(MAX(sort), 0) FROM postin_api_request_after WHERE api_id = ?";
            Integer result = jpaTemplate.getJdbcTemplate().queryForObject(sql, Integer.class, apiId);
            return result != null ? result : 0;
        } catch (Exception e) {
            return 0;
        }
    }
}