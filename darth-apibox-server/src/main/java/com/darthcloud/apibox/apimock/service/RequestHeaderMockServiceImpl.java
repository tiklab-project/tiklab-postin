package com.darthcloud.apibox.apimock.service;

import com.darthcloud.apibox.apimock.dao.RequestHeaderMockDao;
import com.darthcloud.apibox.apimock.entity.RequestHeaderMockPo;
import com.darthcloud.apibox.apimock.model.RequestHeaderMock;
import com.darthcloud.apibox.apimock.model.RequestHeaderMockQuery;

import com.darthcloud.common.Pagination;
import com.darthcloud.dsl.beans.BeanMapper;
import com.darthcloud.dsl.join.join.JoinQuery;
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
public class RequestHeaderMockServiceImpl implements RequestHeaderMockService {

    @Autowired
    RequestHeaderMockDao requestHeaderMockDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createRequestHeaderMock(@NotNull @Valid RequestHeaderMock requestHeaderMock) {
        RequestHeaderMockPo requestHeaderMockPo = BeanMapper.map(requestHeaderMock, RequestHeaderMockPo.class);

        return requestHeaderMockDao.createRequestHeaderMock(requestHeaderMockPo);
    }

    @Override
    public void updateRequestHeaderMock(@NotNull @Valid RequestHeaderMock requestHeaderMock) {
        RequestHeaderMockPo requestHeaderMockPo = BeanMapper.map(requestHeaderMock, RequestHeaderMockPo.class);

        requestHeaderMockDao.updateRequestHeaderMock(requestHeaderMockPo);
    }

    @Override
    public void deleteRequestHeaderMock(@NotNull String id) {
        requestHeaderMockDao.deleteRequestHeaderMock(id);
    }

    @Override
    public RequestHeaderMock findRequestHeaderMock(@NotNull String id) {
        RequestHeaderMockPo requestHeaderMockPo = requestHeaderMockDao.findRequestHeaderMock(id);

        RequestHeaderMock requestHeaderMock = BeanMapper.map(requestHeaderMockPo, RequestHeaderMock.class);

        joinQuery.queryOne(requestHeaderMock);

        return requestHeaderMock;
    }

    @Override
    public List<RequestHeaderMock> findAllRequestHeaderMock() {
        List<RequestHeaderMockPo> requestHeaderMockPoList =  requestHeaderMockDao.findAllRequestHeaderMock();

        List<RequestHeaderMock> requestHeaderMockList =  BeanMapper.mapList(requestHeaderMockPoList,RequestHeaderMock.class);

        joinQuery.queryList(requestHeaderMockList);

        return requestHeaderMockList;
    }

    @Override
    public List<RequestHeaderMock> findRequestHeaderMockList(RequestHeaderMockQuery requestHeaderMockQuery) {
        List<RequestHeaderMockPo> requestHeaderMockPoList = requestHeaderMockDao.findRequestHeaderMockList(requestHeaderMockQuery);

        List<RequestHeaderMock> requestHeaderMockList = BeanMapper.mapList(requestHeaderMockPoList,RequestHeaderMock.class);

        joinQuery.queryList(requestHeaderMockList);

        return requestHeaderMockList;
    }

    @Override
    public Pagination<RequestHeaderMock> findRequestHeaderMockPage(RequestHeaderMockQuery requestHeaderMockQuery) {
        Pagination<RequestHeaderMock> pg = new Pagination<>();

        Pagination<RequestHeaderMockPo>  pagination = requestHeaderMockDao.findRequestHeaderMockPage(requestHeaderMockQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<RequestHeaderMock> requestHeaderMockList = BeanMapper.mapList(pagination.getDataList(),RequestHeaderMock.class);

        joinQuery.queryList(requestHeaderMockList);

        pg.setDataList(requestHeaderMockList);
        return pg;
    }
}