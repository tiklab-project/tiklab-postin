package com.doublekit.apibox.apidef.service;

import com.doublekit.apibox.apidef.dao.ResponseResultDao;
import com.doublekit.apibox.apidef.entity.ResponseResultEntity;
import com.doublekit.apibox.apidef.model.ResponseResult;
import com.doublekit.apibox.apidef.model.ResponseResultQuery;

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
public class ResponseResultServiceImpl implements ResponseResultService {

    @Autowired
    ResponseResultDao responseResultDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createResponseResult(@NotNull @Valid ResponseResult responseResult) {
        ResponseResultEntity responseResultEntity = BeanMapper.map(responseResult, ResponseResultEntity.class);

        return responseResultDao.createResponseResult(responseResultEntity);
    }

    @Override
    public void updateResponseResult(@NotNull @Valid ResponseResult responseResult) {
        ResponseResultEntity responseResultEntity = BeanMapper.map(responseResult, ResponseResultEntity.class);

        responseResultDao.updateResponseResult(responseResultEntity);
    }

    @Override
    public void deleteResponseResult(@NotNull String id) {
        responseResultDao.deleteResponseResult(id);
    }

    @Override
    public ResponseResult findResponseResult(@NotNull String id) {
        ResponseResultEntity responseResultEntity = responseResultDao.findResponseResult(id);

        ResponseResult responseResult = BeanMapper.map(responseResultEntity, ResponseResult.class);

        joinQuery.queryOne(responseResult);

        return responseResult;
    }

    @Override
    public List<ResponseResult> findAllResponseResult() {
        List<ResponseResultEntity> responseResultEntityList =  responseResultDao.findAllResponseResult();

        List<ResponseResult> responseResultList =  BeanMapper.mapList(responseResultEntityList,ResponseResult.class);

        joinQuery.queryList(responseResultList);

        return responseResultList;
    }

    @Override
    public List<ResponseResult> findResponseResultList(ResponseResultQuery responseResultQuery) {
        List<ResponseResultEntity> responseResultEntityList = responseResultDao.findResponseResultList(responseResultQuery);

        List<ResponseResult> responseResultList = BeanMapper.mapList(responseResultEntityList,ResponseResult.class);

        joinQuery.queryList(responseResultList);

        return responseResultList;
    }

    @Override
    public Pagination<ResponseResult> findResponseResultPage(ResponseResultQuery responseResultQuery) {

        Pagination<ResponseResultEntity>  pagination = responseResultDao.findResponseResultPage(responseResultQuery);

        List<ResponseResult> responseResultList = BeanMapper.mapList(pagination.getDataList(),ResponseResult.class);

        joinQuery.queryList(responseResultList);

        return PaginationBuilder.build(pagination,responseResultList);
    }
}