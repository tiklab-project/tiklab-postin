package com.doublekit.apibox.apimock.dao;

import com.doublekit.apibox.apimock.entity.RequestBodyMockEntity;
import com.doublekit.apibox.apimock.model.RequestBodyMockQuery;
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
public class RequestBodyMockDao{

    private static Logger logger = LoggerFactory.getLogger(RequestBodyMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param requestBodyMockEntity
     * @return
     */
    public String createRequestBodyMock(RequestBodyMockEntity requestBodyMockEntity) {
        return jpaTemplate.save(requestBodyMockEntity,String.class);
    }

    /**
     * 更新用户
     * @param requestBodyMockEntity
     */
    public void updateRequestBodyMock(RequestBodyMockEntity requestBodyMockEntity){
        jpaTemplate.update(requestBodyMockEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRequestBodyMock(String id){
        jpaTemplate.delete(RequestBodyMockEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RequestBodyMockEntity findRequestBodyMock(String id){
        return jpaTemplate.findOne(RequestBodyMockEntity.class,id);
    }

    /**
    * findAllRequestBodyMock
    * @return
    */
    public List<RequestBodyMockEntity> findAllRequestBodyMock() {
        return jpaTemplate.findAll(RequestBodyMockEntity.class);
    }

    public List<RequestBodyMockEntity> findRequestBodyMockList(List<String> idList) {
        return jpaTemplate.findList(RequestBodyMockEntity.class,idList);
    }

    public List<RequestBodyMockEntity> findRequestBodyMockList(RequestBodyMockQuery requestBodyMockQuery) {
        return jpaTemplate.findList(requestBodyMockQuery, RequestBodyMockEntity.class);
    }

    public Pagination<RequestBodyMockEntity> findRequestBodyMockPage(RequestBodyMockQuery requestBodyMockQuery) {
        return jpaTemplate.findPage(requestBodyMockQuery, RequestBodyMockEntity.class);
    }
}