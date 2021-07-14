package com.doublekit.apibox.apidef.dao;

import com.doublekit.apibox.apidef.entity.JsonParamPo;
import com.doublekit.apibox.apidef.entity.MethodPo;
import com.doublekit.apibox.apidef.model.JsonResponseQuery;
import com.doublekit.common.Pagination;
import com.doublekit.apibox.apidef.entity.JsonResponsePo;
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
     * @param jsonResponsePo
     * @return
     */
    public String createJsonResponse(JsonResponsePo jsonResponsePo) {
        return jpaTemplate.save(jsonResponsePo,String.class);
    }

    public List<MethodPo> findJsonResponseList(List<String> idList) {
        return jpaTemplate.findList(MethodPo.class,idList);
    }

    /**
     * 更新用户
     * @param jsonResponsePo
     */
    public void updateJsonResponse(JsonResponsePo jsonResponsePo){
        jpaTemplate.update(jsonResponsePo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteJsonResponse(String id){
        jpaTemplate.delete(JsonResponsePo.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteJsonResponseList(DeleteCondition deleteCondition){
        jpaTemplate.delete(JsonResponsePo.class,deleteCondition);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public JsonResponsePo findJsonResponse(String id){
        return jpaTemplate.findOne(JsonResponsePo.class,id);
    }

    /**
    * findAllJsonResponse
    * @return
    */
    public List<JsonResponsePo> findAllJsonResponse() {
        return jpaTemplate.findAll(JsonResponsePo.class);
    }

    public List<JsonResponsePo> findJsonResponseList(JsonResponseQuery jsonResponseQuery) {
        return jpaTemplate.findList(JsonResponsePo.class,jsonResponseQuery);
    }

    public Pagination<JsonResponsePo> findJsonResponsePage(JsonResponseQuery jsonResponseQuery) {
        return jpaTemplate.findPage(JsonResponsePo.class,jsonResponseQuery);
    }
}