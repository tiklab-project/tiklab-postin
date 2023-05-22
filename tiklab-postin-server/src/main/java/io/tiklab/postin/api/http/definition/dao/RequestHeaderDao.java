package io.tiklab.postin.api.http.definition.dao;

import io.tiklab.postin.api.http.definition.entity.RequestHeadersEntity;
import io.tiklab.postin.api.http.definition.model.RequestHeaderQuery;
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
 * 请求头 数据访问
 */
@Repository
public class RequestHeaderDao{

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建请求头
     * @param requestHeadersEntity
     * @return
     */
    public String createRequestHeader(RequestHeadersEntity requestHeadersEntity) {
        return jpaTemplate.save(requestHeadersEntity,String.class);
    }

    /**
     * 更新请求头
     * @param requestHeadersEntity
     */
    public void updateRequestHeader(RequestHeadersEntity requestHeadersEntity){
        jpaTemplate.update(requestHeadersEntity);
    }

    /**
     * 删除请求头
     * @param id
     */
    public void deleteRequestHeader(String id){
        jpaTemplate.delete(RequestHeadersEntity.class,id);
    }

    /**
     * 通过条件删除请求头
     * @param deleteCondition
     */
    public void deleteRequestHeaderList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找请求头
     * @param id
     * @return
     */
    public RequestHeadersEntity findRequestHeader(String id){
        return jpaTemplate.findOne(RequestHeadersEntity.class,id);
    }

    /**
    * 查找所有请求头
    * @return
    */
    public List<RequestHeadersEntity> findAllRequestHeader() {
        return jpaTemplate.findAll(RequestHeadersEntity.class);
    }

    /**
     * 根据查询参数查找请求头列表
     * @param requestHeaderQuery
     * @return
     */
    public List<RequestHeadersEntity> findRequestHeaderList(RequestHeaderQuery requestHeaderQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestHeadersEntity.class)
                .eq("httpId", requestHeaderQuery.getHttpId())
                .eq("workspaceId",requestHeaderQuery.getWorkspaceId())
                .orders(requestHeaderQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, RequestHeadersEntity.class);
    }

    /**
     * 根据查询参数按分页查找请求头列表
     * @param requestHeaderQuery
     * @return
     */
    public Pagination<RequestHeadersEntity> findRequestHeaderPage(RequestHeaderQuery requestHeaderQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestHeadersEntity.class)
                .eq("httpId", requestHeaderQuery.getHttpId())
                .eq("workspaceId",requestHeaderQuery.getWorkspaceId())
                .pagination(requestHeaderQuery.getPageParam())
                .orders(requestHeaderQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, RequestHeadersEntity.class);
    }
}