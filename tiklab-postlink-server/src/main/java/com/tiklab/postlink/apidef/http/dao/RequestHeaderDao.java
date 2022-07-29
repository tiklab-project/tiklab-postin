package com.tiklab.postlink.apidef.http.dao;

import com.tiklab.postlink.apidef.http.entity.RequestHeaderEntity;
import com.tiklab.postlink.apidef.http.model.RequestHeaderQuery;
import com.tiklab.core.page.Pagination;
import com.tiklab.dal.jpa.JpaTemplate;
import com.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import com.tiklab.dal.jpa.criterial.condition.QueryCondition;
import com.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class RequestHeaderDao{

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param requestHeaderEntity
     * @return
     */
    public String createRequestHeader(RequestHeaderEntity requestHeaderEntity) {
        return jpaTemplate.save(requestHeaderEntity,String.class);
    }

    /**
     * 更新用户
     * @param requestHeaderEntity
     */
    public void updateRequestHeader(RequestHeaderEntity requestHeaderEntity){
        jpaTemplate.update(requestHeaderEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRequestHeader(String id){
        jpaTemplate.delete(RequestHeaderEntity.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteRequestHeaderList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RequestHeaderEntity findRequestHeader(String id){
        return jpaTemplate.findOne(RequestHeaderEntity.class,id);
    }

    /**
    * findAllRequestHeader
    * @return
    */
    public List<RequestHeaderEntity> findAllRequestHeader() {
        return jpaTemplate.findAll(RequestHeaderEntity.class);
    }

    public List<RequestHeaderEntity> findRequestHeaderList(RequestHeaderQuery requestHeaderQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestHeaderEntity.class)
                .eq("httpId", requestHeaderQuery.getHttpId())
                .orders(requestHeaderQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, RequestHeaderEntity.class);
    }

    public Pagination<RequestHeaderEntity> findRequestHeaderPage(RequestHeaderQuery requestHeaderQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestHeaderEntity.class)
                .eq("httpId", requestHeaderQuery.getHttpId())
                .pagination(requestHeaderQuery.getPageParam())
                .orders(requestHeaderQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, RequestHeaderEntity.class);
    }
}