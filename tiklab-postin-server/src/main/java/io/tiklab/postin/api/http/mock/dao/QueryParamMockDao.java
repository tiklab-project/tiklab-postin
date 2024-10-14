package io.tiklab.postin.api.http.mock.dao;

import io.tiklab.postin.api.http.mock.entity.QueryParamMockEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.api.http.mock.model.QueryParamMockQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * mock
 * query查询参数 数据访问
 */
@Repository
public class QueryParamMockDao{

    private static Logger logger = LoggerFactory.getLogger(QueryParamMockDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建query查询参数
     * @param queryParamMockEntity
     * @return
     */
    public String createQueryParamMock(QueryParamMockEntity queryParamMockEntity) {
        return jpaTemplate.save(queryParamMockEntity,String.class);
    }

    /**
     * 更新query查询参数
     * @param queryParamMockEntity
     */
    public void updateQueryParamMock(QueryParamMockEntity queryParamMockEntity){
        jpaTemplate.update(queryParamMockEntity);
    }

    /**
     * 删除query查询参数
     * @param id
     */
    public void deleteQueryParamMock(String id){
        jpaTemplate.delete(QueryParamMockEntity.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteQueryParamMockList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找query查询参数
     * @param id
     * @return
     */
    public QueryParamMockEntity findQueryParamMock(String id){
        return jpaTemplate.findOne(QueryParamMockEntity.class,id);
    }

    /**
    * 查找所有query参数
    * @return
    */
    public List<QueryParamMockEntity> findAllQueryParamMock() {
        return jpaTemplate.findAll(QueryParamMockEntity.class);
    }

    /**
     * 通过查询参数查找query列表
     * @param queryParamMockQuery
     * @return
     */
    public List<QueryParamMockEntity> findQueryParamMockList(QueryParamMockQuery queryParamMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(QueryParamMockEntity.class)
                .eq("mockId", queryParamMockQuery.getMockId())
                .orders(queryParamMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, QueryParamMockEntity.class);
    }

    /**
     * 根据查询参数按分页查找query列表
     * @param queryParamMockQuery
     * @return
     */
    public Pagination<QueryParamMockEntity> findQueryParamMockPage(QueryParamMockQuery queryParamMockQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(QueryParamMockEntity.class)
                .eq("mockId", queryParamMockQuery.getMockId())
                .pagination(queryParamMockQuery.getPageParam())
                .orders(queryParamMockQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, QueryParamMockEntity.class);
    }
}