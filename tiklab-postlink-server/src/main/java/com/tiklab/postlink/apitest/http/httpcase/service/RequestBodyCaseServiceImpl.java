package com.tiklab.postlink.apitest.http.httpcase.service;

import com.tiklab.postlink.apitest.http.httpcase.dao.RequestBodyCaseDao;
import com.tiklab.postlink.apitest.http.httpcase.entity.RequestBodyCaseEntity;
import com.tiklab.postlink.apitest.http.httpcase.model.RequestBodyCase;
import com.tiklab.postlink.apitest.http.httpcase.model.RequestBodyCaseQuery;

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
public class RequestBodyCaseServiceImpl implements RequestBodyCaseService {

    @Autowired
    RequestBodyCaseDao requestBodyCaseDao;

    @Autowired
    JoinTemplate joinTemplate;

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

        joinTemplate.joinQuery(requestBodyCase);
        return requestBodyCase;
    }

    @Override
    public List<RequestBodyCase> findAllRequestBodyCase() {
        List<RequestBodyCaseEntity> requestBodyCaseEntityList =  requestBodyCaseDao.findAllRequestBodyCase();

        List<RequestBodyCase> requestBodyCaseList =  BeanMapper.mapList(requestBodyCaseEntityList,RequestBodyCase.class);

        joinTemplate.joinQuery(requestBodyCaseList);
        return requestBodyCaseList;
    }

    @Override
    public List<RequestBodyCase> findRequestBodyCaseList(RequestBodyCaseQuery requestBodyCaseQuery) {
        List<RequestBodyCaseEntity> requestBodyCaseEntityList = requestBodyCaseDao.findRequestBodyCaseList(requestBodyCaseQuery);

        List<RequestBodyCase> requestBodyCaseList = BeanMapper.mapList(requestBodyCaseEntityList,RequestBodyCase.class);

        joinTemplate.joinQuery(requestBodyCaseList);

        return requestBodyCaseList;
    }

    @Override
    public Pagination<RequestBodyCase> findRequestBodyCasePage(RequestBodyCaseQuery requestBodyCaseQuery) {

        Pagination<RequestBodyCaseEntity>  pagination = requestBodyCaseDao.findRequestBodyCasePage(requestBodyCaseQuery);

        List<RequestBodyCase> requestBodyCaseList = BeanMapper.mapList(pagination.getDataList(),RequestBodyCase.class);

        joinTemplate.joinQuery(requestBodyCaseList);

        return PaginationBuilder.build(pagination,requestBodyCaseList);
    }
}