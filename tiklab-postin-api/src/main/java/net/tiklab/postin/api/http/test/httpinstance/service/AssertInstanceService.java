package net.tiklab.postin.api.http.test.httpinstance.service;

import net.tiklab.postin.api.http.test.httpinstance.model.AssertInstance;
import net.tiklab.postin.api.http.test.httpinstance.model.AssertInstanceQuery;
import net.tiklab.core.page.Pagination;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface AssertInstanceService {

    /**
    * 创建用户
    * @param assertInstance
    * @return
    */
    String createAssertInstance(@NotNull @Valid AssertInstance assertInstance);

    /**
    * 更新用户
    * @param assertInstance
    */
    void updateAssertInstance(@NotNull @Valid AssertInstance assertInstance);

    /**
    * 删除用户
    * @param id
    */
    void deleteAssertInstance(@NotNull String id);

    AssertInstance findOne(@NotNull String id);

    List<AssertInstance> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    AssertInstance findAssertInstance(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<AssertInstance> findAllAssertInstance();

    /**
    * 查询列表
    * @param assertInstanceQuery
    * @return
    */
    List<AssertInstance> findAssertInstanceList(AssertInstanceQuery assertInstanceQuery);

    /**
    * 按分页查询
    * @param assertInstanceQuery
    * @return
    */
    Pagination<AssertInstance> findAssertInstancePage(AssertInstanceQuery assertInstanceQuery);

}