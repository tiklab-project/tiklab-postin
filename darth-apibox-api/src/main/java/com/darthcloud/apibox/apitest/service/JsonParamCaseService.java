package com.darthcloud.apibox.apitest.service;

import com.darthcloud.common.Pagination;

import com.darthcloud.apibox.apitest.model.JsonParamCase;
import com.darthcloud.apibox.apitest.model.JsonParamCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
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

    JsonParamCase findOne(@NotNull String id);

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
    Pagination<List<JsonParamCase>> findJsonParamCasePage(JsonParamCaseQuery jsonParamCaseQuery);

}