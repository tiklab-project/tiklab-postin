package io.tiklab.postin.api.apix.service;

import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.apix.service.QueryParamService;
import io.tiklab.postin.api.apix.dao.QueryParamDao;
import io.tiklab.postin.api.apix.entity.QueryParamEntity;
import io.tiklab.postin.api.apix.model.QueryParam;
import io.tiklab.postin.api.apix.model.QueryParamQuery;

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
 * 定义
 * http协议
 * query 服务
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
    public void deleteAllQueryParam(String id) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(QueryParamEntity.class)
                .eq("apiId", id)
                .get();
        queryParamDao.deleteQueryParamList(deleteCondition);
    }

    @Override
    public QueryParam findQueryParam(@NotNull String id) {
        QueryParamEntity queryParamEntity = queryParamDao.findQueryParam(id);

        QueryParam queryParam = BeanMapper.map(queryParamEntity, QueryParam.class);

        return queryParam;
    }

    @Override
    public List<QueryParam> findAllQueryParam() {
        List<QueryParamEntity> queryParamEntityList =  queryParamDao.findAllQueryParam();

        List<QueryParam> queryParamList = BeanMapper.mapList(queryParamEntityList,QueryParam.class);

        return queryParamList;
    }

    @Override
    public List<QueryParam> findQueryParamList(QueryParamQuery queryParamQuery) {
        List<QueryParamEntity> queryParamEntityList = queryParamDao.findQueryParamList(queryParamQuery);

        List<QueryParam> queryParamList = BeanMapper.mapList(queryParamEntityList,QueryParam.class);

        return queryParamList;
    }

    @Override
    public Pagination<QueryParam> findQueryParamPage(QueryParamQuery queryParamQuery) {

        Pagination<QueryParamEntity>  pagination = queryParamDao.findQueryParamPage(queryParamQuery);

        List<QueryParam> queryParamList = BeanMapper.mapList(pagination.getDataList(),QueryParam.class);

        return PaginationBuilder.build(pagination,queryParamList);
    }
}