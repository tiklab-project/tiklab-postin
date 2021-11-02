package com.doublekit.apibox.apidef.service;

import com.doublekit.apibox.apidef.dao.QueryParamDao;
import com.doublekit.apibox.apidef.entity.QueryParamEntity;
import com.doublekit.apibox.apidef.model.QueryParam;
import com.doublekit.apibox.apidef.model.QueryParamQuery;

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
public class QueryParamServiceImpl implements QueryParamService {

    @Autowired
    QueryParamDao queryParamDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createQueryParam(@NotNull @Valid QueryParam queryParam) {
        QueryParamEntity queryParamEntity = BeanMapper.map(queryParam, QueryParamEntity.class);

        return queryParamDao.createQueryParam(queryParamEntity);
    }

    @Override
    public void updateQueryParam(@NotNull @Valid QueryParam queryParam) {
        QueryParamEntity queryParamEntity = BeanMapper.map(queryParam, QueryParamEntity.class);

        queryParamDao.updateQueryParam(queryParamEntity);
    }

    @Override
    public void deleteQueryParam(@NotNull String id) {
        queryParamDao.deleteQueryParam(id);
    }

    @Override
    public QueryParam findQueryParam(@NotNull String id) {
        QueryParamEntity queryParamEntity = queryParamDao.findQueryParam(id);

        QueryParam queryParam = BeanMapper.map(queryParamEntity, QueryParam.class);

        joinQuery.queryOne(queryParam);

        return queryParam;
    }

    @Override
    public List<QueryParam> findAllQueryParam() {
        List<QueryParamEntity> queryParamEntityList =  queryParamDao.findAllQueryParam();

        List<QueryParam> queryParamList = BeanMapper.mapList(queryParamEntityList,QueryParam.class);

        joinQuery.queryList(queryParamList);

        return queryParamList;
    }

    @Override
    public List<QueryParam> findQueryParamList(QueryParamQuery queryParamQuery) {
        List<QueryParamEntity> queryParamEntityList = queryParamDao.findQueryParamList(queryParamQuery);

        List<QueryParam> queryParamList = BeanMapper.mapList(queryParamEntityList,QueryParam.class);

        joinQuery.queryList(queryParamList);

        return queryParamList;
    }

    @Override
    public Pagination<QueryParam> findQueryParamPage(QueryParamQuery queryParamQuery) {

        Pagination<QueryParamEntity>  pagination = queryParamDao.findQueryParamPage(queryParamQuery);

        List<QueryParam> queryParamList = BeanMapper.mapList(pagination.getDataList(),QueryParam.class);

        joinQuery.queryList(queryParamList);

        return PaginationBuilder.build(pagination,queryParamList);
    }
}