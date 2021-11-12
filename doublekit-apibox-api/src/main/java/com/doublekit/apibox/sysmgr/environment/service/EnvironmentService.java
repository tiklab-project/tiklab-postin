package com.doublekit.apibox.sysmgr.environment.service;

import com.doublekit.common.page.Pagination;

import com.doublekit.apibox.sysmgr.environment.model.Environment;
import com.doublekit.apibox.sysmgr.environment.model.EnvironmentQuery;
import com.doublekit.join.annotation.FindList;
import com.doublekit.join.annotation.FindOne;
import com.doublekit.join.annotation.Provider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@Provider(model = Environment.class)
public interface EnvironmentService {

    /**
    * 创建用户
    * @param environment
    * @return
    */
    String createEnvironment(@NotNull @Valid Environment environment);

    /**
    * 更新用户
    * @param environment
    */
    void updateEnvironment(@NotNull @Valid Environment environment);

    /**
    * 删除用户
    * @param id
    */
    void deleteEnvironment(@NotNull String id);

    @FindOne
    Environment findOne(@NotNull String id);

    @FindList
    List<Environment> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    Environment findEnvironment(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<Environment> findAllEnvironment();

    /**
    * 查询列表
    * @param environmentQuery
    * @return
    */
    List<Environment> findEnvironmentList(EnvironmentQuery environmentQuery);

    /**
    * 按分页查询
    * @param environmentQuery
    * @return
    */
    Pagination<Environment> findEnvironmentPage(EnvironmentQuery environmentQuery);

}