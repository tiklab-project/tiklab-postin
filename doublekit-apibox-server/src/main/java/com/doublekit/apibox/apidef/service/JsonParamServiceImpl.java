package com.doublekit.apibox.apidef.service;

import com.doublekit.apibox.apidef.dao.JsonParamDao;
import com.doublekit.apibox.apidef.entity.JsonParamEntity;
import com.doublekit.apibox.apidef.model.JsonParam;
import com.doublekit.apibox.apidef.model.JsonParamQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.join.JoinTemplate;
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
public class JsonParamServiceImpl implements JsonParamService {

    @Autowired
    JsonParamDao jsonParamDao;

    @Autowired
    JoinTemplate joinQuery;

    @Override
    public String createJsonParam(@NotNull @Valid JsonParam jsonParam) {
        JsonParamEntity jsonParamPo = BeanMapper.map(jsonParam, JsonParamEntity.class);

        return jsonParamDao.createJsonParam(jsonParamPo);
    }

    @Override
    public void updateJsonParam(@NotNull @Valid JsonParam jsonParam) {
        JsonParamEntity jsonParamPo = BeanMapper.map(jsonParam, JsonParamEntity.class);

        jsonParamDao.updateJsonParam(jsonParamPo);
    }

    @Override
    public void deleteJsonParam(@NotNull String id) {
        jsonParamDao.deleteJsonParam(id);
    }

    @Override
    public JsonParam findOne(String id) {
        JsonParamEntity jsonParamPo = jsonParamDao.findJsonParam(id);

        JsonParam jsonParam = BeanMapper.map(jsonParamPo, JsonParam.class);
        return jsonParam;
    }

    @Override
    public List<JsonParam> findList(List<String> idList) {
        List<JsonParamEntity> jsonParamPoList =  jsonParamDao.findJsonParamList(idList);

        List<JsonParam> jsonParamList = BeanMapper.mapList(jsonParamPoList,JsonParam.class);

        joinQuery.queryList(jsonParamList);
        return jsonParamList;
    }

    @Override
    public JsonParam findJsonParam(@NotNull String id) {
        JsonParam jsonParam = findOne(id);

        joinQuery.queryOne(jsonParam);
        return jsonParam;
    }

    @Override
    public List<JsonParam> findAllJsonParam() {
        List<JsonParamEntity> jsonParamPoList =  jsonParamDao.findAllJsonParam();

        List<JsonParam> jsonParamList = BeanMapper.mapList(jsonParamPoList,JsonParam.class);

        joinQuery.queryList(jsonParamList);
        return jsonParamList;
    }

    @Override
    public List<JsonParam> findJsonParamList(JsonParamQuery jsonParamQuery) {
        List<JsonParamEntity> jsonParamPoList = jsonParamDao.findJsonParamList(jsonParamQuery);

        List<JsonParam> jsonParamList = BeanMapper.mapList(jsonParamPoList,JsonParam.class);

        joinQuery.queryList(jsonParamList);

        return jsonParamList;
    }

    @Override
    public List<JsonParam> findJsonParamListTree(JsonParamQuery jsonParamQuery) {
        List<JsonParam> matchJsonParamList = this.findJsonParamList(jsonParamQuery);

        //查找第一级参数列表
        List<JsonParam> topJsonParamList = findTopJsonParamList(matchJsonParamList);

        //设置下级节点列表
        if(topJsonParamList != null && topJsonParamList.size() > 0){
            for(JsonParam topJsonParam:topJsonParamList){
                setChildren(matchJsonParamList,topJsonParam);
            }
        }

        return topJsonParamList;
    }

    /**
     * 查找第一级参数列表
     * @param matchJsonParamList
     * @return
     */
    List<JsonParam> findTopJsonParamList(List<JsonParam> matchJsonParamList) {
        List<JsonParam> jsonParamList = matchJsonParamList.stream()
                .filter(jsonParam -> (jsonParam.getParent() == null || jsonParam.getParent().getId() == null))
                .collect(Collectors.toList());
        return jsonParamList;
    }

    /**
     * 设置下级节点列表
     * @param matchJsonParamList
     * @param parentJsonParam
     */
    void setChildren(List<JsonParam> matchJsonParamList,JsonParam parentJsonParam){
        List<JsonParam> childList = matchJsonParamList.stream()
                .filter(jsonParam -> (jsonParam.getParent() != null && jsonParam.getParent().getId() != null && jsonParam.getParent().getId().equals(parentJsonParam.getId())))
                .collect(Collectors.toList());

        if(childList != null && childList.size() > 0){
            parentJsonParam.setChildren(childList);

            //设置下级节点列表
            for(JsonParam childJsonParam:childList){
                setChildren(matchJsonParamList,childJsonParam);
            }
        }
    }

    @Override
    public Pagination<JsonParam> findJsonParamPage(JsonParamQuery jsonParamQuery) {
        Pagination<JsonParam> pg = new Pagination<>();

        Pagination<JsonParamEntity>  pagination = jsonParamDao.findJsonParamPage(jsonParamQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<JsonParam> jsonParamList = BeanMapper.mapList(pagination.getDataList(),JsonParam.class);

        joinQuery.queryList(jsonParamList);

        pg.setDataList(jsonParamList);
        return pg;
    }
}