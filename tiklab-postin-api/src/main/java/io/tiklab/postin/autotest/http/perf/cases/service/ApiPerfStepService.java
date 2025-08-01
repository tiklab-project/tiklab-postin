package io.tiklab.postin.autotest.http.perf.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.perf.cases.model.ApiPerfStep;
import io.tiklab.postin.autotest.http.perf.cases.model.ApiPerfStepQuery;
import io.tiklab.postin.autotest.test.model.TestCase;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口性能场景步骤 服务接口
*/
public interface ApiPerfStepService {

    /**
    * 创建接口性能场景步骤
    * @param apiPerfStep
    * @return
    */
    String createApiPerfStep(@NotNull @Valid ApiPerfStep apiPerfStep);

    /**
    * 更新接口性能场景步骤
    * @param apiPerfStep
    */
    void updateApiPerfStep(@NotNull @Valid ApiPerfStep apiPerfStep);

    /**
    * 删除接口性能场景步骤
    * @param id
    */
    void deleteApiPerfStep(@NotNull String id);

    void deleteAllApiPerfStep( String caseId);

    ApiPerfStep findOne(@NotNull String id);

    List<ApiPerfStep> findList(List<String> idList);

    /**
    * 查找接口性能场景步骤
    * @param id
    * @return
    */
    ApiPerfStep findApiPerfStep(@NotNull String id);

    /**
     * 查询步骤数量
     * @param caseId
     * @return
     */
    int findApiPerfStepNum(String caseId);


    /**
     * 判断是否被接口性能绑定
     * @param caseId
     * @return
     */
    Boolean isApiSceneExist(String caseId);

    /**
    * 查找所有接口性能场景步骤
    * @return
    */
    List<ApiPerfStep> findAllApiPerfStep();

    /**
    * 查询列表接口性能场景步骤
    * @param apiPerfStepQuery
    * @return
    */
    List<ApiPerfStep> findApiPerfStepList(ApiPerfStepQuery apiPerfStepQuery);

    /**
    * 按分页查询接口性能场景步骤
    * @param apiPerfStepQuery
    * @return
    */
    Pagination<ApiPerfStep> findApiPerfStepPage(ApiPerfStepQuery apiPerfStepQuery);


    /**
     * 绑定场景步骤
     * @param apiSceneStepList
     * @return
     */
    void bindApiScene(List<ApiPerfStep> apiSceneStepList);


    Pagination<TestCase> findApiPerfStepWillBindCasePage(ApiPerfStepQuery apiPerfStepQuery);

}