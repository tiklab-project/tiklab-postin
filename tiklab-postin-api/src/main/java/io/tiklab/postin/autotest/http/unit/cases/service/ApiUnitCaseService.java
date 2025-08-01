package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCase;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCaseDataConstruction;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCaseQuery;
import io.tiklab.postin.autotest.test.model.TestCaseQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口单元 服务接口
*/
@JoinProvider(model = ApiUnitCase.class)
public interface ApiUnitCaseService {

    /**
    * 创建接口单元
    * @param unitCase
    * @return
    */
    String createApiUnitCase(@NotNull @Valid ApiUnitCase unitCase);

    /**
    * 更新接口单元
    * @param unitCase
    */
    void updateApiUnitCase(@NotNull @Valid ApiUnitCase unitCase);

    /**
    * 删除接口单元
    * @param id
    */
    void deleteApiUnitCase(@NotNull String id);

    @FindOne
    ApiUnitCase findOne(@NotNull String id);

    @FindList
    List<ApiUnitCase> findList(List<String> idList);

    /**
    * 通过id查找接口单元
    * @param id
    * @return
    */
    ApiUnitCase findApiUnitCase(@NotNull String id);

    /**
    * 查找所有接口单元
    * @return
    */
    @FindAll
    List<ApiUnitCase> findAllApiUnitCase();

    /**
    * 根据查询参数查询接口单元列表
    * @param apiUnitCaseQuery
    * @return
    */
    List<ApiUnitCase> findApiUnitCaseList(ApiUnitCaseQuery apiUnitCaseQuery);

    /**
     * 判断是否被接口场景绑定
     * @param id
     * @return
     */
    Boolean isApiUnitBind(String id);

    /**
    * 根据查询参数按分页查询接口单元
    * @param apiUnitCaseQuery
    * @return
    */
    Pagination<ApiUnitCase> findApiUnitCasePage(ApiUnitCaseQuery apiUnitCaseQuery);

    /**
     * 用于封装单元数据
     */
    ApiUnitCaseDataConstruction findApiUnitCaseExt(ApiUnitCase apiUnitCase);

    /**
     * 通过testCaseQuery查询
     * @param testCaseQuery
     * @return
     */
     List<ApiUnitCase> findApiUnitCaseListByTestCase(TestCaseQuery testCaseQuery);

}