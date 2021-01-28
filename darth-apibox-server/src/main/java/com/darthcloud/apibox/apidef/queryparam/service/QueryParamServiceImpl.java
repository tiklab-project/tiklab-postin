package com.darthcloud.apibox.apidef.queryparam.service;

import com.darthcloud.apibox.apidef.queryparam.dao.QueryParamDao;
import com.darthcloud.apibox.apidef.queryparam.entity.QueryParamPo;
import com.darthcloud.apibox.apidef.queryparam.model.QueryParam;
import com.darthcloud.apibox.apidef.queryparam.model.QueryParamQuery;

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
public class QueryParamServiceImpl implements QueryParamService {

    @Autowired
    QueryParamDao queryParamDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createQueryParam(@NotNull @Valid QueryParam queryParam) {
        QueryParamPo queryParamPo = BeanMapper.map(queryParam, QueryParamPo.class);

        return queryParamDao.createQueryParam(queryParamPo);
    }

    @Override
    public void updateQueryParam(@NotNull @Valid QueryParam queryParam) {
        QueryParamPo queryParamPo = BeanMapper.map(queryParam, QueryParamPo.class);

        queryParamDao.updateQueryParam(queryParamPo);
    }

    @Override
    public void deleteQueryParam(@NotNull String id) {
        queryParamDao.deleteQueryParam(id);
    }

    @Override
    public QueryParam findQueryParam(@NotNull String id) {
        QueryParamPo queryParamPo = queryParamDao.findQueryParam(id);

        QueryParam queryParam = BeanMapper.map(queryParamPo, QueryParam.class);

        joinQuery.queryOne(queryParam);

        return queryParam;
    }

    @Override
    public List<QueryParam> findAllQueryParam() {
        List<QueryParamPo> queryParamPoList =  queryParamDao.findAllQueryParam();

        List<QueryParam> queryParamList = BeanMapper.mapList(queryParamPoList,QueryParam.class);

        joinQuery.queryList(queryParamList);

        return queryParamList;
    }

    @Override
    public List<QueryParam> findQueryParamList(QueryParamQuery queryParamQuery) {
        List<QueryParamPo> queryParamPoList = queryParamDao.findQueryParamList(queryParamQuery);

        List<QueryParam> queryParamList = BeanMapper.mapList(queryParamPoList,QueryParam.class);

        joinQuery.queryList(queryParamList);

        return queryParamList;
    }

    @Override
    public Pagination<List<QueryParam>> findQueryParamPage(QueryParamQuery queryParamQuery) {
        Pagination<List<QueryParam>> pg = new Pagination<>();

        Pagination<List<QueryParamPo>>  pagination = queryParamDao.findQueryParamPage(queryParamQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<QueryParam> queryParamList = BeanMapper.mapList(pagination.getDataList(),QueryParam.class);

        joinQuery.queryList(queryParamList);

        pg.setDataList(queryParamList);
        return pg;
    }
}