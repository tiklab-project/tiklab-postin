package io.thoughtware.postin.api.apix.dao;

import io.thoughtware.postin.api.apix.entity.QueryParamEntity;
import io.thoughtware.postin.api.apix.model.QueryParamQuery;
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
 * 定义
 * http协议
 * 查询参数 数据访问
 */
@Repository
public class QueryParamDao{

    private static Logger logger = LoggerFactory.getLogger(QueryParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建query参数
     * @param queryParamEntity
     * @return
     */
    public String createQueryParam(QueryParamEntity queryParamEntity) {
        return jpaTemplate.save(queryParamEntity,String.class);
    }

    /**
     * 更新query参数
     * @param queryParamEntity
     */
    public void updateQueryParam(QueryParamEntity queryParamEntity){
        jpaTemplate.update(queryParamEntity);
    }

    /**
     * 删除query参数
     * @param id
     */
    public void deleteQueryParam(String id){
        jpaTemplate.delete(QueryParamEntity.class,id);
    }

    /**
     * 通过条件删除query参数
     * @param deleteCondition
     */
    public void deleteQueryParamList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找query参数
     * @param id
     * @return
     */
    public QueryParamEntity findQueryParam(String id){
        return jpaTemplate.findOne(QueryParamEntity.class,id);
    }

    /**
    * 查找所有query参数
    * @return
    */
    public List<QueryParamEntity> findAllQueryParam() {
        return jpaTemplate.findAll(QueryParamEntity.class);
    }

    /**
     * 根据查询查收查找query参数列表
     * @param queryParamQuery
     * @return
     */
    public List<QueryParamEntity> findQueryParamList(QueryParamQuery queryParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(QueryParamEntity.class)
                .eq("apiId", queryParamQuery.getApiId())
                .orders(queryParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, QueryParamEntity.class);
    }

    /**
     * 根据查询参数按分页查找query参数列表
     * @param queryParamQuery
     * @return
     */
    public Pagination<QueryParamEntity> findQueryParamPage(QueryParamQuery queryParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(QueryParamEntity.class)
                .eq("apiId", queryParamQuery.getApiId())
                .pagination(queryParamQuery.getPageParam())
                .orders(queryParamQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, QueryParamEntity.class);
    }
}