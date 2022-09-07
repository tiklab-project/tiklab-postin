package com.tiklab.postin.apitest.http.httpcase.service;

import com.tiklab.core.page.Pagination;

import com.tiklab.postin.apitest.http.httpcase.model.AssertCase;
import com.tiklab.postin.apitest.http.httpcase.model.AssertCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface AssertCaseService {

    /**
    * 创建用户
    * @param assertCase
    * @return
    */
    String createAssertCase(@NotNull @Valid AssertCase assertCase);

    /**
    * 更新用户
    * @param assertCase
    */
    void updateAssertCase(@NotNull @Valid AssertCase assertCase);

    /**
    * 删除用户
    * @param id
    */
    void deleteAssertCase(@NotNull String id);

    AssertCase findOne(@NotNull String id);

    List<AssertCase> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    AssertCase findAssertCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<AssertCase> findAllAssertCase();

    /**
    * 查询列表
    * @param assertCaseQuery
    * @return
    */
    List<AssertCase> findAssertCaseList(AssertCaseQuery assertCaseQuery);

    /**
    * 按分页查询
    * @param assertCaseQuery
    * @return
    */
    Pagination<AssertCase> findAssertCasePage(AssertCaseQuery assertCaseQuery);

}