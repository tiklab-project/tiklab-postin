package com.doublekit.apibox.apitest.http.httpcase.service;

import com.doublekit.apibox.apitest.http.httpcase.dao.RequestHeaderCaseDao;
import com.doublekit.apibox.apitest.http.httpcase.entity.RequestHeaderCaseEntity;

import com.doublekit.apibox.apitest.http.httpcase.model.RequestHeaderCase;
import com.doublekit.apibox.apitest.http.httpcase.model.RequestHeaderCaseQuery;
import com.doublekit.common.page.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.page.PaginationBuilder;
import com.doublekit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务业务处理
*/
@Service
public class RequestHeaderCaseServiceImpl implements RequestHeaderCaseService {

    @Autowired
    RequestHeaderCaseDao requestHeaderCaseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRequestHeaderCase(@NotNull @Valid RequestHeaderCase requestHeaderCase) {
        RequestHeaderCaseEntity requestHeaderCaseEntity = BeanMapper.map(requestHeaderCase, RequestHeaderCaseEntity.class);

        return requestHeaderCaseDao.createRequestHeaderCase(requestHeaderCaseEntity);
    }

    @Override
    public void updateRequestHeaderCase(@NotNull @Valid RequestHeaderCase requestHeaderCase) {
        RequestHeaderCaseEntity requestHeaderCaseEntity = BeanMapper.map(requestHeaderCase, RequestHeaderCaseEntity.class);

        requestHeaderCaseDao.updateRequestHeaderCase(requestHeaderCaseEntity);
    }

    @Override
    public void deleteRequestHeaderCase(@NotNull String id) {
        requestHeaderCaseDao.deleteRequestHeaderCase(id);
    }

    @Override
    public RequestHeaderCase findOne(String id) {
        RequestHeaderCaseEntity requestHeaderCaseEntity = requestHeaderCaseDao.findRequestHeaderCase(id);

        RequestHeaderCase requestHeaderCase = BeanMapper.map(requestHeaderCaseEntity, RequestHeaderCase.class);
        return requestHeaderCase;
    }

    @Override
    public List<RequestHeaderCase> findList(List<String> idList) {
        List<RequestHeaderCaseEntity> requestHeaderCaseEntityList =  requestHeaderCaseDao.findRequestHeaderCaseList(idList);

        List<RequestHeaderCase> requestHeaderCaseList =  BeanMapper.mapList(requestHeaderCaseEntityList,RequestHeaderCase.class);
        return requestHeaderCaseList;
    }

    @Override
    public RequestHeaderCase findRequestHeaderCase(@NotNull String id) {
        RequestHeaderCase requestHeaderCase = findOne(id);

        joinTemplate.joinQuery(requestHeaderCase);
        return requestHeaderCase;
    }

    @Override
    public List<RequestHeaderCase> findAllRequestHeaderCase() {
        List<RequestHeaderCaseEntity> requestHeaderCaseEntityList =  requestHeaderCaseDao.findAllRequestHeaderCase();

        List<RequestHeaderCase> requestHeaderCaseList =  BeanMapper.mapList(requestHeaderCaseEntityList,RequestHeaderCase.class);

        joinTemplate.joinQuery(requestHeaderCaseList);
        return requestHeaderCaseList;
    }

    @Override
    public List<RequestHeaderCase> findRequestHeaderCaseList(RequestHeaderCaseQuery requestHeaderCaseQuery) {
        List<RequestHeaderCaseEntity> requestHeaderCaseEntityList = requestHeaderCaseDao.findRequestHeaderCaseList(requestHeaderCaseQuery);

        List<RequestHeaderCase> requestHeaderCaseList = BeanMapper.mapList(requestHeaderCaseEntityList,RequestHeaderCase.class);

        joinTemplate.joinQuery(requestHeaderCaseList);

        return requestHeaderCaseList;
    }

    @Override
    public Pagination<RequestHeaderCase> findRequestHeaderCasePage(RequestHeaderCaseQuery requestHeaderCaseQuery) {

        Pagination<RequestHeaderCaseEntity>  pagination = requestHeaderCaseDao.findRequestHeaderCasePage(requestHeaderCaseQuery);

        List<RequestHeaderCase> requestHeaderCaseList = BeanMapper.mapList(pagination.getDataList(),RequestHeaderCase.class);

        joinTemplate.joinQuery(requestHeaderCaseList);

        return PaginationBuilder.build(pagination,requestHeaderCaseList);
    }
}