package io.tiklab.postin.api.http.definition.service;

import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.http.definition.dao.RequestHeaderDao;
import io.tiklab.postin.api.http.definition.entity.RequestHeadersEntity;
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
        RequestHeadersEntity requestHeadersEntity = BeanMapper.map(requestHeader, RequestHeadersEntity.class);

        return requestHeaderDao.createRequestHeader(requestHeadersEntity);
    }

    @Override
    public void updateRequestHeader(@NotNull @Valid RequestHeader requestHeader) {
        RequestHeadersEntity requestHeadersEntity = BeanMapper.map(requestHeader, RequestHeadersEntity.class);

        requestHeaderDao.updateRequestHeader(requestHeadersEntity);
    }

    @Override
    public void deleteRequestHeader(@NotNull String id) {
        requestHeaderDao.deleteRequestHeader(id);
    }

    @Override
    public void deleteAllRequestHeader(String id) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(RequestHeadersEntity.class)
                .eq("httpId", id)
                .get();
        requestHeaderDao.deleteRequestHeaderList(deleteCondition);
    }

    @Override
    public RequestHeader findRequestHeader(@NotNull String id) {
        RequestHeadersEntity requestHeadersEntity = requestHeaderDao.findRequestHeader(id);

        RequestHeader requestHeader = BeanMapper.map(requestHeadersEntity, RequestHeader.class);

        joinTemplate.joinQuery(requestHeader);

        return requestHeader;
    }

    @Override
    public List<RequestHeader> findAllRequestHeader() {
        List<RequestHeadersEntity> requestHeadersEntityList =  requestHeaderDao.findAllRequestHeader();

        List<RequestHeader> requestHeaderList = BeanMapper.mapList(requestHeadersEntityList, RequestHeader.class);

        joinTemplate.joinQuery(requestHeaderList);

        return requestHeaderList;
    }

    @Override
    public List<RequestHeader> findRequestHeaderList(RequestHeaderQuery requestHeaderQuery) {
        List<RequestHeadersEntity> requestHeadersEntityList = requestHeaderDao.findRequestHeaderList(requestHeaderQuery);

        List<RequestHeader> requestHeaderList = BeanMapper.mapList(requestHeadersEntityList, RequestHeader.class);

        joinTemplate.joinQuery(requestHeaderList);

        return requestHeaderList;
    }

    @Override
    public Pagination<RequestHeader> findRequestHeaderPage(RequestHeaderQuery requestHeaderQuery) {

        Pagination<RequestHeadersEntity>  pagination = requestHeaderDao.findRequestHeaderPage(requestHeaderQuery);

        List<RequestHeader> requestHeaderList = BeanMapper.mapList(pagination.getDataList(), RequestHeader.class);

        joinTemplate.joinQuery(requestHeaderList);

        return PaginationBuilder.build(pagination, requestHeaderList);
    }
}