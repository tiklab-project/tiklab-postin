package com.tiklab.postin.apidef.http.service;

import com.tiklab.postin.apidef.http.dao.ResponseResultDao;
import com.tiklab.postin.apidef.http.entity.ResponseResultEntity;
import com.tiklab.postin.apidef.http.model.ResponseResult;
import com.tiklab.postin.apidef.http.model.ResponseResultQuery;

import com.tiklab.core.page.Pagination;
import com.tiklab.beans.BeanMapper;
import com.tiklab.core.page.PaginationBuilder;
import com.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务业务处理
*/
@Service
public class ResponseResultServiceImpl implements ResponseResultService {

    @Autowired
    ResponseResultDao responseResultDao;

    @Autowired
    JoinTemplate joinTemplate;

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

        joinTemplate.joinQuery(responseResult);

        return responseResult;
    }

    @Override
    public List<ResponseResult> findAllResponseResult() {
        List<ResponseResultEntity> responseResultEntityList =  responseResultDao.findAllResponseResult();

        List<ResponseResult> responseResultList =  BeanMapper.mapList(responseResultEntityList,ResponseResult.class);

        joinTemplate.joinQuery(responseResultList);

        return responseResultList;
    }

    @Override
    public List<ResponseResult> findResponseResultList(ResponseResultQuery responseResultQuery) {
        List<ResponseResultEntity> responseResultEntityList = responseResultDao.findResponseResultList(responseResultQuery);

        List<ResponseResult> responseResultList = BeanMapper.mapList(responseResultEntityList,ResponseResult.class);

        joinTemplate.joinQuery(responseResultList);

        return responseResultList;
    }

    @Override
    public Pagination<ResponseResult> findResponseResultPage(ResponseResultQuery responseResultQuery) {

        Pagination<ResponseResultEntity>  pagination = responseResultDao.findResponseResultPage(responseResultQuery);

        List<ResponseResult> responseResultList = BeanMapper.mapList(pagination.getDataList(),ResponseResult.class);

        joinTemplate.joinQuery(responseResultList);

        return PaginationBuilder.build(pagination,responseResultList);
    }
}