package io.tiklab.postin.autotest.http.scene.instance.service;


import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneStepInstanceBind;
import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneStepInstanceBindQuery;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口场景下步骤的实例公共表 服务接口
*/
@JoinProvider(model = ApiSceneStepInstanceBind.class)
public interface ApiSceneStepInstanceBindService {

    /**
    * 创建接口场景下步骤的实例公共表
    * @param apiSceneStepInstanceBind
    * @return
    */
    String createApiSceneStepInstanceBind(@NotNull @Valid ApiSceneStepInstanceBind apiSceneStepInstanceBind);

    /**
    * 更新接口场景下步骤的实例公共表
    * @param apiSceneStepInstanceBind
    */
    void updateApiSceneStepInstanceBind(@NotNull @Valid ApiSceneStepInstanceBind apiSceneStepInstanceBind);

    /**
    * 删除接口场景下步骤的实例公共表
    * @param id
    */
    void deleteApiSceneStepInstanceBind(@NotNull String id);

    @FindOne
    ApiSceneStepInstanceBind findOne(@NotNull String id);

    @FindList
    List<ApiSceneStepInstanceBind> findList(List<String> idList);

    /**
    * 查找接口场景下步骤的实例公共表
    * @param id
    * @return
    */
    ApiSceneStepInstanceBind findApiSceneStepInstanceBind(@NotNull String id);

    /**
    * 查找所有接口场景下步骤的实例公共表
    * @return
    */
    @FindList
    List<ApiSceneStepInstanceBind> findAllApiSceneStepInstanceBind();

    /**
    * 查询接口场景下步骤的实例公共表列表
    * @param apiSceneStepInstanceBindQuery
    * @return
    */
    List<ApiSceneStepInstanceBind> findApiSceneStepInstanceBindList(ApiSceneStepInstanceBindQuery apiSceneStepInstanceBindQuery);

    /**
    * 按分页查询接口场景下步骤的实例公共表
    * @param apiSceneStepInstanceBindQuery
    * @return
    */
    Pagination<ApiSceneStepInstanceBind> findApiSceneStepInstanceBindPage(ApiSceneStepInstanceBindQuery apiSceneStepInstanceBindQuery);

}