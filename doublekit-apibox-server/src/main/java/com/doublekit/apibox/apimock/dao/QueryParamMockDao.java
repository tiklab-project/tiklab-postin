package com.doublekit.apibox.apimock.dao;

import com.doublekit.common.Pagination;
import com.doublekit.apibox.apimock.entity.QueryParamMockPo;
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
     * @param queryParamMockPo
     * @return
     */
    public String createQueryParamMock(QueryParamMockPo queryParamMockPo) {
        return jpaTemplate.save(queryParamMockPo,String.class);
    }

    /**
     * 更新用户
     * @param queryParamMockPo
     */
    public void updateQueryParamMock(QueryParamMockPo queryParamMockPo){
        jpaTemplate.update(queryParamMockPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteQueryParamMock(String id){
        jpaTemplate.delete(QueryParamMockPo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public QueryParamMockPo findQueryParamMock(String id){
        return jpaTemplate.findOne(QueryParamMockPo.class,id);
    }

    /**
    * findAllQueryParamMock
    * @return
    */
    public List<QueryParamMockPo> findAllQueryParamMock() {
        return jpaTemplate.findAll(QueryParamMockPo.class);
    }

    public List<QueryParamMockPo> findQueryParamMockList(QueryParamMockQuery queryParamMockQuery) {
        return jpaTemplate.findList(QueryParamMockPo.class,queryParamMockQuery);
    }

    public Pagination<QueryParamMockPo> findQueryParamMockPage(QueryParamMockQuery queryParamMockQuery) {
        return jpaTemplate.findPage(QueryParamMockPo.class,queryParamMockQuery);
    }
}