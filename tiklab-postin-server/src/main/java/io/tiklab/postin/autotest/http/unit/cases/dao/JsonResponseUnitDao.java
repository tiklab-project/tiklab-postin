package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.postin.autotest.http.unit.cases.entity.JsonResponseUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.JsonResponseUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 响应中json 数据访问
 */
@Repository
public class JsonResponseUnitDao {

    private static Logger logger = LoggerFactory.getLogger(JsonResponseUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建响应中json
     * @param jsonResponseUnitEntity
     * @return
     */
    public String createJsonResponse(JsonResponseUnitEntity jsonResponseUnitEntity) {
        return jpaTemplate.save(jsonResponseUnitEntity,String.class);
    }

    /**
     * 更新响应中json
     * @param jsonResponseUnitEntity
     */
    public void updateJsonResponse(JsonResponseUnitEntity jsonResponseUnitEntity){
        jpaTemplate.update(jsonResponseUnitEntity);
    }

    /**
     * 删除响应中json
     * @param id
     */
    public void deleteJsonResponse(String id){
        jpaTemplate.delete(JsonResponseUnitEntity.class,id);
    }

    public void deleteJsonResponse(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找响应中json
     * @param id
     * @return
     */
    public JsonResponseUnitEntity findJsonResponse(String id){
        return jpaTemplate.findOne(JsonResponseUnitEntity.class,id);
    }

    /**
    * 查找所有响应中json
    * @return
    */
    public List<JsonResponseUnitEntity> findAllJsonResponse() {
        return jpaTemplate.findAll(JsonResponseUnitEntity.class);
    }

    public List<JsonResponseUnitEntity> findJsonResponseList(List<String> idList) {
        return jpaTemplate.findList(JsonResponseUnitEntity.class,idList);
    }

    /**
     * 根据查询参数查找查询响应中json列表
     * @param jsonResponseUnitQuery
     * @return
     */
    public List<JsonResponseUnitEntity> findJsonResponseList(JsonResponseUnitQuery jsonResponseUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonResponseUnitEntity.class)
                .eq("apiUnitId", jsonResponseUnitQuery.getApiUnitId())
                .orders(jsonResponseUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, JsonResponseUnitEntity.class);
    }

    /**
     * 根据查询参数查找按分页查询响应中json
     * @param jsonResponseUnitQuery
     * @return
     */
    public Pagination<JsonResponseUnitEntity> findJsonResponsePage(JsonResponseUnitQuery jsonResponseUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonResponseUnitEntity.class)
                .eq("apiUnitId", jsonResponseUnitQuery.getApiUnitId())
                .orders(jsonResponseUnitQuery.getOrderParams())
                .pagination(jsonResponseUnitQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, JsonResponseUnitEntity.class);
    }
}