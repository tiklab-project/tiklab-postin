package com.doublekit.apibox.apimock.service;

import com.doublekit.apibox.apimock.dao.ResponseResultMockDao;
import com.doublekit.apibox.apimock.entity.ResponseResultMockPo;
import com.doublekit.apibox.apimock.model.ResponseResultMock;
import com.doublekit.apibox.apimock.model.ResponseResultMockQuery;

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
public class ResponseResultMockServiceImpl implements ResponseResultMockService {

    @Autowired
    ResponseResultMockDao responseResultMockDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createResponseResultMock(@NotNull @Valid ResponseResultMock responseResultMock) {
        ResponseResultMockPo responseResultMockPo = BeanMapper.map(responseResultMock, ResponseResultMockPo.class);

        return responseResultMockDao.createResponseResultMock(responseResultMockPo);
    }

    @Override
    public void updateResponseResultMock(@NotNull @Valid ResponseResultMock responseResultMock) {
        ResponseResultMockPo responseResultMockPo = BeanMapper.map(responseResultMock, ResponseResultMockPo.class);

        responseResultMockDao.updateResponseResultMock(responseResultMockPo);
    }

    @Override
    public void deleteResponseResultMock(@NotNull String id) {
        responseResultMockDao.deleteResponseResultMock(id);
    }

    @Override
    public ResponseResultMock findResponseResultMock(@NotNull String id) {
        ResponseResultMockPo responseResultMockPo = responseResultMockDao.findResponseResultMock(id);

        ResponseResultMock responseResultMock = BeanMapper.map(responseResultMockPo, ResponseResultMock.class);

        joinQuery.queryOne(responseResultMock);

        return responseResultMock;
    }

    @Override
    public List<ResponseResultMock> findAllResponseResultMock() {
        List<ResponseResultMockPo> responseResultMockPoList =  responseResultMockDao.findAllResponseResultMock();

        List<ResponseResultMock> responseResultMockList =  BeanMapper.mapList(responseResultMockPoList,ResponseResultMock.class);

        joinQuery.queryList(responseResultMockList);

        return responseResultMockList;
    }

    @Override
    public List<ResponseResultMock> findResponseResultMockList(ResponseResultMockQuery responseResultMockQuery) {
        List<ResponseResultMockPo> responseResultMockPoList = responseResultMockDao.findResponseResultMockList(responseResultMockQuery);

        List<ResponseResultMock> responseResultMockList = BeanMapper.mapList(responseResultMockPoList,ResponseResultMock.class);

        joinQuery.queryList(responseResultMockList);

        return responseResultMockList;
    }

    @Override
    public Pagination<ResponseResultMock> findResponseResultMockPage(ResponseResultMockQuery responseResultMockQuery) {
        Pagination<ResponseResultMock> pg = new Pagination<>();

        Pagination<ResponseResultMockPo>  pagination = responseResultMockDao.findResponseResultMockPage(responseResultMockQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<ResponseResultMock> responseResultMockList = BeanMapper.mapList(pagination.getDataList(),ResponseResultMock.class);

        joinQuery.queryList(responseResultMockList);

        pg.setDataList(responseResultMockList);
        return pg;
    }
}