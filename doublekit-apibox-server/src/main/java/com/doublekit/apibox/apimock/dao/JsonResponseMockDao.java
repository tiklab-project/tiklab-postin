package com.doublekit.apibox.apimock.dao;

import com.doublekit.apibox.apimock.entity.JsonResponseMockEntity;
import com.doublekit.apibox.apimock.model.JsonResponseMockQuery;
import com.doublekit.common.page.Pagination;
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
     * @param jsonResponseMockEntity
     * @return
     */
    public String createJsonResponseMock(JsonResponseMockEntity jsonResponseMockEntity) {
        return jpaTemplate.save(jsonResponseMockEntity,String.class);
    }

    /**
     * 更新用户
     * @param jsonResponseMockEntity
     */
    public void updateJsonResponseMock(JsonResponseMockEntity jsonResponseMockEntity){
        jpaTemplate.update(jsonResponseMockEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteJsonResponseMock(String id){
        jpaTemplate.delete(JsonResponseMockEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public JsonResponseMockEntity findJsonResponseMock(String id){
        return jpaTemplate.findOne(JsonResponseMockEntity.class,id);
    }

    /**
    * findAllJsonResponseMock
    * @return
    */
    public List<JsonResponseMockEntity> findAllJsonResponseMock() {
        return jpaTemplate.findAll(JsonResponseMockEntity.class);
    }

    public List<JsonResponseMockEntity> findJsonResponseMockList(JsonResponseMockQuery jsonResponseMockQuery) {
        return jpaTemplate.findList(jsonResponseMockQuery, JsonResponseMockEntity.class);
    }

    public Pagination<JsonResponseMockEntity> findJsonResponseMockPage(JsonResponseMockQuery jsonResponseMockQuery) {
        return jpaTemplate.findPage(jsonResponseMockQuery, JsonResponseMockEntity.class);
    }
}