package com.doublekit.apibox.apitest.apicase.dao;

import com.doublekit.apibox.apitest.apicase.entity.AfterScriptCaseEntity;
import com.doublekit.apibox.apitest.apicase.model.AfterScriptCaseQuery;
import com.doublekit.common.page.Pagination;
import com.doublekit.dal.jpa.JpaTemplate;
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
public class AfterScriptCaseDao{

    private static Logger logger = LoggerFactory.getLogger(AfterScriptCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param afterScriptCaseEntity
     * @return
     */
    public String createAfterScriptCase(AfterScriptCaseEntity afterScriptCaseEntity) {
        return jpaTemplate.save(afterScriptCaseEntity,String.class);
    }

    /**
     * 更新用户
     * @param afterScriptCaseEntity
     */
    public void updateAfterScriptCase(AfterScriptCaseEntity afterScriptCaseEntity){
        jpaTemplate.update(afterScriptCaseEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteAfterScriptCase(String id){
        jpaTemplate.delete(AfterScriptCaseEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public AfterScriptCaseEntity findAfterScriptCase(String id){
        return jpaTemplate.findOne(AfterScriptCaseEntity.class,id);
    }

    /**
    * findAllAfterScriptCase
    * @return
    */
    public List<AfterScriptCaseEntity> findAllAfterScriptCase() {
        return jpaTemplate.findAll(AfterScriptCaseEntity.class);
    }

    public List<AfterScriptCaseEntity> findAfterScriptCaseList(List<String> idList) {
        return jpaTemplate.findList(AfterScriptCaseEntity.class,idList);
    }

    public List<AfterScriptCaseEntity> findAfterScriptCaseList(AfterScriptCaseQuery afterScriptCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AfterScriptCaseEntity.class)
                .eq("testcaseId", afterScriptCaseQuery.getTestcaseId())
                .orders(afterScriptCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, AfterScriptCaseEntity.class);
    }

    public Pagination<AfterScriptCaseEntity> findAfterScriptCasePage(AfterScriptCaseQuery afterScriptCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AfterScriptCaseEntity.class)
                .eq("testcaseId", afterScriptCaseQuery.getTestcaseId())
                .pagination(afterScriptCaseQuery.getPageParam())
                .orders(afterScriptCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, AfterScriptCaseEntity.class);
    }
}