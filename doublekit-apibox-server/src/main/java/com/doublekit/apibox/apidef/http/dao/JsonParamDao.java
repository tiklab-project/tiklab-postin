package com.doublekit.apibox.apidef.http.dao;

import com.doublekit.apibox.apidef.http.entity.JsonParamEntity;
import com.doublekit.apibox.apidef.http.model.JsonParamQuery;
import com.doublekit.core.page.Pagination;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.criterial.condition.DeleteCondition;
import com.doublekit.dal.jpa.criterial.condition.QueryCondition;
import com.doublekit.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class JsonParamDao{

    private static Logger logger = LoggerFactory.getLogger(JsonParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param jsonParamPo
     * @return
     */
    public String createJsonParam(JsonParamEntity jsonParamPo) {
        return jpaTemplate.save(jsonParamPo,String.class);
    }

    /**
     * 更新用户
     * @param jsonParamPo
     */
    public void updateJsonParam(JsonParamEntity jsonParamPo){
        jpaTemplate.update(jsonParamPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteJsonParam(String id){
        jpaTemplate.delete(JsonParamEntity.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteJsonParamList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public JsonParamEntity findJsonParam(String id){
        return jpaTemplate.findOne(JsonParamEntity.class,id);
    }

    /**
    * findAllJsonParam
    * @return
    */
    public List<JsonParamEntity> findAllJsonParam() {
        return jpaTemplate.findAll(JsonParamEntity.class);
    }

    public List<JsonParamEntity> findJsonParamList(List<String> idList) {
        return jpaTemplate.findList(JsonParamEntity.class,idList);
    }

    public List<JsonParamEntity> findJsonParamList(JsonParamQuery jsonParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonParamEntity.class)
                .eq("httpId", jsonParamQuery.getHttpId())
                .orders(jsonParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, JsonParamEntity.class);
    }

    public Pagination<JsonParamEntity> findJsonParamPage(JsonParamQuery jsonParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(JsonParamEntity.class)
                .eq("httpId", jsonParamQuery.getHttpId())
                .pagination(jsonParamQuery.getPageParam())
                .orders(jsonParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, JsonParamEntity.class);
    }
}