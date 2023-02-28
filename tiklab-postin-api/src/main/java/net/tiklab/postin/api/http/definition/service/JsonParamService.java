package net.tiklab.postin.api.http.definition.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.postin.api.http.definition.model.JsonParam;
import net.tiklab.postin.api.http.definition.model.JsonParamQuery;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindOne;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 请求json 服务接口
*/
@JoinProvider(model = JsonParam.class)
public interface JsonParamService {

    /**
    * 创建json
    * @param jsonParam
    * @return
    */
    String createJsonParam(@NotNull @Valid JsonParam jsonParam);

    /**
    * 更新json
    * @param jsonParam
    */
    void updateJsonParam(@NotNull @Valid JsonParam jsonParam);

    /**
    * 删除json
    * @param id
    */
    void deleteJsonParam(@NotNull String id);

    @FindOne
    JsonParam findOne(@NotNull String id);

    @FindList
    List<JsonParam> findList(List<String> idList);

    /**
    * 查找json
    * @param id
    * @return
    */
    JsonParam findJsonParam(@NotNull String id);

    /**
    * 查找所有json
    * @return
    */
    @FindAll
    List<JsonParam> findAllJsonParam();

    /**
    * 查询列表json
    * @param jsonParamQuery
    * @return
    */
    List<JsonParam> findJsonParamList(JsonParamQuery jsonParamQuery);

    /**
    * 按分页查询json
    * @param jsonParamQuery
    * @return
    */
    Pagination<JsonParam> findJsonParamPage(JsonParamQuery jsonParamQuery);

    /**
     * 查询json列表树
     * @param jsonParamQuery
     * @return
     */
    List<JsonParam> findJsonParamListTree(JsonParamQuery jsonParamQuery);

}