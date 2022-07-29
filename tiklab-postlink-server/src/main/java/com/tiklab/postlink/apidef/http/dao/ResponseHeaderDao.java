package com.tiklab.postlink.apidef.http.dao;

import com.tiklab.core.page.Pagination;
import com.tiklab.postlink.apidef.http.entity.ResponseHeaderEntity;
import com.tiklab.postlink.apidef.http.model.ResponseHeaderQuery;
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
public class ResponseHeaderDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseHeaderDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param responseHeaderEntity
     * @return
     */
    public String createResponseHeader(ResponseHeaderEntity responseHeaderEntity) {
        return jpaTemplate.save(responseHeaderEntity,String.class);
    }

    /**
     * 更新用户
     * @param responseHeaderEntity
     */
    public void updateResponseHeader(ResponseHeaderEntity responseHeaderEntity){
        jpaTemplate.update(responseHeaderEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteResponseHeader(String id){
        jpaTemplate.delete(ResponseHeaderEntity.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteResponseHeaderList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }


    /**
     * 查找用户
     * @param id
     * @return
     */
    public ResponseHeaderEntity findResponseHeader(String id){
        return jpaTemplate.findOne(ResponseHeaderEntity.class,id);
    }

    /**
    * findAllResponseHeader
    * @return
    */
    public List<ResponseHeaderEntity> findAllResponseHeader() {
        return jpaTemplate.findAll(ResponseHeaderEntity.class);
    }

    public List<ResponseHeaderEntity> findResponseHeaderList(ResponseHeaderQuery responseHeaderQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseHeaderEntity.class)
                .eq("httpId", responseHeaderQuery.getHttpId())
                .orders(responseHeaderQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ResponseHeaderEntity.class);
    }

    public Pagination<ResponseHeaderEntity> findResponseHeaderPage(ResponseHeaderQuery responseHeaderQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseHeaderEntity.class)
                .eq("httpId", responseHeaderQuery.getHttpId())
                .pagination(responseHeaderQuery.getPageParam())
                .orders(responseHeaderQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ResponseHeaderEntity.class);
    }
}