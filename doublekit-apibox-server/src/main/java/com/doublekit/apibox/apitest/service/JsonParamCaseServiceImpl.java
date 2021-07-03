package com.doublekit.apibox.apitest.service;

import com.doublekit.apibox.apitest.dao.JsonParamCaseDao;
import com.doublekit.apibox.apitest.entity.JsonParamCasePo;
import com.doublekit.apibox.apitest.model.JsonParamCase;
import com.doublekit.apibox.apitest.model.JsonParamCaseQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.join.join.JoinQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

/**
* 用户服务业务处理
*/
@Service
public class JsonParamCaseServiceImpl implements JsonParamCaseService {

    @Autowired
    JsonParamCaseDao jsonParamCaseDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createJsonParamCase(@NotNull @Valid JsonParamCase jsonParamCase) {
        JsonParamCasePo jsonParamCasePo = BeanMapper.map(jsonParamCase, JsonParamCasePo.class);

        return jsonParamCaseDao.createJsonParamCase(jsonParamCasePo);
    }

    @Override
    public void updateJsonParamCase(@NotNull @Valid JsonParamCase jsonParamCase) {
        JsonParamCasePo jsonParamCasePo = BeanMapper.map(jsonParamCase, JsonParamCasePo.class);

        jsonParamCaseDao.updateJsonParamCase(jsonParamCasePo);
    }

    @Override
    public void deleteJsonParamCase(@NotNull String id) {
        jsonParamCaseDao.deleteJsonParamCase(id);
    }

    @Override
    public JsonParamCase findOne(String id) {
        JsonParamCasePo jsonParamCasePo = jsonParamCaseDao.findJsonParamCase(id);

        JsonParamCase jsonParamCase = BeanMapper.map(jsonParamCasePo, JsonParamCase.class);
        return jsonParamCase;
    }

    @Override
    public List<JsonParamCase> findList(List<String> idList) {
        List<JsonParamCasePo> jsonParamCasePoList =  jsonParamCaseDao.findJsonParamCaseList(idList);

        List<JsonParamCase> jsonParamCaseList =  BeanMapper.mapList(jsonParamCasePoList,JsonParamCase.class);
        return jsonParamCaseList;
    }

    @Override
    public JsonParamCase findJsonParamCase(@NotNull String id) {
        JsonParamCase jsonParamCase = findOne(id);

        joinQuery.queryOne(jsonParamCase);
        return jsonParamCase;
    }

    @Override
    public List<JsonParamCase> findAllJsonParamCase() {
        List<JsonParamCasePo> jsonParamCasePoList =  jsonParamCaseDao.findAllJsonParamCase();

        List<JsonParamCase> jsonParamCaseList =  BeanMapper.mapList(jsonParamCasePoList,JsonParamCase.class);

        joinQuery.queryList(jsonParamCaseList);
        return jsonParamCaseList;
    }

    @Override
    public List<JsonParamCase> findJsonParamCaseList(JsonParamCaseQuery jsonParamCaseQuery) {
        List<JsonParamCasePo> jsonParamCasePoList = jsonParamCaseDao.findJsonParamCaseList(jsonParamCaseQuery);

        List<JsonParamCase> jsonParamCaseList = BeanMapper.mapList(jsonParamCasePoList,JsonParamCase.class);

        joinQuery.queryList(jsonParamCaseList);

        return jsonParamCaseList;
    }

    @Override
    public Pagination<JsonParamCase> findJsonParamCasePage(JsonParamCaseQuery jsonParamCaseQuery) {
        Pagination<JsonParamCase> pg = new Pagination<>();

        Pagination<JsonParamCasePo>  pagination = jsonParamCaseDao.findJsonParamCasePage(jsonParamCaseQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<JsonParamCase> jsonParamCaseList = BeanMapper.mapList(pagination.getDataList(),JsonParamCase.class);

        joinQuery.queryList(jsonParamCaseList);

        pg.setDataList(jsonParamCaseList);
        return pg;
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