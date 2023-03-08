package io.tiklab.postin.api.http.mock.service;

import io.tiklab.postin.api.http.mock.dao.RequestHeaderMockDao;
import io.tiklab.postin.api.http.mock.entity.RequestHeaderMockEntity;
import io.tiklab.postin.api.http.mock.model.RequestHeaderMock;
import io.tiklab.postin.api.http.mock.model.RequestHeaderMockQuery;

import io.tiklab.core.page.Pagination;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * mock
 * 请求头 服务
 */
@Service
public class RequestHeaderMockServiceImpl implements RequestHeaderMockService {

    @Autowired
    RequestHeaderMockDao requestHeaderMockDao;

    @Autowired
    JoinTemplate joinTemplate;

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

        joinTemplate.joinQuery(requestHeaderMock);

        return requestHeaderMock;
    }

    @Override
    public List<RequestHeaderMock> findAllRequestHeaderMock() {
        List<RequestHeaderMockEntity> requestHeaderMockEntityList =  requestHeaderMockDao.findAllRequestHeaderMock();

        List<RequestHeaderMock> requestHeaderMockList =  BeanMapper.mapList(requestHeaderMockEntityList,RequestHeaderMock.class);

        joinTemplate.joinQuery(requestHeaderMockList);

        return requestHeaderMockList;
    }

    @Override
    public List<RequestHeaderMock> findRequestHeaderMockList(RequestHeaderMockQuery requestHeaderMockQuery) {
        List<RequestHeaderMockEntity> requestHeaderMockEntityList = requestHeaderMockDao.findRequestHeaderMockList(requestHeaderMockQuery);

        List<RequestHeaderMock> requestHeaderMockList = BeanMapper.mapList(requestHeaderMockEntityList,RequestHeaderMock.class);

        joinTemplate.joinQuery(requestHeaderMockList);

        return requestHeaderMockList;
    }

    @Override
    public Pagination<RequestHeaderMock> findRequestHeaderMockPage(RequestHeaderMockQuery requestHeaderMockQuery) {

        Pagination<RequestHeaderMockEntity>  pagination = requestHeaderMockDao.findRequestHeaderMockPage(requestHeaderMockQuery);

        List<RequestHeaderMock> requestHeaderMockList = BeanMapper.mapList(pagination.getDataList(),RequestHeaderMock.class);

        joinTemplate.joinQuery(requestHeaderMockList);

        return PaginationBuilder.build(pagination,requestHeaderMockList);
    }
}