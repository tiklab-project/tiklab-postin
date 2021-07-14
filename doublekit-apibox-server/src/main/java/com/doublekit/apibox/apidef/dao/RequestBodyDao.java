package com.doublekit.apibox.apidef.dao;

import com.doublekit.common.Pagination;
import com.doublekit.apibox.apidef.entity.RequestBodyPo;
import com.doublekit.apibox.apidef.model.RequestBodyExQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.builder.deletelist.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class RequestBodyDao{

    private static Logger logger = LoggerFactory.getLogger(RequestBodyDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param requestBodyPo
     * @return
     */
    public String createRequestBody(RequestBodyPo requestBodyPo) {
        return jpaTemplate.save(requestBodyPo,String.class);
    }

    /**
     * 更新用户
     * @param requestBodyPo
     */
    public void updateRequestBody(RequestBodyPo requestBodyPo){
        jpaTemplate.update(requestBodyPo);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteRequestBodyList(DeleteCondition deleteCondition){
        jpaTemplate.delete(RequestBodyPo.class,deleteCondition);
    }
    /**
     * 删除
     * @param id
     */
    public void deleteRequestBody(String id){
        jpaTemplate.delete(RequestBodyPo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RequestBodyPo findRequestBody(String id){
        return jpaTemplate.findOne(RequestBodyPo.class,id);
    }

    /**
    * findAllRequestBody
    * @return
    */
    public List<RequestBodyPo> findAllRequestBody() {
        return jpaTemplate.findAll(RequestBodyPo.class);
    }

    public List<RequestBodyPo> findRequestBodyList(RequestBodyExQuery requestBodyQuery) {
        return jpaTemplate.findList(RequestBodyPo.class,requestBodyQuery);
    }

    public Pagination<RequestBodyPo> findRequestBodyPage(RequestBodyExQuery requestBodyQuery) {
        return jpaTemplate.findPage(RequestBodyPo.class,requestBodyQuery);
    }
}