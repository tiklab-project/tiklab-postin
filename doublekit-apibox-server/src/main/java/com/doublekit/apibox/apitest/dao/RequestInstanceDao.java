package com.doublekit.apibox.apitest.dao;

import com.doublekit.common.page.Pagination;
import com.doublekit.apibox.apitest.entity.RequestInstanceEntity;
import com.doublekit.apibox.apitest.model.RequestInstanceQuery;
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
public class RequestInstanceDao{

    private static Logger logger = LoggerFactory.getLogger(RequestInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param requestInstanceEntity
     * @return
     */
    public String createRequestInstance(RequestInstanceEntity requestInstanceEntity) {
        return jpaTemplate.save(requestInstanceEntity,String.class);
    }

    /**
     * 更新用户
     * @param requestInstanceEntity
     */
    public void updateRequestInstance(RequestInstanceEntity requestInstanceEntity){
        jpaTemplate.update(requestInstanceEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRequestInstance(String id){
        jpaTemplate.delete(RequestInstanceEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RequestInstanceEntity findRequestInstance(String id){
        return jpaTemplate.findOne(RequestInstanceEntity.class,id);
    }

    /**
    * findAllRequestInstance
    * @return
    */
    public List<RequestInstanceEntity> findAllRequestInstance() {
        return jpaTemplate.findAll(RequestInstanceEntity.class);
    }

    public List<RequestInstanceEntity> findRequestInstanceList(List<String> idList) {
        return jpaTemplate.findList(RequestInstanceEntity.class,idList);
    }

    public List<RequestInstanceEntity> findRequestInstanceList(RequestInstanceQuery requestInstanceQuery) {
        return jpaTemplate.findList(requestInstanceQuery, RequestInstanceEntity.class);
    }

    public Pagination<RequestInstanceEntity> findRequestInstancePage(RequestInstanceQuery requestInstanceQuery) {
        return jpaTemplate.findPage(requestInstanceQuery, RequestInstanceEntity.class);
    }
}