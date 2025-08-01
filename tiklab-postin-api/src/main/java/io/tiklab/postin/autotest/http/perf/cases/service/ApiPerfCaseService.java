package io.tiklab.postin.autotest.http.perf.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.perf.cases.model.ApiPerfCase;
import io.tiklab.postin.autotest.http.perf.cases.model.ApiPerfCaseQuery;
import io.tiklab.postin.autotest.test.model.TestCaseQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口性能 服务接口
*/
@JoinProvider(model = ApiPerfCase.class)
public interface ApiPerfCaseService {

    /**
    * 创建接口性能
    * @param perfCase
    * @return
    */
    String createApiPerfCase(@NotNull @Valid ApiPerfCase perfCase);

    /**
    * 更新接口性能
    * @param perfCase
    */
    void updateApiPerfCase(@NotNull @Valid ApiPerfCase perfCase);

    /**
    * 删除接口性能
    * @param id
    */
    void deleteApiPerfCase(@NotNull String id);

    @FindOne
    ApiPerfCase findOne(@NotNull String id);

    @FindList
    List<ApiPerfCase> findList(List<String> idList);

    /**
    * 查找接口性能
    * @param id
    * @return
    */
    ApiPerfCase findApiPerfCase(@NotNull String id);

    /**
    * 查找所有接口性能
    * @return
    */
    @FindAll
    List<ApiPerfCase> findAllApiPerfCase();

    /**
    * 查询列表接口性能
    * @param performanceTestQuery
    * @return
    */
    List<ApiPerfCase> findApiPerfCaseList(ApiPerfCaseQuery performanceTestQuery);

    /**
    * 按分页查询接口性能
    * @param performanceTestQuery
    * @return
    */
    Pagination<ApiPerfCase> findApiPerfCasePage(ApiPerfCaseQuery performanceTestQuery);


    /**
     * 通过testCaseQuery查询接口性能
     * @param testCaseQuery
     * @return
     */
    List<ApiPerfCase> findApiPerfCaseListByTestCase(TestCaseQuery testCaseQuery);


}