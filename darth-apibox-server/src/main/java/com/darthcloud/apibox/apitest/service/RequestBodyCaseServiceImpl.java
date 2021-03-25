package com.darthcloud.apibox.apitest.service;

import com.darthcloud.apibox.apitest.dao.RequestBodyCaseDao;
import com.darthcloud.apibox.apitest.entity.RequestBodyCasePo;
import com.darthcloud.apibox.apitest.model.RequestBodyCase;
import com.darthcloud.apibox.apitest.model.RequestBodyCaseQuery;

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
public class RequestBodyCaseServiceImpl implements RequestBodyCaseService {

    @Autowired
    RequestBodyCaseDao requestBodyCaseDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createRequestBodyCase(@NotNull @Valid RequestBodyCase requestBodyCase) {
        RequestBodyCasePo requestBodyCasePo = BeanMapper.map(requestBodyCase, RequestBodyCasePo.class);

        return requestBodyCaseDao.createRequestBodyCase(requestBodyCasePo);
    }

    @Override
    public void updateRequestBodyCase(@NotNull @Valid RequestBodyCase requestBodyCase) {
        RequestBodyCasePo requestBodyCasePo = BeanMapper.map(requestBodyCase, RequestBodyCasePo.class);

        requestBodyCaseDao.updateRequestBodyCase(requestBodyCasePo);
    }

    @Override
    public void deleteRequestBodyCase(@NotNull String id) {
        requestBodyCaseDao.deleteRequestBodyCase(id);
    }

    @Override
    public RequestBodyCase findOne(String id) {
        RequestBodyCasePo requestBodyCasePo = requestBodyCaseDao.findRequestBodyCase(id);

        RequestBodyCase requestBodyCase = BeanMapper.map(requestBodyCasePo, RequestBodyCase.class);
        return requestBodyCase;
    }

    @Override
    public List<RequestBodyCase> findList(List<String> idList) {
        List<RequestBodyCasePo> requestBodyCasePoList =  requestBodyCaseDao.findRequestBodyCaseList(idList);

        List<RequestBodyCase> requestBodyCaseList =  BeanMapper.mapList(requestBodyCasePoList,RequestBodyCase.class);
        return requestBodyCaseList;
    }

    @Override
    public RequestBodyCase findRequestBodyCase(@NotNull String id) {
        RequestBodyCase requestBodyCase = findOne(id);

        joinQuery.queryOne(requestBodyCase);
        return requestBodyCase;
    }

    @Override
    public List<RequestBodyCase> findAllRequestBodyCase() {
        List<RequestBodyCasePo> requestBodyCasePoList =  requestBodyCaseDao.findAllRequestBodyCase();

        List<RequestBodyCase> requestBodyCaseList =  BeanMapper.mapList(requestBodyCasePoList,RequestBodyCase.class);

        joinQuery.queryList(requestBodyCaseList);
        return requestBodyCaseList;
    }

    @Override
    public List<RequestBodyCase> findRequestBodyCaseList(RequestBodyCaseQuery requestBodyCaseQuery) {
        List<RequestBodyCasePo> requestBodyCasePoList = requestBodyCaseDao.findRequestBodyCaseList(requestBodyCaseQuery);

        List<RequestBodyCase> requestBodyCaseList = BeanMapper.mapList(requestBodyCasePoList,RequestBodyCase.class);

        joinQuery.queryList(requestBodyCaseList);

        return requestBodyCaseList;
    }

    @Override
    public Pagination<RequestBodyCase> findRequestBodyCasePage(RequestBodyCaseQuery requestBodyCaseQuery) {
        Pagination<RequestBodyCase> pg = new Pagination<>();

        Pagination<RequestBodyCasePo>  pagination = requestBodyCaseDao.findRequestBodyCasePage(requestBodyCaseQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<RequestBodyCase> requestBodyCaseList = BeanMapper.mapList(pagination.getDataList(),RequestBodyCase.class);

        joinQuery.queryList(requestBodyCaseList);

        pg.setDataList(requestBodyCaseList);
        return pg;
    }
}