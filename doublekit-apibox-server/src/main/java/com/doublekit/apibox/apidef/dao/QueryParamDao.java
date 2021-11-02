package com.doublekit.apibox.apidef.dao;

import com.doublekit.apibox.apidef.entity.JsonResponseEntity;
import com.doublekit.apibox.apidef.entity.QueryParamEntity;
import com.doublekit.apibox.apidef.model.QueryParamQuery;
import com.doublekit.common.Pagination;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.builder.deletelist.condition.DeleteCondition;
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
        jpaTemplate.delete(QueryParamEntity.class,deleteCondition);
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
        return jpaTemplate.findList(QueryParamEntity.class,queryParamQuery);
    }

    public Pagination<QueryParamEntity> findQueryParamPage(QueryParamQuery queryParamQuery) {
        return jpaTemplate.findPage(QueryParamEntity.class,queryParamQuery);
    }
}