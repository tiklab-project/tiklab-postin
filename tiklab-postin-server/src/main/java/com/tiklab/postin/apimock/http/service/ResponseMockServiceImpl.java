package com.tiklab.postin.apimock.http.service;

import com.tiklab.postin.apimock.http.dao.ResponseMockDao;
import com.tiklab.postin.apimock.http.entity.ResponseMockEntity;
import com.tiklab.postin.apimock.http.model.ResponseMock;
import com.tiklab.postin.apimock.http.model.ResponseMockQuery;

import com.tiklab.core.page.Pagination;
import com.tiklab.beans.BeanMapper;
import com.tiklab.core.page.PaginationBuilder;
import com.tiklab.join.JoinTemplate;
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