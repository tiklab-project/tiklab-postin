package com.darthcloud.apibox.apitest.service;

import com.darthcloud.apibox.apitest.dao.TestInstanceDao;
import com.darthcloud.apibox.apitest.entity.TestInstancePo;
import com.darthcloud.apibox.apitest.model.TestInstance;
import com.darthcloud.apibox.apitest.model.TestInstanceQuery;

import com.darthcloud.common.Pagination;
import com.darthcloud.beans.BeanMapper;
import com.darthcloud.join.join.JoinQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @Override
    public String createTestInstance(@NotNull @Valid TestInstance testInstance) {
        TestInstancePo testInstancePo = BeanMapper.map(testInstance, TestInstancePo.class);

        return testInstanceDao.createTestInstance(testInstancePo);
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
    public Pagination<List<TestInstance>> findTestInstancePage(TestInstanceQuery testInstanceQuery) {
        Pagination<List<TestInstance>> pg = new Pagination<>();

        Pagination<List<TestInstancePo>>  pagination = testInstanceDao.findTestInstancePage(testInstanceQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<TestInstance> testInstanceList = BeanMapper.mapList(pagination.getDataList(),TestInstance.class);

        joinQuery.queryList(testInstanceList);

        pg.setDataList(testInstanceList);
        return pg;
    }
}