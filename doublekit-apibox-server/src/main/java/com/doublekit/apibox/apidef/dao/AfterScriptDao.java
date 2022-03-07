package com.doublekit.apibox.apidef.dao;

import com.doublekit.apibox.apidef.entity.AfterScriptEntity;
import com.doublekit.apibox.apidef.model.AfterScriptQuery;
import com.doublekit.common.page.Pagination;
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
public class AfterScriptDao{

    private static Logger logger = LoggerFactory.getLogger(AfterScriptDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param afterScriptEntity
     * @return
     */
    public String createAfterScript(AfterScriptEntity afterScriptEntity) {
        return jpaTemplate.save(afterScriptEntity,String.class);
    }

    /**
     * 更新用户
     * @param afterScriptEntity
     */
    public void updateAfterScript(AfterScriptEntity afterScriptEntity){
        jpaTemplate.update(afterScriptEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteAfterScript(String id){
        jpaTemplate.delete(AfterScriptEntity.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deleteAfterScriptList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }
    /**
     * 查找用户
     * @param id
     * @return
     */
    public AfterScriptEntity findAfterScript(String id){
        return jpaTemplate.findOne(AfterScriptEntity.class,id);
    }

    /**
    * findAllAfterScript
    * @return
    */
    public List<AfterScriptEntity> findAllAfterScript() {
        return jpaTemplate.findAll(AfterScriptEntity.class);
    }

    public List<AfterScriptEntity> findAfterScriptList(AfterScriptQuery afterScriptQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AfterScriptEntity.class)
                .eq("methodId", afterScriptQuery.getMethodId())
                .orders(afterScriptQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, AfterScriptEntity.class);
    }

    public Pagination<AfterScriptEntity> findAfterScriptPage(AfterScriptQuery afterScriptQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AfterScriptEntity.class)
                .eq("methodId", afterScriptQuery.getMethodId())
                .pagination(afterScriptQuery.getPageParam())
                .orders(afterScriptQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, AfterScriptEntity.class);
    }
}