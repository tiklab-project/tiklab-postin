package com.darthcloud.apibox.apitest.dao;

import com.darthcloud.apibox.apitest.model.QueryParamCaseQuery;
import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.apitest.entity.QueryParamCasePo;
import com.darthcloud.dal.jpa.JpaTemplate;
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
     * @param queryParamCasePo
     * @return
     */
    public String createQueryParamCase(QueryParamCasePo queryParamCasePo) {
        return jpaTemplate.save(queryParamCasePo,String.class);
    }

    /**
     * 更新用户
     * @param queryParamCasePo
     */
    public void updateQueryParamCase(QueryParamCasePo queryParamCasePo){
        jpaTemplate.update(queryParamCasePo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteQueryParamCase(String id){
        jpaTemplate.delete(QueryParamCasePo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public QueryParamCasePo findQueryParamCase(String id){
        return jpaTemplate.findOne(QueryParamCasePo.class,id);
    }

    /**
    * findAllQueryParamCase
    * @return
    */
    public List<QueryParamCasePo> findAllQueryParamCase() {
        return jpaTemplate.findAll(QueryParamCasePo.class);
    }

    public List<QueryParamCasePo> findQueryParamCaseList(List<String> idList) {
        return jpaTemplate.findList(QueryParamCasePo.class,idList);
    }

    public List<QueryParamCasePo> findQueryParamCaseList(QueryParamCaseQuery queryParamCaseQuery) {
        return jpaTemplate.findList(QueryParamCasePo.class,queryParamCaseQuery);
    }

    public Pagination<List<QueryParamCasePo>> findQueryParamCasePage(QueryParamCaseQuery queryParamCaseQuery) { 
        return jpaTemplate.findPage(QueryParamCasePo.class,queryParamCaseQuery);
    }
}