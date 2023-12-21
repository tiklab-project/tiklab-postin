package io.thoughtware.postin.api.apix.dao;

import io.thoughtware.postin.api.apix.entity.RequestHeaderEntity;
import io.thoughtware.postin.api.apix.model.RequestHeaderQuery;
import io.thoughtware.core.page.Pagination;
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
 * 请求头 数据访问
 */
@Repository
public class RequestHeaderDao{

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建请求头
     * @param requestHeaderEntity
     * @return
     */
    public String createRequestHeader(RequestHeaderEntity requestHeaderEntity) {
        return jpaTemplate.save(requestHeaderEntity,String.class);
    }

    /**
     * 更新请求头
     * @param requestHeaderEntity
     */
    public void updateRequestHeader(RequestHeaderEntity requestHeaderEntity){
        jpaTemplate.update(requestHeaderEntity);
    }

    /**
     * 删除请求头
     * @param id
     */
    public void deleteRequestHeader(String id){
        jpaTemplate.delete(RequestHeaderEntity.class,id);
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
    public RequestHeaderEntity findRequestHeader(String id){
        return jpaTemplate.findOne(RequestHeaderEntity.class,id);
    }

    /**
    * 查找所有请求头
    * @return
    */
    public List<RequestHeaderEntity> findAllRequestHeader() {
        return jpaTemplate.findAll(RequestHeaderEntity.class);
    }

    /**
     * 根据查询参数查找请求头列表
     * @param requestHeaderQuery
     * @return
     */
    public List<RequestHeaderEntity> findRequestHeaderList(RequestHeaderQuery requestHeaderQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestHeaderEntity.class)
                .eq("apiId", requestHeaderQuery.getApiId())
                .eq("workspaceId",requestHeaderQuery.getWorkspaceId())
                .orders(requestHeaderQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, RequestHeaderEntity.class);
    }

    /**
     * 根据查询参数按分页查找请求头列表
     * @param requestHeaderQuery
     * @return
     */
    public Pagination<RequestHeaderEntity> findRequestHeaderPage(RequestHeaderQuery requestHeaderQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestHeaderEntity.class)
                .eq("apiId", requestHeaderQuery.getApiId())
                .eq("workspaceId",requestHeaderQuery.getWorkspaceId())
                .pagination(requestHeaderQuery.getPageParam())
                .orders(requestHeaderQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, RequestHeaderEntity.class);
    }
}