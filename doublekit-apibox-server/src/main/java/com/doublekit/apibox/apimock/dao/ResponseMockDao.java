package com.doublekit.apibox.apimock.dao;

import com.doublekit.common.page.Pagination;
import com.doublekit.apibox.apimock.entity.ResponseMockEntity;
import com.doublekit.apibox.apimock.model.ResponseMockQuery;
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
public class ResponseMockDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param responseMockEntity
     * @return
     */
    public String createResponseMock(ResponseMockEntity responseMockEntity) {
        return jpaTemplate.save(responseMockEntity,String.class);
    }

    /**
     * 更新用户
     * @param responseMockEntity
     */
    public void updateResponseMock(ResponseMockEntity responseMockEntity){
        jpaTemplate.update(responseMockEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteResponseMock(String id){
        jpaTemplate.delete(ResponseMockEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public ResponseMockEntity findResponseMock(String id){
        return jpaTemplate.findOne(ResponseMockEntity.class,id);
    }

    /**
    * findAllResponseMock
    * @return
    */
    public List<ResponseMockEntity> findAllResponseMock() {
        return jpaTemplate.findAll(ResponseMockEntity.class);
    }

    public List<ResponseMockEntity> findResponseMockList(ResponseMockQuery responseMockQuery) {
        return jpaTemplate.findList(responseMockQuery, ResponseMockEntity.class);
    }

    public Pagination<ResponseMockEntity> findResponseMockPage(ResponseMockQuery responseMockQuery) {
        return jpaTemplate.findPage(responseMockQuery, ResponseMockEntity.class);
    }
}