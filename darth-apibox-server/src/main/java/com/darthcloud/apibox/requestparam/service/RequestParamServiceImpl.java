package com.darthcloud.apibox.requestparam.service;

import com.darthcloud.apibox.requestparam.dao.RequestParamDao;
import com.darthcloud.apibox.requestparam.entity.RequestParamPo;
import com.darthcloud.apibox.requestparam.model.RequestParam;
import com.darthcloud.apibox.requestparam.model.RequestParamQuery;

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
public class RequestParamServiceImpl implements RequestParamService {

    @Autowired
    RequestParamDao requestParamDao;

    @Override
    public String createRequestParam(@NotNull @Valid RequestParam requestParam) {
        RequestParamPo requestParamPo = BeanMapper.map(requestParam, RequestParamPo.class);

        return requestParamDao.createRequestParam(requestParamPo);
    }

    @Override
    public void updateRequestParam(@NotNull @Valid RequestParam requestParam) {
        RequestParamPo requestParamPo = BeanMapper.map(requestParam, RequestParamPo.class);

        requestParamDao.updateRequestParam(requestParamPo);
    }

    @Override
    public void deleteRequestParam(@NotNull String id) {
        requestParamDao.deleteRequestParam(id);
    }

    @Override
    public RequestParam findRequestParam(@NotNull String id) {
        RequestParamPo requestParamPo = requestParamDao.findRequestParam(id);

        return BeanMapper.map(requestParamPo, RequestParam.class);
    }

    @Override
    public List<RequestParam> findAllRequestParam() {
        List<RequestParamPo> requestParamPoList =  requestParamDao.findAllRequestParam();

        return BeanMapper.mapList(requestParamPoList,RequestParam.class);
    }

    @Override
    public List<RequestParam> findRequestParamList(RequestParamQuery requestParamQuery) {
        List<RequestParamPo> requestParamPoList = requestParamDao.findRequestParamList(requestParamQuery);

        return BeanMapper.mapList(requestParamPoList,RequestParam.class);
    }

    @Override
    public Pagination<List<RequestParam>> findRequestParamPage(RequestParamQuery requestParamQuery) {
        Pagination<List<RequestParam>> pg = new Pagination<>();

        Pagination<List<RequestParamPo>>  pagination = requestParamDao.findRequestParamPage(requestParamQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<RequestParam> requestParamList = BeanMapper.mapList(pagination.getDataList(),RequestParam.class);
        pg.setDataList(requestParamList);
        return pg;
    }
}