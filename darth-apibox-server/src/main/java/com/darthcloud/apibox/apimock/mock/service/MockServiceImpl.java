package com.darthcloud.apibox.apimock.mock.service;

import com.darthcloud.apibox.apimock.mock.dao.MockDao;
import com.darthcloud.apibox.apimock.mock.entity.MockPo;
import com.darthcloud.apibox.apimock.mock.model.Mock;
import com.darthcloud.apibox.apimock.mock.model.MockQuery;

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
public class MockServiceImpl implements MockService {

    @Autowired
    MockDao mockDao;

    @Autowired
    JoinQuery joinQuery;

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
    public Mock findMock(@NotNull String id) {
        MockPo mockPo = mockDao.findMock(id);

        Mock mock = BeanMapper.map(mockPo, Mock.class);

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
    public Pagination<List<Mock>> findMockPage(MockQuery mockQuery) {
        Pagination<List<Mock>> pg = new Pagination<>();

        Pagination<List<MockPo>>  pagination = mockDao.findMockPage(mockQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<Mock> mockList = BeanMapper.mapList(pagination.getDataList(),Mock.class);

        joinQuery.queryList(mockList);

        pg.setDataList(mockList);
        return pg;
    }
}