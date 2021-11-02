package com.doublekit.apibox.apimock.dao;

import com.doublekit.common.Pagination;
import com.doublekit.apibox.apimock.entity.QueryParamMockEntity;
import com.doublekit.apibox.apimock.model.QueryParamMockQuery;
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
        return jpaTemplate.findList(QueryParamMockEntity.class,queryParamMockQuery);
    }

    public Pagination<QueryParamMockEntity> findQueryParamMockPage(QueryParamMockQuery queryParamMockQuery) {
        return jpaTemplate.findPage(QueryParamMockEntity.class,queryParamMockQuery);
    }
}