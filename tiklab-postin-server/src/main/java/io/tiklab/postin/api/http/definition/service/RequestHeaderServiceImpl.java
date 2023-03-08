package io.tiklab.postin.api.http.definition.service;

import io.tiklab.postin.api.http.definition.dao.RequestHeaderDao;
import io.tiklab.postin.api.http.definition.entity.RequestHeaderEntity;
import io.tiklab.postin.api.http.definition.model.RequestHeader;
import io.tiklab.postin.api.http.definition.model.RequestHeaderQuery;

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
 * 定义
 * http协议
 * 请求头服务
 */
@Service
public class RequestHeaderServiceImpl implements RequestHeaderService {

    @Autowired
    RequestHeaderDao requestHeaderDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRequestHeader(@NotNull @Valid RequestHeader requestHeader) {
        RequestHeaderEntity requestHeaderEntity = BeanMapper.map(requestHeader, RequestHeaderEntity.class);

        return requestHeaderDao.createRequestHeader(requestHeaderEntity);
    }

    @Override
    public void updateRequestHeader(@NotNull @Valid RequestHeader requestHeader) {
        RequestHeaderEntity requestHeaderEntity = BeanMapper.map(requestHeader, RequestHeaderEntity.class);

        requestHeaderDao.updateRequestHeader(requestHeaderEntity);
    }

    @Override
    public void deleteRequestHeader(@NotNull String id) {
        requestHeaderDao.deleteRequestHeader(id);
    }

    @Override
    public RequestHeader findRequestHeader(@NotNull String id) {
        RequestHeaderEntity requestHeaderEntity = requestHeaderDao.findRequestHeader(id);

        RequestHeader requestHeader = BeanMapper.map(requestHeaderEntity, RequestHeader.class);

        joinTemplate.joinQuery(requestHeader);

        return requestHeader;
    }

    @Override
    public List<RequestHeader> findAllRequestHeader() {
        List<RequestHeaderEntity> requestHeaderEntityList =  requestHeaderDao.findAllRequestHeader();

        List<RequestHeader> requestHeaderList = BeanMapper.mapList(requestHeaderEntityList,RequestHeader.class);

        joinTemplate.joinQuery(requestHeaderList);

        return requestHeaderList;
    }

    @Override
    public List<RequestHeader> findRequestHeaderList(RequestHeaderQuery requestHeaderQuery) {
        List<RequestHeaderEntity> requestHeaderEntityList = requestHeaderDao.findRequestHeaderList(requestHeaderQuery);

        List<RequestHeader> requestHeaderList = BeanMapper.mapList(requestHeaderEntityList,RequestHeader.class);

        joinTemplate.joinQuery(requestHeaderList);

        return requestHeaderList;
    }

    @Override
    public Pagination<RequestHeader> findRequestHeaderPage(RequestHeaderQuery requestHeaderQuery) {

        Pagination<RequestHeaderEntity>  pagination = requestHeaderDao.findRequestHeaderPage(requestHeaderQuery);

        List<RequestHeader> requestHeaderList = BeanMapper.mapList(pagination.getDataList(),RequestHeader.class);

        joinTemplate.joinQuery(requestHeaderList);

        return PaginationBuilder.build(pagination,requestHeaderList);
    }
}