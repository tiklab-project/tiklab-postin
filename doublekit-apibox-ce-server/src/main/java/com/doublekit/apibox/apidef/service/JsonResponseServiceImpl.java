package com.doublekit.apibox.apidef.service;

import com.doublekit.apibox.apidef.dao.JsonResponseDao;
import com.doublekit.apibox.apidef.entity.JsonResponsePo;
import com.doublekit.apibox.apidef.entity.MethodPo;
import com.doublekit.apibox.apidef.model.JsonResponse;
import com.doublekit.apibox.apidef.model.JsonResponseQuery;
import com.doublekit.apibox.apidef.model.MethodEx;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.Pagination;
import com.doublekit.join.join.JoinQuery;
import org.springframework.beans.BeanUtils;
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
public class JsonResponseServiceImpl implements JsonResponseService {

    @Autowired
    JsonResponseDao jsonResponseDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createJsonResponse(@NotNull @Valid JsonResponse jsonResponse) {
        JsonResponsePo jsonResponsePo = BeanMapper.map(jsonResponse, JsonResponsePo.class);

        return jsonResponseDao.createJsonResponse(jsonResponsePo);
    }

    @Override
    public void updateJsonResponse(@NotNull @Valid JsonResponse jsonResponse) {
        JsonResponsePo jsonResponsePo = BeanMapper.map(jsonResponse, JsonResponsePo.class);

        jsonResponseDao.updateJsonResponse(jsonResponsePo);
    }

    @Override
    public void deleteJsonResponse(@NotNull String id) {
        jsonResponseDao.deleteJsonResponse(id);
    }

    @Override
    public JsonResponse findJsonResponse(@NotNull String id) {
        JsonResponsePo jsonResponsePo = jsonResponseDao.findJsonResponse(id);

        JsonResponse jsonResponse = BeanMapper.map(jsonResponsePo, JsonResponse.class);

        joinQuery.queryOne(jsonResponse);

        return jsonResponse;
    }

    @Override
    public List<JsonResponse> findAllJsonResponse() {
        List<JsonResponsePo> jsonResponsePoList =  jsonResponseDao.findAllJsonResponse();

        List<JsonResponse> jsonResponseList = BeanMapper.mapList(jsonResponsePoList, JsonResponse.class);

        joinQuery.queryList(jsonResponseList);

        return jsonResponseList;
    }

    @Override
    public List<JsonResponse> findJsonResponseList(JsonResponseQuery jsonResponseQuery) {
        List<JsonResponsePo> jsonResponsePoList = jsonResponseDao.findJsonResponseList(jsonResponseQuery);

        List<JsonResponse> jsonResponseList = BeanMapper.mapList(jsonResponsePoList, JsonResponse.class);

        joinQuery.queryList(jsonResponseList);

        return jsonResponseList;
    }

    @Override
    public Pagination<JsonResponse> findJsonResponsePage(JsonResponseQuery jsonResponseQuery) {
        Pagination<JsonResponse> pg = new Pagination<>();

        Pagination<JsonResponsePo>  pagination = jsonResponseDao.findJsonResponsePage(jsonResponseQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<JsonResponse> jsonResponseList = BeanMapper.mapList(pagination.getDataList(), JsonResponse.class);

        joinQuery.queryList(jsonResponseList);

        pg.setDataList(jsonResponseList);
        return pg;
    }

    @Override
    public List<JsonResponse> findJsonResponseListTree(JsonResponseQuery jsonResponseQuery) {
        List<JsonResponse> matchJsonResponseList = this.findJsonResponseList(jsonResponseQuery);

        //查找第一级属性列表
        List<JsonResponse> topJsonResponseList = findTopJsonResponseList(matchJsonResponseList);

        //设置下级节点列表
        if(topJsonResponseList != null && topJsonResponseList.size() > 0){
            for(JsonResponse jsonResponse:topJsonResponseList){
                setChildren(matchJsonResponseList,jsonResponse);
            }
        }

        return topJsonResponseList;
    }

    @Override
    public List<JsonResponse> findList(List<String> idList) {
        List<MethodPo> jsonResponseList = jsonResponseDao.findJsonResponseList(idList);

        List<JsonResponse> jsonResponses = BeanMapper.mapList(jsonResponseList, JsonResponse.class);

        return jsonResponses;
    }

    /**
     * 查找第一级属性列表
     * @param matchJsonResponseList
     * @return
     */
    List<JsonResponse> findTopJsonResponseList(List<JsonResponse> matchJsonResponseList) {
        List<JsonResponse> jsonResponseList = matchJsonResponseList.stream()
                .filter(jsonResponse -> (jsonResponse.getParent() == null || jsonResponse.getParent().getId() == null))
                .collect(Collectors.toList());
        return jsonResponseList;
    }

    /**
     * 设置下级节点列表
     * @param matchJsonResponseList
     * @param parentJsonResponse
     */
    void setChildren(List<JsonResponse> matchJsonResponseList,JsonResponse parentJsonResponse){
        List<JsonResponse> childList = matchJsonResponseList.stream()
                .filter(jsonResponse -> (jsonResponse.getParent() != null && jsonResponse.getParent().getId() != null && jsonResponse.getParent().getId().equals(parentJsonResponse.getId())))
                .collect(Collectors.toList());

        if(childList != null && childList.size() > 0){
            parentJsonResponse.setChildren(childList);

            //设置下级节点列表
            for(JsonResponse jsonResponse:childList){
                setChildren(matchJsonResponseList,jsonResponse);
            }
        }
    }
}