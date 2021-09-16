package com.doublekit.apibox.apidef.dao;

import com.doublekit.apibox.apidef.entity.RequestBodyPo;
import com.doublekit.apibox.apidef.entity.RequestHeaderPo;
import com.doublekit.apibox.apidef.model.RequestHeaderQuery;
import com.doublekit.common.Pagination;
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
public class RequestHeaderDao{

    private static Logger logger = LoggerFactory.getLogger(RequestHeaderDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param requestHeaderPo
     * @return
     */
    public String createRequestHeader(RequestHeaderPo requestHeaderPo) {
        return jpaTemplate.save(requestHeaderPo,String.class);
    }

    /**
     * 更新用户
     * @param requestHeaderPo
     */
    public void updateRequestHeader(RequestHeaderPo requestHeaderPo){
        jpaTemplate.update(requestHeaderPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteRequestHeader(String id){
        jpaTemplate.delete(RequestHeaderPo.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteRequestHeaderList(DeleteCondition deleteCondition){
        jpaTemplate.delete(RequestHeaderPo.class,deleteCondition);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public RequestHeaderPo findRequestHeader(String id){
        return jpaTemplate.findOne(RequestHeaderPo.class,id);
    }

    /**
    * findAllRequestHeader
    * @return
    */
    public List<RequestHeaderPo> findAllRequestHeader() {
        return jpaTemplate.findAll(RequestHeaderPo.class);
    }

    public List<RequestHeaderPo> findRequestHeaderList(RequestHeaderQuery requestHeaderQuery) {
        return jpaTemplate.findList(RequestHeaderPo.class,requestHeaderQuery);
    }

    public Pagination<RequestHeaderPo> findRequestHeaderPage(RequestHeaderQuery requestHeaderQuery) {
        return jpaTemplate.findPage(RequestHeaderPo.class,requestHeaderQuery);
    }
}