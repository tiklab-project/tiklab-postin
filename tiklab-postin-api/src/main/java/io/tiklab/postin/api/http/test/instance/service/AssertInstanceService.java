package io.tiklab.postin.api.http.test.instance.service;

import io.tiklab.postin.api.http.test.instance.model.AssertInstances;
import io.tiklab.postin.api.http.test.instance.model.AssertInstanceQuery;
import io.tiklab.core.page.Pagination;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface AssertInstanceService {

    /**
    * 创建用户
    * @param assertInstances
    * @return
    */
    String createAssertInstance(@NotNull @Valid AssertInstances assertInstances);

    /**
    * 更新用户
    * @param assertInstances
    */
    void updateAssertInstance(@NotNull @Valid AssertInstances assertInstances);

    /**
    * 删除用户
    * @param id
    */
    void deleteAssertInstance(@NotNull String id);

    AssertInstances findOne(@NotNull String id);

    List<AssertInstances> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    AssertInstances findAssertInstance(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<AssertInstances> findAllAssertInstance();

    /**
    * 查询列表
    * @param assertInstanceQuery
    * @return
    */
    List<AssertInstances> findAssertInstanceList(AssertInstanceQuery assertInstanceQuery);

    /**
    * 按分页查询
    * @param assertInstanceQuery
    * @return
    */
    Pagination<AssertInstances> findAssertInstancePage(AssertInstanceQuery assertInstanceQuery);

}