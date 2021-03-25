package com.darthcloud.apibox.apitest.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.apitest.entity.RequestBodyCasePo;
import com.darthcloud.apibox.apitest.model.RequestBodyCaseQuery;
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
public class RequestBodyCaseDao{

    private static Logger logger = LoggerFactory.getLogger(RequestBodyCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param requestBodyCasePo
     * @return
     */
    public String createRequestBodyCase(RequestBodyCasePo requestBodyCasePo) {
        return jpaTemplate.save(requestBodyCasePo,String.class);
    }

    /**
     * 更新用户
     * @param requestBodyCasePo
     */
    public void updateRequestBodyCase(RequestBodyCasePo requestBodyCasePo){
        jpaTemplate.update(requestBodyCasePo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRequestBodyCase(String id){
        jpaTemplate.delete(RequestBodyCasePo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RequestBodyCasePo findRequestBodyCase(String id){
        return jpaTemplate.findOne(RequestBodyCasePo.class,id);
    }

    /**
    * findAllRequestBodyCase
    * @return
    */
    public List<RequestBodyCasePo> findAllRequestBodyCase() {
        return jpaTemplate.findAll(RequestBodyCasePo.class);
    }

    public List<RequestBodyCasePo> findRequestBodyCaseList(List<String> idList) {
        return jpaTemplate.findList(RequestBodyCasePo.class,idList);
    }

    public List<RequestBodyCasePo> findRequestBodyCaseList(RequestBodyCaseQuery requestBodyCaseQuery) {
        return jpaTemplate.findList(RequestBodyCasePo.class,requestBodyCaseQuery);
    }

    public Pagination<RequestBodyCasePo> findRequestBodyCasePage(RequestBodyCaseQuery requestBodyCaseQuery) {
        return jpaTemplate.findPage(RequestBodyCasePo.class,requestBodyCaseQuery);
    }
}