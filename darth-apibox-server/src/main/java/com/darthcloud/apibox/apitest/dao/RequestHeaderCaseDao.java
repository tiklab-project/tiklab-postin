package com.darthcloud.apibox.apitest.dao;

import com.darthcloud.apibox.apitest.model.RequestHeaderCaseQuery;
import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.apitest.entity.RequestHeaderCasePo;
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
public class RequestHeaderCaseDao{

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param requestHeaderCasePo
     * @return
     */
    public String createRequestHeaderCase(RequestHeaderCasePo requestHeaderCasePo) {
        return jpaTemplate.save(requestHeaderCasePo,String.class);
    }

    /**
     * 更新用户
     * @param requestHeaderCasePo
     */
    public void updateRequestHeaderCase(RequestHeaderCasePo requestHeaderCasePo){
        jpaTemplate.update(requestHeaderCasePo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRequestHeaderCase(String id){
        jpaTemplate.delete(RequestHeaderCasePo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RequestHeaderCasePo findRequestHeaderCase(String id){
        return jpaTemplate.findOne(RequestHeaderCasePo.class,id);
    }

    /**
    * findAllRequestHeaderCase
    * @return
    */
    public List<RequestHeaderCasePo> findAllRequestHeaderCase() {
        return jpaTemplate.findAll(RequestHeaderCasePo.class);
    }

    public List<RequestHeaderCasePo> findRequestHeaderCaseList(List<String> idList) {
        return jpaTemplate.findList(RequestHeaderCasePo.class,idList);
    }

    public List<RequestHeaderCasePo> findRequestHeaderCaseList(RequestHeaderCaseQuery requestHeaderCaseQuery) {
        return jpaTemplate.findList(RequestHeaderCasePo.class,requestHeaderCaseQuery);
    }

    public Pagination<RequestHeaderCasePo> findRequestHeaderCasePage(RequestHeaderCaseQuery requestHeaderCaseQuery) {
        return jpaTemplate.findPage(RequestHeaderCasePo.class,requestHeaderCaseQuery);
    }
}