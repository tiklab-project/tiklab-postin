package com.tiklab.postlink.apitest.http.httpinstance.dao;

import com.tiklab.postlink.apitest.http.httpinstance.entity.AssertInstanceEntity;
import com.tiklab.postlink.apitest.http.httpinstance.model.AssertInstanceQuery;
import com.tiklab.core.page.Pagination;
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
public class AssertInstanceDao{

    private static Logger logger = LoggerFactory.getLogger(AssertInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param assertInstanceEntity
     * @return
     */
    public String createAssertInstance(AssertInstanceEntity assertInstanceEntity) {
        return jpaTemplate.save(assertInstanceEntity,String.class);
    }

    /**
     * 更新用户
     * @param assertInstanceEntity
     */
    public void updateAssertInstance(AssertInstanceEntity assertInstanceEntity){
        jpaTemplate.update(assertInstanceEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteAssertInstance(String id){
        jpaTemplate.delete(AssertInstanceEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public AssertInstanceEntity findAssertInstance(String id){
        return jpaTemplate.findOne(AssertInstanceEntity.class,id);
    }

    /**
    * findAllAssertInstance
    * @return
    */
    public List<AssertInstanceEntity> findAllAssertInstance() {
        return jpaTemplate.findAll(AssertInstanceEntity.class);
    }

    public List<AssertInstanceEntity> findAssertInstanceList(List<String> idList) {
        return jpaTemplate.findList(AssertInstanceEntity.class,idList);
    }

    public List<AssertInstanceEntity> findAssertInstanceList(AssertInstanceQuery assertInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AssertInstanceEntity.class)
                .eq("httpInstanceId", assertInstanceQuery.getHttpInstanceId())
                .orders(assertInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, AssertInstanceEntity.class);
    }

    public Pagination<AssertInstanceEntity> findAssertInstancePage(AssertInstanceQuery assertInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AssertInstanceEntity.class)
                .eq("httpInstanceId", assertInstanceQuery.getHttpInstanceId())
                .pagination(assertInstanceQuery.getPageParam())
                .orders(assertInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, AssertInstanceEntity.class);
    }
}