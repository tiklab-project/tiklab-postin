package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.PaginationBuilder;


import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.dao.ResponseResultUnitDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.ResponseResultUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.ResponseResultUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.ResponseResultUnitQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* 响应结果 服务
*/
@Service
public class ResponseResultUnitUnitServiceImpl implements ResponseResultUnitService {

    @Autowired
    ResponseResultUnitDao responseResultUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createResponseResult(@NotNull @Valid ResponseResultUnit responseResultUnit) {
        ResponseResultUnitEntity responseResultUnitEntity = BeanMapper.map(responseResultUnit, ResponseResultUnitEntity.class);
        responseResultUnitEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return responseResultUnitDao.createResponseResult(responseResultUnitEntity);
    }

    @Override
    public void updateResponseResult(@NotNull @Valid ResponseResultUnit responseResultUnit) {
        ResponseResultUnitEntity responseResultUnitEntity = BeanMapper.map(responseResultUnit, ResponseResultUnitEntity.class);

        ResponseResultUnit isExist = findResponseResult(responseResultUnit.getId());
        if(isExist==null){
            createResponseResult(responseResultUnit);
        }
        responseResultUnitDao.updateResponseResult(responseResultUnitEntity);
    }

    @Override
    public void deleteResponseResult(@NotNull String id) {
        responseResultUnitDao.deleteResponseResult(id);
    }

    @Override
    public ResponseResultUnit findOne(String id) {
        ResponseResultUnitEntity responseResultUnitEntity = responseResultUnitDao.findResponseResult(id);

        ResponseResultUnit responseResultUnit = BeanMapper.map(responseResultUnitEntity, ResponseResultUnit.class);
        return responseResultUnit;
    }

    @Override
    public List<ResponseResultUnit> findList(List<String> idList) {
        List<ResponseResultUnitEntity> responseResultUnitEntityList =  responseResultUnitDao.findResponseResultList(idList);

        List<ResponseResultUnit> responseResultUnitList =  BeanMapper.mapList(responseResultUnitEntityList, ResponseResultUnit.class);
        return responseResultUnitList;
    }

    @Override
    public ResponseResultUnit findResponseResult(@NotNull String id) {
        ResponseResultUnit responseResultUnit = findOne(id);

        joinTemplate.joinQuery(responseResultUnit);
        return responseResultUnit;
    }

    @Override
    public List<ResponseResultUnit> findAllResponseResult() {
        List<ResponseResultUnitEntity> responseResultUnitEntityList =  responseResultUnitDao.findAllResponseResult();

        List<ResponseResultUnit> responseResultUnitList =  BeanMapper.mapList(responseResultUnitEntityList, ResponseResultUnit.class);

        joinTemplate.joinQuery(responseResultUnitList);
        return responseResultUnitList;
    }

    @Override
    public List<ResponseResultUnit> findResponseResultList(ResponseResultUnitQuery responseResultUnitQuery) {
        List<ResponseResultUnitEntity> responseResultUnitEntityList = responseResultUnitDao.findResponseResultList(responseResultUnitQuery);

        List<ResponseResultUnit> responseResultUnitList = BeanMapper.mapList(responseResultUnitEntityList, ResponseResultUnit.class);

        joinTemplate.joinQuery(responseResultUnitList);

        return responseResultUnitList;
    }

    @Override
    public Pagination<ResponseResultUnit> findResponseResultPage(ResponseResultUnitQuery responseResultUnitQuery) {

        Pagination<ResponseResultUnitEntity>  pagination = responseResultUnitDao.findResponseResultPage(responseResultUnitQuery);

        List<ResponseResultUnit> responseResultUnitList = BeanMapper.mapList(pagination.getDataList(), ResponseResultUnit.class);

        joinTemplate.joinQuery(responseResultUnitList);

        return PaginationBuilder.build(pagination, responseResultUnitList);
    }
}