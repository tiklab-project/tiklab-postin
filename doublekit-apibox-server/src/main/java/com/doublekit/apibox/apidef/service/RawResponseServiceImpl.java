package com.doublekit.apibox.apidef.service;

import com.doublekit.apibox.apidef.dao.RawResponseDao;
import com.doublekit.apibox.apidef.entity.RawResponseEntity;
import com.doublekit.apibox.apidef.model.RawResponse;
import com.doublekit.apibox.apidef.model.RawResponseQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.PaginationBuilder;
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
public class RawResponseServiceImpl implements RawResponseService {

    @Autowired
    RawResponseDao rawResponseDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createRawResponse(@NotNull @Valid RawResponse rawResponse) {
        RawResponseEntity rawResponseEntity = BeanMapper.map(rawResponse, RawResponseEntity.class);

        return rawResponseDao.createRawResponse(rawResponseEntity);
    }

    @Override
    public void updateRawResponse(@NotNull @Valid RawResponse rawResponse) {
        RawResponseEntity rawResponseEntity = BeanMapper.map(rawResponse, RawResponseEntity.class);

        rawResponseDao.updateRawResponse(rawResponseEntity);
    }

    @Override
    public void deleteRawResponse(@NotNull String id) {
        rawResponseDao.deleteRawResponse(id);
    }

    @Override
    public RawResponse findRawResponse(@NotNull String id) {
        RawResponseEntity rawResponseEntity = rawResponseDao.findRawResponse(id);

        RawResponse rawResponse = BeanMapper.map(rawResponseEntity, RawResponse.class);

        joinQuery.queryOne(rawResponse);

        return rawResponse;
    }

    @Override
    public List<RawResponse> findAllRawResponse() {
        List<RawResponseEntity> rawResponseEntityList =  rawResponseDao.findAllRawResponse();

        List<RawResponse> rawResponseList =  BeanMapper.mapList(rawResponseEntityList,RawResponse.class);

        joinQuery.queryList(rawResponseList);

        return rawResponseList;
    }

    @Override
    public List<RawResponse> findRawResponseList(RawResponseQuery rawResponseQuery) {
        List<RawResponseEntity> rawResponseEntityList = rawResponseDao.findRawResponseList(rawResponseQuery);

        List<RawResponse> rawResponseList = BeanMapper.mapList(rawResponseEntityList,RawResponse.class);

        joinQuery.queryList(rawResponseList);

        return rawResponseList;
    }

    @Override
    public Pagination<RawResponse> findRawResponsePage(RawResponseQuery rawResponseQuery) {

        Pagination<RawResponseEntity>  pagination = rawResponseDao.findRawResponsePage(rawResponseQuery);

        List<RawResponse> rawResponseList = BeanMapper.mapList(pagination.getDataList(),RawResponse.class);

        joinQuery.queryList(rawResponseList);

        return PaginationBuilder.build(pagination,rawResponseList);
    }
}