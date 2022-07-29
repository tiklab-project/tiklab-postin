package com.tiklab.postlink.apidef.http.dao;

import com.tiklab.postlink.apidef.http.entity.ResponseResultEntity;
import com.tiklab.postlink.apidef.http.model.ResponseResultQuery;
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
public class ResponseResultDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseResultDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param responseResultEntity
     * @return
     */
    public String createResponseResult(ResponseResultEntity responseResultEntity) {
        return jpaTemplate.save(responseResultEntity,String.class);
    }

    /**
     * 更新用户
     * @param responseResultEntity
     */
    public void updateResponseResult(ResponseResultEntity responseResultEntity){
        jpaTemplate.update(responseResultEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteResponseResult(String id){
        jpaTemplate.delete(ResponseResultEntity.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteResponseResultList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }


    /**
     * 查找用户
     * @param id
     * @return
     */
    public ResponseResultEntity findResponseResult(String id){
        return jpaTemplate.findOne(ResponseResultEntity.class,id);
    }

    /**
    * findAllResponseResult
    * @return
    */
    public List<ResponseResultEntity> findAllResponseResult() {
        return jpaTemplate.findAll(ResponseResultEntity.class);
    }

    public List<ResponseResultEntity> findResponseResultList(ResponseResultQuery responseResultQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseResultEntity.class)
                .eq("httpId", responseResultQuery.getHttpId())
                .get();
        return jpaTemplate.findList(queryCondition, ResponseResultEntity.class);
    }

    public Pagination<ResponseResultEntity> findResponseResultPage(ResponseResultQuery responseResultQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseResultEntity.class)
                .eq("httpId", responseResultQuery.getHttpId())
                .get();

        return jpaTemplate.findPage(queryCondition, ResponseResultEntity.class);
    }
}