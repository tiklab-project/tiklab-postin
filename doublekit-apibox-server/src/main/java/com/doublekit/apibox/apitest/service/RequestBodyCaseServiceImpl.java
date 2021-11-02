package com.doublekit.apibox.apitest.service;

import com.doublekit.apibox.apitest.dao.RequestBodyCaseDao;
import com.doublekit.apibox.apitest.entity.RequestBodyCaseEntity;
import com.doublekit.apibox.apitest.model.RequestBodyCase;
import com.doublekit.apibox.apitest.model.RequestBodyCaseQuery;

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
public class RequestBodyCaseServiceImpl implements RequestBodyCaseService {

    @Autowired
    RequestBodyCaseDao requestBodyCaseDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createRequestBodyCase(@NotNull @Valid RequestBodyCase requestBodyCase) {
        RequestBodyCaseEntity requestBodyCaseEntity = BeanMapper.map(requestBodyCase, RequestBodyCaseEntity.class);

        return requestBodyCaseDao.createRequestBodyCase(requestBodyCaseEntity);
    }

    @Override
    public void updateRequestBodyCase(@NotNull @Valid RequestBodyCase requestBodyCase) {
        RequestBodyCaseEntity requestBodyCaseEntity = BeanMapper.map(requestBodyCase, RequestBodyCaseEntity.class);

        requestBodyCaseDao.updateRequestBodyCase(requestBodyCaseEntity);
    }

    @Override
    public void deleteRequestBodyCase(@NotNull String id) {
        requestBodyCaseDao.deleteRequestBodyCase(id);
    }

    @Override
    public RequestBodyCase findOne(String id) {
        RequestBodyCaseEntity requestBodyCaseEntity = requestBodyCaseDao.findRequestBodyCase(id);

        RequestBodyCase requestBodyCase = BeanMapper.map(requestBodyCaseEntity, RequestBodyCase.class);
        return requestBodyCase;
    }

    @Override
    public List<RequestBodyCase> findList(List<String> idList) {
        List<RequestBodyCaseEntity> requestBodyCaseEntityList =  requestBodyCaseDao.findRequestBodyCaseList(idList);

        List<RequestBodyCase> requestBodyCaseList =  BeanMapper.mapList(requestBodyCaseEntityList,RequestBodyCase.class);
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
        List<RequestBodyCaseEntity> requestBodyCaseEntityList =  requestBodyCaseDao.findAllRequestBodyCase();

        List<RequestBodyCase> requestBodyCaseList =  BeanMapper.mapList(requestBodyCaseEntityList,RequestBodyCase.class);

        joinQuery.queryList(requestBodyCaseList);
        return requestBodyCaseList;
    }

    @Override
    public List<RequestBodyCase> findRequestBodyCaseList(RequestBodyCaseQuery requestBodyCaseQuery) {
        List<RequestBodyCaseEntity> requestBodyCaseEntityList = requestBodyCaseDao.findRequestBodyCaseList(requestBodyCaseQuery);

        List<RequestBodyCase> requestBodyCaseList = BeanMapper.mapList(requestBodyCaseEntityList,RequestBodyCase.class);

        joinQuery.queryList(requestBodyCaseList);

        return requestBodyCaseList;
    }

    @Override
    public Pagination<RequestBodyCase> findRequestBodyCasePage(RequestBodyCaseQuery requestBodyCaseQuery) {

        Pagination<RequestBodyCaseEntity>  pagination = requestBodyCaseDao.findRequestBodyCasePage(requestBodyCaseQuery);

        List<RequestBodyCase> requestBodyCaseList = BeanMapper.mapList(pagination.getDataList(),RequestBodyCase.class);

        joinQuery.queryList(requestBodyCaseList);

        return PaginationBuilder.build(pagination,requestBodyCaseList);
    }
}