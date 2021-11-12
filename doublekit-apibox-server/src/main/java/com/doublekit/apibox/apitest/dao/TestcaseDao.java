package com.doublekit.apibox.apitest.dao;

import com.doublekit.common.page.Pagination;
import com.doublekit.apibox.apitest.entity.TestcaseEntity;
import com.doublekit.apibox.apitest.model.TestcaseQuery;
import com.doublekit.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class TestcaseDao{

    private static Logger logger = LoggerFactory.getLogger(TestcaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param testcaseEntity
     * @return
     */
    public String createTestcase(TestcaseEntity testcaseEntity) {
        return jpaTemplate.save(testcaseEntity,String.class);
    }

    /**
     * 更新用户
     * @param testcaseEntity
     */
    public void updateTestcase(TestcaseEntity testcaseEntity){
        jpaTemplate.update(testcaseEntity);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteTestcase(String id){
        jpaTemplate.delete(TestcaseEntity.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public TestcaseEntity findTestcase(String id){
        return jpaTemplate.findOne(TestcaseEntity.class,id);
    }

    /**
    * findAllTestcase
    * @return
    */
    public List<TestcaseEntity> findAllTestcase() {
        return jpaTemplate.findAll(TestcaseEntity.class);
    }

    public List<TestcaseEntity> findTestcaseList(List<String> idList) {
        return jpaTemplate.findList(TestcaseEntity.class,idList);
    }

    public List<TestcaseEntity> findTestcaseList(TestcaseQuery testcaseQuery) {
        return jpaTemplate.findList(testcaseQuery, TestcaseEntity.class);
    }

    public Pagination<TestcaseEntity> findTestcasePage(TestcaseQuery testcaseQuery) {
        return jpaTemplate.findPage(testcaseQuery, TestcaseEntity.class);
    }
}