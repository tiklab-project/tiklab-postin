package com.doublekit.apibox.apimock.service;

import com.doublekit.apibox.apimock.dao.QueryParamMockDao;
import com.doublekit.apibox.apimock.entity.QueryParamMockEntity;
import com.doublekit.apibox.apimock.model.QueryParamMock;
import com.doublekit.apibox.apimock.model.QueryParamMockQuery;

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
public class QueryParamMockServiceImpl implements QueryParamMockService {

    @Autowired
    QueryParamMockDao queryParamMockDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createQueryParamMock(@NotNull @Valid QueryParamMock queryParamMock) {
        QueryParamMockEntity queryParamMockEntity = BeanMapper.map(queryParamMock, QueryParamMockEntity.class);

        return queryParamMockDao.createQueryParamMock(queryParamMockEntity);
    }

    @Override
    public void updateQueryParamMock(@NotNull @Valid QueryParamMock queryParamMock) {
        QueryParamMockEntity queryParamMockEntity = BeanMapper.map(queryParamMock, QueryParamMockEntity.class);

        queryParamMockDao.updateQueryParamMock(queryParamMockEntity);
    }

    @Override
    public void deleteQueryParamMock(@NotNull String id) {
        queryParamMockDao.deleteQueryParamMock(id);
    }

    @Override
    public QueryParamMock findQueryParamMock(@NotNull String id) {
        QueryParamMockEntity queryParamMockEntity = queryParamMockDao.findQueryParamMock(id);

        QueryParamMock queryParamMock = BeanMapper.map(queryParamMockEntity, QueryParamMock.class);

        joinQuery.queryOne(queryParamMock);

        return queryParamMock;
    }

    @Override
    public List<QueryParamMock> findAllQueryParamMock() {
        List<QueryParamMockEntity> queryParamMockEntityList =  queryParamMockDao.findAllQueryParamMock();

        List<QueryParamMock> queryParamMockList =  BeanMapper.mapList(queryParamMockEntityList,QueryParamMock.class);

        joinQuery.queryList(queryParamMockList);

        return queryParamMockList;
    }

    @Override
    public List<QueryParamMock> findQueryParamMockList(QueryParamMockQuery queryParamMockQuery) {
        List<QueryParamMockEntity> queryParamMockEntityList = queryParamMockDao.findQueryParamMockList(queryParamMockQuery);

        List<QueryParamMock> queryParamMockList = BeanMapper.mapList(queryParamMockEntityList,QueryParamMock.class);

        joinQuery.queryList(queryParamMockList);

        return queryParamMockList;
    }

    @Override
    public Pagination<QueryParamMock> findQueryParamMockPage(QueryParamMockQuery queryParamMockQuery) {

        Pagination<QueryParamMockEntity>  pagination = queryParamMockDao.findQueryParamMockPage(queryParamMockQuery);

        List<QueryParamMock> queryParamMockList = BeanMapper.mapList(pagination.getDataList(),QueryParamMock.class);

        joinQuery.queryList(queryParamMockList);

        return PaginationBuilder.build(pagination,queryParamMockList);
    }
}