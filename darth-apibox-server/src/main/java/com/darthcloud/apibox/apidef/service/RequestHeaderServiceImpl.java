package com.darthcloud.apibox.apidef.service;

import com.darthcloud.apibox.apidef.dao.RequestHeaderDao;
import com.darthcloud.apibox.apidef.entity.RequestHeaderPo;
import com.darthcloud.apibox.apidef.model.RequestHeader;
import com.darthcloud.apibox.apidef.model.RequestHeaderQuery;

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
public class RequestHeaderServiceImpl implements RequestHeaderService {

    @Autowired
    RequestHeaderDao requestHeaderDao;

    @Autowired
    JoinQuery joinQuery;

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

        RequestHeader requestHeader = BeanMapper.map(requestHeaderPo, RequestHeader.class);

        joinQuery.queryOne(requestHeader);

        return requestHeader;
    }

    @Override
    public List<RequestHeader> findAllRequestHeader() {
        List<RequestHeaderPo> requestHeaderPoList =  requestHeaderDao.findAllRequestHeader();

        List<RequestHeader> requestHeaderList = BeanMapper.mapList(requestHeaderPoList,RequestHeader.class);

        joinQuery.queryList(requestHeaderList);

        return requestHeaderList;
    }

    @Override
    public List<RequestHeader> findRequestHeaderList(RequestHeaderQuery requestHeaderQuery) {
        List<RequestHeaderPo> requestHeaderPoList = requestHeaderDao.findRequestHeaderList(requestHeaderQuery);

        List<RequestHeader> requestHeaderList = BeanMapper.mapList(requestHeaderPoList,RequestHeader.class);

        joinQuery.queryList(requestHeaderList);

        return requestHeaderList;
    }

    @Override
    public Pagination<RequestHeader> findRequestHeaderPage(RequestHeaderQuery requestHeaderQuery) {
        Pagination<RequestHeader> pg = new Pagination<>();

        Pagination<RequestHeaderPo>  pagination = requestHeaderDao.findRequestHeaderPage(requestHeaderQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<RequestHeader> requestHeaderList = BeanMapper.mapList(pagination.getDataList(),RequestHeader.class);

        joinQuery.queryList(requestHeaderList);

        pg.setDataList(requestHeaderList);
        return pg;
    }
}