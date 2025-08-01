package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.PaginationBuilder;


import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.dao.JsonResponseUnitDao;
import io.tiklab.postin.autotest.http.unit.cases.entity.JsonResponseUnitEntity;
import io.tiklab.postin.autotest.http.unit.cases.model.JsonResponseUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.JsonResponseUnitQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
* 响应中json 服务
*/
@Service
public class JsonResponseUnitUnitServiceImpl implements JsonResponseUnitService {

    @Autowired
    JsonResponseUnitDao jsonResponseUnitDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createJsonResponse(@NotNull @Valid JsonResponseUnit jsonResponseUnit) {
        JsonResponseUnitEntity jsonResponseUnitEntity = BeanMapper.map(jsonResponseUnit, JsonResponseUnitEntity.class);

        return jsonResponseUnitDao.createJsonResponse(jsonResponseUnitEntity);
    }

    @Override
    public void updateJsonResponse(@NotNull @Valid JsonResponseUnit jsonResponseUnit) {
        JsonResponseUnitEntity jsonResponseUnitEntity = BeanMapper.map(jsonResponseUnit, JsonResponseUnitEntity.class);

        jsonResponseUnitDao.updateJsonResponse(jsonResponseUnitEntity);
    }

    @Override
    public void deleteJsonResponse(@NotNull String id) {
        jsonResponseUnitDao.deleteJsonResponse(id);
    }

    @Override
    public JsonResponseUnit findOne(String id) {
        JsonResponseUnitEntity jsonResponseUnitEntity = jsonResponseUnitDao.findJsonResponse(id);

        JsonResponseUnit jsonResponseUnit = BeanMapper.map(jsonResponseUnitEntity, JsonResponseUnit.class);
        return jsonResponseUnit;
    }

    @Override
    public List<JsonResponseUnit> findList(List<String> idList) {
        List<JsonResponseUnitEntity> jsonResponseUnitEntityList =  jsonResponseUnitDao.findJsonResponseList(idList);

        List<JsonResponseUnit> jsonResponseUnitList =  BeanMapper.mapList(jsonResponseUnitEntityList, JsonResponseUnit.class);
        return jsonResponseUnitList;
    }

    @Override
    public JsonResponseUnit findJsonResponse(@NotNull String id) {
        JsonResponseUnit jsonResponseUnit = findOne(id);

        joinTemplate.joinQuery(jsonResponseUnit);
        return jsonResponseUnit;
    }

    @Override
    public List<JsonResponseUnit> findAllJsonResponse() {
        List<JsonResponseUnitEntity> jsonResponseUnitEntityList =  jsonResponseUnitDao.findAllJsonResponse();

        List<JsonResponseUnit> jsonResponseUnitList =  BeanMapper.mapList(jsonResponseUnitEntityList, JsonResponseUnit.class);

        joinTemplate.joinQuery(jsonResponseUnitList);
        return jsonResponseUnitList;
    }

    @Override
    public List<JsonResponseUnit> findJsonResponseList(JsonResponseUnitQuery jsonResponseUnitQuery) {
        List<JsonResponseUnitEntity> jsonResponseUnitEntityList = jsonResponseUnitDao.findJsonResponseList(jsonResponseUnitQuery);

        List<JsonResponseUnit> jsonResponseUnitList = BeanMapper.mapList(jsonResponseUnitEntityList, JsonResponseUnit.class);

        joinTemplate.joinQuery(jsonResponseUnitList);

        return jsonResponseUnitList;
    }

    @Override
    public Pagination<JsonResponseUnit> findJsonResponsePage(JsonResponseUnitQuery jsonResponseUnitQuery) {

        Pagination<JsonResponseUnitEntity>  pagination = jsonResponseUnitDao.findJsonResponsePage(jsonResponseUnitQuery);

        List<JsonResponseUnit> jsonResponseUnitList = BeanMapper.mapList(pagination.getDataList(), JsonResponseUnit.class);

        joinTemplate.joinQuery(jsonResponseUnitList);

        return PaginationBuilder.build(pagination, jsonResponseUnitList);
    }

    @Override
    public List<JsonResponseUnit> findJsonResponseListTree(JsonResponseUnitQuery jsonResponseUnitQuery) {
        List<JsonResponseUnit> matchJsonResponseUnitList = this.findJsonResponseList(jsonResponseUnitQuery);

        //查找第一级属性列表
        List<JsonResponseUnit> topJsonResponseUnitList = findTopJsonResponseList(matchJsonResponseUnitList);

        //设置下级节点列表
        if(topJsonResponseUnitList != null && topJsonResponseUnitList.size() > 0){
            for(JsonResponseUnit jsonResponseUnit : topJsonResponseUnitList){
                setChildren(matchJsonResponseUnitList, jsonResponseUnit);
            }
        }

        return topJsonResponseUnitList;
    }

    /**
     * 查找第一级属性列表
     * @param matchJsonResponseUnitList
     * @return
     */
    List<JsonResponseUnit> findTopJsonResponseList(List<JsonResponseUnit> matchJsonResponseUnitList) {
        List<JsonResponseUnit> jsonResponseUnitList = matchJsonResponseUnitList.stream()
                .filter(jsonResponseUnit -> (jsonResponseUnit.getParent() == null || jsonResponseUnit.getParent().getId() == null))
                .collect(Collectors.toList());
        return jsonResponseUnitList;
    }

    /**
     * 设置下级节点列表
     * @param matchJsonResponseUnitList
     * @param parentJsonResponseUnit
     */
    void setChildren(List<JsonResponseUnit> matchJsonResponseUnitList, JsonResponseUnit parentJsonResponseUnit){
        List<JsonResponseUnit> childList = matchJsonResponseUnitList.stream()
                .filter(jsonResponseUnit -> (jsonResponseUnit.getParent() != null && jsonResponseUnit.getParent().getId() != null && jsonResponseUnit.getParent().getId().equals(parentJsonResponseUnit.getId())))
                .collect(Collectors.toList());

        if(childList != null && childList.size() > 0){
            parentJsonResponseUnit.setChildren(childList);

            //设置下级节点列表
            for(JsonResponseUnit jsonResponseUnit :childList){
                setChildren(matchJsonResponseUnitList, jsonResponseUnit);
            }
        }
    }
}