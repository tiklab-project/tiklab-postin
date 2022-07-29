package com.tiklab.postlink.apidef.http.dao;

import com.tiklab.postlink.apidef.http.entity.RawResponseEntity;
import com.tiklab.postlink.apidef.http.model.RawResponseQuery;
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
public class RawResponseDao{

    private static Logger logger = LoggerFactory.getLogger(RawResponseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param rawResponseEntity
     * @return
     */
    public String createRawResponse(RawResponseEntity rawResponseEntity) {
        return jpaTemplate.save(rawResponseEntity,String.class);
    }

    /**
     * 更新用户
     * @param rawResponseEntity
     */
    public void updateRawResponse(RawResponseEntity rawResponseEntity){
        jpaTemplate.update(rawResponseEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRawResponse(String id){
        jpaTemplate.delete(RawResponseEntity.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteRawResponseList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RawResponseEntity findRawResponse(String id){
        return jpaTemplate.findOne(RawResponseEntity.class,id);
    }

    /**
    * findAllRawResponse
    * @return
    */
    public List<RawResponseEntity> findAllRawResponse() {
        return jpaTemplate.findAll(RawResponseEntity.class);
    }

    public List<RawResponseEntity> findRawResponseList(RawResponseQuery rawResponseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawResponseEntity.class)
                .eq("httpId", rawResponseQuery.getHttpId())
                .orders(rawResponseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RawResponseEntity.class);
    }

    public Pagination<RawResponseEntity> findRawResponsePage(RawResponseQuery rawResponseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawResponseEntity.class)
                .eq("httpId", rawResponseQuery.getHttpId())
                .pagination(rawResponseQuery.getPageParam())
                .orders(rawResponseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, RawResponseEntity.class);
    }
}