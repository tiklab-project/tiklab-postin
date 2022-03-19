package com.doublekit.apibox.apitest.http.httpcase.dao;

import com.doublekit.apibox.apitest.http.httpcase.model.HttpTestcaseQuery;
import com.doublekit.common.page.Pagination;
import com.doublekit.apibox.apitest.http.httpcase.entity.HttpTestcaseEntity;
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
public class HttpTestcaseDao {

    private static Logger logger = LoggerFactory.getLogger(HttpTestcaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param httpTestcaseEntity
     * @return
     */
    public String createTestcase(HttpTestcaseEntity httpTestcaseEntity) {
        return jpaTemplate.save(httpTestcaseEntity,String.class);
    }

    /**
     * 更新用户
     * @param httpTestcaseEntity
     */
    public void updateTestcase(HttpTestcaseEntity httpTestcaseEntity){
        jpaTemplate.update(httpTestcaseEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteTestcase(String id){
        jpaTemplate.delete(HttpTestcaseEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public HttpTestcaseEntity findTestcase(String id){
        return jpaTemplate.findOne(HttpTestcaseEntity.class,id);
    }

    /**
    * findAllTestcase
    * @return
    */
    public List<HttpTestcaseEntity> findAllTestcase() {
        return jpaTemplate.findAll(HttpTestcaseEntity.class);
    }

    public List<HttpTestcaseEntity> findTestcaseList(List<String> idList) {
        return jpaTemplate.findList(HttpTestcaseEntity.class,idList);
    }

    public List<HttpTestcaseEntity> findTestcaseList(HttpTestcaseQuery httpTestcaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(HttpTestcaseEntity.class)
                .eq("httpId", httpTestcaseQuery.getHttpId())
                .orders(httpTestcaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, HttpTestcaseEntity.class);
    }

    public Pagination<HttpTestcaseEntity> findTestcasePage(HttpTestcaseQuery httpTestcaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(HttpTestcaseEntity.class)
                .eq("httpId", httpTestcaseQuery.getHttpId())
                .like("name", httpTestcaseQuery.getName())
                .pagination(httpTestcaseQuery.getPageParam())
                .orders(httpTestcaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, HttpTestcaseEntity.class);
    }
}