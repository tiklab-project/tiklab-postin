package com.doublekit.apibox.apidef.service;

import com.doublekit.apibox.apidef.dao.QueryParamDao;
import com.doublekit.apibox.apidef.entity.QueryParamPo;
import com.doublekit.apibox.apidef.model.QueryParam;
import com.doublekit.apibox.apidef.model.QueryParamQuery;

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
public class QueryParamServiceImpl implements QueryParamService {

    @Autowired
    QueryParamDao queryParamDao;

    @Autowired
    JoinTemplate joinQuery;

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
    public Pagination<QueryParam> findQueryParamPage(QueryParamQuery queryParamQuery) {
        Pagination<QueryParam> pg = new Pagination<>();

        Pagination<QueryParamPo>  pagination = queryParamDao.findQueryParamPage(queryParamQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<QueryParam> queryParamList = BeanMapper.mapList(pagination.getDataList(),QueryParam.class);

        joinQuery.queryList(queryParamList);

        pg.setDataList(queryParamList);
        return pg;
    }
}