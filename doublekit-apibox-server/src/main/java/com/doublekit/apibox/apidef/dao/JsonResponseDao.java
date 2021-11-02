package com.doublekit.apibox.apidef.dao;

import com.doublekit.apibox.apidef.entity.JsonParamEntity;
import com.doublekit.apibox.apidef.entity.MethodEntity;
import com.doublekit.apibox.apidef.model.JsonResponseQuery;
import com.doublekit.common.Pagination;
import com.doublekit.apibox.apidef.entity.JsonResponseEntity;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.builder.deletelist.condition.DeleteCondition;
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

    public List<MethodEntity> findJsonResponseList(List<String> idList) {
        return jpaTemplate.findList(MethodEntity.class,idList);
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
        jpaTemplate.delete(JsonResponseEntity.class,deleteCondition);
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
        return jpaTemplate.findList(JsonResponseEntity.class,jsonResponseQuery);
    }

    public Pagination<JsonResponseEntity> findJsonResponsePage(JsonResponseQuery jsonResponseQuery) {
        return jpaTemplate.findPage(JsonResponseEntity.class,jsonResponseQuery);
    }
}