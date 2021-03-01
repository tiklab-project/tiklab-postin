package com.darthcloud.apibox.apitest.service;

import com.darthcloud.apibox.apitest.dao.RequestHeaderCaseDao;
import com.darthcloud.apibox.apitest.entity.RequestHeaderCasePo;

import com.darthcloud.apibox.apitest.model.RequestHeaderCase;
import com.darthcloud.apibox.apitest.model.RequestHeaderCaseQuery;
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
public class RequestHeaderCaseServiceImpl implements RequestHeaderCaseService {

    @Autowired
    RequestHeaderCaseDao requestHeaderCaseDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createRequestHeaderCase(@NotNull @Valid RequestHeaderCase requestHeaderCase) {
        RequestHeaderCasePo requestHeaderCasePo = BeanMapper.map(requestHeaderCase, RequestHeaderCasePo.class);

        return requestHeaderCaseDao.createRequestHeaderCase(requestHeaderCasePo);
    }

    @Override
    public void updateRequestHeaderCase(@NotNull @Valid RequestHeaderCase requestHeaderCase) {
        RequestHeaderCasePo requestHeaderCasePo = BeanMapper.map(requestHeaderCase, RequestHeaderCasePo.class);

        requestHeaderCaseDao.updateRequestHeaderCase(requestHeaderCasePo);
    }

    @Override
    public void deleteRequestHeaderCase(@NotNull String id) {
        requestHeaderCaseDao.deleteRequestHeaderCase(id);
    }

    @Override
    public RequestHeaderCase findOne(String id) {
        RequestHeaderCasePo requestHeaderCasePo = requestHeaderCaseDao.findRequestHeaderCase(id);

        RequestHeaderCase requestHeaderCase = BeanMapper.map(requestHeaderCasePo, RequestHeaderCase.class);
        return requestHeaderCase;
    }

    @Override
    public List<RequestHeaderCase> findList(List<String> idList) {
        List<RequestHeaderCasePo> requestHeaderCasePoList =  requestHeaderCaseDao.findRequestHeaderCaseList(idList);

        List<RequestHeaderCase> requestHeaderCaseList =  BeanMapper.mapList(requestHeaderCasePoList,RequestHeaderCase.class);
        return requestHeaderCaseList;
    }

    @Override
    public RequestHeaderCase findRequestHeaderCase(@NotNull String id) {
        RequestHeaderCase requestHeaderCase = findOne(id);

        joinQuery.queryOne(requestHeaderCase);
        return requestHeaderCase;
    }

    @Override
    public List<RequestHeaderCase> findAllRequestHeaderCase() {
        List<RequestHeaderCasePo> requestHeaderCasePoList =  requestHeaderCaseDao.findAllRequestHeaderCase();

        List<RequestHeaderCase> requestHeaderCaseList =  BeanMapper.mapList(requestHeaderCasePoList,RequestHeaderCase.class);

        joinQuery.queryList(requestHeaderCaseList);
        return requestHeaderCaseList;
    }

    @Override
    public List<RequestHeaderCase> findRequestHeaderCaseList(RequestHeaderCaseQuery requestHeaderCaseQuery) {
        List<RequestHeaderCasePo> requestHeaderCasePoList = requestHeaderCaseDao.findRequestHeaderCaseList(requestHeaderCaseQuery);

        List<RequestHeaderCase> requestHeaderCaseList = BeanMapper.mapList(requestHeaderCasePoList,RequestHeaderCase.class);

        joinQuery.queryList(requestHeaderCaseList);

        return requestHeaderCaseList;
    }

    @Override
    public Pagination<List<RequestHeaderCase>> findRequestHeaderCasePage(RequestHeaderCaseQuery requestHeaderCaseQuery) {
        Pagination<List<RequestHeaderCase>> pg = new Pagination<>();

        Pagination<List<RequestHeaderCasePo>>  pagination = requestHeaderCaseDao.findRequestHeaderCasePage(requestHeaderCaseQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<RequestHeaderCase> requestHeaderCaseList = BeanMapper.mapList(pagination.getDataList(),RequestHeaderCase.class);

        joinQuery.queryList(requestHeaderCaseList);

        pg.setDataList(requestHeaderCaseList);
        return pg;
    }
}