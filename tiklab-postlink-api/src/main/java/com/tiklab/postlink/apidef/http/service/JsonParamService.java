package com.tiklab.postlink.apidef.http.service;

import com.tiklab.core.page.Pagination;

import com.tiklab.postlink.apidef.http.model.JsonParam;
import com.tiklab.postlink.apidef.http.model.JsonParamQuery;
import com.tiklab.join.annotation.FindList;
import com.tiklab.join.annotation.JoinProvider;
import com.tiklab.join.annotation.FindAll;
import com.tiklab.join.annotation.FindOne;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@JoinProvider(model = JsonParam.class)
public interface JsonParamService {

    /**
    * 创建用户
    * @param jsonParam
    * @return
    */
    String createJsonParam(@NotNull @Valid JsonParam jsonParam);

    /**
    * 更新用户
    * @param jsonParam
    */
    void updateJsonParam(@NotNull @Valid JsonParam jsonParam);

    /**
    * 删除用户
    * @param id
    */
    void deleteJsonParam(@NotNull String id);

    @FindOne
    JsonParam findOne(@NotNull String id);

    @FindList
    List<JsonParam> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    JsonParam findJsonParam(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<JsonParam> findAllJsonParam();

    /**
    * 查询列表
    * @param jsonParamQuery
    * @return
    */
    List<JsonParam> findJsonParamList(JsonParamQuery jsonParamQuery);

    /**
    * 按分页查询
    * @param jsonParamQuery
    * @return
    */
    Pagination<JsonParam> findJsonParamPage(JsonParamQuery jsonParamQuery);

    /**
     * 查询列表树
     * @param jsonParamQuery
     * @return
     */
    List<JsonParam> findJsonParamListTree(JsonParamQuery jsonParamQuery);

}