package io.tiklab.postin.api.http.definition.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.api.http.definition.entity.ResponseHeadersEntity;
import io.tiklab.postin.api.http.definition.model.ResponseHeaderQuery;
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
 * 响应头 数据访问
 */
@Repository
public class ResponseHeaderDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseHeaderDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建响应头
     * @param responseHeadersEntity
     * @return
     */
    public String createResponseHeader(ResponseHeadersEntity responseHeadersEntity) {
        return jpaTemplate.save(responseHeadersEntity,String.class);
    }

    /**
     * 更新响应头
     * @param responseHeadersEntity
     */
    public void updateResponseHeader(ResponseHeadersEntity responseHeadersEntity){
        jpaTemplate.update(responseHeadersEntity);
    }

    /**
     * 删除响应头
     * @param id
     */
    public void deleteResponseHeader(String id){
        jpaTemplate.delete(ResponseHeadersEntity.class,id);
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
    public ResponseHeadersEntity findResponseHeader(String id){
        return jpaTemplate.findOne(ResponseHeadersEntity.class,id);
    }

    /**
    * 查找所有响应头
    * @return
    */
    public List<ResponseHeadersEntity> findAllResponseHeader() {
        return jpaTemplate.findAll(ResponseHeadersEntity.class);
    }

    /**
     * 根据查询参数查找响应头列表
     * @param responseHeaderQuery
     * @return
     */
    public List<ResponseHeadersEntity> findResponseHeaderList(ResponseHeaderQuery responseHeaderQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseHeadersEntity.class)
                .eq("httpId", responseHeaderQuery.getHttpId())
                .orders(responseHeaderQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ResponseHeadersEntity.class);
    }

    /**
     * 根据查询参数按分页查找响应头列表
     * @param responseHeaderQuery
     * @return
     */
    public Pagination<ResponseHeadersEntity> findResponseHeaderPage(ResponseHeaderQuery responseHeaderQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ResponseHeadersEntity.class)
                .eq("httpId", responseHeaderQuery.getHttpId())
                .pagination(responseHeaderQuery.getPageParam())
                .orders(responseHeaderQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, ResponseHeadersEntity.class);
    }
}