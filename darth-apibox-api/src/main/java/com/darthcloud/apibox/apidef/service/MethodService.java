package com.darthcloud.apibox.apidef.service;

import com.darthcloud.common.Pagination;

import com.darthcloud.apibox.apidef.model.MethodEx;
import com.darthcloud.apibox.apidef.model.MethodExQuery;
import com.darthcloud.dsl.join.annotation.FindList;
import com.darthcloud.dsl.join.annotation.Provider;
import com.darthcloud.dsl.join.annotation.FindAll;
import com.darthcloud.dsl.join.annotation.FindOne;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@Provider(model = MethodEx.class)
public interface MethodService {

    /**
    * 创建用户
    * @param methodEx
    * @return
    */
    String createMethod(@NotNull @Valid MethodEx methodEx);

    /**
    * 更新用户
    * @param methodEx
    */
    void updateMethod(@NotNull @Valid MethodEx methodEx);

    /**
    * 删除用户
    * @param id
    */
    void deleteMethod(@NotNull String id);

    @FindOne
    MethodEx findOne(@NotNull String id);

    @FindList
    List<MethodEx> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    MethodEx findMethod(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<MethodEx> findAllMethod();

    /**
    * 查询列表
    * @param methodExQuery
    * @return
    */
    List<MethodEx> findMethodList(MethodExQuery methodExQuery);

    /**
    * 按分页查询
    * @param methodExQuery
    * @return
    */
    Pagination<MethodEx> findMethodPage(MethodExQuery methodExQuery);

}