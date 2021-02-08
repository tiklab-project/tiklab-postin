package com.darthcloud.apibox.api.apimock.service;

import com.darthcloud.apibox.api.apimock.dao.ResponseHeaderMockDao;
import com.darthcloud.apibox.api.apimock.entity.ResponseHeaderMockPo;
import com.darthcloud.apibox.api.apimock.model.ResponseHeaderMock;
import com.darthcloud.apibox.api.apimock.model.ResponseHeaderMockQuery;

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
public class ResponseHeaderMockServiceImpl implements ResponseHeaderMockService {

    @Autowired
    ResponseHeaderMockDao responseHeaderMockDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createResponseHeaderMock(@NotNull @Valid ResponseHeaderMock responseHeaderMock) {
        ResponseHeaderMockPo responseHeaderMockPo = BeanMapper.map(responseHeaderMock, ResponseHeaderMockPo.class);

        return responseHeaderMockDao.createResponseHeaderMock(responseHeaderMockPo);
    }

    @Override
    public void updateResponseHeaderMock(@NotNull @Valid ResponseHeaderMock responseHeaderMock) {
        ResponseHeaderMockPo responseHeaderMockPo = BeanMapper.map(responseHeaderMock, ResponseHeaderMockPo.class);

        responseHeaderMockDao.updateResponseHeaderMock(responseHeaderMockPo);
    }

    @Override
    public void deleteResponseHeaderMock(@NotNull String id) {
        responseHeaderMockDao.deleteResponseHeaderMock(id);
    }

    @Override
    public ResponseHeaderMock findResponseHeaderMock(@NotNull String id) {
        ResponseHeaderMockPo responseHeaderMockPo = responseHeaderMockDao.findResponseHeaderMock(id);

        ResponseHeaderMock responseHeaderMock = BeanMapper.map(responseHeaderMockPo, ResponseHeaderMock.class);

        joinQuery.queryOne(responseHeaderMock);

        return responseHeaderMock;
    }

    @Override
    public List<ResponseHeaderMock> findAllResponseHeaderMock() {
        List<ResponseHeaderMockPo> responseHeaderMockPoList =  responseHeaderMockDao.findAllResponseHeaderMock();

        List<ResponseHeaderMock> responseHeaderMockList =  BeanMapper.mapList(responseHeaderMockPoList,ResponseHeaderMock.class);

        joinQuery.queryList(responseHeaderMockList);

        return responseHeaderMockList;
    }

    @Override
    public List<ResponseHeaderMock> findResponseHeaderMockList(ResponseHeaderMockQuery responseHeaderMockQuery) {
        List<ResponseHeaderMockPo> responseHeaderMockPoList = responseHeaderMockDao.findResponseHeaderMockList(responseHeaderMockQuery);

        List<ResponseHeaderMock> responseHeaderMockList = BeanMapper.mapList(responseHeaderMockPoList,ResponseHeaderMock.class);

        joinQuery.queryList(responseHeaderMockList);

        return responseHeaderMockList;
    }

    @Override
    public Pagination<List<ResponseHeaderMock>> findResponseHeaderMockPage(ResponseHeaderMockQuery responseHeaderMockQuery) {
        Pagination<List<ResponseHeaderMock>> pg = new Pagination<>();

        Pagination<List<ResponseHeaderMockPo>>  pagination = responseHeaderMockDao.findResponseHeaderMockPage(responseHeaderMockQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<ResponseHeaderMock> responseHeaderMockList = BeanMapper.mapList(pagination.getDataList(),ResponseHeaderMock.class);

        joinQuery.queryList(responseHeaderMockList);

        pg.setDataList(responseHeaderMockList);
        return pg;
    }
}