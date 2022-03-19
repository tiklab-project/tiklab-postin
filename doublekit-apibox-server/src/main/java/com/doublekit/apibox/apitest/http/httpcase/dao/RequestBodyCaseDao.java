package com.doublekit.apibox.apitest.http.httpcase.dao;

import com.doublekit.common.page.Pagination;
import com.doublekit.apibox.apitest.http.httpcase.entity.RequestBodyCaseEntity;
import com.doublekit.apibox.apitest.http.httpcase.model.RequestBodyCaseQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.criterial.condition.QueryCondition;
import com.doublekit.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class RequestBodyCaseDao{

    private static Logger logger = LoggerFactory.getLogger(RequestBodyCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param requestBodyCaseEntity
     * @return
     */
    public String createRequestBodyCase(RequestBodyCaseEntity requestBodyCaseEntity) {
        return jpaTemplate.save(requestBodyCaseEntity,String.class);
    }

    /**
     * 更新用户
     * @param requestBodyCaseEntity
     */
    public void updateRequestBodyCase(RequestBodyCaseEntity requestBodyCaseEntity){
        jpaTemplate.update(requestBodyCaseEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRequestBodyCase(String id){
        jpaTemplate.delete(RequestBodyCaseEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RequestBodyCaseEntity findRequestBodyCase(String id){
        return jpaTemplate.findOne(RequestBodyCaseEntity.class,id);
    }

    /**
    * findAllRequestBodyCase
    * @return
    */
    public List<RequestBodyCaseEntity> findAllRequestBodyCase() {
        return jpaTemplate.findAll(RequestBodyCaseEntity.class);
    }

    public List<RequestBodyCaseEntity> findRequestBodyCaseList(List<String> idList) {
        return jpaTemplate.findList(RequestBodyCaseEntity.class,idList);
    }

    public List<RequestBodyCaseEntity> findRequestBodyCaseList(RequestBodyCaseQuery requestBodyCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestBodyCaseEntity.class)
                .eq("testcaseId", requestBodyCaseQuery.getTestcaseId())
                .orders(requestBodyCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, RequestBodyCaseEntity.class);
    }

    public Pagination<RequestBodyCaseEntity> findRequestBodyCasePage(RequestBodyCaseQuery requestBodyCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RequestBodyCaseEntity.class)
                .eq("testcaseId", requestBodyCaseQuery.getTestcaseId())
                .pagination(requestBodyCaseQuery.getPageParam())
                .orders(requestBodyCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, RequestBodyCaseEntity.class);
    }
}