package net.tiklab.postin.api.http.mock.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.postin.api.http.mock.dao.QueryParamMockDao;
import net.tiklab.postin.api.http.mock.entity.QueryParamMockEntity;
import net.tiklab.postin.api.http.mock.model.QueryParamMock;
import net.tiklab.postin.api.http.mock.model.QueryParamMockQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * mock
 * query 服务
 */
@Service
public class QueryParamMockServiceImpl implements QueryParamMockService {

    @Autowired
    QueryParamMockDao queryParamMockDao;

    @Autowired
    JoinTemplate joinTemplate;

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

        joinTemplate.joinQuery(queryParamMock);

        return queryParamMock;
    }

    @Override
    public List<QueryParamMock> findAllQueryParamMock() {
        List<QueryParamMockEntity> queryParamMockEntityList =  queryParamMockDao.findAllQueryParamMock();

        List<QueryParamMock> queryParamMockList =  BeanMapper.mapList(queryParamMockEntityList,QueryParamMock.class);

        joinTemplate.joinQuery(queryParamMockList);

        return queryParamMockList;
    }

    @Override
    public List<QueryParamMock> findQueryParamMockList(QueryParamMockQuery queryParamMockQuery) {
        List<QueryParamMockEntity> queryParamMockEntityList = queryParamMockDao.findQueryParamMockList(queryParamMockQuery);

        List<QueryParamMock> queryParamMockList = BeanMapper.mapList(queryParamMockEntityList,QueryParamMock.class);

        joinTemplate.joinQuery(queryParamMockList);

        return queryParamMockList;
    }

    @Override
    public Pagination<QueryParamMock> findQueryParamMockPage(QueryParamMockQuery queryParamMockQuery) {

        Pagination<QueryParamMockEntity>  pagination = queryParamMockDao.findQueryParamMockPage(queryParamMockQuery);

        List<QueryParamMock> queryParamMockList = BeanMapper.mapList(pagination.getDataList(),QueryParamMock.class);

        joinTemplate.joinQuery(queryParamMockList);

        return PaginationBuilder.build(pagination,queryParamMockList);
    }
}