package com.tiklab.postin.apidef.http.dao;

import com.tiklab.postin.apidef.http.entity.QueryParamEntity;
import com.tiklab.postin.apidef.http.model.QueryParamQuery;
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
public class QueryParamDao{

    private static Logger logger = LoggerFactory.getLogger(QueryParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param queryParamEntity
     * @return
     */
    public String createQueryParam(QueryParamEntity queryParamEntity) {
        return jpaTemplate.save(queryParamEntity,String.class);
    }

    /**
     * 更新用户
     * @param queryParamEntity
     */
    public void updateQueryParam(QueryParamEntity queryParamEntity){
        jpaTemplate.update(queryParamEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteQueryParam(String id){
        jpaTemplate.delete(QueryParamEntity.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteQueryParamList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public QueryParamEntity findQueryParam(String id){
        return jpaTemplate.findOne(QueryParamEntity.class,id);
    }

    /**
    * findAllQueryParam
    * @return
    */
    public List<QueryParamEntity> findAllQueryParam() {
        return jpaTemplate.findAll(QueryParamEntity.class);
    }

    public List<QueryParamEntity> findQueryParamList(QueryParamQuery queryParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(QueryParamEntity.class)
                .eq("httpId", queryParamQuery.getHttpId())
                .orders(queryParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, QueryParamEntity.class);
    }

    public Pagination<QueryParamEntity> findQueryParamPage(QueryParamQuery queryParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(QueryParamEntity.class)
                .eq("httpId", queryParamQuery.getHttpId())
                .pagination(queryParamQuery.getPageParam())
                .orders(queryParamQuery.getOrderParams())
                .get();

        return jpaTemplate.findPage(queryCondition, QueryParamEntity.class);
    }
}