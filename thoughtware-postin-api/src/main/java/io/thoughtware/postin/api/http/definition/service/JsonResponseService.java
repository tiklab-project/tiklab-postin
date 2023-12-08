package io.thoughtware.postin.api.http.definition.service;

import io.thoughtware.postin.api.http.definition.model.JsonResponse;
import io.thoughtware.postin.api.http.definition.model.JsonResponseQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.join.annotation.FindList;
import io.thoughtware.join.annotation.JoinProvider;
import io.thoughtware.join.annotation.FindAll;
import io.thoughtware.join.annotation.FindOne;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 响应json服务接口
*/
@JoinProvider(model = JsonResponse.class)
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
    @FindOne
    JsonResponse findJsonResponse(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
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
    Pagination<JsonResponse> findJsonResponsePage(JsonResponseQuery jsonResponseQuery);

    /**
     * 查找列表树
     * @param jsonResponseQuery
     * @return
     */
    List<JsonResponse> findJsonResponseListTree(JsonResponseQuery jsonResponseQuery);
    /**
     * 通过id查询
     * @param idList
     * @return
     */
    @FindList
    List<JsonResponse> findList(List<String> idList);
}