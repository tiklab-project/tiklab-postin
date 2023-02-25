package net.tiklab.postin.api.http.mock.service;

import net.tiklab.postin.api.http.mock.dao.RequestHeaderMockDao;
import net.tiklab.postin.api.http.mock.entity.RequestHeaderMockEntity;
import net.tiklab.postin.api.http.mock.model.RequestHeaderMock;
import net.tiklab.postin.api.http.mock.model.RequestHeaderMockQuery;

import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
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