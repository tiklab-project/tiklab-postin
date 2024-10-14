package io.tiklab.postin.api.http.test.cases.dao;

import io.tiklab.postin.api.http.test.cases.entity.RequestHeaderCaseEntity;
import io.tiklab.postin.api.http.test.cases.model.RequestHeaderCaseQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class RequestHeaderCaseDao{

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param requestHeaderCaseEntity
     * @return
     */
    public String createRequestHeaderCase(RequestHeaderCaseEntity requestHeaderCaseEntity) {
        return jpaTemplate.save(requestHeaderCaseEntity,String.class);
    }

    /**
     * 更新用户
     * @param requestHeaderCaseEntity
     */
    public void updateRequestHeaderCase(RequestHeaderCaseEntity requestHeaderCaseEntity){
        jpaTemplate.update(requestHeaderCaseEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRequestHeaderCase(String id){
        jpaTemplate.delete(RequestHeaderCaseEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RequestHeaderCaseEntity findRequestHeaderCase(String id){
        return jpaTemplate.findOne(RequestHeaderCaseEntity.class,id);
    }

    /**
    * findAllRequestHeaderCase
    * @return
    */
    public List<RequestHeaderCaseEntity> findAllRequestHeaderCase() {
        return jpaTemplate.findAll(RequestHeaderCaseEntity.class);
    }

    public List<RequestHeaderCaseEntity> findRequestHeaderCaseList(List<String> idList) {
        return jpaTemplate.findList(RequestHeaderCaseEntity.class,idList);
    }

    public List<RequestHeaderCaseEntity> findRequestHeaderCaseList(RequestHeaderCaseQuery requestHeaderCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestHeaderCaseEntity.class)
                .eq("httpCaseId", requestHeaderCaseQuery.getHttpCaseId())
                .orders(requestHeaderCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RequestHeaderCaseEntity.class);
    }

    public Pagination<RequestHeaderCaseEntity> findRequestHeaderCasePage(RequestHeaderCaseQuery requestHeaderCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestHeaderCaseEntity.class)
                .eq("httpCaseId", requestHeaderCaseQuery.getHttpCaseId())
                .pagination(requestHeaderCaseQuery.getPageParam())
                .orders(requestHeaderCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, RequestHeaderCaseEntity.class);
    }
}