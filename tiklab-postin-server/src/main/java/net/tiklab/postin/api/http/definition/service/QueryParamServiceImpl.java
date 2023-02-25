package net.tiklab.postin.api.http.definition.service;

import net.tiklab.postin.api.http.definition.dao.QueryParamDao;
import net.tiklab.postin.api.http.definition.entity.QueryParamEntity;
import net.tiklab.postin.api.http.definition.model.QueryParam;
import net.tiklab.postin.api.http.definition.model.QueryParamQuery;

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
public class QueryParamServiceImpl implements QueryParamService {

    @Autowired
    QueryParamDao queryParamDao;

    @Autowired
    JoinTemplate joinTemplate;

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

        joinTemplate.joinQuery(queryParam);

        return queryParam;
    }

    @Override
    public List<QueryParam> findAllQueryParam() {
        List<QueryParamEntity> queryParamEntityList =  queryParamDao.findAllQueryParam();

        List<QueryParam> queryParamList = BeanMapper.mapList(queryParamEntityList,QueryParam.class);

        joinTemplate.joinQuery(queryParamList);

        return queryParamList;
    }

    @Override
    public List<QueryParam> findQueryParamList(QueryParamQuery queryParamQuery) {
        List<QueryParamEntity> queryParamEntityList = queryParamDao.findQueryParamList(queryParamQuery);

        List<QueryParam> queryParamList = BeanMapper.mapList(queryParamEntityList,QueryParam.class);

        joinTemplate.joinQuery(queryParamList);

        return queryParamList;
    }

    @Override
    public Pagination<QueryParam> findQueryParamPage(QueryParamQuery queryParamQuery) {

        Pagination<QueryParamEntity>  pagination = queryParamDao.findQueryParamPage(queryParamQuery);

        List<QueryParam> queryParamList = BeanMapper.mapList(pagination.getDataList(),QueryParam.class);

        joinTemplate.joinQuery(queryParamList);

        return PaginationBuilder.build(pagination,queryParamList);
    }
}