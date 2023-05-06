package io.tiklab.postin.support.environment.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.postin.support.environment.model.Environment;
import io.tiklab.postin.support.environment.model.EnvironmentQuery;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口环境 服务接口
*/
@JoinProvider(model = Environment.class)
public interface EnvironmentService {

    /**
    * 创建接口环境
    * @param environment
    * @return
    */
    String createEnvironment(@NotNull @Valid Environment environment);

    /**
    * 更新接口环境
    * @param environment
    */
    void updateEnvironment(@NotNull @Valid Environment environment);

    /**
    * 删除接口环境
    * @param id
    */
    void deleteEnvironment(@NotNull String id);

    /**
     * 通过workspaceId删除
     * @param workspaceId
     */
    void deleteAllEnvironment(@NotNull String workspaceId);


    @FindOne
    Environment findOne(@NotNull String id);

    @FindList
    List<Environment> findList(List<String> idList);

    /**
    * 查找接口环境
    * @param id
    * @return
    */
    Environment findEnvironment(@NotNull String id);

    /**
    * 查找所有接口环境
    * @return
    */
    List<Environment> findAllEnvironment();

    /**
    * 查询列表接口环境
    * @param environmentQuery
    * @return
    */
    List<Environment> findEnvironmentList(EnvironmentQuery environmentQuery);

    /**
    * 按分页查询接口环境
    * @param environmentQuery
    * @return
    */
    Pagination<Environment> findEnvironmentPage(EnvironmentQuery environmentQuery);

}