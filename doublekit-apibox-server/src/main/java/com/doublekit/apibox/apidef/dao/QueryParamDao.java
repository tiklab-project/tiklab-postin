package com.doublekit.apibox.apidef.dao;

import com.doublekit.apibox.apidef.entity.QueryParamPo;
import com.doublekit.apibox.apidef.model.QueryParamQuery;
import com.doublekit.common.Pagination;
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
public class QueryParamDao{

    private static Logger logger = LoggerFactory.getLogger(QueryParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param queryParamPo
     * @return
     */
    public String createQueryParam(QueryParamPo queryParamPo) {
        return jpaTemplate.save(queryParamPo,String.class);
    }

    /**
     * 更新用户
     * @param queryParamPo
     */
    public void updateQueryParam(QueryParamPo queryParamPo){
        jpaTemplate.update(queryParamPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteQueryParam(String id){
        jpaTemplate.delete(QueryParamPo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public QueryParamPo findQueryParam(String id){
        return jpaTemplate.findOne(QueryParamPo.class,id);
    }

    /**
    * findAllQueryParam
    * @return
    */
    public List<QueryParamPo> findAllQueryParam() {
        return jpaTemplate.findAll(QueryParamPo.class);
    }

    public List<QueryParamPo> findQueryParamList(QueryParamQuery queryParamQuery) {
        return jpaTemplate.findList(QueryParamPo.class,queryParamQuery);
    }

    public Pagination<QueryParamPo> findQueryParamPage(QueryParamQuery queryParamQuery) {
        return jpaTemplate.findPage(QueryParamPo.class,queryParamQuery);
    }
}