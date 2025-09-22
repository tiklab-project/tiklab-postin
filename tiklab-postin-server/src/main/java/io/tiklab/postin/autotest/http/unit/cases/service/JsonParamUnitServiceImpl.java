package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.PaginationBuilder;


import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.dao.JsonParamUnitDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.JsonParamUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.JsonParamUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.JsonParamUnitQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 请求中json  服务
*/
@Service
public class JsonParamUnitServiceImpl implements JsonParamUnitService {

    @Autowired
    JsonParamUnitDao jsonParamUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createJsonParam(@NotNull @Valid JsonParamUnit jsonParamUnit) {
        JsonParamUnitEntity jsonParamUnitEntity = BeanMapper.map(jsonParamUnit, JsonParamUnitEntity.class);

        return jsonParamUnitDao.createJsonParam(jsonParamUnitEntity);
    }

    @Override
    public void updateJsonParam(@NotNull @Valid JsonParamUnit jsonParamUnit) {
        JsonParamUnitEntity jsonParamUnitEntity = BeanMapper.map(jsonParamUnit, JsonParamUnitEntity.class);

        jsonParamUnitDao.updateJsonParam(jsonParamUnitEntity);
    }

    @Override
    public void deleteJsonParam(@NotNull String id) {
        jsonParamUnitDao.deleteJsonParam(id);
    }

    @Override
    public void deleteAllJsonParam( String caseId) {
        JsonParamUnitQuery jsonParamUnitQuery = new JsonParamUnitQuery();
        jsonParamUnitQuery.setApiUnitId(caseId);
        List<JsonParamUnit> jsonParamList = findJsonParamList(jsonParamUnitQuery);
        for (JsonParamUnit jsonParamUnit : jsonParamList) {
            deleteJsonParam(jsonParamUnit.getId());
        }
    }


    @Override
    public JsonParamUnit findOne(String id) {
        JsonParamUnitEntity jsonParamUnitEntity = jsonParamUnitDao.findJsonParam(id);

        JsonParamUnit jsonParamUnit = BeanMapper.map(jsonParamUnitEntity, JsonParamUnit.class);
        return jsonParamUnit;
    }

    @Override
    public List<JsonParamUnit> findList(List<String> idList) {
        List<JsonParamUnitEntity> jsonParamUnitEntityList =  jsonParamUnitDao.findJsonParamList(idList);

        List<JsonParamUnit> jsonParamUnitList =  BeanMapper.mapList(jsonParamUnitEntityList, JsonParamUnit.class);
        return jsonParamUnitList;
    }

    @Override
    public JsonParamUnit findJsonParam(@NotNull String id) {
        JsonParamUnit jsonParamUnit = findOne(id);

        joinTemplate.joinQuery(jsonParamUnit);
        return jsonParamUnit;
    }

    @Override
    public List<JsonParamUnit> findAllJsonParam() {
        List<JsonParamUnitEntity> jsonParamUnitEntityList =  jsonParamUnitDao.findAllJsonParam();

        List<JsonParamUnit> jsonParamUnitList =  BeanMapper.mapList(jsonParamUnitEntityList, JsonParamUnit.class);

        joinTemplate.joinQuery(jsonParamUnitList);
        return jsonParamUnitList;
    }

    @Override
    public List<JsonParamUnit> findJsonParamList(JsonParamUnitQuery jsonParamUnitQuery) {
        List<JsonParamUnitEntity> jsonParamUnitEntityList = jsonParamUnitDao.findJsonParamList(jsonParamUnitQuery);

        List<JsonParamUnit> jsonParamUnitList = BeanMapper.mapList(jsonParamUnitEntityList, JsonParamUnit.class);

        joinTemplate.joinQuery(jsonParamUnitList);

        return jsonParamUnitList;
    }

    @Override
    public Pagination<JsonParamUnit> findJsonParamPage(JsonParamUnitQuery jsonParamUnitQuery) {

        Pagination<JsonParamUnitEntity>  pagination = jsonParamUnitDao.findJsonParamPage(jsonParamUnitQuery);

        List<JsonParamUnit> jsonParamUnitList = BeanMapper.mapList(pagination.getDataList(), JsonParamUnit.class);

        joinTemplate.joinQuery(jsonParamUnitList);

        return PaginationBuilder.build(pagination, jsonParamUnitList);
    }



}