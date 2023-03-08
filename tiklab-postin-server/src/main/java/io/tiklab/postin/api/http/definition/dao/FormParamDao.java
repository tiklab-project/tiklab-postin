package io.tiklab.postin.api.http.definition.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.api.http.definition.entity.FormParamEntity;
import io.tiklab.postin.api.http.definition.model.FormParamQuery;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定义
 * http协议
 * formdata 数据访问
 */
@Repository
public class FormParamDao{

    private static Logger logger = LoggerFactory.getLogger(FormParamDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建formdata
     * @param formParamEntity
     * @return
     */
    public String createFormParam(FormParamEntity formParamEntity) {
        return jpaTemplate.save(formParamEntity,String.class);
    }

    /**
     * 更新formdata
     * @param formParamEntity
     */
    public void updateFormParam(FormParamEntity formParamEntity){
        jpaTemplate.update(formParamEntity);
    }

    /**
     * 删除formdata
     * @param id
     */
    public void deleteFormParam(String id){
        jpaTemplate.delete(FormParamEntity.class,id);
    }

    /**
     * 通过条件删除formdata
     * @param deleteCondition
     */
    public void deleteFormParamLsit(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找formdata
     * @param id
     * @return
     */
    public FormParamEntity findFormParam(String id){
        return jpaTemplate.findOne(FormParamEntity.class,id);
    }

    /**
    * 查找所有formdata
    * @return
    */
    public List<FormParamEntity> findAllFormParam() {
        return jpaTemplate.findAll(FormParamEntity.class);
    }

    /**
     * 根据查询参数查找formdata列表
     * @param formParamQuery
     * @return
     */
    public List<FormParamEntity> findFormParamList(FormParamQuery formParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormParamEntity.class)
                .eq("httpId",formParamQuery.getHttpId())
                .orders(formParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, FormParamEntity.class);
    }

    /**
     * 根据查询参数按分页查找formdata列表
     * @param formParamQuery
     * @return
     */
    public Pagination<FormParamEntity> findFormParamPage(FormParamQuery formParamQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormParamEntity.class)
                .eq("httpId",formParamQuery.getHttpId())
                .pagination(formParamQuery.getPageParam())
                .orders(formParamQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, FormParamEntity.class);
    }
}