package com.tiklab.postin.apitest.http.httpcase.dao;

import com.tiklab.core.page.Pagination;
import com.tiklab.postin.apitest.http.httpcase.entity.PreScriptCaseEntity;
import com.tiklab.postin.apitest.http.httpcase.model.PreScriptCaseQuery;
import com.tiklab.dal.jpa.JpaTemplate;
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
public class PreScriptCaseDao{

    private static Logger logger = LoggerFactory.getLogger(PreScriptCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param preScriptCaseEntity
     * @return
     */
    public String createPreScriptCase(PreScriptCaseEntity preScriptCaseEntity) {
        return jpaTemplate.save(preScriptCaseEntity,String.class);
    }

    /**
     * 更新用户
     * @param preScriptCaseEntity
     */
    public void updatePreScriptCase(PreScriptCaseEntity preScriptCaseEntity){
        jpaTemplate.update(preScriptCaseEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deletePreScriptCase(String id){
        jpaTemplate.delete(PreScriptCaseEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public PreScriptCaseEntity findPreScriptCase(String id){
        return jpaTemplate.findOne(PreScriptCaseEntity.class,id);
    }

    /**
    * findAllPreScriptCase
    * @return
    */
    public List<PreScriptCaseEntity> findAllPreScriptCase() {
        return jpaTemplate.findAll(PreScriptCaseEntity.class);
    }

    public List<PreScriptCaseEntity> findPreScriptCaseList(List<String> idList) {
        return jpaTemplate.findList(PreScriptCaseEntity.class,idList);
    }

    public List<PreScriptCaseEntity> findPreScriptCaseList(PreScriptCaseQuery preScriptCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PreScriptCaseEntity.class)
                .eq("httpCaseId", preScriptCaseQuery.getHttpCaseId())
                .orders(preScriptCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, PreScriptCaseEntity.class);
    }

    public Pagination<PreScriptCaseEntity> findPreScriptCasePage(PreScriptCaseQuery preScriptCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PreScriptCaseEntity.class)
                .eq("httpCaseId", preScriptCaseQuery.getHttpCaseId())
                .pagination(preScriptCaseQuery.getPageParam())
                .orders(preScriptCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, PreScriptCaseEntity.class);
    }
}