package com.tiklab.postin.apimock.http.dao;

import com.tiklab.core.page.Pagination;
import com.tiklab.dal.jpa.JpaTemplate;
import com.tiklab.dal.jpa.criterial.condition.QueryCondition;
import com.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import com.tiklab.postin.apimock.http.entity.QueryParamMockEntity;
import com.tiklab.postin.apimock.http.model.QueryParamMockQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class QueryParamMockDao{

    private static Logger logger = LoggerFactory.getLogger(QueryParamMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param queryParamMockEntity
     * @return
     */
    public String createQueryParamMock(QueryParamMockEntity queryParamMockEntity) {
        return jpaTemplate.save(queryParamMockEntity,String.class);
    }

    /**
     * 更新用户
     * @param queryParamMockEntity
     */
    public void updateQueryParamMock(QueryParamMockEntity queryParamMockEntity){
        jpaTemplate.update(queryParamMockEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteQueryParamMock(String id){
        jpaTemplate.delete(QueryParamMockEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public QueryParamMockEntity findQueryParamMock(String id){
        return jpaTemplate.findOne(QueryParamMockEntity.class,id);
    }

    /**
    * findAllQueryParamMock
    * @return
    */
    public List<QueryParamMockEntity> findAllQueryParamMock() {
        return jpaTemplate.findAll(QueryParamMockEntity.class);
    }

    public List<QueryParamMockEntity> findQueryParamMockList(QueryParamMockQuery queryParamMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(QueryParamMockEntity.class)
                .eq("mockId", queryParamMockQuery.getMockId())
                .orders(queryParamMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, QueryParamMockEntity.class);
    }

    public Pagination<QueryParamMockEntity> findQueryParamMockPage(QueryParamMockQuery queryParamMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(QueryParamMockEntity.class)
                .eq("mockId", queryParamMockQuery.getMockId())
                .pagination(queryParamMockQuery.getPageParam())
                .orders(queryParamMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, QueryParamMockEntity.class);
    }
}