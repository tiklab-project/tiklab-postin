package com.doublekit.apibox.apimock.http.dao;

import com.doublekit.common.page.Pagination;
import com.doublekit.apibox.apimock.http.entity.ResponseResultMockEntity;
import com.doublekit.apibox.apimock.http.model.ResponseResultMockQuery;
import com.doublekit.dal.jpa.JpaTemplate;
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
public class ResponseResultMockDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseResultMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param responseResultMockEntity
     * @return
     */
    public String createResponseResultMock(ResponseResultMockEntity responseResultMockEntity) {
        return jpaTemplate.save(responseResultMockEntity,String.class);
    }

    /**
     * 更新用户
     * @param responseResultMockEntity
     */
    public void updateResponseResultMock(ResponseResultMockEntity responseResultMockEntity){
        jpaTemplate.update(responseResultMockEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteResponseResultMock(String id){
        jpaTemplate.delete(ResponseResultMockEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public ResponseResultMockEntity findResponseResultMock(String id){
        return jpaTemplate.findOne(ResponseResultMockEntity.class,id);
    }

    /**
    * findAllResponseResultMock
    * @return
    */
    public List<ResponseResultMockEntity> findAllResponseResultMock() {
        return jpaTemplate.findAll(ResponseResultMockEntity.class);
    }

    public List<ResponseResultMockEntity> findResponseResultMockList(ResponseResultMockQuery responseResultMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseResultMockEntity.class)
                .eq("mockId",responseResultMockQuery.getMockId())
                .orders(responseResultMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ResponseResultMockEntity.class);
    }

    public Pagination<ResponseResultMockEntity> findResponseResultMockPage(ResponseResultMockQuery responseResultMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseResultMockEntity.class)
                .eq("mockId",responseResultMockQuery.getMockId())
                .pagination(responseResultMockQuery.getPageParam())
                .orders(responseResultMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ResponseResultMockEntity.class);
    }
}