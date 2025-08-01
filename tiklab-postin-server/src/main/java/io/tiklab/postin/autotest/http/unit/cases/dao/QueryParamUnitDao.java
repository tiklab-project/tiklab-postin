package io.tiklab.postin.autotest.http.unit.cases.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.postin.autotest.http.unit.cases.entity.QueryParamsUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.QueryParamUnitQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * query 数据访问
 */
@Repository
public class QueryParamUnitDao {

    private static Logger logger = LoggerFactory.getLogger(QueryParamUnitDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建query
     * @param queryParamsUnitEntity
     * @return
     */
    public String createQueryParam(QueryParamsUnitEntity queryParamsUnitEntity) {
        return jpaTemplate.save(queryParamsUnitEntity,String.class);
    }

    /**
     * 更新query
     * @param queryParamsUnitEntity
     */
    public void updateQueryParam(QueryParamsUnitEntity queryParamsUnitEntity){
        jpaTemplate.update(queryParamsUnitEntity);
    }

    /**
     * 删除query
     * @param id
     */
    public void deleteQueryParam(String id){
        jpaTemplate.delete(QueryParamsUnitEntity.class,id);
    }

    public void deleteQueryParam(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找query
     * @param id
     * @return
     */
    public QueryParamsUnitEntity findQueryParam(String id){
        return jpaTemplate.findOne(QueryParamsUnitEntity.class,id);
    }

    /**
    * 查找所有query
    * @return
    */
    public List<QueryParamsUnitEntity> findAllQueryParam() {
        return jpaTemplate.findAll(QueryParamsUnitEntity.class);
    }

    public List<QueryParamsUnitEntity> findQueryParamList(List<String> idList) {
        return jpaTemplate.findList(QueryParamsUnitEntity.class,idList);
    }

    /**
     * 根据查询参数查询query列表
     * @param queryParamUnitQuery
     * @return
     */
    public List<QueryParamsUnitEntity> findQueryParamList(QueryParamUnitQuery queryParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(QueryParamsUnitEntity.class)
                .eq("apiUnitId", queryParamUnitQuery.getApiUnitId())
                .orders(queryParamUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, QueryParamsUnitEntity.class);
    }

    /**
     * 根据查询参数按分页查询query
     * @param queryParamUnitQuery
     * @return
     */
    public Pagination<QueryParamsUnitEntity> findQueryParamPage(QueryParamUnitQuery queryParamUnitQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(QueryParamsUnitEntity.class)
                .eq("apiUnitId", queryParamUnitQuery.getApiUnitId())
                .pagination(queryParamUnitQuery.getPageParam())
                .orders(queryParamUnitQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, QueryParamsUnitEntity.class);
    }
}