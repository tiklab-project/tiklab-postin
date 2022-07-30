package com.tiklab.postin.apidef.http.dao;

import com.tiklab.core.page.Pagination;
import com.tiklab.postin.apidef.http.entity.PreScriptEntity;
import com.tiklab.postin.apidef.http.model.PreScriptQuery;
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
public class PreScriptDao{

    private static Logger logger = LoggerFactory.getLogger(PreScriptDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param preScriptEntity
     * @return
     */
    public String createPreScript(PreScriptEntity preScriptEntity) {
        return jpaTemplate.save(preScriptEntity,String.class);
    }

    /**
     * 更新用户
     * @param preScriptEntity
     */
    public void updatePreScript(PreScriptEntity preScriptEntity){
        jpaTemplate.update(preScriptEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deletePreScript(String id){
        jpaTemplate.delete(PreScriptEntity.class,id);
    }

    /**
     * 通过条件删除
     * @param deleteCondition
     */
    public void deletePreScriptList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }


    /**
     * 查找用户
     * @param id
     * @return
     */
    public PreScriptEntity findPreScript(String id){
        return jpaTemplate.findOne(PreScriptEntity.class,id);
    }

    /**
    * findAllPreScript
    * @return
    */
    public List<PreScriptEntity> findAllPreScript() {
        return jpaTemplate.findAll(PreScriptEntity.class);
    }

    public List<PreScriptEntity> findPreScriptList(PreScriptQuery preScriptQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PreScriptEntity.class)
                .eq("httpId", preScriptQuery.getHttpId())
                .orders(preScriptQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, PreScriptEntity.class);
    }

    public Pagination<PreScriptEntity> findPreScriptPage(PreScriptQuery preScriptQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PreScriptEntity.class)
                .eq("httpId", preScriptQuery.getHttpId())
                .pagination(preScriptQuery.getPageParam())
                .orders(preScriptQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, PreScriptEntity.class);
    }
}