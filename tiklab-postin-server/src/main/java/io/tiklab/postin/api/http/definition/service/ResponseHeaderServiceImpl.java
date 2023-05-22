package io.tiklab.postin.api.http.definition.service;

import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.http.definition.dao.ResponseHeaderDao;
import io.tiklab.postin.api.http.definition.entity.RequestHeadersEntity;
import io.tiklab.postin.api.http.definition.entity.ResponseHeadersEntity;
import io.tiklab.postin.api.http.definition.model.ResponseHeaders;
import io.tiklab.postin.api.http.definition.model.ResponseHeaderQuery;

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
 * 响应头服务
 */
@Service
public class ResponseHeaderServiceImpl implements ResponseHeaderService {

    @Autowired
    ResponseHeaderDao responseHeaderDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createResponseHeader(@NotNull @Valid ResponseHeaders responseHeaders) {
        ResponseHeadersEntity responseHeadersEntity = BeanMapper.map(responseHeaders, ResponseHeadersEntity.class);

        return responseHeaderDao.createResponseHeader(responseHeadersEntity);
    }

    @Override
    public void updateResponseHeader(@NotNull @Valid ResponseHeaders responseHeaders) {
        ResponseHeadersEntity responseHeadersEntity = BeanMapper.map(responseHeaders, ResponseHeadersEntity.class);

        responseHeaderDao.updateResponseHeader(responseHeadersEntity);
    }

    @Override
    public void deleteResponseHeader(@NotNull String id) {
        responseHeaderDao.deleteResponseHeader(id);
    }

    @Override
    public void deleteAllResponseHeader(String id) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(RequestHeadersEntity.class)
                .eq("httpId", id)
                .get();
        responseHeaderDao.deleteResponseHeaderList(deleteCondition);
    }

    @Override
    public ResponseHeaders findResponseHeader(@NotNull String id) {
        ResponseHeadersEntity responseHeadersEntity = responseHeaderDao.findResponseHeader(id);

        ResponseHeaders responseHeaders = BeanMapper.map(responseHeadersEntity, ResponseHeaders.class);

        joinTemplate.joinQuery(responseHeaders);

        return responseHeaders;
    }

    @Override
    public List<ResponseHeaders> findAllResponseHeader() {
        List<ResponseHeadersEntity> responseHeadersEntityList =  responseHeaderDao.findAllResponseHeader();

        List<ResponseHeaders> responseHeadersList = BeanMapper.mapList(responseHeadersEntityList, ResponseHeaders.class);

        joinTemplate.joinQuery(responseHeadersList);

        return responseHeadersList;
    }

    @Override
    public List<ResponseHeaders> findResponseHeaderList(ResponseHeaderQuery responseHeaderQuery) {
        List<ResponseHeadersEntity> responseHeadersEntityList = responseHeaderDao.findResponseHeaderList(responseHeaderQuery);

        List<ResponseHeaders> responseHeadersList = BeanMapper.mapList(responseHeadersEntityList, ResponseHeaders.class);

        joinTemplate.joinQuery(responseHeadersList);

        return responseHeadersList;
    }

    @Override
    public Pagination<ResponseHeaders> findResponseHeaderPage(ResponseHeaderQuery responseHeaderQuery) {

        Pagination<ResponseHeadersEntity>  pagination = responseHeaderDao.findResponseHeaderPage(responseHeaderQuery);

        List<ResponseHeaders> responseHeadersList = BeanMapper.mapList(pagination.getDataList(), ResponseHeaders.class);

        joinTemplate.joinQuery(responseHeadersList);

        return PaginationBuilder.build(pagination, responseHeadersList);
    }
}