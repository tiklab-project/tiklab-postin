package io.tiklab.postin.api.http.test.cases.service;

import io.tiklab.postin.api.http.test.cases.model.HttpTestcase;
import io.tiklab.postin.api.http.test.cases.model.HttpTestcaseQuery;
import io.tiklab.core.page.Pagination;

import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@JoinProvider(model = HttpTestcase.class)
public interface HttpTestcaseService {

    /**
    * 创建用户
    * @param httpTestcase
    * @return
    */
    String createTestcase(@NotNull @Valid HttpTestcase httpTestcase);

    /**
     * 创建测试用例，级联从表、子表
     * @param httpTestcase
     * @return
     */
    String createTestcaseWithNest(@NotNull @Valid HttpTestcase httpTestcase);

    /**
    * 更新用户
    * @param httpTestcase
    */
    void updateTestcase(@NotNull @Valid HttpTestcase httpTestcase);

    /**
    * 删除用户
    * @param id
    */
    void deleteTestcase(@NotNull String id);

    @FindOne
    HttpTestcase findOne(@NotNull String id);

    @FindList
    List<HttpTestcase> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    HttpTestcase findTestcase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<HttpTestcase> findAllTestcase();

    /**
    * 查询列表
    * @param httpTestcaseQuery
    * @return
    */
    List<HttpTestcase> findTestcaseList(HttpTestcaseQuery httpTestcaseQuery);

    /**
    * 按分页查询
    * @param httpTestcaseQuery
    * @return
    */
    Pagination<HttpTestcase> findTestcasePage(HttpTestcaseQuery httpTestcaseQuery);

}