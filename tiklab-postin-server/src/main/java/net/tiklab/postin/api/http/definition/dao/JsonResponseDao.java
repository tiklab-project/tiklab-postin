package net.tiklab.postin.api.http.definition.dao;

import net.tiklab.postin.api.http.definition.entity.HttpApiEntity;
import net.tiklab.postin.api.http.definition.model.JsonResponseQuery;
import net.tiklab.core.page.Pagination;
import net.tiklab.postin.api.http.definition.entity.JsonResponseEntity;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class JsonResponseDao {

    private static Logger logger = LoggerFactory.getLogger(JsonResponseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
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
     * 更新用户
     * @param jsonResponseEntity
     */
    public void updateJsonResponse(JsonResponseEntity jsonResponseEntity){
        jpaTemplate.update(jsonResponseEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteJsonResponse(String id){
        jpaTemplate.delete(JsonResponseEntity.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteJsonResponseList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public JsonResponseEntity findJsonResponse(String id){
        return jpaTemplate.findOne(JsonResponseEntity.class,id);
    }

    /**
    * findAllJsonResponse
    * @return
    */
    public List<JsonResponseEntity> findAllJsonResponse() {
        return jpaTemplate.findAll(JsonResponseEntity.class);
    }

    public List<JsonResponseEntity> findJsonResponseList(JsonResponseQuery jsonResponseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonResponseEntity.class)
                .eq("httpId", jsonResponseQuery.getHttpId())
                .orders(jsonResponseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, JsonResponseEntity.class);
    }

    public Pagination<JsonResponseEntity> findJsonResponsePage(JsonResponseQuery jsonResponseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonResponseEntity.class)
                .eq("httpId", jsonResponseQuery.getHttpId())
                .pagination(jsonResponseQuery.getPageParam())
                .orders(jsonResponseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, JsonResponseEntity.class);
    }
}