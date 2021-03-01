package com.darthcloud.apibox.apitest.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.apitest.entity.TestcasePo;
import com.darthcloud.apibox.apitest.model.TestcaseQuery;
import com.darthcloud.dal.jpa.JpaTemplate;
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
     * @param testcasePo
     * @return
     */
    public String createTestcase(TestcasePo testcasePo) {
        return jpaTemplate.save(testcasePo,String.class);
    }

    /**
     * 更新用户
     * @param testcasePo
     */
    public void updateTestcase(TestcasePo testcasePo){
        jpaTemplate.update(testcasePo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteTestcase(String id){
        jpaTemplate.delete(TestcasePo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public TestcasePo findTestcase(String id){
        return jpaTemplate.findOne(TestcasePo.class,id);
    }

    /**
    * findAllTestcase
    * @return
    */
    public List<TestcasePo> findAllTestcase() {
        return jpaTemplate.findAll(TestcasePo.class);
    }

    public List<TestcasePo> findTestcaseList(List<String> idList) {
        return jpaTemplate.findList(TestcasePo.class,idList);
    }

    public List<TestcasePo> findTestcaseList(TestcaseQuery testcaseQuery) {
        return jpaTemplate.createCriteria(TestcasePo.class).params(testcaseQuery).list();
    }

    public Pagination<List<TestcasePo>> findTestcasePage(TestcaseQuery testcaseQuery) { 
        return jpaTemplate.createCriteria(TestcasePo.class).params(testcaseQuery).page();
    }
}