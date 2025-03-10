package io.tiklab.postin.api.http.test.cases.dao;

import io.tiklab.postin.api.http.test.cases.entity.FormParamCaseEntity;
import io.tiklab.postin.api.http.test.cases.model.FormParamCaseQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class FormParamCaseDao{

    private static Logger logger = LoggerFactory.getLogger(FormParamCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param formParamCaseEntity
     * @return
     */
    public String createFormParamCase(FormParamCaseEntity formParamCaseEntity) {
        return jpaTemplate.save(formParamCaseEntity,String.class);
    }

    /**
     * 更新用户
     * @param formParamCaseEntity
     */
    public void updateFormParamCase(FormParamCaseEntity formParamCaseEntity){
        jpaTemplate.update(formParamCaseEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteFormParamCase(String id){
        jpaTemplate.delete(FormParamCaseEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public FormParamCaseEntity findFormParamCase(String id){
        return jpaTemplate.findOne(FormParamCaseEntity.class,id);
    }

    /**
    * findAllFormParamCase
    * @return
    */
    public List<FormParamCaseEntity> findAllFormParamCase() {
        return jpaTemplate.findAll(FormParamCaseEntity.class);
    }

    public List<FormParamCaseEntity> findFormParamCaseList(List<String> idList) {
        return jpaTemplate.findList(FormParamCaseEntity.class,idList);
    }

    public List<FormParamCaseEntity> findFormParamCaseList(FormParamCaseQuery formParamCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormParamCaseEntity.class)
                .eq("httpCaseId",formParamCaseQuery.getHttpCaseId())
                .orders(formParamCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, FormParamCaseEntity.class);
    }

    public Pagination<FormParamCaseEntity> findFormParamCasePage(FormParamCaseQuery formParamCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FormParamCaseEntity.class)
                .eq("httpCaseId",formParamCaseQuery.getHttpCaseId())
                .pagination(formParamCaseQuery.getPageParam())
                .orders(formParamCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, FormParamCaseEntity.class);
    }
}