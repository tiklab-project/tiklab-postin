package com.doublekit.apibox.apitest.apiinstance.service;

import com.doublekit.apibox.apitest.apiinstance.model.TestInstance;
import com.doublekit.apibox.apitest.apiinstance.model.TestInstanceQuery;
import com.doublekit.common.page.Pagination;

import com.doublekit.join.annotation.FindList;
import com.doublekit.join.annotation.FindOne;
import com.doublekit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@JoinProvider(model = TestInstance.class)
public interface TestInstanceService {

    /**
    * 创建用户
    * @param testInstance
    * @return
    */
    String createTestInstance(@NotNull @Valid TestInstance testInstance);

    String createTestInstanceWithNest(@NotNull @Valid TestInstance testInstance);

    /**
    * 更新用户
    * @param testInstance
    */
    void updateTestInstance(@NotNull @Valid TestInstance testInstance);

    /**
    * 删除用户
    * @param id
    */
    void deleteTestInstance(@NotNull String id);

    @FindOne
    TestInstance findOne(@NotNull String id);

    @FindList
    List<TestInstance> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    TestInstance findTestInstance(@NotNull String id);

    TestInstance findTestInstanceWithNest(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<TestInstance> findAllTestInstance();

    /**
    * 查询列表
    * @param testInstanceQuery
    * @return
    */
    List<TestInstance> findTestInstanceList(TestInstanceQuery testInstanceQuery);

    /**
    * 按分页查询
    * @param testInstanceQuery
    * @return
    */
    Pagination<TestInstance> findTestInstancePage(TestInstanceQuery testInstanceQuery);

}