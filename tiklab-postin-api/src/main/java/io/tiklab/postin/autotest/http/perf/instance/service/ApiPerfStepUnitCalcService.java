package io.tiklab.postin.autotest.http.perf.instance.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfStepUnitCalc;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfStepUnitCalcQuery;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口性能历史服务接口
*/
@JoinProvider(model = ApiPerfStepUnitCalc.class)
public interface ApiPerfStepUnitCalcService {

    /**
    * 创建接口性能历史
    * @param perfInstance
    * @return
    */
    String createApiPerfStepUnitCalc(@NotNull @Valid ApiPerfStepUnitCalc perfInstance);

    /**
    * 更新
    * @param perfInstance
    */
    void updateApiPerfStepUnitCalc(@NotNull @Valid ApiPerfStepUnitCalc perfInstance);

    /**
    * 删除接口性能历史
    * @param id
    */
    void deleteApiPerfStepUnitCalc(@NotNull String id);

    void deleteAllApiPerfStepUnitCalc(@NotNull String apiPerfStepInstanceId);

    @FindOne
    ApiPerfStepUnitCalc findOne(@NotNull String id);
    @FindList
    List<ApiPerfStepUnitCalc> findList(List<String> idList);

    /**
    * 查找接口性能历史
    * @param id
    * @return
    */
    ApiPerfStepUnitCalc findApiPerfStepUnitCalc(@NotNull String id);

    /**
    * 查找所有接口性能历史
    * @return
    */
    List<ApiPerfStepUnitCalc> findAllApiPerfStepUnitCalc();

    /**
    * 查询列表接口性能历史
    * @param ApiPerfStepUnitCalcQuery
    * @return
    */
    List<ApiPerfStepUnitCalc> findApiPerfStepUnitCalcList(ApiPerfStepUnitCalcQuery ApiPerfStepUnitCalcQuery);

    /**
    * 按分页查询接口性能历史
    * @param ApiPerfStepUnitCalcQuery
    * @return
    */
    Pagination<ApiPerfStepUnitCalc> findApiPerfStepUnitCalcPage(ApiPerfStepUnitCalcQuery ApiPerfStepUnitCalcQuery);

}