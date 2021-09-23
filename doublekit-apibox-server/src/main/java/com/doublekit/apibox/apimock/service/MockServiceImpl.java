package com.doublekit.apibox.apimock.service;

import com.doublekit.apibox.apimock.dao.MockDao;
import com.doublekit.apibox.apimock.entity.MockPo;
import com.doublekit.apibox.apimock.model.Mock;
import com.doublekit.apibox.apimock.model.MockQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.join.JoinTemplate;
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
public class MockServiceImpl implements MockService {

    @Autowired
    MockDao mockDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createMock(@NotNull @Valid Mock mock) {
        MockPo mockPo = BeanMapper.map(mock, MockPo.class);

        return mockDao.createMock(mockPo);
    }

    @Override
    public void updateMock(@NotNull @Valid Mock mock) {
        MockPo mockPo = BeanMapper.map(mock, MockPo.class);

        mockDao.updateMock(mockPo);
    }

    @Override
    public void deleteMock(@NotNull String id) {
        mockDao.deleteMock(id);
    }

    @Override
    public Mock findOne(String id) {
        MockPo mockPo = mockDao.findMock(id);

        Mock mock = BeanMapper.map(mockPo, Mock.class);
        return mock;
    }

    @Override
    public List<Mock> findList(List<String> idList) {
        List<MockPo> mockPoList =  mockDao.findMockList(idList);

        List<Mock> mockList =  BeanMapper.mapList(mockPoList,Mock.class);
        return mockList;
    }

    @Override
    public Mock findMock(@NotNull String id) {
        Mock mock = findOne(id);

        joinQuery.queryOne(mock);
        return mock;
    }

    @Override
    public List<Mock> findAllMock() {
        List<MockPo> mockPoList =  mockDao.findAllMock();

        List<Mock> mockList =  BeanMapper.mapList(mockPoList,Mock.class);

        joinQuery.queryList(mockList);

        return mockList;
    }

    @Override
    public List<Mock> findMockList(MockQuery mockQuery) {
        List<MockPo> mockPoList = mockDao.findMockList(mockQuery);

        List<Mock> mockList = BeanMapper.mapList(mockPoList,Mock.class);

        joinQuery.queryList(mockList);

        return mockList;
    }

    @Override
    public Pagination<Mock> findMockPage(MockQuery mockQuery) {
        Pagination<Mock> pg = new Pagination<>();

        Pagination<MockPo>  pagination = mockDao.findMockPage(mockQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<Mock> mockList = BeanMapper.mapList(pagination.getDataList(),Mock.class);

        joinQuery.queryList(mockList);

        pg.setDataList(mockList);
        return pg;
    }
}