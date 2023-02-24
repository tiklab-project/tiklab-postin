package net.tiklab.postin.apidef.http.test.httpcase.service;

import net.tiklab.postin.apidef.http.test.httpcase.dao.QueryParamCaseDao;
import net.tiklab.postin.apidef.http.test.httpcase.entity.QueryParamCaseEntity;

import net.tiklab.postin.apidef.http.test.httpcase.model.QueryParamCase;
import net.tiklab.postin.apidef.http.test.httpcase.model.QueryParamCaseQuery;
import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务业务处理
*/
@Service
public class QueryParamCaseServiceImpl implements QueryParamCaseService {

    @Autowired
    QueryParamCaseDao queryParamCaseDao;

    @Autowired
    JoinTemplate joinTemplate;

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

        joinTemplate.joinQuery(queryParamCase);
        return queryParamCase;
    }

    @Override
    public List<QueryParamCase> findAllQueryParamCase() {
        List<QueryParamCaseEntity> queryParamCaseEntityList =  queryParamCaseDao.findAllQueryParamCase();

        List<QueryParamCase> queryParamCaseList =  BeanMapper.mapList(queryParamCaseEntityList,QueryParamCase.class);

        joinTemplate.joinQuery(queryParamCaseList);
        return queryParamCaseList;
    }

    @Override
    public List<QueryParamCase> findQueryParamCaseList(QueryParamCaseQuery queryParamCaseQuery) {
        List<QueryParamCaseEntity> queryParamCaseEntityList = queryParamCaseDao.findQueryParamCaseList(queryParamCaseQuery);

        List<QueryParamCase> queryParamCaseList = BeanMapper.mapList(queryParamCaseEntityList,QueryParamCase.class);

        joinTemplate.joinQuery(queryParamCaseList);

        return queryParamCaseList;
    }

    @Override
    public Pagination<QueryParamCase> findQueryParamCasePage(QueryParamCaseQuery queryParamCaseQuery) {

        Pagination<QueryParamCaseEntity>  pagination = queryParamCaseDao.findQueryParamCasePage(queryParamCaseQuery);

        List<QueryParamCase> queryParamCaseList = BeanMapper.mapList(pagination.getDataList(),QueryParamCase.class);

        joinTemplate.joinQuery(queryParamCaseList);

        return PaginationBuilder.build(pagination,queryParamCaseList);
    }
}