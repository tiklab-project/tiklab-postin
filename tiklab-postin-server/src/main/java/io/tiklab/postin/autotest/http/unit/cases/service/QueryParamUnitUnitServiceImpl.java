package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.postin.autotest.http.unit.cases.dao.QueryParamUnitDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.QueryParamsUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCase;
import io.tiklab.postin.autotest.http.unit.cases.model.QueryParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.QueryParamUnitQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* query 服务
*/
@Service
public class QueryParamUnitUnitServiceImpl implements QueryParamUnitService {

    @Autowired
    QueryParamUnitDao queryParamUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createQueryParam(@NotNull @Valid QueryParamUnit queryParamUnit) {
        QueryParamsUnitEntity queryParamsUnitEntity = BeanMapper.map(queryParamUnit, QueryParamsUnitEntity.class);

        return queryParamUnitDao.createQueryParam(queryParamsUnitEntity);
    }

    @Override
    public void updateQueryParam(@NotNull @Valid QueryParamUnit queryParamUnit) {
        QueryParamsUnitEntity queryParamsUnitEntity = BeanMapper.map(queryParamUnit, QueryParamsUnitEntity.class);

        queryParamUnitDao.updateQueryParam(queryParamsUnitEntity);
    }

    @Override
    public void deleteQueryParam(@NotNull String id) {
        queryParamUnitDao.deleteQueryParam(id);
    }

    @Override
    public void deleteAllQueryParam( String caseId) {
        QueryParamUnitQuery queryParamUnitQuery = new QueryParamUnitQuery();
        queryParamUnitQuery.setApiUnitId(caseId);
        List<QueryParamUnit> queryParamList = findQueryParamList(queryParamUnitQuery);
        for(QueryParamUnit queryParamUnit : queryParamList){
            deleteQueryParam(queryParamUnit.getId());
        }
    }


    @Override
    public QueryParamUnit findOne(String id) {
        QueryParamsUnitEntity queryParamsUnitEntity = queryParamUnitDao.findQueryParam(id);

        QueryParamUnit queryParamUnit = BeanMapper.map(queryParamsUnitEntity, QueryParamUnit.class);
        return queryParamUnit;
    }

    @Override
    public List<QueryParamUnit> findList(List<String> idList) {
        List<QueryParamsUnitEntity> queryParamsUnitEntityList =  queryParamUnitDao.findQueryParamList(idList);

        List<QueryParamUnit> queryParamUnitList =  BeanMapper.mapList(queryParamsUnitEntityList, QueryParamUnit.class);
        return queryParamUnitList;
    }

    @Override
    public QueryParamUnit findQueryParam(@NotNull String id) {
        QueryParamUnit queryParamUnit = findOne(id);

        joinTemplate.joinQuery(queryParamUnit);
        return queryParamUnit;
    }

    @Override
    public List<QueryParamUnit> findAllQueryParam() {
        List<QueryParamsUnitEntity> queryParamsUnitEntityList =  queryParamUnitDao.findAllQueryParam();

        List<QueryParamUnit> queryParamUnitList =  BeanMapper.mapList(queryParamsUnitEntityList, QueryParamUnit.class);

        joinTemplate.joinQuery(queryParamUnitList);
        return queryParamUnitList;
    }

    @Override
    public List<QueryParamUnit> findQueryParamList(QueryParamUnitQuery queryParamUnitQuery) {
        List<QueryParamsUnitEntity> queryParamsUnitEntityList = queryParamUnitDao.findQueryParamList(queryParamUnitQuery);

        List<QueryParamUnit> queryParamUnitList = BeanMapper.mapList(queryParamsUnitEntityList, QueryParamUnit.class);

        joinTemplate.joinQuery(queryParamUnitList);

        return queryParamUnitList;
    }

    @Override
    public Pagination<QueryParamUnit> findQueryParamPage(QueryParamUnitQuery queryParamUnitQuery) {

        Pagination<QueryParamsUnitEntity>  pagination = queryParamUnitDao.findQueryParamPage(queryParamUnitQuery);

        List<QueryParamUnit> queryParamUnitList = BeanMapper.mapList(pagination.getDataList(), QueryParamUnit.class);

        joinTemplate.joinQuery(queryParamUnitList);

        return PaginationBuilder.build(pagination, queryParamUnitList);
    }

    /**
     *请求param参数
     * @param
     */
    @Override
    public String jointParam(ApiUnitCase apiUnitCase){
        String param = "";
        QueryParamUnitQuery queryParamUnitQuery = new QueryParamUnitQuery();
        queryParamUnitQuery.setApiUnitId(apiUnitCase.getId());
        List<QueryParamUnit> queryParamUnitList = this.findQueryParamList(queryParamUnitQuery);

        if (CollectionUtils.isNotEmpty(queryParamUnitList)){
            for (int i = 0; i < queryParamUnitList.size(); i++) {
                QueryParamUnit queryParamUnit = queryParamUnitList.get(i);
                param += queryParamUnit.getParamName() + "=" + queryParamUnit.getValue();
                if (i < queryParamUnitList.size() - 1) {
                    param += "&";
                }
            }
        }

        return param;
    }
}