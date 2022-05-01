package com.doublekit.apibox.apimock.http.service;

import com.doublekit.apibox.apimock.http.dao.RequestBodyMockDao;
import com.doublekit.apibox.apimock.http.entity.RequestBodyMockEntity;
import com.doublekit.apibox.apimock.http.model.RequestBodyMock;
import com.doublekit.apibox.apimock.http.model.RequestBodyMockQuery;

import com.doublekit.core.page.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.core.page.PaginationBuilder;
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
public class RequestBodyMockServiceImpl implements RequestBodyMockService {

    @Autowired
    RequestBodyMockDao requestBodyMockDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRequestBodyMock(@NotNull @Valid RequestBodyMock requestBodyMock) {
        RequestBodyMockEntity requestBodyMockEntity = BeanMapper.map(requestBodyMock, RequestBodyMockEntity.class);

        return requestBodyMockDao.createRequestBodyMock(requestBodyMockEntity);
    }

    @Override
    public void updateRequestBodyMock(@NotNull @Valid RequestBodyMock requestBodyMock) {
        RequestBodyMockEntity requestBodyMockEntity = BeanMapper.map(requestBodyMock, RequestBodyMockEntity.class);

        requestBodyMockDao.updateRequestBodyMock(requestBodyMockEntity);
    }

    @Override
    public void deleteRequestBodyMock(@NotNull String id) {
        requestBodyMockDao.deleteRequestBodyMock(id);
    }

    @Override
    public RequestBodyMock findOne(String id) {
        RequestBodyMockEntity requestBodyMockEntity = requestBodyMockDao.findRequestBodyMock(id);

        RequestBodyMock requestBodyMock = BeanMapper.map(requestBodyMockEntity, RequestBodyMock.class);
        return requestBodyMock;
    }

    @Override
    public List<RequestBodyMock> findList(List<String> idList) {
        List<RequestBodyMockEntity> requestBodyMockEntityList =  requestBodyMockDao.findRequestBodyMockList(idList);

        List<RequestBodyMock> requestBodyMockList =  BeanMapper.mapList(requestBodyMockEntityList,RequestBodyMock.class);
        return requestBodyMockList;
    }

    @Override
    public RequestBodyMock findRequestBodyMock(@NotNull String id) {
        RequestBodyMock requestBodyMock = findOne(id);

        joinTemplate.joinQuery(requestBodyMock);
        return requestBodyMock;
    }

    @Override
    public List<RequestBodyMock> findAllRequestBodyMock() {
        List<RequestBodyMockEntity> requestBodyMockEntityList =  requestBodyMockDao.findAllRequestBodyMock();

        List<RequestBodyMock> requestBodyMockList =  BeanMapper.mapList(requestBodyMockEntityList,RequestBodyMock.class);

        joinTemplate.joinQuery(requestBodyMockList);
        return requestBodyMockList;
    }

    @Override
    public List<RequestBodyMock> findRequestBodyMockList(RequestBodyMockQuery requestBodyMockQuery) {
        List<RequestBodyMockEntity> requestBodyMockEntityList = requestBodyMockDao.findRequestBodyMockList(requestBodyMockQuery);

        List<RequestBodyMock> requestBodyMockList = BeanMapper.mapList(requestBodyMockEntityList,RequestBodyMock.class);

        joinTemplate.joinQuery(requestBodyMockList);

        return requestBodyMockList;
    }

    @Override
    public Pagination<RequestBodyMock> findRequestBodyMockPage(RequestBodyMockQuery requestBodyMockQuery) {

        Pagination<RequestBodyMockEntity>  pagination = requestBodyMockDao.findRequestBodyMockPage(requestBodyMockQuery);

        List<RequestBodyMock> requestBodyMockList = BeanMapper.mapList(pagination.getDataList(),RequestBodyMock.class);

        joinTemplate.joinQuery(requestBodyMockList);

        return PaginationBuilder.build(pagination,requestBodyMockList);
    }
}