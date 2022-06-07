package com.doublekit.apibox.apitest.http.httpinstance.dao;

import com.doublekit.apibox.apitest.http.httpinstance.model.HttpInstanceQuery;
import com.doublekit.core.page.Pagination;
import com.doublekit.apibox.apitest.http.httpinstance.entity.HttpInstanceEntity;
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
public class HttpInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(HttpInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param httpInstanceEntity
     * @return
     */
    public String createTestInstance(HttpInstanceEntity httpInstanceEntity) {
        return jpaTemplate.save(httpInstanceEntity,String.class);
    }

    /**
     * 更新用户
     * @param httpInstanceEntity
     */
    public void updateTestInstance(HttpInstanceEntity httpInstanceEntity){
        jpaTemplate.update(httpInstanceEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteTestInstance(String id){
        jpaTemplate.delete(HttpInstanceEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public HttpInstanceEntity findTestInstance(String id){
        return jpaTemplate.findOne(HttpInstanceEntity.class,id);
    }

    /**
    * findAllTestInstance
    * @return
    */
    public List<HttpInstanceEntity> findAllTestInstance() {
        return jpaTemplate.findAll(HttpInstanceEntity.class);
    }

    public List<HttpInstanceEntity> findTestInstanceList(List<String> idList) {
        return jpaTemplate.findList(HttpInstanceEntity.class,idList);
    }

    public List<HttpInstanceEntity> findTestInstanceList(HttpInstanceQuery httpInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(HttpInstanceEntity.class)
                .eq("httpCaseId", httpInstanceQuery.getHttpCaseId())
                .orders(httpInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, HttpInstanceEntity.class);
    }

    public Pagination<HttpInstanceEntity> findTestInstancePage(HttpInstanceQuery httpInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(HttpInstanceEntity.class)
                .eq("httpCaseId", httpInstanceQuery.getHttpCaseId())
                .pagination(httpInstanceQuery.getPageParam())
                .orders(httpInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, HttpInstanceEntity.class);
    }
}