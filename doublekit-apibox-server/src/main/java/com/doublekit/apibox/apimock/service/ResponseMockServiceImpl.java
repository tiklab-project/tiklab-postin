package com.doublekit.apibox.apimock.service;

import com.doublekit.apibox.apimock.dao.ResponseMockDao;
import com.doublekit.apibox.apimock.entity.ResponseMockEntity;
import com.doublekit.apibox.apimock.model.ResponseMock;
import com.doublekit.apibox.apimock.model.ResponseMockQuery;

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
public class ResponseMockServiceImpl implements ResponseMockService {

    @Autowired
    ResponseMockDao responseMockDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createResponseMock(@NotNull @Valid ResponseMock responseMock) {
        ResponseMockEntity responseMockEntity = BeanMapper.map(responseMock, ResponseMockEntity.class);

        return responseMockDao.createResponseMock(responseMockEntity);
    }

    @Override
    public void updateResponseMock(@NotNull @Valid ResponseMock responseMock) {
        ResponseMockEntity responseMockEntity = BeanMapper.map(responseMock, ResponseMockEntity.class);

        responseMockDao.updateResponseMock(responseMockEntity);
    }

    @Override
    public void deleteResponseMock(@NotNull String id) {
        responseMockDao.deleteResponseMock(id);
    }

    @Override
    public ResponseMock findResponseMock(@NotNull String id) {
        ResponseMockEntity responseMockEntity = responseMockDao.findResponseMock(id);

        ResponseMock responseMock = BeanMapper.map(responseMockEntity, ResponseMock.class);

        joinTemplate.joinQuery(responseMock);

        return responseMock;
    }

    @Override
    public List<ResponseMock> findAllResponseMock() {
        List<ResponseMockEntity> responseMockEntityList =  responseMockDao.findAllResponseMock();

        List<ResponseMock> responseMockList =  BeanMapper.mapList(responseMockEntityList,ResponseMock.class);

        joinTemplate.joinQuery(responseMockList);

        return responseMockList;
    }

    @Override
    public List<ResponseMock> findResponseMockList(ResponseMockQuery responseMockQuery) {
        List<ResponseMockEntity> responseMockEntityList = responseMockDao.findResponseMockList(responseMockQuery);

        List<ResponseMock> responseMockList = BeanMapper.mapList(responseMockEntityList,ResponseMock.class);

        joinTemplate.joinQuery(responseMockList);

        return responseMockList;
    }

    @Override
    public Pagination<ResponseMock> findResponseMockPage(ResponseMockQuery responseMockQuery) {

        Pagination<ResponseMockEntity>  pagination = responseMockDao.findResponseMockPage(responseMockQuery);

        List<ResponseMock> responseMockList = BeanMapper.mapList(pagination.getDataList(),ResponseMock.class);

        joinTemplate.joinQuery(responseMockList);

        return PaginationBuilder.build(pagination,responseMockList);
    }
}