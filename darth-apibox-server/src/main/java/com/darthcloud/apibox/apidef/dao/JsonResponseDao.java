package com.darthcloud.apibox.apidef.dao;

import com.darthcloud.apibox.apidef.model.JsonResponseQuery;
import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.apidef.entity.JsonResponsePo;
import com.darthcloud.dal.jpa.JpaTemplate;
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

    public Pagination<List<JsonResponsePo>> findJsonResponsePage(JsonResponseQuery jsonResponseQuery) {
        return jpaTemplate.findPage(JsonResponsePo.class,jsonResponseQuery);
    }
}