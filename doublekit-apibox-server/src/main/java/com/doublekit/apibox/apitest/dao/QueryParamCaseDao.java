package com.doublekit.apibox.apitest.dao;

import com.doublekit.apibox.apitest.model.QueryParamCaseQuery;
import com.doublekit.common.Pagination;
import com.doublekit.apibox.apitest.entity.QueryParamCaseEntity;
import com.doublekit.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class QueryParamCaseDao{

    private static Logger logger = LoggerFactory.getLogger(QueryParamCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param queryParamCaseEntity
     * @return
     */
    public String createQueryParamCase(QueryParamCaseEntity queryParamCaseEntity) {
        return jpaTemplate.save(queryParamCaseEntity,String.class);
    }

    /**
     * 更新用户
     * @param queryParamCaseEntity
     */
    public void updateQueryParamCase(QueryParamCaseEntity queryParamCaseEntity){
        jpaTemplate.update(queryParamCaseEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteQueryParamCase(String id){
        jpaTemplate.delete(QueryParamCaseEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public QueryParamCaseEntity findQueryParamCase(String id){
        return jpaTemplate.findOne(QueryParamCaseEntity.class,id);
    }

    /**
    * findAllQueryParamCase
    * @return
    */
    public List<QueryParamCaseEntity> findAllQueryParamCase() {
        return jpaTemplate.findAll(QueryParamCaseEntity.class);
    }

    public List<QueryParamCaseEntity> findQueryParamCaseList(List<String> idList) {
        return jpaTemplate.findList(QueryParamCaseEntity.class,idList);
    }

    public List<QueryParamCaseEntity> findQueryParamCaseList(QueryParamCaseQuery queryParamCaseQuery) {
        return jpaTemplate.findList(queryParamCaseQuery, QueryParamCaseEntity.class);
    }

    public Pagination<QueryParamCaseEntity> findQueryParamCasePage(QueryParamCaseQuery queryParamCaseQuery) {
        return jpaTemplate.findPage(queryParamCaseQuery, QueryParamCaseEntity.class);
    }
}