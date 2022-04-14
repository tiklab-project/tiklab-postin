package com.doublekit.apibox.apimock.http.service;

import com.doublekit.apibox.apimock.http.dao.MockDao;
import com.doublekit.apibox.apimock.http.entity.MockEntity;
import com.doublekit.apibox.apimock.http.model.Mock;
import com.doublekit.apibox.apimock.http.model.MockQuery;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.page.Pagination;
import com.doublekit.common.page.PaginationBuilder;
import com.doublekit.join.JoinTemplate;
import com.doublekit.utils.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
* 用户服务业务处理
*/
@Service
public class MockServiceImpl implements MockService {

    @Autowired
    MockDao mockDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createMock(@NotNull @Valid Mock mock) {
        MockEntity mockEntity = BeanMapper.map(mock, MockEntity.class);

        //创建人
        String createUserId = LoginContext.getLoginId();
        mockEntity.setCreateUser(createUserId);

        //创建时间
        mockEntity.setCreateTime(new Date());

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

        joinTemplate.joinQuery(mock);
        return mock;
    }

    @Override
    public List<Mock> findAllMock() {
        List<MockEntity> mockEntityList =  mockDao.findAllMock();

        List<Mock> mockList =  BeanMapper.mapList(mockEntityList,Mock.class);

        joinTemplate.joinQuery(mockList);

        return mockList;
    }

    @Override
    public List<Mock> findMockList(MockQuery mockQuery) {
        List<MockEntity> mockEntityList = mockDao.findMockList(mockQuery);

        List<Mock> mockList = BeanMapper.mapList(mockEntityList,Mock.class);

        joinTemplate.joinQuery(mockList);

        return mockList;
    }

    @Override
    public Pagination<Mock> findMockPage(MockQuery mockQuery) {

        Pagination<MockEntity>  pagination = mockDao.findMockPage(mockQuery);

        List<Mock> mockList = BeanMapper.mapList(pagination.getDataList(),Mock.class);

        joinTemplate.joinQuery(mockList);

        return PaginationBuilder.build(pagination,mockList);
    }
}