package com.doublekit.apibox.apitest.apiinstance.dao;

import com.doublekit.apibox.apitest.apiinstance.model.TestInstanceQuery;
import com.doublekit.common.page.Pagination;
import com.doublekit.apibox.apitest.apiinstance.entity.TestInstanceEntity;
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
public class TestInstanceDao{

    private static Logger logger = LoggerFactory.getLogger(TestInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param testInstanceEntity
     * @return
     */
    public String createTestInstance(TestInstanceEntity testInstanceEntity) {
        return jpaTemplate.save(testInstanceEntity,String.class);
    }

    /**
     * 更新用户
     * @param testInstanceEntity
     */
    public void updateTestInstance(TestInstanceEntity testInstanceEntity){
        jpaTemplate.update(testInstanceEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteTestInstance(String id){
        jpaTemplate.delete(TestInstanceEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public TestInstanceEntity findTestInstance(String id){
        return jpaTemplate.findOne(TestInstanceEntity.class,id);
    }

    /**
    * findAllTestInstance
    * @return
    */
    public List<TestInstanceEntity> findAllTestInstance() {
        return jpaTemplate.findAll(TestInstanceEntity.class);
    }

    public List<TestInstanceEntity> findTestInstanceList(List<String> idList) {
        return jpaTemplate.findList(TestInstanceEntity.class,idList);
    }

    public List<TestInstanceEntity> findTestInstanceList(TestInstanceQuery testInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestInstanceEntity.class)
                .eq("testcaseId", testInstanceQuery.getTestcaseId())
                .orders(testInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, TestInstanceEntity.class);
    }

    public Pagination<TestInstanceEntity> findTestInstancePage(TestInstanceQuery testInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestInstanceEntity.class)
                .eq("testcaseId", testInstanceQuery.getTestcaseId())
                .pagination(testInstanceQuery.getPageParam())
                .orders(testInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, TestInstanceEntity.class);
    }
}