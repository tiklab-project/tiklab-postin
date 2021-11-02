package com.doublekit.apibox.apitest.dao;

import com.doublekit.apibox.apitest.model.RequestHeaderCaseQuery;
import com.doublekit.common.Pagination;
import com.doublekit.apibox.apitest.entity.RequestHeaderCaseEntity;
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
public class RequestHeaderCaseDao{

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param requestHeaderCaseEntity
     * @return
     */
    public String createRequestHeaderCase(RequestHeaderCaseEntity requestHeaderCaseEntity) {
        return jpaTemplate.save(requestHeaderCaseEntity,String.class);
    }

    /**
     * 更新用户
     * @param requestHeaderCaseEntity
     */
    public void updateRequestHeaderCase(RequestHeaderCaseEntity requestHeaderCaseEntity){
        jpaTemplate.update(requestHeaderCaseEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRequestHeaderCase(String id){
        jpaTemplate.delete(RequestHeaderCaseEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RequestHeaderCaseEntity findRequestHeaderCase(String id){
        return jpaTemplate.findOne(RequestHeaderCaseEntity.class,id);
    }

    /**
    * findAllRequestHeaderCase
    * @return
    */
    public List<RequestHeaderCaseEntity> findAllRequestHeaderCase() {
        return jpaTemplate.findAll(RequestHeaderCaseEntity.class);
    }

    public List<RequestHeaderCaseEntity> findRequestHeaderCaseList(List<String> idList) {
        return jpaTemplate.findList(RequestHeaderCaseEntity.class,idList);
    }

    public List<RequestHeaderCaseEntity> findRequestHeaderCaseList(RequestHeaderCaseQuery requestHeaderCaseQuery) {
        return jpaTemplate.findList(RequestHeaderCaseEntity.class,requestHeaderCaseQuery);
    }

    public Pagination<RequestHeaderCaseEntity> findRequestHeaderCasePage(RequestHeaderCaseQuery requestHeaderCaseQuery) {
        return jpaTemplate.findPage(RequestHeaderCaseEntity.class,requestHeaderCaseQuery);
    }
}