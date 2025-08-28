package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.autotest.http.unit.cases.entity.AfterParamUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.AfterParamUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 后置操作 数据访问
 */
@Repository
public class AfterParamUnitDao {

    private static Logger logger = LoggerFactory.getLogger(AfterParamUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建后置操作
     * @param afterParamUnitEntity
     * @return
     */
    public String createAfterParamUnit(AfterParamUnitEntity afterParamUnitEntity) {
        return jpaTemplate.save(afterParamUnitEntity,String.class);
    }

    /**
     * 更新后置操作
     * @param afterParamUnitEntity
     */
    public void updateAfterParamUnit(AfterParamUnitEntity afterParamUnitEntity){
        jpaTemplate.update(afterParamUnitEntity);
    }

    /**
     * 删除后置操作
     * @param id
     */
    public void deleteAfterParamUnit(String id){
        jpaTemplate.delete(AfterParamUnitEntity.class,id);
    }

    /**
     * 通过条件删除后置操作
     * @param deleteCondition
     */
    public void deleteAfterParamUnitList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找后置操作
     * @param id
     * @return
     */
    public AfterParamUnitEntity findAfterParamUnit(String id){
        return jpaTemplate.findOne(AfterParamUnitEntity.class,id);
    }

    /**
    * 查找所有后置操作
    * @return
    */
    public List<AfterParamUnitEntity> findAllAfterParamUnit() {
        return jpaTemplate.findAll(AfterParamUnitEntity.class);
    }

    /**
     * 根据查询参数查找后置操作列表
     * @param afterParamUnitQuery
     * @return
     */
    public List<AfterParamUnitEntity> findAfterParamUnitList(AfterParamUnitQuery afterParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AfterParamUnitEntity.class)
                .eq("apiUnitId", afterParamUnitQuery.getApiUnitId())
                .orders(afterParamUnitQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, AfterParamUnitEntity.class);
    }

    /**
     * 根据查询参数按分页查找后置操作列表
     * @param afterParamUnitQuery
     * @return
     */
    public Pagination<AfterParamUnitEntity> findAfterParamUnitPage(AfterParamUnitQuery afterParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AfterParamUnitEntity.class)
                .eq("apiUnitId", afterParamUnitQuery.getApiUnitId())
                .pagination(afterParamUnitQuery.getPageParam())
                .orders(afterParamUnitQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, AfterParamUnitEntity.class);
    }


    public int bigSort(String apiUnitId) {
        try {
            String sql = "SELECT COALESCE(MAX(sort), 0) FROM postin_api_request_after WHERE api_unit_id = ?";
            Integer result = jpaTemplate.getJdbcTemplate().queryForObject(sql, Integer.class, apiUnitId);
            return result != null ? result : 0;
        } catch (Exception e) {
            return 0;
        }
    }
}