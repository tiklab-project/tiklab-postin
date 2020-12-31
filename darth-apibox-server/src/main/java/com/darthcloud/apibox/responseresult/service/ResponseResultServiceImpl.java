package com.darthcloud.apibox.responseresult.service;

import com.darthcloud.apibox.responseresult.dao.ResponseResultDao;
import com.darthcloud.apibox.responseresult.entity.ResponseResultPo;
import com.darthcloud.apibox.responseresult.model.ResponseResult;
import com.darthcloud.apibox.responseresult.model.ResponseResultQuery;

import com.darthcloud.common.Pagination;
import com.darthcloud.beans.BeanMapper;
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

        return BeanMapper.map(responseResultPo, ResponseResult.class);
    }

    @Override
    public List<ResponseResult> findAllResponseResult() {
        List<ResponseResultPo> responseResultPoList =  responseResultDao.findAllResponseResult();

        return BeanMapper.mapList(responseResultPoList,ResponseResult.class);
    }

    @Override
    public List<ResponseResult> findResponseResultList(ResponseResultQuery responseResultQuery) {
        List<ResponseResultPo> responseResultPoList = responseResultDao.findResponseResultList(responseResultQuery);

        return BeanMapper.mapList(responseResultPoList,ResponseResult.class);
    }

    @Override
    public Pagination<List<ResponseResult>> findResponseResultPage(ResponseResultQuery responseResultQuery) {
        Pagination<List<ResponseResult>> pg = new Pagination<>();

        Pagination<List<ResponseResultPo>>  pagination = responseResultDao.findResponseResultPage(responseResultQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<ResponseResult> responseResultList = BeanMapper.mapList(pagination.getDataList(),ResponseResult.class);
        pg.setDataList(responseResultList);
        return pg;
    }
}