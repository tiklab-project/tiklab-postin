package com.darthcloud.apibox.apidef.requestheader.service;

import com.darthcloud.apibox.apidef.requestheader.dao.RequestHeaderDao;
import com.darthcloud.apibox.apidef.requestheader.entity.RequestHeaderPo;
import com.darthcloud.apibox.apidef.requestheader.model.RequestHeader;
import com.darthcloud.apibox.apidef.requestheader.model.RequestHeaderQuery;

import com.darthcloud.common.Pagination;
import com.darthcloud.beans.BeanMapper;
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
public class RequestHeaderServiceImpl implements RequestHeaderService {

    @Autowired
    RequestHeaderDao requestHeaderDao;

    @Override
    public String createRequestHeader(@NotNull @Valid RequestHeader requestHeader) {
        RequestHeaderPo requestHeaderPo = BeanMapper.map(requestHeader, RequestHeaderPo.class);

        return requestHeaderDao.createRequestHeader(requestHeaderPo);
    }

    @Override
    public void updateRequestHeader(@NotNull @Valid RequestHeader requestHeader) {
        RequestHeaderPo requestHeaderPo = BeanMapper.map(requestHeader, RequestHeaderPo.class);

        requestHeaderDao.updateRequestHeader(requestHeaderPo);
    }

    @Override
    public void deleteRequestHeader(@NotNull String id) {
        requestHeaderDao.deleteRequestHeader(id);
    }

    @Override
    public RequestHeader findRequestHeader(@NotNull String id) {
        RequestHeaderPo requestHeaderPo = requestHeaderDao.findRequestHeader(id);

        return BeanMapper.map(requestHeaderPo, RequestHeader.class);
    }

    @Override
    public List<RequestHeader> findAllRequestHeader() {
        List<RequestHeaderPo> requestHeaderPoList =  requestHeaderDao.findAllRequestHeader();

        return BeanMapper.mapList(requestHeaderPoList,RequestHeader.class);
    }

    @Override
    public List<RequestHeader> findRequestHeaderList(RequestHeaderQuery requestHeaderQuery) {
        List<RequestHeaderPo> requestHeaderPoList = requestHeaderDao.findRequestHeaderList(requestHeaderQuery);

        return BeanMapper.mapList(requestHeaderPoList,RequestHeader.class);
    }

    @Override
    public Pagination<List<RequestHeader>> findRequestHeaderPage(RequestHeaderQuery requestHeaderQuery) {
        Pagination<List<RequestHeader>> pg = new Pagination<>();

        Pagination<List<RequestHeaderPo>>  pagination = requestHeaderDao.findRequestHeaderPage(requestHeaderQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<RequestHeader> requestHeaderList = BeanMapper.mapList(pagination.getDataList(),RequestHeader.class);
        pg.setDataList(requestHeaderList);
        return pg;
    }
}