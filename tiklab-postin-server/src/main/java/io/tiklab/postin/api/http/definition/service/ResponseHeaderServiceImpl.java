package io.tiklab.postin.api.http.definition.service;

import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.http.definition.dao.ResponseHeaderDao;
import io.tiklab.postin.api.http.definition.entity.ResponseHeaderEntity;
import io.tiklab.postin.api.http.definition.model.ResponseHeader;
import io.tiklab.postin.api.http.definition.model.ResponseHeaderQuery;

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
    public String createResponseHeader(@NotNull @Valid ResponseHeader responseHeader) {
        ResponseHeaderEntity responseHeaderEntity = BeanMapper.map(responseHeader, ResponseHeaderEntity.class);

        return responseHeaderDao.createResponseHeader(responseHeaderEntity);
    }

    @Override
    public void updateResponseHeader(@NotNull @Valid ResponseHeader responseHeader) {
        ResponseHeaderEntity responseHeaderEntity = BeanMapper.map(responseHeader, ResponseHeaderEntity.class);

        responseHeaderDao.updateResponseHeader(responseHeaderEntity);
    }

    @Override
    public void deleteResponseHeader(@NotNull String id) {
        responseHeaderDao.deleteResponseHeader(id);
    }

    @Override
    public void deleteAllResponseHeader(String id) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(ResponseHeaderEntity.class)
                .eq("httpId", id)
                .get();
        responseHeaderDao.deleteResponseHeaderList(deleteCondition);
    }

    @Override
    public ResponseHeader findResponseHeader(@NotNull String id) {
        ResponseHeaderEntity responseHeaderEntity = responseHeaderDao.findResponseHeader(id);

        ResponseHeader responseHeader = BeanMapper.map(responseHeaderEntity, ResponseHeader.class);

        joinTemplate.joinQuery(responseHeader,new String[]{
                "http"
        });

        return responseHeader;
    }

    @Override
    public List<ResponseHeader> findAllResponseHeader() {
        List<ResponseHeaderEntity> responseHeaderEntityList =  responseHeaderDao.findAllResponseHeader();

        List<ResponseHeader> responseHeaderList = BeanMapper.mapList(responseHeaderEntityList, ResponseHeader.class);

        joinTemplate.joinQuery(responseHeaderList,new String[]{
                "http"
        });

        return responseHeaderList;
    }

    @Override
    public List<ResponseHeader> findResponseHeaderList(ResponseHeaderQuery responseHeaderQuery) {
        List<ResponseHeaderEntity> responseHeaderEntityList = responseHeaderDao.findResponseHeaderList(responseHeaderQuery);

        List<ResponseHeader> responseHeaderList = BeanMapper.mapList(responseHeaderEntityList, ResponseHeader.class);

        joinTemplate.joinQuery(responseHeaderList,new String[]{
                "http"
        });

        return responseHeaderList;
    }

    @Override
    public Pagination<ResponseHeader> findResponseHeaderPage(ResponseHeaderQuery responseHeaderQuery) {

        Pagination<ResponseHeaderEntity>  pagination = responseHeaderDao.findResponseHeaderPage(responseHeaderQuery);

        List<ResponseHeader> responseHeaderList = BeanMapper.mapList(pagination.getDataList(), ResponseHeader.class);

        joinTemplate.joinQuery(responseHeaderList,new String[]{
                "http"
        });

        return PaginationBuilder.build(pagination, responseHeaderList);
    }
}