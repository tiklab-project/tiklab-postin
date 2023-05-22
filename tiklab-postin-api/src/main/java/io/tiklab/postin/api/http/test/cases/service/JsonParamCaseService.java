package io.tiklab.postin.api.http.test.cases.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.postin.api.http.test.cases.model.JsonParamCases;
import io.tiklab.postin.api.http.test.cases.model.JsonParamCaseQuery;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@JoinProvider(model = JsonParamCases.class)
public interface JsonParamCaseService {

    /**
    * 创建用户
    * @param jsonParamCases
    * @return
    */
    String createJsonParamCase(@NotNull @Valid JsonParamCases jsonParamCases);

    /**
    * 更新用户
    * @param jsonParamCases
    */
    void updateJsonParamCase(@NotNull @Valid JsonParamCases jsonParamCases);

    /**
    * 删除用户
    * @param id
    */
    void deleteJsonParamCase(@NotNull String id);

    @FindOne
    JsonParamCases findOne(@NotNull String id);

    @FindList
    List<JsonParamCases> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    JsonParamCases findJsonParamCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<JsonParamCases> findAllJsonParamCase();

    /**
    * 查询列表
    * @param jsonParamCaseQuery
    * @return
    */
    List<JsonParamCases> findJsonParamCaseList(JsonParamCaseQuery jsonParamCaseQuery);

    /**
    * 按分页查询
    * @param jsonParamCaseQuery
    * @return
    */
    Pagination<JsonParamCases> findJsonParamCasePage(JsonParamCaseQuery jsonParamCaseQuery);

    /**
     * 查询列表树
     * @param jsonParamCaseQuery
     * @return
     */
    List<JsonParamCases> findJsonParamCaseListTree(JsonParamCaseQuery jsonParamCaseQuery);

}