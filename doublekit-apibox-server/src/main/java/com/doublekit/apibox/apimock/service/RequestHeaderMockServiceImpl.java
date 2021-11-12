package com.doublekit.apibox.apimock.service;

import com.doublekit.apibox.apimock.dao.RequestHeaderMockDao;
import com.doublekit.apibox.apimock.entity.RequestHeaderMockEntity;
import com.doublekit.apibox.apimock.model.RequestHeaderMock;
import com.doublekit.apibox.apimock.model.RequestHeaderMockQuery;

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
public class RequestHeaderMockServiceImpl implements RequestHeaderMockService {

    @Autowired
    RequestHeaderMockDao requestHeaderMockDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createRequestHeaderMock(@NotNull @Valid RequestHeaderMock requestHeaderMock) {
        RequestHeaderMockEntity requestHeaderMockEntity = BeanMapper.map(requestHeaderMock, RequestHeaderMockEntity.class);

        return requestHeaderMockDao.createRequestHeaderMock(requestHeaderMockEntity);
    }

    @Override
    public void updateRequestHeaderMock(@NotNull @Valid RequestHeaderMock requestHeaderMock) {
        RequestHeaderMockEntity requestHeaderMockEntity = BeanMapper.map(requestHeaderMock, RequestHeaderMockEntity.class);

        requestHeaderMockDao.updateRequestHeaderMock(requestHeaderMockEntity);
    }

    @Override
    public void deleteRequestHeaderMock(@NotNull String id) {
        requestHeaderMockDao.deleteRequestHeaderMock(id);
    }

    @Override
    public RequestHeaderMock findRequestHeaderMock(@NotNull String id) {
        RequestHeaderMockEntity requestHeaderMockEntity = requestHeaderMockDao.findRequestHeaderMock(id);

        RequestHeaderMock requestHeaderMock = BeanMapper.map(requestHeaderMockEntity, RequestHeaderMock.class);

        joinQuery.queryOne(requestHeaderMock);

        return requestHeaderMock;
    }

    @Override
    public List<RequestHeaderMock> findAllRequestHeaderMock() {
        List<RequestHeaderMockEntity> requestHeaderMockEntityList =  requestHeaderMockDao.findAllRequestHeaderMock();

        List<RequestHeaderMock> requestHeaderMockList =  BeanMapper.mapList(requestHeaderMockEntityList,RequestHeaderMock.class);

        joinQuery.queryList(requestHeaderMockList);

        return requestHeaderMockList;
    }

    @Override
    public List<RequestHeaderMock> findRequestHeaderMockList(RequestHeaderMockQuery requestHeaderMockQuery) {
        List<RequestHeaderMockEntity> requestHeaderMockEntityList = requestHeaderMockDao.findRequestHeaderMockList(requestHeaderMockQuery);

        List<RequestHeaderMock> requestHeaderMockList = BeanMapper.mapList(requestHeaderMockEntityList,RequestHeaderMock.class);

        joinQuery.queryList(requestHeaderMockList);

        return requestHeaderMockList;
    }

    @Override
    public Pagination<RequestHeaderMock> findRequestHeaderMockPage(RequestHeaderMockQuery requestHeaderMockQuery) {

        Pagination<RequestHeaderMockEntity>  pagination = requestHeaderMockDao.findRequestHeaderMockPage(requestHeaderMockQuery);

        List<RequestHeaderMock> requestHeaderMockList = BeanMapper.mapList(pagination.getDataList(),RequestHeaderMock.class);

        joinQuery.queryList(requestHeaderMockList);

        return PaginationBuilder.build(pagination,requestHeaderMockList);
    }
}