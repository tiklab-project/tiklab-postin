package com.darthcloud.apibox.requestparam.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.requestparam.entity.RequestParamPo;
import com.darthcloud.apibox.requestparam.model.RequestParamQuery;
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
public class RequestParamDao{

    private static Logger logger = LoggerFactory.getLogger(RequestParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param requestParamPo
     * @return
     */
    public String createRequestParam(RequestParamPo requestParamPo) {
        return jpaTemplate.save(requestParamPo,String.class);
    }

    /**
     * 更新用户
     * @param requestParamPo
     */
    public void updateRequestParam(RequestParamPo requestParamPo){
        jpaTemplate.update(requestParamPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRequestParam(String id){
        jpaTemplate.delete(RequestParamPo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RequestParamPo findRequestParam(String id){
        return jpaTemplate.findOne(RequestParamPo.class,id);
    }

    /**
    * findAllRequestParam
    * @return
    */
    public List<RequestParamPo> findAllRequestParam() {
        return jpaTemplate.findAll(RequestParamPo.class);
    }

    public List<RequestParamPo> findRequestParamList(RequestParamQuery requestParamQuery) {
        return jpaTemplate.createCriteriaForQuery(RequestParamPo.class).list(requestParamQuery);
    }

    public Pagination<List<RequestParamPo>> findRequestParamPage(RequestParamQuery requestParamQuery) { 
        return jpaTemplate.createCriteriaForQuery(RequestParamPo.class).page(requestParamQuery);
    }
}