package com.doublekit.apibox.apidef.dao;

import com.doublekit.apibox.apidef.entity.FormParamEntity;
import com.doublekit.apibox.apidef.entity.JsonParamEntity;
import com.doublekit.apibox.apidef.model.JsonParamQuery;
import com.doublekit.common.Pagination;
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
        jpaTemplate.delete(JsonParamEntity.class,deleteCondition);
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
        return jpaTemplate.findList(JsonParamEntity.class,jsonParamQuery);
    }

    public Pagination<JsonParamEntity> findJsonParamPage(JsonParamQuery jsonParamQuery) {
        return jpaTemplate.findPage(JsonParamEntity.class,jsonParamQuery);
    }
}