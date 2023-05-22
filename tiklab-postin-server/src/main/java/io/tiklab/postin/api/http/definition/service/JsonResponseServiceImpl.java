package io.tiklab.postin.api.http.definition.service;

import io.tiklab.postin.api.http.definition.dao.JsonResponseDao;
import io.tiklab.postin.api.http.definition.entity.JsonResponsesEntity;
import io.tiklab.postin.api.http.definition.entity.HttpApiEntity;
import io.tiklab.postin.api.http.definition.model.JsonResponses;
import io.tiklab.postin.api.http.definition.model.JsonResponseQuery;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 定义
 * http协议
 * 响应中json服务
 */
@Service
public class JsonResponseServiceImpl implements JsonResponseService {

    @Autowired
    JsonResponseDao jsonResponseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createJsonResponse(@NotNull @Valid JsonResponses jsonResponses) {
        JsonResponsesEntity jsonResponsesEntity = BeanMapper.map(jsonResponses, JsonResponsesEntity.class);

        return jsonResponseDao.createJsonResponse(jsonResponsesEntity);
    }

    @Override
    public void updateJsonResponse(@NotNull @Valid JsonResponses jsonResponses) {
        JsonResponsesEntity jsonResponsesEntity = BeanMapper.map(jsonResponses, JsonResponsesEntity.class);

        jsonResponseDao.updateJsonResponse(jsonResponsesEntity);
    }

    @Override
    public void deleteJsonResponse(@NotNull String id) {
        jsonResponseDao.deleteJsonResponse(id);
    }

    @Override
    public JsonResponses findJsonResponse(@NotNull String id) {
        JsonResponsesEntity jsonResponsesEntity = jsonResponseDao.findJsonResponse(id);

        JsonResponses jsonResponses = BeanMapper.map(jsonResponsesEntity, JsonResponses.class);

        joinTemplate.joinQuery(jsonResponses);

        return jsonResponses;
    }

    @Override
    public List<JsonResponses> findAllJsonResponse() {
        List<JsonResponsesEntity> jsonResponsesEntityList =  jsonResponseDao.findAllJsonResponse();

        List<JsonResponses> jsonResponsesList = BeanMapper.mapList(jsonResponsesEntityList, JsonResponses.class);

        joinTemplate.joinQuery(jsonResponsesList);

        return jsonResponsesList;
    }

    @Override
    public List<JsonResponses> findJsonResponseList(JsonResponseQuery jsonResponseQuery) {
        List<JsonResponsesEntity> jsonResponsesEntityList = jsonResponseDao.findJsonResponseList(jsonResponseQuery);

        List<JsonResponses> jsonResponsesList = BeanMapper.mapList(jsonResponsesEntityList, JsonResponses.class);

        joinTemplate.joinQuery(jsonResponsesList);

        return jsonResponsesList;
    }

    @Override
    public Pagination<JsonResponses> findJsonResponsePage(JsonResponseQuery jsonResponseQuery) {

        Pagination<JsonResponsesEntity>  pagination = jsonResponseDao.findJsonResponsePage(jsonResponseQuery);

        List<JsonResponses> jsonResponsesList = BeanMapper.mapList(pagination.getDataList(), JsonResponses.class);

        joinTemplate.joinQuery(jsonResponsesList);

        return PaginationBuilder.build(pagination, jsonResponsesList);
    }

    @Override
    public List<JsonResponses> findJsonResponseListTree(JsonResponseQuery jsonResponseQuery) {
        List<JsonResponses> matchJsonResponsesList = this.findJsonResponseList(jsonResponseQuery);

        //查找第一级属性列表
        List<JsonResponses> topJsonResponsesList = findTopJsonResponseList(matchJsonResponsesList);

        //设置下级节点列表
        if(topJsonResponsesList != null && topJsonResponsesList.size() > 0){
            for(JsonResponses jsonResponses : topJsonResponsesList){
                setChildren(matchJsonResponsesList, jsonResponses);
            }
        }

        return topJsonResponsesList;
    }

    @Override
    public List<JsonResponses> findList(List<String> idList) {
        List<HttpApiEntity> jsonResponseList = jsonResponseDao.findJsonResponseList(idList);

        List<JsonResponses> jsonResponses = BeanMapper.mapList(jsonResponseList, JsonResponses.class);

        return jsonResponses;
    }

    /**
     * 查找第一级属性列表
     * @param matchJsonResponsesList
     * @return
     */
    List<JsonResponses> findTopJsonResponseList(List<JsonResponses> matchJsonResponsesList) {
        List<JsonResponses> jsonResponsesList = matchJsonResponsesList.stream()
                .filter(jsonResponse -> (jsonResponse.getParent() == null || jsonResponse.getParent().getId() == null))
                .collect(Collectors.toList());
        return jsonResponsesList;
    }

    /**
     * 设置下级节点列表
     * @param matchJsonResponsesList
     * @param parentJsonResponses
     */
    void setChildren(List<JsonResponses> matchJsonResponsesList, JsonResponses parentJsonResponses){
        List<JsonResponses> childList = matchJsonResponsesList.stream()
                .filter(jsonResponse -> (jsonResponse.getParent() != null && jsonResponse.getParent().getId() != null && jsonResponse.getParent().getId().equals(parentJsonResponses.getId())))
                .collect(Collectors.toList());

        if(childList != null && childList.size() > 0){
            parentJsonResponses.setChildren(childList);

            //设置下级节点列表
            for(JsonResponses jsonResponses :childList){
                setChildren(matchJsonResponsesList, jsonResponses);
            }
        }
    }
}