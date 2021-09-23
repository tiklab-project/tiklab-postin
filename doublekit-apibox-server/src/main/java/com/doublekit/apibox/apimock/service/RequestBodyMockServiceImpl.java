package com.doublekit.apibox.apimock.service;

import com.doublekit.apibox.apimock.dao.RequestBodyMockDao;
import com.doublekit.apibox.apimock.entity.RequestBodyMockPo;
import com.doublekit.apibox.apimock.model.RequestBodyMock;
import com.doublekit.apibox.apimock.model.RequestBodyMockQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
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
public class RequestBodyMockServiceImpl implements RequestBodyMockService {

    @Autowired
    RequestBodyMockDao requestBodyMockDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createRequestBodyMock(@NotNull @Valid RequestBodyMock requestBodyMock) {
        RequestBodyMockPo requestBodyMockPo = BeanMapper.map(requestBodyMock, RequestBodyMockPo.class);

        return requestBodyMockDao.createRequestBodyMock(requestBodyMockPo);
    }

    @Override
    public void updateRequestBodyMock(@NotNull @Valid RequestBodyMock requestBodyMock) {
        RequestBodyMockPo requestBodyMockPo = BeanMapper.map(requestBodyMock, RequestBodyMockPo.class);

        requestBodyMockDao.updateRequestBodyMock(requestBodyMockPo);
    }

    @Override
    public void deleteRequestBodyMock(@NotNull String id) {
        requestBodyMockDao.deleteRequestBodyMock(id);
    }

    @Override
    public RequestBodyMock findOne(String id) {
        RequestBodyMockPo requestBodyMockPo = requestBodyMockDao.findRequestBodyMock(id);

        RequestBodyMock requestBodyMock = BeanMapper.map(requestBodyMockPo, RequestBodyMock.class);
        return requestBodyMock;
    }

    @Override
    public List<RequestBodyMock> findList(List<String> idList) {
        List<RequestBodyMockPo> requestBodyMockPoList =  requestBodyMockDao.findRequestBodyMockList(idList);

        List<RequestBodyMock> requestBodyMockList =  BeanMapper.mapList(requestBodyMockPoList,RequestBodyMock.class);
        return requestBodyMockList;
    }

    @Override
    public RequestBodyMock findRequestBodyMock(@NotNull String id) {
        RequestBodyMock requestBodyMock = findOne(id);

        joinQuery.queryOne(requestBodyMock);
        return requestBodyMock;
    }

    @Override
    public List<RequestBodyMock> findAllRequestBodyMock() {
        List<RequestBodyMockPo> requestBodyMockPoList =  requestBodyMockDao.findAllRequestBodyMock();

        List<RequestBodyMock> requestBodyMockList =  BeanMapper.mapList(requestBodyMockPoList,RequestBodyMock.class);

        joinQuery.queryList(requestBodyMockList);
        return requestBodyMockList;
    }

    @Override
    public List<RequestBodyMock> findRequestBodyMockList(RequestBodyMockQuery requestBodyMockQuery) {
        List<RequestBodyMockPo> requestBodyMockPoList = requestBodyMockDao.findRequestBodyMockList(requestBodyMockQuery);

        List<RequestBodyMock> requestBodyMockList = BeanMapper.mapList(requestBodyMockPoList,RequestBodyMock.class);

        joinQuery.queryList(requestBodyMockList);

        return requestBodyMockList;
    }

    @Override
    public Pagination<RequestBodyMock> findRequestBodyMockPage(RequestBodyMockQuery requestBodyMockQuery) {
        Pagination<RequestBodyMock> pg = new Pagination<>();

        Pagination<RequestBodyMockPo>  pagination = requestBodyMockDao.findRequestBodyMockPage(requestBodyMockQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<RequestBodyMock> requestBodyMockList = BeanMapper.mapList(pagination.getDataList(),RequestBodyMock.class);

        joinQuery.queryList(requestBodyMockList);

        pg.setDataList(requestBodyMockList);
        return pg;
    }
}