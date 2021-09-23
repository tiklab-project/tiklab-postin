package com.doublekit.apibox.apidef.service;

import com.doublekit.apibox.apidef.dao.RequestBodyDao;
import com.doublekit.apibox.apidef.entity.RequestBodyPo;
import com.doublekit.apibox.apidef.model.RequestBodyEx;
import com.doublekit.apibox.apidef.model.RequestBodyExQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
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
public class RequestBodyServiceImpl implements RequestBodyService {

    @Autowired
    RequestBodyDao requestBodyDao;

    @Autowired
    JoinTemplate joinQuery;

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
    public Pagination<RequestBodyEx> findRequestBodyPage(RequestBodyExQuery requestBodyQuery) {
        Pagination<RequestBodyEx> pg = new Pagination<>();

        Pagination<RequestBodyPo>  pagination = requestBodyDao.findRequestBodyPage(requestBodyQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<RequestBodyEx> requestBodyList = BeanMapper.mapList(pagination.getDataList(), RequestBodyEx.class);

        joinQuery.queryList(requestBodyList);

        pg.setDataList(requestBodyList);
        return pg;
    }
}