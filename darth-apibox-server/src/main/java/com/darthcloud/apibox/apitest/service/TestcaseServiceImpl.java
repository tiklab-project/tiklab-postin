package com.darthcloud.apibox.apitest.service;

import com.darthcloud.apibox.apitest.dao.TestcaseDao;
import com.darthcloud.apibox.apitest.entity.TestcasePo;
import com.darthcloud.apibox.apitest.model.Testcase;
import com.darthcloud.apibox.apitest.model.TestcaseQuery;

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
public class TestcaseServiceImpl implements TestcaseService {

    @Autowired
    TestcaseDao testcaseDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createTestcase(@NotNull @Valid Testcase testcase) {
        TestcasePo testcasePo = BeanMapper.map(testcase, TestcasePo.class);

        return testcaseDao.createTestcase(testcasePo);
    }

    @Override
    public void updateTestcase(@NotNull @Valid Testcase testcase) {
        TestcasePo testcasePo = BeanMapper.map(testcase, TestcasePo.class);

        testcaseDao.updateTestcase(testcasePo);
    }

    @Override
    public void deleteTestcase(@NotNull String id) {
        testcaseDao.deleteTestcase(id);
    }

    @Override
    public Testcase findOne(String id) {
        TestcasePo testcasePo = testcaseDao.findTestcase(id);

        Testcase testcase = BeanMapper.map(testcasePo, Testcase.class);
        return testcase;
    }

    @Override
    public List<Testcase> findList(List<String> idList) {
        List<TestcasePo> testcasePoList =  testcaseDao.findTestcaseList(idList);

        List<Testcase> testcaseList =  BeanMapper.mapList(testcasePoList,Testcase.class);
        return testcaseList;
    }

    @Override
    public Testcase findTestcase(@NotNull String id) {
        Testcase testcase = findOne(id);

        joinQuery.queryOne(testcase);
        return testcase;
    }

    @Override
    public List<Testcase> findAllTestcase() {
        List<TestcasePo> testcasePoList =  testcaseDao.findAllTestcase();

        List<Testcase> testcaseList =  BeanMapper.mapList(testcasePoList,Testcase.class);

        joinQuery.queryList(testcaseList);
        return testcaseList;
    }

    @Override
    public List<Testcase> findTestcaseList(TestcaseQuery testcaseQuery) {
        List<TestcasePo> testcasePoList = testcaseDao.findTestcaseList(testcaseQuery);

        List<Testcase> testcaseList = BeanMapper.mapList(testcasePoList,Testcase.class);

        joinQuery.queryList(testcaseList);

        return testcaseList;
    }

    @Override
    public Pagination<List<Testcase>> findTestcasePage(TestcaseQuery testcaseQuery) {
        Pagination<List<Testcase>> pg = new Pagination<>();

        Pagination<List<TestcasePo>>  pagination = testcaseDao.findTestcasePage(testcaseQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<Testcase> testcaseList = BeanMapper.mapList(pagination.getDataList(),Testcase.class);

        joinQuery.queryList(testcaseList);

        pg.setDataList(testcaseList);
        return pg;
    }
}