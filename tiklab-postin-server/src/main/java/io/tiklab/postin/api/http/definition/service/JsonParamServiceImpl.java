package io.tiklab.postin.api.http.definition.service;

import io.tiklab.postin.api.http.definition.dao.JsonParamDao;
import io.tiklab.postin.api.http.definition.entity.JsonParamEntity;
import io.tiklab.postin.api.http.definition.model.JsonParam;
import io.tiklab.postin.api.http.definition.model.JsonParamQuery;

import io.tiklab.core.page.Pagination;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 定义
 * http协议
 * 请求中json服务
*/
@Service
public class JsonParamServiceImpl implements JsonParamService {

    @Autowired
    JsonParamDao jsonParamDao;

    @Autowired
    JoinTemplate joinTemplate;

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

        joinTemplate.joinQuery(jsonParamList);
        return jsonParamList;
    }

    @Override
    public JsonParam findJsonParam(@NotNull String id) {
        JsonParam jsonParam = findOne(id);

        joinTemplate.joinQuery(jsonParam);
        return jsonParam;
    }

    @Override
    public List<JsonParam> findAllJsonParam() {
        List<JsonParamEntity> jsonParamPoList =  jsonParamDao.findAllJsonParam();

        List<JsonParam> jsonParamList = BeanMapper.mapList(jsonParamPoList,JsonParam.class);

        joinTemplate.joinQuery(jsonParamList);
        return jsonParamList;
    }

    @Override
    public List<JsonParam> findJsonParamList(JsonParamQuery jsonParamQuery) {
        List<JsonParamEntity> jsonParamPoList = jsonParamDao.findJsonParamList(jsonParamQuery);

        List<JsonParam> jsonParamList = BeanMapper.mapList(jsonParamPoList,JsonParam.class);

        joinTemplate.joinQuery(jsonParamList);

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

        if(CollectionUtils.isNotEmpty(childList) && childList.size() > 0){
            parentJsonParam.setChildren(childList);

            //设置下级节点列表
            for(JsonParam childJsonParam:childList){
                setChildren(matchJsonParamList,childJsonParam);
            }
        }
    }

    @Override
    public Pagination<JsonParam> findJsonParamPage(JsonParamQuery jsonParamQuery) {

        Pagination<JsonParamEntity>  pagination = jsonParamDao.findJsonParamPage(jsonParamQuery);

        List<JsonParam> jsonParamList = BeanMapper.mapList(pagination.getDataList(),JsonParam.class);

        joinTemplate.joinQuery(jsonParamList);

        return PaginationBuilder.build(pagination,jsonParamList);
    }
}