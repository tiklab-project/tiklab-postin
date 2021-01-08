package com.darthcloud.apibox.jsonresponse.service;

import com.darthcloud.apibox.jsonresponse.model.JsonResponse;
import com.darthcloud.apibox.jsonresponse.model.JsonResponseQuery;
import com.darthcloud.common.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface JsonResponseService {

    /**
    * 创建用户
    * @param jsonResponse
    * @return
    */
    String createJsonResponse(@NotNull @Valid JsonResponse jsonResponse);

    /**
    * 更新用户
    * @param jsonResponse
    */
    void updateJsonResponse(@NotNull @Valid JsonResponse jsonResponse);

    /**
    * 删除用户
    * @param id
    */
    void deleteJsonResponse(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    JsonResponse findJsonResponse(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<JsonResponse> findAllJsonResponse();

    /**
    * 查询列表
    * @param jsonResponseQuery
    * @return
    */
    List<JsonResponse> findJsonResponseList(JsonResponseQuery jsonResponseQuery);

    /**
    * 按分页查询
    * @param jsonResponseQuery
    * @return
    */
    Pagination<List<JsonResponse>> findJsonResponsePage(JsonResponseQuery jsonResponseQuery);

}