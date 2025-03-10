package io.tiklab.postin.api.http.test.cases.dao;

import io.tiklab.postin.api.http.test.cases.entity.RequestCaseEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.postin.api.http.test.cases.model.RequestCaseQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RequestCaseDao
 */
@Repository
public class RequestCaseDao{

    private static Logger logger = LoggerFactory.getLogger(RequestCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param requestCaseEntity
     * @return
     */
    public String createRequestCase(RequestCaseEntity requestCaseEntity) {
        return jpaTemplate.save(requestCaseEntity,String.class);
    }

    /**
     * 更新
     * @param requestCaseEntity
     */
    public void updateRequestCase(RequestCaseEntity requestCaseEntity){
        jpaTemplate.update(requestCaseEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteRequestCase(String id){
        jpaTemplate.delete(RequestCaseEntity.class,id);
    }

    public void deleteRequestCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public RequestCaseEntity findRequestCase(String id){
        return jpaTemplate.findOne(RequestCaseEntity.class,id);
    }

    /**
    * findAllRequestCase
    * @return
    */
    public List<RequestCaseEntity> findAllRequestCase() {
        return jpaTemplate.findAll(RequestCaseEntity.class);
    }

    public List<RequestCaseEntity> findRequestCaseList(List<String> idList) {
        return jpaTemplate.findList(RequestCaseEntity.class,idList);
    }

    public List<RequestCaseEntity> findRequestCaseList(RequestCaseQuery requestCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestCaseEntity.class)
                .eq("httpCaseId", requestCaseQuery.getHttpCaseId())
                .orders(requestCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(requestCaseQuery,RequestCaseEntity.class);
    }

    public Pagination<RequestCaseEntity> findRequestCasePage(RequestCaseQuery requestCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestCaseEntity.class)
                .eq("httpCaseId", requestCaseQuery.getHttpCaseId())
                .pagination(requestCaseQuery.getPageParam())
                .orders(requestCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition,RequestCaseEntity.class);
    }
}