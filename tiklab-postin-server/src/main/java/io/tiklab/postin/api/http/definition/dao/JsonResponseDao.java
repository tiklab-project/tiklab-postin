package io.tiklab.postin.api.http.definition.dao;

import io.tiklab.postin.api.http.definition.entity.HttpApiEntity;
import io.tiklab.postin.api.http.definition.entity.JsonResponseEntity;
import io.tiklab.postin.api.http.definition.model.JsonResponseQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定义
 * http协议
 * 响应json 数据访问
 */
@Repository
public class JsonResponseDao {

    private static Logger logger = LoggerFactory.getLogger(JsonResponseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建响应json
     * @param jsonResponseEntity
     * @return
     */
    public String createJsonResponse(JsonResponseEntity jsonResponseEntity) {
        return jpaTemplate.save(jsonResponseEntity,String.class);
    }

    public List<HttpApiEntity> findJsonResponseList(List<String> idList) {
        return jpaTemplate.findList(HttpApiEntity.class,idList);
    }

    /**
     * 更新响应json
     * @param jsonResponseEntity
     */
    public void updateJsonResponse(JsonResponseEntity jsonResponseEntity){
        jpaTemplate.update(jsonResponseEntity);
    }

    /**
     * 删除响应json
     * @param id
     */
    public void deleteJsonResponse(String id){
        jpaTemplate.delete(JsonResponseEntity.class,id);
    }

    /**
     * 通过条件删除响应json
     * @param deleteCondition
     */
    public void deleteJsonResponseList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找响应json
     * @param id
     * @return
     */
    public JsonResponseEntity findJsonResponse(String id){
        return jpaTemplate.findOne(JsonResponseEntity.class,id);
    }

    /**
    * 查找所有响应json
    * @return
    */
    public List<JsonResponseEntity> findAllJsonResponse() {
        return jpaTemplate.findAll(JsonResponseEntity.class);
    }

    /**
     * 根据查询参数查找响应json列表
     * @param jsonResponseQuery
     * @return
     */
    public List<JsonResponseEntity> findJsonResponseList(JsonResponseQuery jsonResponseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonResponseEntity.class)
                .eq("httpId", jsonResponseQuery.getHttpId())
                .orders(jsonResponseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, JsonResponseEntity.class);
    }

    /**
     * 根据查询参数按分页查找响应json列表
     * @param jsonResponseQuery
     * @return
     */
    public Pagination<JsonResponseEntity> findJsonResponsePage(JsonResponseQuery jsonResponseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonResponseEntity.class)
                .eq("httpId", jsonResponseQuery.getHttpId())
                .pagination(jsonResponseQuery.getPageParam())
                .orders(jsonResponseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, JsonResponseEntity.class);
    }
}