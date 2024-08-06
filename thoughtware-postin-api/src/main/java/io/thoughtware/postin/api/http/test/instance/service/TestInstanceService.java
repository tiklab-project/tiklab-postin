package io.thoughtware.postin.api.http.test.instance.service;

import io.thoughtware.postin.api.http.test.instance.model.HttpInstance;
import io.thoughtware.postin.api.http.test.instance.model.HttpInstanceQuery;
import io.thoughtware.core.page.Pagination;

import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

/**
* 用户服务接口
*/
@JoinProvider(model = HttpInstance.class)
public interface TestInstanceService {

    /**
    * 创建用户
    * @param httpInstance
    * @return
    */
    String createTestInstance(@NotNull @Valid HttpInstance httpInstance);

    String createTestInstanceWithNest(@NotNull @Valid HttpInstance httpInstance);

    /**
    * 更新用户
    * @param httpInstance
    */
    void updateTestInstance(@NotNull @Valid HttpInstance httpInstance);

    /**
    * 删除用户
    * @param id
    */
    void deleteTestInstance(@NotNull String id);

    void deleteAllTestInstance(@NotNull String workspaceId);

    @FindOne
    HttpInstance findOne(@NotNull String id);

    @FindList
    List<HttpInstance> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    HttpInstance findTestInstance(@NotNull String id);

    HttpInstance findTestInstanceWithNest(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<HttpInstance> findAllTestInstance();

    /**
    * 查询列表
    * @param httpInstanceQuery
    * @return
    */
    List<HttpInstance> findTestInstanceList(HttpInstanceQuery httpInstanceQuery);

    /**
    * 按分页查询
    * @param httpInstanceQuery
    * @return
    */
    HashMap<String, List<HttpInstance>>  findTestInstanceGroupByCreateTime(HttpInstanceQuery httpInstanceQuery);


}