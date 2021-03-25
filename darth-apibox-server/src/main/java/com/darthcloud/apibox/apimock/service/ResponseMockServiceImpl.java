package com.darthcloud.apibox.apimock.service;

import com.darthcloud.apibox.apimock.dao.ResponseMockDao;
import com.darthcloud.apibox.apimock.entity.ResponseMockPo;
import com.darthcloud.apibox.apimock.model.ResponseMock;
import com.darthcloud.apibox.apimock.model.ResponseMockQuery;

import com.darthcloud.common.Pagination;
import com.darthcloud.beans.BeanMapper;
import com.darthcloud.join.join.JoinQuery;
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
public class ResponseMockServiceImpl implements ResponseMockService {

    @Autowired
    ResponseMockDao responseMockDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createResponseMock(@NotNull @Valid ResponseMock responseMock) {
        ResponseMockPo responseMockPo = BeanMapper.map(responseMock, ResponseMockPo.class);

        return responseMockDao.createResponseMock(responseMockPo);
    }

    @Override
    public void updateResponseMock(@NotNull @Valid ResponseMock responseMock) {
        ResponseMockPo responseMockPo = BeanMapper.map(responseMock, ResponseMockPo.class);

        responseMockDao.updateResponseMock(responseMockPo);
    }

    @Override
    public void deleteResponseMock(@NotNull String id) {
        responseMockDao.deleteResponseMock(id);
    }

    @Override
    public ResponseMock findResponseMock(@NotNull String id) {
        ResponseMockPo responseMockPo = responseMockDao.findResponseMock(id);

        ResponseMock responseMock = BeanMapper.map(responseMockPo, ResponseMock.class);

        joinQuery.queryOne(responseMock);

        return responseMock;
    }

    @Override
    public List<ResponseMock> findAllResponseMock() {
        List<ResponseMockPo> responseMockPoList =  responseMockDao.findAllResponseMock();

        List<ResponseMock> responseMockList =  BeanMapper.mapList(responseMockPoList,ResponseMock.class);

        joinQuery.queryList(responseMockList);

        return responseMockList;
    }

    @Override
    public List<ResponseMock> findResponseMockList(ResponseMockQuery responseMockQuery) {
        List<ResponseMockPo> responseMockPoList = responseMockDao.findResponseMockList(responseMockQuery);

        List<ResponseMock> responseMockList = BeanMapper.mapList(responseMockPoList,ResponseMock.class);

        joinQuery.queryList(responseMockList);

        return responseMockList;
    }

    @Override
    public Pagination<ResponseMock> findResponseMockPage(ResponseMockQuery responseMockQuery) {
        Pagination<ResponseMock> pg = new Pagination<>();

        Pagination<ResponseMockPo>  pagination = responseMockDao.findResponseMockPage(responseMockQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<ResponseMock> responseMockList = BeanMapper.mapList(pagination.getDataList(),ResponseMock.class);

        joinQuery.queryList(responseMockList);

        pg.setDataList(responseMockList);
        return pg;
    }
}