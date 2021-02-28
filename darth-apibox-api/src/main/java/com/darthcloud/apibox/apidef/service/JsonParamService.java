package com.darthcloud.apibox.apidef.service;

import com.darthcloud.common.Pagination;

import com.darthcloud.apibox.apidef.model.JsonParam;
import com.darthcloud.apibox.apidef.model.JsonParamQuery;
import com.darthcloud.join.annotation.Provider;
import com.darthcloud.join.annotation.FindAll;
import com.darthcloud.join.annotation.FindOne;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@Provider(model = JsonParam.class)
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

    /**
    * 查找用户
    * @param id
    * @return
    */
    @FindOne
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
    Pagination<List<JsonParam>> findJsonParamPage(JsonParamQuery jsonParamQuery);

    /**
     * 查询列表树
     * @param jsonParamQuery
     * @return
     */
    List<JsonParam> findJsonParamListTree(JsonParamQuery jsonParamQuery);

}