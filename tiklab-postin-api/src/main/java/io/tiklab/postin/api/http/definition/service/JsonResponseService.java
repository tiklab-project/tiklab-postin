package io.tiklab.postin.api.http.definition.service;

import io.tiklab.postin.api.http.definition.model.JsonResponses;
import io.tiklab.postin.api.http.definition.model.JsonResponseQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindOne;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 响应json服务接口
*/
@JoinProvider(model = JsonResponses.class)
public interface JsonResponseService {

    /**
    * 创建用户
    * @param jsonResponses
    * @return
    */
    String createJsonResponse(@NotNull @Valid JsonResponses jsonResponses);

    /**
    * 更新用户
    * @param jsonResponses
    */
    void updateJsonResponse(@NotNull @Valid JsonResponses jsonResponses);

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
    JsonResponses findJsonResponse(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<JsonResponses> findAllJsonResponse();

    /**
    * 查询列表
    * @param jsonResponseQuery
    * @return
    */
    List<JsonResponses> findJsonResponseList(JsonResponseQuery jsonResponseQuery);

    /**
    * 按分页查询
    * @param jsonResponseQuery
    * @return
    */
    Pagination<JsonResponses> findJsonResponsePage(JsonResponseQuery jsonResponseQuery);

    /**
     * 查找列表树
     * @param jsonResponseQuery
     * @return
     */
    List<JsonResponses> findJsonResponseListTree(JsonResponseQuery jsonResponseQuery);
    /**
     * 通过id查询
     * @param idList
     * @return
     */
    @FindList
    List<JsonResponses> findList(List<String> idList);
}