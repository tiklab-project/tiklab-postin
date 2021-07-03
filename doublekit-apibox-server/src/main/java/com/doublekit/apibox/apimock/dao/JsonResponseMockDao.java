package com.doublekit.apibox.apimock.dao;

import com.doublekit.apibox.apimock.entity.JsonResponseMockPo;
import com.doublekit.apibox.apimock.model.JsonResponseMockQuery;
import com.doublekit.common.Pagination;
import com.doublekit.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class JsonResponseMockDao{

    private static Logger logger = LoggerFactory.getLogger(JsonResponseMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param jsonResponseMockPo
     * @return
     */
    public String createJsonResponseMock(JsonResponseMockPo jsonResponseMockPo) {
        return jpaTemplate.save(jsonResponseMockPo,String.class);
    }

    /**
     * 更新用户
     * @param jsonResponseMockPo
     */
    public void updateJsonResponseMock(JsonResponseMockPo jsonResponseMockPo){
        jpaTemplate.update(jsonResponseMockPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteJsonResponseMock(String id){
        jpaTemplate.delete(JsonResponseMockPo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public JsonResponseMockPo findJsonResponseMock(String id){
        return jpaTemplate.findOne(JsonResponseMockPo.class,id);
    }

    /**
    * findAllJsonResponseMock
    * @return
    */
    public List<JsonResponseMockPo> findAllJsonResponseMock() {
        return jpaTemplate.findAll(JsonResponseMockPo.class);
    }

    public List<JsonResponseMockPo> findJsonResponseMockList(JsonResponseMockQuery jsonResponseMockQuery) {
        return jpaTemplate.findList(JsonResponseMockPo.class,jsonResponseMockQuery);
    }

    public Pagination<JsonResponseMockPo> findJsonResponseMockPage(JsonResponseMockQuery jsonResponseMockQuery) {
        return jpaTemplate.findPage(JsonResponseMockPo.class,jsonResponseMockQuery);
    }
}