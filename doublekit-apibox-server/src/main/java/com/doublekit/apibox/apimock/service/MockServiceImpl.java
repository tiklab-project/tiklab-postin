package com.doublekit.apibox.apimock.service;

import com.doublekit.apibox.apimock.dao.MockDao;
import com.doublekit.apibox.apimock.entity.MockEntity;
import com.doublekit.apibox.apimock.model.Mock;
import com.doublekit.apibox.apimock.model.MockQuery;

import com.doublekit.common.page.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.page.PaginationBuilder;
import com.doublekit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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
        MockEntity mockEntity = BeanMapper.map(mock, MockEntity.class);

        return mockDao.createMock(mockEntity);
    }

    @Override
    public void updateMock(@NotNull @Valid Mock mock) {
        MockEntity mockEntity = BeanMapper.map(mock, MockEntity.class);

        mockDao.updateMock(mockEntity);
    }

    @Override
    public void deleteMock(@NotNull String id) {
        mockDao.deleteMock(id);
    }

    @Override
    public Mock findOne(String id) {
        MockEntity mockEntity = mockDao.findMock(id);

        Mock mock = BeanMapper.map(mockEntity, Mock.class);
        return mock;
    }

    @Override
    public List<Mock> findList(List<String> idList) {
        List<MockEntity> mockEntityList =  mockDao.findMockList(idList);

        List<Mock> mockList =  BeanMapper.mapList(mockEntityList,Mock.class);
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
        List<MockEntity> mockEntityList =  mockDao.findAllMock();

        List<Mock> mockList =  BeanMapper.mapList(mockEntityList,Mock.class);

        joinQuery.queryList(mockList);

        return mockList;
    }

    @Override
    public List<Mock> findMockList(MockQuery mockQuery) {
        List<MockEntity> mockEntityList = mockDao.findMockList(mockQuery);

        List<Mock> mockList = BeanMapper.mapList(mockEntityList,Mock.class);

        joinQuery.queryList(mockList);

        return mockList;
    }

    @Override
    public Pagination<Mock> findMockPage(MockQuery mockQuery) {

        Pagination<MockEntity>  pagination = mockDao.findMockPage(mockQuery);

        List<Mock> mockList = BeanMapper.mapList(pagination.getDataList(),Mock.class);

        joinQuery.queryList(mockList);

        return PaginationBuilder.build(pagination,mockList);
    }
}