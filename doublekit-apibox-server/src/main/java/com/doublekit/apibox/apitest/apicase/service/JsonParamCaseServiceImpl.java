package com.doublekit.apibox.apitest.apicase.service;

import com.doublekit.apibox.apitest.apicase.dao.JsonParamCaseDao;
import com.doublekit.apibox.apitest.apicase.entity.JsonParamCaseEntity;
import com.doublekit.apibox.apitest.apicase.model.JsonParamCase;
import com.doublekit.apibox.apitest.apicase.model.JsonParamCaseQuery;

import com.doublekit.common.page.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.page.PaginationBuilder;
import com.doublekit.join.JoinTemplate;
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
    public String createJsonParamCase(@NotNull @Valid JsonParamCase jsonParamCase) {
        JsonParamCaseEntity jsonParamCaseEntity = BeanMapper.map(jsonParamCase, JsonParamCaseEntity.class);

        return jsonParamCaseDao.createJsonParamCase(jsonParamCaseEntity);
    }

    @Override
    public void updateJsonParamCase(@NotNull @Valid JsonParamCase jsonParamCase) {
        JsonParamCaseEntity jsonParamCaseEntity = BeanMapper.map(jsonParamCase, JsonParamCaseEntity.class);

        jsonParamCaseDao.updateJsonParamCase(jsonParamCaseEntity);
    }

    @Override
    public void deleteJsonParamCase(@NotNull String id) {
        jsonParamCaseDao.deleteJsonParamCase(id);
    }

    @Override
    public JsonParamCase findOne(String id) {
        JsonParamCaseEntity jsonParamCaseEntity = jsonParamCaseDao.findJsonParamCase(id);

        JsonParamCase jsonParamCase = BeanMapper.map(jsonParamCaseEntity, JsonParamCase.class);
        return jsonParamCase;
    }

    @Override
    public List<JsonParamCase> findList(List<String> idList) {
        List<JsonParamCaseEntity> jsonParamCaseEntityList =  jsonParamCaseDao.findJsonParamCaseList(idList);

        List<JsonParamCase> jsonParamCaseList =  BeanMapper.mapList(jsonParamCaseEntityList,JsonParamCase.class);
        return jsonParamCaseList;
    }

    @Override
    public JsonParamCase findJsonParamCase(@NotNull String id) {
        JsonParamCase jsonParamCase = findOne(id);

        joinTemplate.joinQuery(jsonParamCase);
        return jsonParamCase;
    }

    @Override
    public List<JsonParamCase> findAllJsonParamCase() {
        List<JsonParamCaseEntity> jsonParamCaseEntityList =  jsonParamCaseDao.findAllJsonParamCase();

        List<JsonParamCase> jsonParamCaseList =  BeanMapper.mapList(jsonParamCaseEntityList,JsonParamCase.class);

        joinTemplate.joinQuery(jsonParamCaseList);
        return jsonParamCaseList;
    }

    @Override
    public List<JsonParamCase> findJsonParamCaseList(JsonParamCaseQuery jsonParamCaseQuery) {
        List<JsonParamCaseEntity> jsonParamCaseEntityList = jsonParamCaseDao.findJsonParamCaseList(jsonParamCaseQuery);

        List<JsonParamCase> jsonParamCaseList = BeanMapper.mapList(jsonParamCaseEntityList,JsonParamCase.class);

        joinTemplate.joinQuery(jsonParamCaseList);

        return jsonParamCaseList;
    }

    @Override
    public Pagination<JsonParamCase> findJsonParamCasePage(JsonParamCaseQuery jsonParamCaseQuery) {

        Pagination<JsonParamCaseEntity>  pagination = jsonParamCaseDao.findJsonParamCasePage(jsonParamCaseQuery);

        List<JsonParamCase> jsonParamCaseList = BeanMapper.mapList(pagination.getDataList(),JsonParamCase.class);

        joinTemplate.joinQuery(jsonParamCaseList);

        return PaginationBuilder.build(pagination,jsonParamCaseList);
    }

    @Override
    public List<JsonParamCase> findJsonParamCaseListTree(JsonParamCaseQuery jsonParamCaseQuery) {
        //查找所有参数列表
        List<JsonParamCase> matchJsonParamCaseList = this.findJsonParamCaseList(jsonParamCaseQuery);

        //查找第一级参数列表
        List<JsonParamCase> topJsonParamCaseList = findTopJsonParamCaseList(matchJsonParamCaseList);

        //设置下级节点列表
        if(topJsonParamCaseList != null && topJsonParamCaseList.size() > 0){
            for(JsonParamCase topJsonParamCase:topJsonParamCaseList){
                setChildren(matchJsonParamCaseList,topJsonParamCase);
            }
        }

        return topJsonParamCaseList;
    }

    /**
     * 查找第一级参数列表
     * @param matchJsonParamCaseList
     * @return
     */
    List<JsonParamCase> findTopJsonParamCaseList(List<JsonParamCase> matchJsonParamCaseList) {
        List<JsonParamCase> jsonParamCaseList = matchJsonParamCaseList.stream()
                .filter(jsonParamCase -> (jsonParamCase.getParent() == null || jsonParamCase.getParent().getId() == null))
                .collect(Collectors.toList());
        return jsonParamCaseList;
    }

    /**
     * 设置下级节点列表
     * @param matchJsonParamCaseList
     * @param parentJsonParamCase
     */
    void setChildren(List<JsonParamCase> matchJsonParamCaseList,JsonParamCase parentJsonParamCase){
        List<JsonParamCase> childList = matchJsonParamCaseList.stream()
                .filter(jsonParamCase -> (jsonParamCase.getParent() != null && jsonParamCase.getParent().getId() != null && jsonParamCase.getParent().getId().equals(parentJsonParamCase.getId())))
                .collect(Collectors.toList());

        if(childList != null && childList.size() > 0){
            parentJsonParamCase.setChildren(childList);

            //设置下级节点列表
            for(JsonParamCase childJsonParamCase:childList){
                setChildren(matchJsonParamCaseList,childJsonParamCase);
            }
        }
    }

}