package io.tiklab.postin.api.http.test.cases.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.postin.api.http.test.cases.model.AssertCases;
import io.tiklab.postin.api.http.test.cases.model.AssertCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface AssertCaseService {

    /**
    * 创建用户
    * @param assertCases
    * @return
    */
    String createAssertCase(@NotNull @Valid AssertCases assertCases);

    /**
    * 更新用户
    * @param assertCases
    */
    void updateAssertCase(@NotNull @Valid AssertCases assertCases);

    /**
    * 删除用户
    * @param id
    */
    void deleteAssertCase(@NotNull String id);

    AssertCases findOne(@NotNull String id);

    List<AssertCases> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    AssertCases findAssertCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<AssertCases> findAllAssertCase();

    /**
    * 查询列表
    * @param assertCaseQuery
    * @return
    */
    List<AssertCases> findAssertCaseList(AssertCaseQuery assertCaseQuery);

    /**
    * 按分页查询
    * @param assertCaseQuery
    * @return
    */
    Pagination<AssertCases> findAssertCasePage(AssertCaseQuery assertCaseQuery);

}