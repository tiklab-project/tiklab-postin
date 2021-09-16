package com.doublekit.apibox.apidef.dao;

import com.doublekit.apibox.apidef.entity.ResponseResultPo;
import com.doublekit.apibox.apidef.model.ResponseResultQuery;
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
public class ResponseResultDao{

    private static Logger logger = LoggerFactory.getLogger(ResponseResultDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param responseResultPo
     * @return
     */
    public String createResponseResult(ResponseResultPo responseResultPo) {
        return jpaTemplate.save(responseResultPo,String.class);
    }

    /**
     * 更新用户
     * @param responseResultPo
     */
    public void updateResponseResult(ResponseResultPo responseResultPo){
        jpaTemplate.update(responseResultPo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteResponseResult(String id){
        jpaTemplate.delete(ResponseResultPo.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteResponseResultList(DeleteCondition deleteCondition){
        jpaTemplate.delete(ResponseResultPo.class,deleteCondition);
    }


    /**
     * 查找用户
     * @param id
     * @return
     */
    public ResponseResultPo findResponseResult(String id){
        return jpaTemplate.findOne(ResponseResultPo.class,id);
    }

    /**
    * findAllResponseResult
    * @return
    */
    public List<ResponseResultPo> findAllResponseResult() {
        return jpaTemplate.findAll(ResponseResultPo.class);
    }

    public List<ResponseResultPo> findResponseResultList(ResponseResultQuery responseResultQuery) {
        return jpaTemplate.findList(ResponseResultPo.class,responseResultQuery);
    }

    public Pagination<ResponseResultPo> findResponseResultPage(ResponseResultQuery responseResultQuery) {
        return jpaTemplate.findPage(ResponseResultPo.class,responseResultQuery);
    }
}