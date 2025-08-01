package io.tiklab.postin.autotest.http.perf.cases.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.perf.cases.model.ApiPerfTestData;
import io.tiklab.postin.autotest.http.perf.cases.model.ApiPerfTestDataQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口性能测试数据 服务接口
*/
@JoinProvider(model = ApiPerfTestData.class)
public interface ApiPerfTestDataService {

    /**
    * 创建接口性能测试数据
    * @param apiPerfTestData
    * @return
    */
    String createApiPerfTestData(@NotNull @Valid ApiPerfTestData apiPerfTestData);

    /**
    * 更新接口性能测试数据
    * @param apiPerfTestData
    */
    void updateApiPerfTestData(@NotNull @Valid ApiPerfTestData apiPerfTestData);

    /**
    * 删除接口性能测试数据
    * @param id
    */
    void deleteApiPerfTestData(@NotNull String id);

    void deleteAllApiPerfTestData(String caseId);

    @FindOne
    ApiPerfTestData findOne(@NotNull String id);

    @FindList
    List<ApiPerfTestData> findList(List<String> idList);

    /**
    * 查找接口性能测试数据
    * @param id
    * @return
    */
    ApiPerfTestData findApiPerfTestData(@NotNull String id);


    /**
     * 根据用例ID查找测试数据
     * @param caseId
     * @return
     */
    ApiPerfTestData findApiPerfTestDataByCaseId(@NotNull String caseId);


    /**
    * 查找所有接口性能测试数据
    * @return
    */
    @FindAll
    List<ApiPerfTestData> findAllApiPerfTestData();

    /**
    * 查询列表接口性能测试数据
    * @param performanceTestQuery
    * @return
    */
    List<ApiPerfTestData> findApiPerfTestDataList(ApiPerfTestDataQuery performanceTestQuery);

    /**
    * 按分页查询接口性能测试数据
    * @param performanceTestQuery
    * @return
    */
    Pagination<ApiPerfTestData> findApiPerfTestDataPage(ApiPerfTestDataQuery performanceTestQuery);


    /**
     * 获取测试数据
     * @return
     */
    List<JSONObject> getTestData(String caseId);

}