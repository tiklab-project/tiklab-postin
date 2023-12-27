package io.thoughtware.postin.api.http.mock.service;

import io.thoughtware.postin.api.http.mock.entity.RequestMockEntity;
import io.thoughtware.postin.api.http.mock.model.RequestMock;
import io.thoughtware.postin.api.http.mock.model.RequestMockQuery;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * mock
 * 请求体 服务
 */
@Service
public class RequestMockServiceImpl implements RequestMockService {

    @Autowired
    io.thoughtware.postin.api.http.mock.dao.RequestMockDao RequestMockDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRequestMock(@NotNull @Valid RequestMock requestMock) {
        RequestMockEntity requestMockEntity = BeanMapper.map(requestMock, RequestMockEntity.class);

        return RequestMockDao.createRequestMock(requestMockEntity);
    }

    @Override
    public void updateRequestMock(@NotNull @Valid RequestMock requestMock) {
        RequestMockEntity requestMockEntity = BeanMapper.map(requestMock, RequestMockEntity.class);

        RequestMockDao.updateRequestMock(requestMockEntity);
    }

    @Override
    public void deleteRequestMock(@NotNull String id) {
        RequestMockDao.deleteRequestMock(id);
    }

    @Override
    public RequestMock findOne(String id) {
        RequestMockEntity requestMockEntity = RequestMockDao.findRequestMock(id);

        RequestMock requestMock = BeanMapper.map(requestMockEntity, RequestMock.class);
        return requestMock;
    }

    @Override
    public List<RequestMock> findList(List<String> idList) {
        List<RequestMockEntity> requestMockEntityList =  RequestMockDao.findRequestMockList(idList);

        List<RequestMock> requestMockList =  BeanMapper.mapList(requestMockEntityList, RequestMock.class);
        return requestMockList;
    }

    @Override
    public RequestMock findRequestMock(@NotNull String id) {
        RequestMock requestMock = findOne(id);

        joinTemplate.joinQuery(requestMock);
        return requestMock;
    }

    @Override
    public List<RequestMock> findAllRequestMock() {
        List<RequestMockEntity> requestMockEntityList =  RequestMockDao.findAllRequestMock();

        List<RequestMock> requestMockList =  BeanMapper.mapList(requestMockEntityList, RequestMock.class);

        joinTemplate.joinQuery(requestMockList);
        return requestMockList;
    }

    @Override
    public List<RequestMock> findRequestMockList(RequestMockQuery requestMockQuery) {
        List<RequestMockEntity> requestMockEntityList = RequestMockDao.findRequestMockList(requestMockQuery);

        List<RequestMock> requestMockList = BeanMapper.mapList(requestMockEntityList, RequestMock.class);

        joinTemplate.joinQuery(requestMockList);

        return requestMockList;
    }

    @Override
    public Pagination<RequestMock> findRequestMockPage(RequestMockQuery requestMockQuery) {

        Pagination<RequestMockEntity>  pagination = RequestMockDao.findRequestMockPage(requestMockQuery);

        List<RequestMock> requestMockList = BeanMapper.mapList(pagination.getDataList(), RequestMock.class);

        joinTemplate.joinQuery(requestMockList);

        return PaginationBuilder.build(pagination, requestMockList);
    }
}