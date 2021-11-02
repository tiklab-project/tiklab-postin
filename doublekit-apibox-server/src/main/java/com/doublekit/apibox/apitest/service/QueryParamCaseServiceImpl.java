package com.doublekit.apibox.apitest.service;

import com.doublekit.apibox.apitest.dao.QueryParamCaseDao;
import com.doublekit.apibox.apitest.entity.QueryParamCaseEntity;

import com.doublekit.apibox.apitest.model.QueryParamCase;
import com.doublekit.apibox.apitest.model.QueryParamCaseQuery;
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
public class QueryParamCaseServiceImpl implements QueryParamCaseService {

    @Autowired
    QueryParamCaseDao queryParamCaseDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createQueryParamCase(@NotNull @Valid QueryParamCase queryParamCase) {
        QueryParamCaseEntity queryParamCaseEntity = BeanMapper.map(queryParamCase, QueryParamCaseEntity.class);

        return queryParamCaseDao.createQueryParamCase(queryParamCaseEntity);
    }

    @Override
    public void updateQueryParamCase(@NotNull @Valid QueryParamCase queryParamCase) {
        QueryParamCaseEntity queryParamCaseEntity = BeanMapper.map(queryParamCase, QueryParamCaseEntity.class);

        queryParamCaseDao.updateQueryParamCase(queryParamCaseEntity);
    }

    @Override
    public void deleteQueryParamCase(@NotNull String id) {
        queryParamCaseDao.deleteQueryParamCase(id);
    }

    @Override
    public QueryParamCase findOne(String id) {
        QueryParamCaseEntity queryParamCaseEntity = queryParamCaseDao.findQueryParamCase(id);

        QueryParamCase queryParamCase = BeanMapper.map(queryParamCaseEntity, QueryParamCase.class);
        return queryParamCase;
    }

    @Override
    public List<QueryParamCase> findList(List<String> idList) {
        List<QueryParamCaseEntity> queryParamCaseEntityList =  queryParamCaseDao.findQueryParamCaseList(idList);

        List<QueryParamCase> queryParamCaseList =  BeanMapper.mapList(queryParamCaseEntityList,QueryParamCase.class);
        return queryParamCaseList;
    }

    @Override
    public QueryParamCase findQueryParamCase(@NotNull String id) {
        QueryParamCase queryParamCase = findOne(id);

        joinQuery.queryOne(queryParamCase);
        return queryParamCase;
    }

    @Override
    public List<QueryParamCase> findAllQueryParamCase() {
        List<QueryParamCaseEntity> queryParamCaseEntityList =  queryParamCaseDao.findAllQueryParamCase();

        List<QueryParamCase> queryParamCaseList =  BeanMapper.mapList(queryParamCaseEntityList,QueryParamCase.class);

        joinQuery.queryList(queryParamCaseList);
        return queryParamCaseList;
    }

    @Override
    public List<QueryParamCase> findQueryParamCaseList(QueryParamCaseQuery queryParamCaseQuery) {
        List<QueryParamCaseEntity> queryParamCaseEntityList = queryParamCaseDao.findQueryParamCaseList(queryParamCaseQuery);

        List<QueryParamCase> queryParamCaseList = BeanMapper.mapList(queryParamCaseEntityList,QueryParamCase.class);

        joinQuery.queryList(queryParamCaseList);

        return queryParamCaseList;
    }

    @Override
    public Pagination<QueryParamCase> findQueryParamCasePage(QueryParamCaseQuery queryParamCaseQuery) {
        Pagination<QueryParamCase> pg = new Pagination<>();

        Pagination<QueryParamCaseEntity>  pagination = queryParamCaseDao.findQueryParamCasePage(queryParamCaseQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<QueryParamCase> queryParamCaseList = BeanMapper.mapList(pagination.getDataList(),QueryParamCase.class);

        joinQuery.queryList(queryParamCaseList);

        pg.setDataList(queryParamCaseList);
        return pg;
    }
}