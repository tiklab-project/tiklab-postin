package io.tiklab.postin.api.apix.service;

import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.apix.dao.RequestHeaderDao;
import io.tiklab.postin.api.apix.entity.RequestHeaderEntity;
import io.tiklab.postin.api.apix.model.RequestHeader;
import io.tiklab.postin.api.apix.model.RequestHeaderQuery;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
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
    public void deleteAllRequestHeader(String id) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(RequestHeaderEntity.class)
                .eq("apiId", id)
                .get();
        requestHeaderDao.deleteRequestHeaderList(deleteCondition);
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

        List<RequestHeader> requestHeaderList = BeanMapper.mapList(requestHeaderEntityList, RequestHeader.class);

        joinTemplate.joinQuery(requestHeaderList);

        return requestHeaderList;
    }

    @Override
    public List<RequestHeader> findRequestHeaderList(RequestHeaderQuery requestHeaderQuery) {
        List<RequestHeaderEntity> requestHeaderEntityList = requestHeaderDao.findRequestHeaderList(requestHeaderQuery);

        List<RequestHeader> requestHeaderList = BeanMapper.mapList(requestHeaderEntityList, RequestHeader.class);

        joinTemplate.joinQuery(requestHeaderList);

        return requestHeaderList;
    }

    @Override
    public Pagination<RequestHeader> findRequestHeaderPage(RequestHeaderQuery requestHeaderQuery) {

        Pagination<RequestHeaderEntity>  pagination = requestHeaderDao.findRequestHeaderPage(requestHeaderQuery);

        List<RequestHeader> requestHeaderList = BeanMapper.mapList(pagination.getDataList(), RequestHeader.class);

        joinTemplate.joinQuery(requestHeaderList);

        return PaginationBuilder.build(pagination, requestHeaderList);
    }
}