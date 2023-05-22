package io.tiklab.postin.api.http.definition.dao;

import io.tiklab.postin.api.http.definition.entity.RawResponsesEntity;
import io.tiklab.postin.api.http.definition.model.RawResponseQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定义
 * http协议
 * 响应中raw 数据访问
 */
@Repository
public class RawResponseDao{

    private static Logger logger = LoggerFactory.getLogger(RawResponseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建响应中raw
     * @param rawResponsesEntity
     * @return
     */
    public String createRawResponse(RawResponsesEntity rawResponsesEntity) {
        return jpaTemplate.save(rawResponsesEntity,String.class);
    }

    /**
     * 更新响应中raw
     * @param rawResponsesEntity
     */
    public void updateRawResponse(RawResponsesEntity rawResponsesEntity){
        jpaTemplate.update(rawResponsesEntity);
    }

    /**
     * 删除响应中raw
     * @param id
     */
    public void deleteRawResponse(String id){
        jpaTemplate.delete(RawResponsesEntity.class,id);
    }

    /**
     * 通过条件删除响应中raw
     * @param deleteCondition
     */
    public void deleteRawResponseList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找响应中raw
     * @param id
     * @return
     */
    public RawResponsesEntity findRawResponse(String id){
        return jpaTemplate.findOne(RawResponsesEntity.class,id);
    }

    /**
    * 查找所有响应中raw
    * @return
    */
    public List<RawResponsesEntity> findAllRawResponse() {
        return jpaTemplate.findAll(RawResponsesEntity.class);
    }

    /**
     * 根据查询参数查找响应中raw列表
     * @param rawResponseQuery
     * @return
     */
    public List<RawResponsesEntity> findRawResponseList(RawResponseQuery rawResponseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawResponsesEntity.class)
                .eq("httpId", rawResponseQuery.getHttpId())
                .orders(rawResponseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RawResponsesEntity.class);
    }

    /**
     * 根据查询参数按分页查找响应中raw列表
     * @param rawResponseQuery
     * @return
     */
    public Pagination<RawResponsesEntity> findRawResponsePage(RawResponseQuery rawResponseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RawResponsesEntity.class)
                .eq("httpId", rawResponseQuery.getHttpId())
                .pagination(rawResponseQuery.getPageParam())
                .orders(rawResponseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, RawResponsesEntity.class);
    }
}