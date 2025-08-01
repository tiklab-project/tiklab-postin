package io.tiklab.postin.autotest.http.perf.instance.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfStepInstance;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfStepInstanceQuery;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口性能历史服务接口
*/
@JoinProvider(model = ApiPerfStepInstance.class)
public interface ApiPerfStepInstanceService {

    /**
    * 创建接口性能历史
    * @param perfInstance
    * @return
    */
    String createApiPerfStepInstance(@NotNull @Valid ApiPerfStepInstance perfInstance);

    /**
    * 更新
    * @param perfInstance
    */
    void updateApiPerfStepInstance(@NotNull @Valid ApiPerfStepInstance perfInstance);

    /**
    * 删除接口性能历史
    * @param id
    */
    void deleteApiPerfStepInstance(@NotNull String id);
    @FindOne
    ApiPerfStepInstance findOne(@NotNull String id);
    @FindList
    List<ApiPerfStepInstance> findList(List<String> idList);

    /**
    * 查找接口性能历史
    * @param id
    * @return
    */
    ApiPerfStepInstance findApiPerfStepInstance(@NotNull String id);

    /**
    * 查找所有接口性能历史
    * @return
    */
    List<ApiPerfStepInstance> findAllApiPerfStepInstance();

    /**
    * 查询列表接口性能历史
    * @param ApiPerfStepInstanceQuery
    * @return
    */
    List<ApiPerfStepInstance> findApiPerfStepInstanceList(ApiPerfStepInstanceQuery ApiPerfStepInstanceQuery);

    /**
    * 按分页查询接口性能历史
    * @param ApiPerfStepInstanceQuery
    * @return
    */
    Pagination<ApiPerfStepInstance> findApiPerfStepInstancePage(ApiPerfStepInstanceQuery ApiPerfStepInstanceQuery);

}