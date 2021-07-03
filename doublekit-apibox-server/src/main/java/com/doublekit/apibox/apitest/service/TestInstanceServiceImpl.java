package com.doublekit.apibox.apitest.service;

import com.doublekit.apibox.apitest.dao.TestInstanceDao;
import com.doublekit.apibox.apitest.entity.TestInstancePo;
import com.doublekit.apibox.apitest.model.*;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.join.join.JoinQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Random;

import org.springframework.beans.BeanUtils;

/**
* 用户服务业务处理
*/
@Service
public class TestInstanceServiceImpl implements TestInstanceService {

    @Autowired
    TestInstanceDao testInstanceDao;

    @Autowired
    JoinQuery joinQuery;

    @Autowired
    RequestInstanceService requestInstanceService;

    @Autowired
    ResponseInstanceService responseInstanceService;

    @Autowired
    AssertInstanceService assertInstanceService;

    @Override
    public String createTestInstance(@NotNull @Valid TestInstance testInstance) {
        TestInstancePo testInstancePo = BeanMapper.map(testInstance, TestInstancePo.class);

        return testInstanceDao.createTestInstance(testInstancePo);
    }

    @Override
    public String createTestInstanceWithNest(TestInstance testInstance) {
        //保存实例-主表
        Integer testNo = new Random().nextInt(1000);
        testInstance.setTestNo(testNo);
        String id = createTestInstance(testInstance);

        //保存实例-请求从表
        RequestInstance requestInstance = testInstance.getRequestInstance();
        if(requestInstance != null){
            requestInstance.setId(id);
            requestInstance.setTestInstance(new TestInstance().setId(id));
            requestInstanceService.createRequestInstance(requestInstance);
        }

        //保存实例-响应从表
        ResponseInstance responseInstance = testInstance.getResponseInstance();
        if(responseInstance != null){
            responseInstance.setId(id);
            responseInstance.setTestInstance(new TestInstance().setId(id));
            responseInstanceService.createResponseInstance(responseInstance);
        }

        //保存实例-断言子表
        List<AssertInstance> assertInstanceList = testInstance.getAssertInstanceList();
        if(assertInstanceList != null && assertInstanceList.size() > 0){
            for(AssertInstance assertInstance:assertInstanceList){
                assertInstance.setTestInstance(new TestInstance().setId(id));
                assertInstanceService.createAssertInstance(assertInstance);
            }
        }
        return id;
    }

    @Override
    public void updateTestInstance(@NotNull @Valid TestInstance testInstance) {
        TestInstancePo testInstancePo = BeanMapper.map(testInstance, TestInstancePo.class);

        testInstanceDao.updateTestInstance(testInstancePo);
    }

    @Override
    public void deleteTestInstance(@NotNull String id) {
        testInstanceDao.deleteTestInstance(id);
    }

    @Override
    public TestInstance findOne(String id) {
        TestInstancePo testInstancePo = testInstanceDao.findTestInstance(id);

        TestInstance testInstance = BeanMapper.map(testInstancePo, TestInstance.class);
        return testInstance;
    }

    @Override
    public List<TestInstance> findList(List<String> idList) {
        List<TestInstancePo> testInstancePoList =  testInstanceDao.findTestInstanceList(idList);

        List<TestInstance> testInstanceList =  BeanMapper.mapList(testInstancePoList,TestInstance.class);
        return testInstanceList;
    }

    @Override
    public TestInstance findTestInstance(@NotNull String id) {
        TestInstance testInstance = findOne(id);

        joinQuery.queryOne(testInstance);
        return testInstance;
    }

    @Override
    public TestInstance findTestInstanceWithNest(String id) {
        //查找实例-主表
        TestInstance testInstance = findTestInstance(id);

        //查找实例-请求从表
        RequestInstance requestInstance = requestInstanceService.findRequestInstance(id);
        if(requestInstance != null){
            testInstance.setRequestInstance(requestInstance);
        }

        //查找实例-响应从表
        ResponseInstance responseInstance = responseInstanceService.findResponseInstance(id);
        if(responseInstance != null){
            testInstance.setResponseInstance(responseInstance);
        }

        //查找实例-断言子表
        AssertInstanceQuery assertInstanceQuery = new AssertInstanceQuery();
        assertInstanceQuery.setInstanceId(id);
        List<AssertInstance> assertInstanceList = assertInstanceService.findAssertInstanceList(assertInstanceQuery);
        if(assertInstanceList != null && assertInstanceList.size() > 0){
            testInstance.setAssertInstanceList(assertInstanceList);
        }

        return testInstance;
    }

    @Override
    public List<TestInstance> findAllTestInstance() {
        List<TestInstancePo> testInstancePoList =  testInstanceDao.findAllTestInstance();

        List<TestInstance> testInstanceList =  BeanMapper.mapList(testInstancePoList,TestInstance.class);

        joinQuery.queryList(testInstanceList);
        return testInstanceList;
    }

    @Override
    public List<TestInstance> findTestInstanceList(TestInstanceQuery testInstanceQuery) {
        List<TestInstancePo> testInstancePoList = testInstanceDao.findTestInstanceList(testInstanceQuery);

        List<TestInstance> testInstanceList = BeanMapper.mapList(testInstancePoList,TestInstance.class);

        joinQuery.queryList(testInstanceList);

        return testInstanceList;
    }

    @Override
    public Pagination<TestInstance> findTestInstancePage(TestInstanceQuery testInstanceQuery) {
        Pagination<TestInstance> pg = new Pagination<>();

        Pagination<TestInstancePo>  pagination = testInstanceDao.findTestInstancePage(testInstanceQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<TestInstance> testInstanceList = BeanMapper.mapList(pagination.getDataList(),TestInstance.class);

        joinQuery.queryList(testInstanceList);

        pg.setDataList(testInstanceList);
        return pg;
    }
}