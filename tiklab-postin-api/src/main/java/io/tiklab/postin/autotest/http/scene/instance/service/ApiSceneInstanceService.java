package io.tiklab.postin.autotest.http.scene.instance.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.common.stepcommon.model.StepCommonInstance;
import io.tiklab.postin.autotest.http.scene.execute.model.ApiSceneTestResponse;
import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneInstance;
import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneInstanceQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口场景历史实例 服务接口
*/
@JoinProvider(model = ApiSceneInstance.class)
public interface ApiSceneInstanceService {

    /**
    * 创建接口场景历史实例
    * @param scenInstance
    * @return
    */
    String createApiSceneInstance(@NotNull @Valid ApiSceneInstance scenInstance);

    /**
    * 更新
    * @param scenInstance
    */
    void updateApiSceneInstance(@NotNull @Valid ApiSceneInstance scenInstance);

    /**
    * 删除接口场景历史实例
    * @param id
    */
    void deleteApiSceneInstance(@NotNull String id);

    @FindOne
    ApiSceneInstance findOne(@NotNull String id);

    @FindList
    List<ApiSceneInstance> findList(List<String> idList);

    /**
    * 通过id查找接口场景历史实例
    * @param id
    * @return
    */
    ApiSceneInstance findApiSceneInstance(@NotNull String id);

    /**
    * 查找所有接口场景历史实例
    * @return
    */
    @FindAll
    List<ApiSceneInstance> findAllApiSceneInstance();

    /**
    * 根据查询参数查询接口场景历史实例列表
    * @param scenInstanceQuery
    * @return
    */
    List<ApiSceneInstance> findApiSceneInstanceList(ApiSceneInstanceQuery scenInstanceQuery);

    /**
    * 根据查询按分页查询接口场景历史实例
    * @param scenInstanceQuery
    * @return
    */
    Pagination<ApiSceneInstance> findApiSceneInstancePage(ApiSceneInstanceQuery scenInstanceQuery);


    /**
     * 保存接口场景用例历史到数据库
     * @param apiSceneInstance
     * @param apiSceneTestResponse
     * @return
     */
    String saveApiSceneInstanceToSql(ApiSceneInstance apiSceneInstance, ApiSceneTestResponse apiSceneTestResponse);

    void createWebStepInstance(List<StepCommonInstance> stepCommonInstanceList, String apiSceneInstanceId);

}