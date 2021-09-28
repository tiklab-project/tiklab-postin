package com.doublekit.apibox.apitest.dao;

import com.doublekit.common.Pagination;
import com.doublekit.apibox.apitest.entity.RequestInstancePo;
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
     * @param requestInstancePo
     * @return
     */
    public String createRequestInstance(RequestInstancePo requestInstancePo) {
        return jpaTemplate.save(requestInstancePo,String.class);
    }

    /**
     * 更新用户
     * @param requestInstancePo
     */
    public void updateRequestInstance(RequestInstancePo requestInstancePo){
        jpaTemplate.update(requestInstancePo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRequestInstance(String id){
        jpaTemplate.delete(RequestInstancePo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RequestInstancePo findRequestInstance(String id){
        return jpaTemplate.findOne(RequestInstancePo.class,id);
    }

    /**
    * findAllRequestInstance
    * @return
    */
    public List<RequestInstancePo> findAllRequestInstance() {
        return jpaTemplate.findAll(RequestInstancePo.class);
    }

    public List<RequestInstancePo> findRequestInstanceList(List<String> idList) {
        return jpaTemplate.findList(RequestInstancePo.class,idList);
    }

    public List<RequestInstancePo> findRequestInstanceList(RequestInstanceQuery requestInstanceQuery) {
        return jpaTemplate.findList(RequestInstancePo.class,requestInstanceQuery);
    }

    public Pagination<RequestInstancePo> findRequestInstancePage(RequestInstanceQuery requestInstanceQuery) {
        return jpaTemplate.findPage(RequestInstancePo.class,requestInstanceQuery);
    }
}