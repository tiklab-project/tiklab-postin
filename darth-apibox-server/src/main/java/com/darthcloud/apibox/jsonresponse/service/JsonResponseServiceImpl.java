package com.darthcloud.apibox.jsonresponse.service;

import com.darthcloud.apibox.jsonresponse.dao.JsonResponseDao;
import com.darthcloud.apibox.jsonresponse.entity.JsonResponsePo;
import com.darthcloud.apibox.jsonresponse.model.JsonResponse;
import com.darthcloud.apibox.jsonresponse.model.JsonResponseQuery;

import com.darthcloud.common.Pagination;
import com.darthcloud.beans.BeanMapper;
import com.darthcloud.join.join.JoinQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.beans.BeanUtils;

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
    public Pagination<List<JsonResponse>> findJsonResponsePage(JsonResponseQuery jsonResponseQuery) {
        Pagination<List<JsonResponse>> pg = new Pagination<>();

        Pagination<List<JsonResponsePo>>  pagination = jsonResponseDao.findJsonResponsePage(jsonResponseQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<JsonResponse> jsonResponseList = BeanMapper.mapList(pagination.getDataList(), JsonResponse.class);

        joinQuery.queryList(jsonResponseList);

        pg.setDataList(jsonResponseList);
        return pg;
    }
}