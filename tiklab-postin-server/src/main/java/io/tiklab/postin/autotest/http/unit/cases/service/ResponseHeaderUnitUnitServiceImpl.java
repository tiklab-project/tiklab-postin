package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.PaginationBuilder;


import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.dao.ResponseHeaderUnitDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.ResponseHeaderUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.ResponseHeaderUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.ResponseHeaderUnitQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 响应头 服务
*/
@Service
public class ResponseHeaderUnitUnitServiceImpl implements ResponseHeaderUnitService {

    @Autowired
    ResponseHeaderUnitDao responseHeaderUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createResponseHeader(@NotNull @Valid ResponseHeaderUnit responseHeaderUnit) {
        ResponseHeaderUnitEntity responseHeaderUnitEntity = BeanMapper.map(responseHeaderUnit, ResponseHeaderUnitEntity.class);

        return responseHeaderUnitDao.createResponseHeader(responseHeaderUnitEntity);
    }

    @Override
    public void updateResponseHeader(@NotNull @Valid ResponseHeaderUnit responseHeaderUnit) {
        ResponseHeaderUnitEntity responseHeaderUnitEntity = BeanMapper.map(responseHeaderUnit, ResponseHeaderUnitEntity.class);

        responseHeaderUnitDao.updateResponseHeader(responseHeaderUnitEntity);
    }

    @Override
    public void deleteResponseHeader(@NotNull String id) {
        responseHeaderUnitDao.deleteResponseHeader(id);
    }

    @Override
    public void deleteAllResponseHeader( String caseId) {
        ResponseHeaderUnitQuery responseHeaderUnitQuery = new ResponseHeaderUnitQuery();
        responseHeaderUnitQuery.setApiUnitId(caseId);
        List<ResponseHeaderUnit> responseHeaderList = findResponseHeaderList(responseHeaderUnitQuery);
        for(ResponseHeaderUnit responseHeaderUnit : responseHeaderList){
            responseHeaderUnitDao.deleteResponseHeader(responseHeaderUnit.getId());
        }
    }

    @Override
    public ResponseHeaderUnit findOne(String id) {
        ResponseHeaderUnitEntity responseHeaderUnitEntity = responseHeaderUnitDao.findResponseHeader(id);

        ResponseHeaderUnit responseHeaderUnit = BeanMapper.map(responseHeaderUnitEntity, ResponseHeaderUnit.class);
        return responseHeaderUnit;
    }

    @Override
    public List<ResponseHeaderUnit> findList(List<String> idList) {
        List<ResponseHeaderUnitEntity> responseHeaderUnitEntityList =  responseHeaderUnitDao.findResponseHeaderList(idList);

        List<ResponseHeaderUnit> responseHeaderUnitList =  BeanMapper.mapList(responseHeaderUnitEntityList, ResponseHeaderUnit.class);
        return responseHeaderUnitList;
    }

    @Override
    public ResponseHeaderUnit findResponseHeader(@NotNull String id) {
        ResponseHeaderUnit responseHeaderUnit = findOne(id);

        joinTemplate.joinQuery(responseHeaderUnit);
        return responseHeaderUnit;
    }

    @Override
    public List<ResponseHeaderUnit> findAllResponseHeader() {
        List<ResponseHeaderUnitEntity> responseHeaderUnitEntityList =  responseHeaderUnitDao.findAllResponseHeader();

        List<ResponseHeaderUnit> responseHeaderUnitList =  BeanMapper.mapList(responseHeaderUnitEntityList, ResponseHeaderUnit.class);

        joinTemplate.joinQuery(responseHeaderUnitList);
        return responseHeaderUnitList;
    }

    @Override
    public List<ResponseHeaderUnit> findResponseHeaderList(ResponseHeaderUnitQuery responseHeaderUnitQuery) {
        List<ResponseHeaderUnitEntity> responseHeaderUnitEntityList = responseHeaderUnitDao.findResponseHeaderList(responseHeaderUnitQuery);

        List<ResponseHeaderUnit> responseHeaderUnitList = BeanMapper.mapList(responseHeaderUnitEntityList, ResponseHeaderUnit.class);

        joinTemplate.joinQuery(responseHeaderUnitList);

        return responseHeaderUnitList;
    }

    @Override
    public Pagination<ResponseHeaderUnit> findResponseHeaderPage(ResponseHeaderUnitQuery responseHeaderUnitQuery) {

        Pagination<ResponseHeaderUnitEntity>  pagination = responseHeaderUnitDao.findResponseHeaderPage(responseHeaderUnitQuery);

        List<ResponseHeaderUnit> responseHeaderUnitList = BeanMapper.mapList(pagination.getDataList(), ResponseHeaderUnit.class);

        joinTemplate.joinQuery(responseHeaderUnitList);

        return PaginationBuilder.build(pagination, responseHeaderUnitList);
    }
}