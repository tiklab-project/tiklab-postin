package com.doublekit.apibox.apimock.service;

import com.doublekit.apibox.apimock.dao.ResponseResultMockDao;
import com.doublekit.apibox.apimock.entity.ResponseResultMockEntity;
import com.doublekit.apibox.apimock.model.ResponseResultMock;
import com.doublekit.apibox.apimock.model.ResponseResultMockQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.PaginationBuilder;
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
public class ResponseResultMockServiceImpl implements ResponseResultMockService {

    @Autowired
    ResponseResultMockDao responseResultMockDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createResponseResultMock(@NotNull @Valid ResponseResultMock responseResultMock) {
        ResponseResultMockEntity responseResultMockEntity = BeanMapper.map(responseResultMock, ResponseResultMockEntity.class);

        return responseResultMockDao.createResponseResultMock(responseResultMockEntity);
    }

    @Override
    public void updateResponseResultMock(@NotNull @Valid ResponseResultMock responseResultMock) {
        ResponseResultMockEntity responseResultMockEntity = BeanMapper.map(responseResultMock, ResponseResultMockEntity.class);

        responseResultMockDao.updateResponseResultMock(responseResultMockEntity);
    }

    @Override
    public void deleteResponseResultMock(@NotNull String id) {
        responseResultMockDao.deleteResponseResultMock(id);
    }

    @Override
    public ResponseResultMock findResponseResultMock(@NotNull String id) {
        ResponseResultMockEntity responseResultMockEntity = responseResultMockDao.findResponseResultMock(id);

        ResponseResultMock responseResultMock = BeanMapper.map(responseResultMockEntity, ResponseResultMock.class);

        joinQuery.queryOne(responseResultMock);

        return responseResultMock;
    }

    @Override
    public List<ResponseResultMock> findAllResponseResultMock() {
        List<ResponseResultMockEntity> responseResultMockEntityList =  responseResultMockDao.findAllResponseResultMock();

        List<ResponseResultMock> responseResultMockList =  BeanMapper.mapList(responseResultMockEntityList,ResponseResultMock.class);

        joinQuery.queryList(responseResultMockList);

        return responseResultMockList;
    }

    @Override
    public List<ResponseResultMock> findResponseResultMockList(ResponseResultMockQuery responseResultMockQuery) {
        List<ResponseResultMockEntity> responseResultMockEntityList = responseResultMockDao.findResponseResultMockList(responseResultMockQuery);

        List<ResponseResultMock> responseResultMockList = BeanMapper.mapList(responseResultMockEntityList,ResponseResultMock.class);

        joinQuery.queryList(responseResultMockList);

        return responseResultMockList;
    }

    @Override
    public Pagination<ResponseResultMock> findResponseResultMockPage(ResponseResultMockQuery responseResultMockQuery) {

        Pagination<ResponseResultMockEntity>  pagination = responseResultMockDao.findResponseResultMockPage(responseResultMockQuery);

        List<ResponseResultMock> responseResultMockList = BeanMapper.mapList(pagination.getDataList(),ResponseResultMock.class);

        joinQuery.queryList(responseResultMockList);

        return PaginationBuilder.build(pagination,responseResultMockList);
    }
}