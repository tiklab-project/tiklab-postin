package io.tiklab.postin.api.http.definition.service;

import io.tiklab.postin.api.http.definition.dao.RawResponseDao;
import io.tiklab.postin.api.http.definition.entity.RawResponsesEntity;
import io.tiklab.postin.api.http.definition.model.RawResponse;
import io.tiklab.postin.api.http.definition.model.RawResponseQuery;

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
 * 响应中raw服务
 */
@Service
public class RawResponseServiceImpl implements RawResponseService {

    @Autowired
    RawResponseDao rawResponseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRawResponse(@NotNull @Valid RawResponse rawResponse) {
        RawResponsesEntity rawResponsesEntity = BeanMapper.map(rawResponse, RawResponsesEntity.class);

        return rawResponseDao.createRawResponse(rawResponsesEntity);
    }

    @Override
    public void updateRawResponse(@NotNull @Valid RawResponse rawResponse) {
        RawResponsesEntity rawResponsesEntity = BeanMapper.map(rawResponse, RawResponsesEntity.class);

        rawResponseDao.updateRawResponse(rawResponsesEntity);
    }

    @Override
    public void deleteRawResponse(@NotNull String id) {
        rawResponseDao.deleteRawResponse(id);
    }

    @Override
    public RawResponse findRawResponse(@NotNull String id) {
        RawResponsesEntity rawResponsesEntity = rawResponseDao.findRawResponse(id);

        RawResponse rawResponse = BeanMapper.map(rawResponsesEntity, RawResponse.class);

        joinTemplate.joinQuery(rawResponse);

        return rawResponse;
    }

    @Override
    public List<RawResponse> findAllRawResponse() {
        List<RawResponsesEntity> rawResponsesEntityList =  rawResponseDao.findAllRawResponse();

        List<RawResponse> rawResponseList =  BeanMapper.mapList(rawResponsesEntityList,RawResponse.class);

        joinTemplate.joinQuery(rawResponseList);

        return rawResponseList;
    }

    @Override
    public List<RawResponse> findRawResponseList(RawResponseQuery rawResponseQuery) {
        List<RawResponsesEntity> rawResponsesEntityList = rawResponseDao.findRawResponseList(rawResponseQuery);

        List<RawResponse> rawResponseList = BeanMapper.mapList(rawResponsesEntityList,RawResponse.class);

        joinTemplate.joinQuery(rawResponseList);

        return rawResponseList;
    }

    @Override
    public Pagination<RawResponse> findRawResponsePage(RawResponseQuery rawResponseQuery) {

        Pagination<RawResponsesEntity>  pagination = rawResponseDao.findRawResponsePage(rawResponseQuery);

        List<RawResponse> rawResponseList = BeanMapper.mapList(pagination.getDataList(),RawResponse.class);

        joinTemplate.joinQuery(rawResponseList);

        return PaginationBuilder.build(pagination,rawResponseList);
    }
}