package com.tiklab.postlink.apidef.http.dao;

import com.tiklab.postlink.apidef.http.entity.AfterScriptEntity;
import com.tiklab.postlink.apidef.http.model.AfterScriptQuery;
import com.tiklab.core.page.Pagination;
import com.tiklab.dal.jpa.JpaTemplate;
import com.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import com.tiklab.dal.jpa.criterial.condition.QueryCondition;
import com.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
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
                .eq("httpId", afterScriptQuery.getHttpId())
                .orders(afterScriptQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, AfterScriptEntity.class);
    }

    public Pagination<AfterScriptEntity> findAfterScriptPage(AfterScriptQuery afterScriptQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AfterScriptEntity.class)
                .eq("httpId", afterScriptQuery.getHttpId())
                .pagination(afterScriptQuery.getPageParam())
                .orders(afterScriptQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, AfterScriptEntity.class);
    }
}