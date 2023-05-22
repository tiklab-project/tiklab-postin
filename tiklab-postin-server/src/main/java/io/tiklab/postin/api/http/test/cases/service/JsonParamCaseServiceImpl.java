package io.tiklab.postin.api.http.test.cases.service;

import io.tiklab.postin.api.http.test.cases.dao.JsonParamCaseDao;
import io.tiklab.postin.api.http.test.cases.entity.JsonParamCaseEntity;
import io.tiklab.postin.api.http.test.cases.model.JsonParamCases;
import io.tiklab.postin.api.http.test.cases.model.JsonParamCaseQuery;

import io.tiklab.core.page.Pagination;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
* 用户服务业务处理
*/
@Service
public class JsonParamCaseServiceImpl implements JsonParamCaseService {

    @Autowired
    JsonParamCaseDao jsonParamCaseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createJsonParamCase(@NotNull @Valid JsonParamCases jsonParamCases) {
        JsonParamCaseEntity jsonParamCaseEntity = BeanMapper.map(jsonParamCases, JsonParamCaseEntity.class);

        return jsonParamCaseDao.createJsonParamCase(jsonParamCaseEntity);
    }

    @Override
    public void updateJsonParamCase(@NotNull @Valid JsonParamCases jsonParamCases) {
        JsonParamCaseEntity jsonParamCaseEntity = BeanMapper.map(jsonParamCases, JsonParamCaseEntity.class);

        jsonParamCaseDao.updateJsonParamCase(jsonParamCaseEntity);
    }

    @Override
    public void deleteJsonParamCase(@NotNull String id) {
        jsonParamCaseDao.deleteJsonParamCase(id);
    }

    @Override
    public JsonParamCases findOne(String id) {
        JsonParamCaseEntity jsonParamCaseEntity = jsonParamCaseDao.findJsonParamCase(id);

        JsonParamCases jsonParamCases = BeanMapper.map(jsonParamCaseEntity, JsonParamCases.class);
        return jsonParamCases;
    }

    @Override
    public List<JsonParamCases> findList(List<String> idList) {
        List<JsonParamCaseEntity> jsonParamCaseEntityList =  jsonParamCaseDao.findJsonParamCaseList(idList);

        List<JsonParamCases> jsonParamCasesList =  BeanMapper.mapList(jsonParamCaseEntityList, JsonParamCases.class);
        return jsonParamCasesList;
    }

    @Override
    public JsonParamCases findJsonParamCase(@NotNull String id) {
        JsonParamCases jsonParamCases = findOne(id);

        joinTemplate.joinQuery(jsonParamCases);
        return jsonParamCases;
    }

    @Override
    public List<JsonParamCases> findAllJsonParamCase() {
        List<JsonParamCaseEntity> jsonParamCaseEntityList =  jsonParamCaseDao.findAllJsonParamCase();

        List<JsonParamCases> jsonParamCasesList =  BeanMapper.mapList(jsonParamCaseEntityList, JsonParamCases.class);

        joinTemplate.joinQuery(jsonParamCasesList);
        return jsonParamCasesList;
    }

    @Override
    public List<JsonParamCases> findJsonParamCaseList(JsonParamCaseQuery jsonParamCaseQuery) {
        List<JsonParamCaseEntity> jsonParamCaseEntityList = jsonParamCaseDao.findJsonParamCaseList(jsonParamCaseQuery);

        List<JsonParamCases> jsonParamCasesList = BeanMapper.mapList(jsonParamCaseEntityList, JsonParamCases.class);

        joinTemplate.joinQuery(jsonParamCasesList);

        return jsonParamCasesList;
    }

    @Override
    public Pagination<JsonParamCases> findJsonParamCasePage(JsonParamCaseQuery jsonParamCaseQuery) {

        Pagination<JsonParamCaseEntity>  pagination = jsonParamCaseDao.findJsonParamCasePage(jsonParamCaseQuery);

        List<JsonParamCases> jsonParamCasesList = BeanMapper.mapList(pagination.getDataList(), JsonParamCases.class);

        joinTemplate.joinQuery(jsonParamCasesList);

        return PaginationBuilder.build(pagination, jsonParamCasesList);
    }

    @Override
    public List<JsonParamCases> findJsonParamCaseListTree(JsonParamCaseQuery jsonParamCaseQuery) {
        //查找所有参数列表
        List<JsonParamCases> matchJsonParamCasesList = this.findJsonParamCaseList(jsonParamCaseQuery);

        //查找第一级参数列表
        List<JsonParamCases> topJsonParamCasesList = findTopJsonParamCaseList(matchJsonParamCasesList);

        //设置下级节点列表
        if(topJsonParamCasesList != null && topJsonParamCasesList.size() > 0){
            for(JsonParamCases topJsonParamCases : topJsonParamCasesList){
                setChildren(matchJsonParamCasesList, topJsonParamCases);
            }
        }

        return topJsonParamCasesList;
    }

    /**
     * 查找第一级参数列表
     * @param matchJsonParamCasesList
     * @return
     */
    List<JsonParamCases> findTopJsonParamCaseList(List<JsonParamCases> matchJsonParamCasesList) {
        List<JsonParamCases> jsonParamCasesList = matchJsonParamCasesList.stream()
                .filter(jsonParamCase -> (jsonParamCase.getParent() == null || jsonParamCase.getParent().getId() == null))
                .collect(Collectors.toList());
        return jsonParamCasesList;
    }

    /**
     * 设置下级节点列表
     * @param matchJsonParamCasesList
     * @param parentJsonParamCases
     */
    void setChildren(List<JsonParamCases> matchJsonParamCasesList, JsonParamCases parentJsonParamCases){
        List<JsonParamCases> childList = matchJsonParamCasesList.stream()
                .filter(jsonParamCase -> (jsonParamCase.getParent() != null && jsonParamCase.getParent().getId() != null && jsonParamCase.getParent().getId().equals(parentJsonParamCases.getId())))
                .collect(Collectors.toList());

        if(childList != null && childList.size() > 0){
            parentJsonParamCases.setChildren(childList);

            //设置下级节点列表
            for(JsonParamCases childJsonParamCases :childList){
                setChildren(matchJsonParamCasesList, childJsonParamCases);
            }
        }
    }

}