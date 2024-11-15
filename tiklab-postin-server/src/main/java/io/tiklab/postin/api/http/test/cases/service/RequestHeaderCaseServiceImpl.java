package io.tiklab.postin.api.http.test.cases.service;

import io.tiklab.postin.api.http.test.cases.dao.RequestHeaderCaseDao;
import io.tiklab.postin.api.http.test.cases.entity.RequestHeaderCaseEntity;

import io.tiklab.postin.api.http.test.cases.model.RequestHeaderCase;
import io.tiklab.postin.api.http.test.cases.model.RequestHeaderCaseQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
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