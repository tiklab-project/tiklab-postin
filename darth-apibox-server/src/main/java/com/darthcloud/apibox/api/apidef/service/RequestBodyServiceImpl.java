package com.darthcloud.apibox.api.apidef.service;

import com.darthcloud.apibox.api.apidef.dao.RequestBodyDao;
import com.darthcloud.apibox.api.apidef.entity.RequestBodyPo;
import com.darthcloud.apibox.api.apidef.model.RequestBodyEx;
import com.darthcloud.apibox.api.apidef.model.RequestBodyExQuery;

import com.darthcloud.common.Pagination;
import com.darthcloud.beans.BeanMapper;
import com.darthcloud.join.join.JoinQuery;
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
public class RequestBodyServiceImpl implements RequestBodyService {

    @Autowired
    RequestBodyDao requestBodyDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createRequestBody(@NotNull @Valid RequestBodyEx requestBody) {
        RequestBodyPo requestBodyPo = BeanMapper.map(requestBody, RequestBodyPo.class);

        return requestBodyDao.createRequestBody(requestBodyPo);
    }

    @Override
    public void updateRequestBody(@NotNull @Valid RequestBodyEx requestBody) {
        RequestBodyPo requestBodyPo = BeanMapper.map(requestBody, RequestBodyPo.class);

        requestBodyDao.updateRequestBody(requestBodyPo);
    }

    @Override
    public void deleteRequestBody(@NotNull String id) {
        requestBodyDao.deleteRequestBody(id);
    }

    @Override
    public RequestBodyEx findRequestBody(@NotNull String id) {
        RequestBodyPo requestBodyPo = requestBodyDao.findRequestBody(id);

        RequestBodyEx requestBody = BeanMapper.map(requestBodyPo, RequestBodyEx.class);

        joinQuery.queryOne(requestBody);

        return requestBody;
    }

    @Override
    public List<RequestBodyEx> findAllRequestBody() {
        List<RequestBodyPo> requestBodyPoList =  requestBodyDao.findAllRequestBody();

        List<RequestBodyEx> requestBodyList =  BeanMapper.mapList(requestBodyPoList, RequestBodyEx.class);

        joinQuery.queryList(requestBodyList);

        return requestBodyList;
    }

    @Override
    public List<RequestBodyEx> findRequestBodyList(RequestBodyExQuery requestBodyQuery) {
        List<RequestBodyPo> requestBodyPoList = requestBodyDao.findRequestBodyList(requestBodyQuery);

        List<RequestBodyEx> requestBodyList = BeanMapper.mapList(requestBodyPoList, RequestBodyEx.class);

        joinQuery.queryList(requestBodyList);

        return requestBodyList;
    }

    @Override
    public Pagination<List<RequestBodyEx>> findRequestBodyPage(RequestBodyExQuery requestBodyQuery) {
        Pagination<List<RequestBodyEx>> pg = new Pagination<>();

        Pagination<List<RequestBodyPo>>  pagination = requestBodyDao.findRequestBodyPage(requestBodyQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<RequestBodyEx> requestBodyList = BeanMapper.mapList(pagination.getDataList(), RequestBodyEx.class);

        joinQuery.queryList(requestBodyList);

        pg.setDataList(requestBodyList);
        return pg;
    }
}