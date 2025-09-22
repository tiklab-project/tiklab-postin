package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.postin.autotest.http.unit.cases.dao.RequestHeaderUnitDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.RequestHeaderUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCase;
import io.tiklab.postin.autotest.http.unit.cases.model.RequestHeaderUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.RequestHeaderUnitQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* RequestHeaderServiceImpl
*/
@Service
public class RequestHeaderUnitServiceImpl implements RequestHeaderUnitService {

    @Autowired
    RequestHeaderUnitDao requestHeaderUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRequestHeader(@NotNull @Valid RequestHeaderUnit requestHeaderUnit) {
        RequestHeaderUnitEntity requestHeaderUnitEntity = BeanMapper.map(requestHeaderUnit, RequestHeaderUnitEntity.class);

        return requestHeaderUnitDao.createRequestHeader(requestHeaderUnitEntity);
    }

    @Override
    public void updateRequestHeader(@NotNull @Valid RequestHeaderUnit requestHeaderUnit) {
        RequestHeaderUnitEntity requestHeaderUnitEntity = BeanMapper.map(requestHeaderUnit, RequestHeaderUnitEntity.class);

        requestHeaderUnitDao.updateRequestHeader(requestHeaderUnitEntity);
    }

    @Override
    public void deleteRequestHeader(@NotNull String id) {
        requestHeaderUnitDao.deleteRequestHeader(id);
    }

    @Override
    public void deleteAllRequestHeader(String caseId){
        RequestHeaderUnitQuery requestHeaderQuery = new RequestHeaderUnitQuery();
        requestHeaderQuery.setApiUnitId(caseId);
        List<RequestHeaderUnit> requestHeaderList = findRequestHeaderList(requestHeaderQuery);
        for(RequestHeaderUnit requestHeaderUnit:requestHeaderList){
            deleteRequestHeader(requestHeaderUnit.getId());
        }

    }


    @Override
    public RequestHeaderUnit findOne(String id) {
        RequestHeaderUnitEntity requestHeaderUnitEntity = requestHeaderUnitDao.findRequestHeader(id);

        RequestHeaderUnit requestHeaderUnit = BeanMapper.map(requestHeaderUnitEntity, RequestHeaderUnit.class);
        return requestHeaderUnit;
    }

    @Override
    public List<RequestHeaderUnit> findList(List<String> idList) {
        List<RequestHeaderUnitEntity> requestHeaderUnitEntityList =  requestHeaderUnitDao.findRequestHeaderList(idList);

        List<RequestHeaderUnit> requestHeaderUnitList =  BeanMapper.mapList(requestHeaderUnitEntityList, RequestHeaderUnit.class);
        return requestHeaderUnitList;
    }

    @Override
    public RequestHeaderUnit findRequestHeader(@NotNull String id) {
        RequestHeaderUnit requestHeaderUnit = findOne(id);

        joinTemplate.joinQuery(requestHeaderUnit);
        return requestHeaderUnit;
    }

    @Override
    public List<RequestHeaderUnit> findAllRequestHeader() {
        List<RequestHeaderUnitEntity> requestHeaderUnitEntityList =  requestHeaderUnitDao.findAllRequestHeader();

        List<RequestHeaderUnit> requestHeaderUnitList =  BeanMapper.mapList(requestHeaderUnitEntityList, RequestHeaderUnit.class);

        joinTemplate.joinQuery(requestHeaderUnitList);
        return requestHeaderUnitList;
    }

    @Override
    public List<RequestHeaderUnit> findRequestHeaderList(RequestHeaderUnitQuery requestHeaderUnitQuery) {
        List<RequestHeaderUnitEntity> requestHeaderUnitEntityList = requestHeaderUnitDao.findRequestHeaderList(requestHeaderUnitQuery);

        List<RequestHeaderUnit> requestHeaderUnitList = BeanMapper.mapList(requestHeaderUnitEntityList, RequestHeaderUnit.class);

        joinTemplate.joinQuery(requestHeaderUnitList);

        return requestHeaderUnitList;
    }

    @Override
    public Pagination<RequestHeaderUnit> findRequestHeaderPage(RequestHeaderUnitQuery requestHeaderUnitQuery) {

        Pagination<RequestHeaderUnitEntity>  pagination = requestHeaderUnitDao.findRequestHeaderPage(requestHeaderUnitQuery);

        List<RequestHeaderUnit> requestHeaderUnitList = BeanMapper.mapList(pagination.getDataList(), RequestHeaderUnit.class);

        joinTemplate.joinQuery(requestHeaderUnitList);

        return PaginationBuilder.build(pagination, requestHeaderUnitList);
    }

    /**
     *请求头拼接
     * @param apiUnitCase 用例步骤
     *
     */
    @Override
    public Map<String, String> jointHeader(ApiUnitCase apiUnitCase){
        Map headerMap = new HashMap();
        RequestHeaderUnitQuery requestHeaderUnitQuery = new RequestHeaderUnitQuery();
        requestHeaderUnitQuery.setApiUnitId(apiUnitCase.getId());
        //通过步骤id查询请步骤请求头数据
        List<RequestHeaderUnit> requestHeaderUnitList = this.findRequestHeaderList(requestHeaderUnitQuery);

        if (CollectionUtils.isNotEmpty(requestHeaderUnitList)){
            for (RequestHeaderUnit requestHeaderUnit : requestHeaderUnitList){
                //请求头属性名
                String headerName = requestHeaderUnit.getHeaderName();
                //请求头参数
                String value = requestHeaderUnit.getValue();
                headerMap.put(headerName,value);
            }
        }
        return headerMap;
    }
}