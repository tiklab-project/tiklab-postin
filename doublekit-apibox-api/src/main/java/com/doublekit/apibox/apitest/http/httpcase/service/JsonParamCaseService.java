package com.doublekit.apibox.apitest.http.httpcase.service;

import com.doublekit.core.page.Pagination;

import com.doublekit.apibox.apitest.http.httpcase.model.JsonParamCase;
import com.doublekit.apibox.apitest.http.httpcase.model.JsonParamCaseQuery;
import com.doublekit.join.annotation.FindList;
import com.doublekit.join.annotation.FindOne;
import com.doublekit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@JoinProvider(model = JsonParamCase.class)
public interface JsonParamCaseService {

    /**
    * 创建用户
    * @param jsonParamCase
    * @return
    */
    String createJsonParamCase(@NotNull @Valid JsonParamCase jsonParamCase);

    /**
    * 更新用户
    * @param jsonParamCase
    */
    void updateJsonParamCase(@NotNull @Valid JsonParamCase jsonParamCase);

    /**
    * 删除用户
    * @param id
    */
    void deleteJsonParamCase(@NotNull String id);

    @FindOne
    JsonParamCase findOne(@NotNull String id);

    @FindList
    List<JsonParamCase> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    JsonParamCase findJsonParamCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<JsonParamCase> findAllJsonParamCase();

    /**
    * 查询列表
    * @param jsonParamCaseQuery
    * @return
    */
    List<JsonParamCase> findJsonParamCaseList(JsonParamCaseQuery jsonParamCaseQuery);

    /**
    * 按分页查询
    * @param jsonParamCaseQuery
    * @return
    */
    Pagination<JsonParamCase> findJsonParamCasePage(JsonParamCaseQuery jsonParamCaseQuery);

    /**
     * 查询列表树
     * @param jsonParamCaseQuery
     * @return
     */
    List<JsonParamCase> findJsonParamCaseListTree(JsonParamCaseQuery jsonParamCaseQuery);

}