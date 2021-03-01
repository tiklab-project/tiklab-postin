package com.darthcloud.apibox.apitest.service;

import com.darthcloud.common.Pagination;

import com.darthcloud.apibox.apitest.model.Testcase;
import com.darthcloud.apibox.apitest.model.TestcaseQuery;
import com.darthcloud.join.annotation.FindList;
import com.darthcloud.join.annotation.FindOne;
import com.darthcloud.join.annotation.Provider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@Provider(model = Testcase.class)
public interface TestcaseService {

    /**
    * 创建用户
    * @param testcase
    * @return
    */
    String createTestcase(@NotNull @Valid Testcase testcase);

    /**
    * 更新用户
    * @param testcase
    */
    void updateTestcase(@NotNull @Valid Testcase testcase);

    /**
    * 删除用户
    * @param id
    */
    void deleteTestcase(@NotNull String id);

    @FindOne
    Testcase findOne(@NotNull String id);

    @FindList
    List<Testcase> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    Testcase findTestcase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<Testcase> findAllTestcase();

    /**
    * 查询列表
    * @param testcaseQuery
    * @return
    */
    List<Testcase> findTestcaseList(TestcaseQuery testcaseQuery);

    /**
    * 按分页查询
    * @param testcaseQuery
    * @return
    */
    Pagination<List<Testcase>> findTestcasePage(TestcaseQuery testcaseQuery);

}