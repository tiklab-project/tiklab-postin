package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.postin.autotest.http.unit.cases.entity.ResponseResultUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.ResponseResultUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 响应结果 数据访问
 */
@Repository
public class ResponseResultUnitDao {

    private static Logger logger = LoggerFactory.getLogger(ResponseResultUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建响应结果
     * @param responseResultUnitEntity
     * @return
     */
    public String createResponseResult(ResponseResultUnitEntity responseResultUnitEntity) {
        return jpaTemplate.save(responseResultUnitEntity,String.class);
    }

    /**
     * 更新响应结果
     * @param responseResultUnitEntity
     */
    public void updateResponseResult(ResponseResultUnitEntity responseResultUnitEntity){
        jpaTemplate.update(responseResultUnitEntity);
    }

    /**
     * 删除响应结果
     * @param id
     */
    public void deleteResponseResult(String id){
        jpaTemplate.delete(ResponseResultUnitEntity.class,id);
    }

    public void deleteResponseResult(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找响应结果
     * @param id
     * @return
     */
    public ResponseResultUnitEntity findResponseResult(String id){
        return jpaTemplate.findOne(ResponseResultUnitEntity.class,id);
    }

    /**
    * 查找所有响应结果
    * @return
    */
    public List<ResponseResultUnitEntity> findAllResponseResult() {
        return jpaTemplate.findAll(ResponseResultUnitEntity.class);
    }

    public List<ResponseResultUnitEntity> findResponseResultList(List<String> idList) {
        return jpaTemplate.findList(ResponseResultUnitEntity.class,idList);
    }

    /**
     * 根据查询参数查询响应结果列表
     * @param responseResultUnitQuery
     * @return
     */
    public List<ResponseResultUnitEntity> findResponseResultList(ResponseResultUnitQuery responseResultUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseResultUnitEntity.class)
                .eq("apiUnitId", responseResultUnitQuery.getApiUnitId())
                .get();
        return jpaTemplate.findList(queryCondition, ResponseResultUnitEntity.class);
    }

    /**
     * 根据查询参数按分页查询响应结果
     * @param responseResultUnitQuery
     * @return
     */
    public Pagination<ResponseResultUnitEntity> findResponseResultPage(ResponseResultUnitQuery responseResultUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseResultUnitEntity.class)
                .eq("apiUnitId", responseResultUnitQuery.getApiUnitId())
                .get();
        return jpaTemplate.findPage(queryCondition, ResponseResultUnitEntity.class);
    }
}