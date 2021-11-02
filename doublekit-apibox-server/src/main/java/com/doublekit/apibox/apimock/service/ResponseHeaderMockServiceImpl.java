package com.doublekit.apibox.apimock.service;

import com.doublekit.apibox.apimock.dao.ResponseHeaderMockDao;
import com.doublekit.apibox.apimock.entity.ResponseHeaderMockEntity;
import com.doublekit.apibox.apimock.model.ResponseHeaderMock;
import com.doublekit.apibox.apimock.model.ResponseHeaderMockQuery;

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
public class ResponseHeaderMockServiceImpl implements ResponseHeaderMockService {

    @Autowired
    ResponseHeaderMockDao responseHeaderMockDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createResponseHeaderMock(@NotNull @Valid ResponseHeaderMock responseHeaderMock) {
        ResponseHeaderMockEntity responseHeaderMockEntity = BeanMapper.map(responseHeaderMock, ResponseHeaderMockEntity.class);

        return responseHeaderMockDao.createResponseHeaderMock(responseHeaderMockEntity);
    }

    @Override
    public void updateResponseHeaderMock(@NotNull @Valid ResponseHeaderMock responseHeaderMock) {
        ResponseHeaderMockEntity responseHeaderMockEntity = BeanMapper.map(responseHeaderMock, ResponseHeaderMockEntity.class);

        responseHeaderMockDao.updateResponseHeaderMock(responseHeaderMockEntity);
    }

    @Override
    public void deleteResponseHeaderMock(@NotNull String id) {
        responseHeaderMockDao.deleteResponseHeaderMock(id);
    }

    @Override
    public ResponseHeaderMock findResponseHeaderMock(@NotNull String id) {
        ResponseHeaderMockEntity responseHeaderMockEntity = responseHeaderMockDao.findResponseHeaderMock(id);

        ResponseHeaderMock responseHeaderMock = BeanMapper.map(responseHeaderMockEntity, ResponseHeaderMock.class);

        joinQuery.queryOne(responseHeaderMock);

        return responseHeaderMock;
    }

    @Override
    public List<ResponseHeaderMock> findAllResponseHeaderMock() {
        List<ResponseHeaderMockEntity> responseHeaderMockEntityList =  responseHeaderMockDao.findAllResponseHeaderMock();

        List<ResponseHeaderMock> responseHeaderMockList =  BeanMapper.mapList(responseHeaderMockEntityList,ResponseHeaderMock.class);

        joinQuery.queryList(responseHeaderMockList);

        return responseHeaderMockList;
    }

    @Override
    public List<ResponseHeaderMock> findResponseHeaderMockList(ResponseHeaderMockQuery responseHeaderMockQuery) {
        List<ResponseHeaderMockEntity> responseHeaderMockEntityList = responseHeaderMockDao.findResponseHeaderMockList(responseHeaderMockQuery);

        List<ResponseHeaderMock> responseHeaderMockList = BeanMapper.mapList(responseHeaderMockEntityList,ResponseHeaderMock.class);

        joinQuery.queryList(responseHeaderMockList);

        return responseHeaderMockList;
    }

    @Override
    public Pagination<ResponseHeaderMock> findResponseHeaderMockPage(ResponseHeaderMockQuery responseHeaderMockQuery) {

        Pagination<ResponseHeaderMockEntity>  pagination = responseHeaderMockDao.findResponseHeaderMockPage(responseHeaderMockQuery);

        List<ResponseHeaderMock> responseHeaderMockList = BeanMapper.mapList(pagination.getDataList(),ResponseHeaderMock.class);

        joinQuery.queryList(responseHeaderMockList);

        return PaginationBuilder.build(pagination,responseHeaderMockList);
    }
}