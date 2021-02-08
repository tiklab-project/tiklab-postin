package com.darthcloud.apibox.api.apimock.service;

import com.darthcloud.apibox.api.apimock.dao.QueryParamMockDao;
import com.darthcloud.apibox.api.apimock.entity.QueryParamMockPo;
import com.darthcloud.apibox.api.apimock.model.QueryParamMock;
import com.darthcloud.apibox.api.apimock.model.QueryParamMockQuery;

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
public class QueryParamMockServiceImpl implements QueryParamMockService {

    @Autowired
    QueryParamMockDao queryParamMockDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createQueryParamMock(@NotNull @Valid QueryParamMock queryParamMock) {
        QueryParamMockPo queryParamMockPo = BeanMapper.map(queryParamMock, QueryParamMockPo.class);

        return queryParamMockDao.createQueryParamMock(queryParamMockPo);
    }

    @Override
    public void updateQueryParamMock(@NotNull @Valid QueryParamMock queryParamMock) {
        QueryParamMockPo queryParamMockPo = BeanMapper.map(queryParamMock, QueryParamMockPo.class);

        queryParamMockDao.updateQueryParamMock(queryParamMockPo);
    }

    @Override
    public void deleteQueryParamMock(@NotNull String id) {
        queryParamMockDao.deleteQueryParamMock(id);
    }

    @Override
    public QueryParamMock findQueryParamMock(@NotNull String id) {
        QueryParamMockPo queryParamMockPo = queryParamMockDao.findQueryParamMock(id);

        QueryParamMock queryParamMock = BeanMapper.map(queryParamMockPo, QueryParamMock.class);

        joinQuery.queryOne(queryParamMock);

        return queryParamMock;
    }

    @Override
    public List<QueryParamMock> findAllQueryParamMock() {
        List<QueryParamMockPo> queryParamMockPoList =  queryParamMockDao.findAllQueryParamMock();

        List<QueryParamMock> queryParamMockList =  BeanMapper.mapList(queryParamMockPoList,QueryParamMock.class);

        joinQuery.queryList(queryParamMockList);

        return queryParamMockList;
    }

    @Override
    public List<QueryParamMock> findQueryParamMockList(QueryParamMockQuery queryParamMockQuery) {
        List<QueryParamMockPo> queryParamMockPoList = queryParamMockDao.findQueryParamMockList(queryParamMockQuery);

        List<QueryParamMock> queryParamMockList = BeanMapper.mapList(queryParamMockPoList,QueryParamMock.class);

        joinQuery.queryList(queryParamMockList);

        return queryParamMockList;
    }

    @Override
    public Pagination<List<QueryParamMock>> findQueryParamMockPage(QueryParamMockQuery queryParamMockQuery) {
        Pagination<List<QueryParamMock>> pg = new Pagination<>();

        Pagination<List<QueryParamMockPo>>  pagination = queryParamMockDao.findQueryParamMockPage(queryParamMockQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<QueryParamMock> queryParamMockList = BeanMapper.mapList(pagination.getDataList(),QueryParamMock.class);

        joinQuery.queryList(queryParamMockList);

        pg.setDataList(queryParamMockList);
        return pg;
    }
}