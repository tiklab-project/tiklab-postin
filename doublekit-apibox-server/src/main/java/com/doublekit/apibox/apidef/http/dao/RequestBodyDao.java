package com.doublekit.apibox.apidef.http.dao;

import com.doublekit.core.page.Pagination;
import com.doublekit.apibox.apidef.http.entity.RequestBodyEntity;
import com.doublekit.apibox.apidef.http.model.RequestBodyExQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.criterial.condition.DeleteCondition;
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
public class RequestBodyDao{

    private static Logger logger = LoggerFactory.getLogger(RequestBodyDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param requestBodyEntity
     * @return
     */
    public String createRequestBody(RequestBodyEntity requestBodyEntity) {
        return jpaTemplate.save(requestBodyEntity,String.class);
    }

    /**
     * 更新用户
     * @param requestBodyEntity
     */
    public void updateRequestBody(RequestBodyEntity requestBodyEntity){
        jpaTemplate.update(requestBodyEntity);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteRequestBodyList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }
    /**
     * 删除
     * @param id
     */
    public void deleteRequestBody(String id){
        jpaTemplate.delete(RequestBodyEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RequestBodyEntity findRequestBody(String id){
        return jpaTemplate.findOne(RequestBodyEntity.class,id);
    }

    /**
    * findAllRequestBody
    * @return
    */
    public List<RequestBodyEntity> findAllRequestBody() {
        return jpaTemplate.findAll(RequestBodyEntity.class);
    }

    public List<RequestBodyEntity> findRequestBodyList(RequestBodyExQuery requestBodyQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestBodyEntity.class)
                .eq("httpId", requestBodyQuery.getHttpId())
                .orders(requestBodyQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RequestBodyEntity.class);
    }

    public Pagination<RequestBodyEntity> findRequestBodyPage(RequestBodyExQuery requestBodyQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestBodyEntity.class)
                .eq("httpId", requestBodyQuery.getHttpId())
                .pagination(requestBodyQuery.getPageParam())
                .orders(requestBodyQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, RequestBodyEntity.class);
    }
}