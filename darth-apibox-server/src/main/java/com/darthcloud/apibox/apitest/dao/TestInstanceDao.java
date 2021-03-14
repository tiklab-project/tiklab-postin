package com.darthcloud.apibox.apitest.dao;

import com.darthcloud.common.Pagination;
import com.darthcloud.apibox.apitest.entity.TestInstancePo;
import com.darthcloud.apibox.apitest.model.TestInstanceQuery;
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
public class TestInstanceDao{

    private static Logger logger = LoggerFactory.getLogger(TestInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建用户
     * @param testInstancePo
     * @return
     */
    public String createTestInstance(TestInstancePo testInstancePo) {
        return jpaTemplate.save(testInstancePo,String.class);
    }

    /**
     * 更新用户
     * @param testInstancePo
     */
    public void updateTestInstance(TestInstancePo testInstancePo){
        jpaTemplate.update(testInstancePo);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteTestInstance(String id){
        jpaTemplate.delete(TestInstancePo.class,id);
    }

    /**
     * 查找用户
     * @param id
     * @return
     */
    public TestInstancePo findTestInstance(String id){
        return jpaTemplate.findOne(TestInstancePo.class,id);
    }

    /**
    * findAllTestInstance
    * @return
    */
    public List<TestInstancePo> findAllTestInstance() {
        return jpaTemplate.findAll(TestInstancePo.class);
    }

    public List<TestInstancePo> findTestInstanceList(List<String> idList) {
        return jpaTemplate.findList(TestInstancePo.class,idList);
    }

    public List<TestInstancePo> findTestInstanceList(TestInstanceQuery testInstanceQuery) {
        return jpaTemplate.findList(TestInstancePo.class,testInstanceQuery);
    }

    public Pagination<List<TestInstancePo>> findTestInstancePage(TestInstanceQuery testInstanceQuery) { 
        return jpaTemplate.findPage(TestInstancePo.class,testInstanceQuery);
    }
}