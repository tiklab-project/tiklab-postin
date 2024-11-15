package io.tiklab.postin.api.http.test.cases.service;

import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.postin.api.http.test.cases.dao.RequestCaseDao;
import io.tiklab.postin.api.http.test.cases.entity.RequestCaseEntity;
import io.tiklab.postin.api.http.test.cases.model.RequestCase;
import io.tiklab.postin.api.http.test.cases.model.RequestCaseQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RequestCaseServiceImpl
*/
@Service
public class RequestCaseServiceImpl implements RequestCaseService {

    @Autowired
    RequestCaseDao requestCaseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRequestCase(@NotNull @Valid RequestCase requestCase) {
        RequestCaseEntity requestCaseEntity = BeanMapper.map(requestCase, RequestCaseEntity.class);

        return requestCaseDao.createRequestCase(requestCaseEntity);
    }

    @Override
    public void updateRequestCase(@NotNull @Valid RequestCase requestCase) {
        RequestCaseEntity requestCaseEntity = BeanMapper.map(requestCase, RequestCaseEntity.class);

        requestCaseDao.updateRequestCase(requestCaseEntity);
    }

    @Override
    public void deleteRequestCase(@NotNull String id) {
        requestCaseDao.deleteRequestCase(id);
    }

    @Override
    public RequestCase findOne(String id) {
        RequestCaseEntity requestCaseEntity = requestCaseDao.findRequestCase(id);

        RequestCase requestCase = BeanMapper.map(requestCaseEntity, RequestCase.class);
        return requestCase;
    }

    @Override
    public List<RequestCase> findList(List<String> idList) {
        List<RequestCaseEntity> requestCaseEntityList =  requestCaseDao.findRequestCaseList(idList);

        List<RequestCase> requestCaseList =  BeanMapper.mapList(requestCaseEntityList,RequestCase.class);
        return requestCaseList;
    }

    @Override
    public RequestCase findRequestCase(@NotNull String id) {
        RequestCase requestCase = findOne(id);

        joinTemplate.joinQuery(requestCase);

        return requestCase;
    }

    @Override
    public List<RequestCase> findAllRequestCase() {
        List<RequestCaseEntity> requestCaseEntityList =  requestCaseDao.findAllRequestCase();

        List<RequestCase> requestCaseList =  BeanMapper.mapList(requestCaseEntityList,RequestCase.class);

        joinTemplate.joinQuery(requestCaseList);

        return requestCaseList;
    }

    @Override
    public List<RequestCase> findRequestCaseList(RequestCaseQuery requestCaseQuery) {
        List<RequestCaseEntity> requestCaseEntityList = requestCaseDao.findRequestCaseList(requestCaseQuery);

        List<RequestCase> requestCaseList = BeanMapper.mapList(requestCaseEntityList,RequestCase.class);

        joinTemplate.joinQuery(requestCaseList);

        return requestCaseList;
    }

    @Override
    public Pagination<RequestCase> findRequestCasePage(RequestCaseQuery requestCaseQuery) {
        Pagination<RequestCaseEntity>  pagination = requestCaseDao.findRequestCasePage(requestCaseQuery);

        List<RequestCase> requestCaseList = BeanMapper.mapList(pagination.getDataList(),RequestCase.class);

        joinTemplate.joinQuery(requestCaseList);

        return PaginationBuilder.build(pagination,requestCaseList);
    }
}