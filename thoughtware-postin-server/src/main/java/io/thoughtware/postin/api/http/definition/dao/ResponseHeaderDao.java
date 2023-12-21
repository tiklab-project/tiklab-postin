package io.thoughtware.postin.api.http.definition.dao;

import io.thoughtware.postin.api.http.definition.entity.ResponseHeaderEntity;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.postin.api.http.definition.model.ResponseHeaderQuery;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定义
 * http协议
 * 响应头 数据访问
 */
@Repository
public class ResponseHeaderDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseHeaderDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建响应头
     * @param responseHeaderEntity
     * @return
     */
    public String createResponseHeader(ResponseHeaderEntity responseHeaderEntity) {
        return jpaTemplate.save(responseHeaderEntity,String.class);
    }

    /**
     * 更新响应头
     * @param responseHeaderEntity
     */
    public void updateResponseHeader(ResponseHeaderEntity responseHeaderEntity){
        jpaTemplate.update(responseHeaderEntity);
    }

    /**
     * 删除响应头
     * @param id
     */
    public void deleteResponseHeader(String id){
        jpaTemplate.delete(ResponseHeaderEntity.class,id);
    }

    /**
     * 通过条件删除响应头
     * @param deleteCondition
     */
    public void deleteResponseHeaderList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }


    /**
     * 查找响应头
     * @param id
     * @return
     */
    public ResponseHeaderEntity findResponseHeader(String id){
        return jpaTemplate.findOne(ResponseHeaderEntity.class,id);
    }

    /**
    * 查找所有响应头
    * @return
    */
    public List<ResponseHeaderEntity> findAllResponseHeader() {
        return jpaTemplate.findAll(ResponseHeaderEntity.class);
    }

    /**
     * 根据查询参数查找响应头列表
     * @param responseHeaderQuery
     * @return
     */
    public List<ResponseHeaderEntity> findResponseHeaderList(ResponseHeaderQuery responseHeaderQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseHeaderEntity.class)
                .eq("httpId", responseHeaderQuery.getHttpId())
                .orders(responseHeaderQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ResponseHeaderEntity.class);
    }

    /**
     * 根据查询参数按分页查找响应头列表
     * @param responseHeaderQuery
     * @return
     */
    public Pagination<ResponseHeaderEntity> findResponseHeaderPage(ResponseHeaderQuery responseHeaderQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseHeaderEntity.class)
                .eq("httpId", responseHeaderQuery.getHttpId())
                .pagination(responseHeaderQuery.getPageParam())
                .orders(responseHeaderQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ResponseHeaderEntity.class);
    }
}