package com.darthcloud.apibox.apidef.service;

import com.darthcloud.apibox.apidef.dao.ResponseResultDao;
import com.darthcloud.apibox.apidef.entity.ResponseResultPo;
import com.darthcloud.apibox.apidef.model.ResponseResult;
import com.darthcloud.apibox.apidef.model.ResponseResultQuery;

import com.darthcloud.common.Pagination;
import com.darthcloud.dsl.beans.BeanMapper;
import com.darthcloud.dsl.join.join.JoinQuery;
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
    JoinQuery joinQuery;

    @Override
    public String createResponseResult(@NotNull @Valid ResponseResult responseResult) {
        ResponseResultPo responseResultPo = BeanMapper.map(responseResult, ResponseResultPo.class);

        return responseResultDao.createResponseResult(responseResultPo);
    }

    @Override
    public void updateResponseResult(@NotNull @Valid ResponseResult responseResult) {
        ResponseResultPo responseResultPo = BeanMapper.map(responseResult, ResponseResultPo.class);

        responseResultDao.updateResponseResult(responseResultPo);
    }

    @Override
    public void deleteResponseResult(@NotNull String id) {
        responseResultDao.deleteResponseResult(id);
    }

    @Override
    public ResponseResult findResponseResult(@NotNull String id) {
        ResponseResultPo responseResultPo = responseResultDao.findResponseResult(id);

        ResponseResult responseResult = BeanMapper.map(responseResultPo, ResponseResult.class);

        joinQuery.queryOne(responseResult);

        return responseResult;
    }

    @Override
    public List<ResponseResult> findAllResponseResult() {
        List<ResponseResultPo> responseResultPoList =  responseResultDao.findAllResponseResult();

        List<ResponseResult> responseResultList =  BeanMapper.mapList(responseResultPoList,ResponseResult.class);

        joinQuery.queryList(responseResultList);

        return responseResultList;
    }

    @Override
    public List<ResponseResult> findResponseResultList(ResponseResultQuery responseResultQuery) {
        List<ResponseResultPo> responseResultPoList = responseResultDao.findResponseResultList(responseResultQuery);

        List<ResponseResult> responseResultList = BeanMapper.mapList(responseResultPoList,ResponseResult.class);

        joinQuery.queryList(responseResultList);

        return responseResultList;
    }

    @Override
    public Pagination<ResponseResult> findResponseResultPage(ResponseResultQuery responseResultQuery) {
        Pagination<ResponseResult> pg = new Pagination<>();

        Pagination<ResponseResultPo>  pagination = responseResultDao.findResponseResultPage(responseResultQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<ResponseResult> responseResultList = BeanMapper.mapList(pagination.getDataList(),ResponseResult.class);

        joinQuery.queryList(responseResultList);

        pg.setDataList(responseResultList);
        return pg;
    }
}