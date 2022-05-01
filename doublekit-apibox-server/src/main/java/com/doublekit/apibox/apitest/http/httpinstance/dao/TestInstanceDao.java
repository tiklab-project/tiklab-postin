package com.doublekit.apibox.apitest.http.httpinstance.dao;

import com.doublekit.apibox.apitest.http.httpinstance.model.TestInstanceQuery;
import com.doublekit.core.page.Pagination;
import com.doublekit.apibox.apitest.http.httpinstance.entity.HttpTestInstanceEntity;
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
public class TestInstanceDao{

    private static Logger logger = LoggerFactory.getLogger(TestInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param httpTestInstanceEntity
     * @return
     */
    public String createTestInstance(HttpTestInstanceEntity httpTestInstanceEntity) {
        return jpaTemplate.save(httpTestInstanceEntity,String.class);
    }

    /**
     * 更新用户
     * @param httpTestInstanceEntity
     */
    public void updateTestInstance(HttpTestInstanceEntity httpTestInstanceEntity){
        jpaTemplate.update(httpTestInstanceEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteTestInstance(String id){
        jpaTemplate.delete(HttpTestInstanceEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public HttpTestInstanceEntity findTestInstance(String id){
        return jpaTemplate.findOne(HttpTestInstanceEntity.class,id);
    }

    /**
    * findAllTestInstance
    * @return
    */
    public List<HttpTestInstanceEntity> findAllTestInstance() {
        return jpaTemplate.findAll(HttpTestInstanceEntity.class);
    }

    public List<HttpTestInstanceEntity> findTestInstanceList(List<String> idList) {
        return jpaTemplate.findList(HttpTestInstanceEntity.class,idList);
    }

    public List<HttpTestInstanceEntity> findTestInstanceList(TestInstanceQuery testInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(HttpTestInstanceEntity.class)
                .eq("httpCaseId", testInstanceQuery.getHttpCaseId())
                .orders(testInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, HttpTestInstanceEntity.class);
    }

    public Pagination<HttpTestInstanceEntity> findTestInstancePage(TestInstanceQuery testInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(HttpTestInstanceEntity.class)
                .eq("httpCaseId", testInstanceQuery.getHttpCaseId())
                .pagination(testInstanceQuery.getPageParam())
                .orders(testInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, HttpTestInstanceEntity.class);
    }
}