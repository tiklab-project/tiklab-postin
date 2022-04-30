package com.doublekit.apibox.apidef.http.dao;

import com.doublekit.common.page.Pagination;
import com.doublekit.apibox.apidef.http.entity.FormParamEntity;
import com.doublekit.apibox.apidef.http.model.FormParamQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import com.doublekit.dal.jpa.criterial.condition.DeleteCondition;
import com.doublekit.dal.jpa.criterial.condition.QueryCondition;
import com.doublekit.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class FormParamDao{

    private static Logger logger = LoggerFactory.getLogger(FormParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param formParamEntity
     * @return
     */
    public String createFormParam(FormParamEntity formParamEntity) {
        return jpaTemplate.save(formParamEntity,String.class);
    }

    /**
     * 更新用户
     * @param formParamEntity
     */
    public void updateFormParam(FormParamEntity formParamEntity){
        jpaTemplate.update(formParamEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteFormParam(String id){
        jpaTemplate.delete(FormParamEntity.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteFormParamLsit(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public FormParamEntity findFormParam(String id){
        return jpaTemplate.findOne(FormParamEntity.class,id);
    }

    /**
    * findAllFormParam
    * @return
    */
    public List<FormParamEntity> findAllFormParam() {
        return jpaTemplate.findAll(FormParamEntity.class);
    }

    public List<FormParamEntity> findFormParamList(FormParamQuery formParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormParamEntity.class)
                .eq("httpId",formParamQuery.getHttpId())
                .orders(formParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, FormParamEntity.class);
    }

    public Pagination<FormParamEntity> findFormParamPage(FormParamQuery formParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormParamEntity.class)
                .eq("httpId",formParamQuery.getHttpId())
                .pagination(formParamQuery.getPageParam())
                .orders(formParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, FormParamEntity.class);
    }
}