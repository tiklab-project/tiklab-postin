package com.doublekit.apibox.apidef.dao;

import com.doublekit.apibox.apidef.entity.RequestHeaderPo;
import com.doublekit.common.Pagination;
import com.doublekit.apibox.apidef.entity.ResponseHeaderPo;
import com.doublekit.apibox.apidef.model.ResponseHeaderQuery;
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
public class ResponseHeaderDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseHeaderDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param responseHeaderPo
     * @return
     */
    public String createResponseHeader(ResponseHeaderPo responseHeaderPo) {
        return jpaTemplate.save(responseHeaderPo,String.class);
    }

    /**
     * 更新用户
     * @param responseHeaderPo
     */
    public void updateResponseHeader(ResponseHeaderPo responseHeaderPo){
        jpaTemplate.update(responseHeaderPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteResponseHeader(String id){
        jpaTemplate.delete(ResponseHeaderPo.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteResponseHeaderList(DeleteCondition deleteCondition){
        jpaTemplate.delete(ResponseHeaderPo.class,deleteCondition);
    }


    /**
     * 查找用户
     * @param id
     * @return
     */
    public ResponseHeaderPo findResponseHeader(String id){
        return jpaTemplate.findOne(ResponseHeaderPo.class,id);
    }

    /**
    * findAllResponseHeader
    * @return
    */
    public List<ResponseHeaderPo> findAllResponseHeader() {
        return jpaTemplate.findAll(ResponseHeaderPo.class);
    }

    public List<ResponseHeaderPo> findResponseHeaderList(ResponseHeaderQuery responseHeaderQuery) {
        return jpaTemplate.findList(ResponseHeaderPo.class,responseHeaderQuery);
    }

    public Pagination<ResponseHeaderPo> findResponseHeaderPage(ResponseHeaderQuery responseHeaderQuery) {
        return jpaTemplate.findPage(ResponseHeaderPo.class,responseHeaderQuery);
    }
}