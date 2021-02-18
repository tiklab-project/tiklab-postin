package com.darthcloud.apibox.api.apidef.service;

import com.darthcloud.common.Pagination;

import com.darthcloud.apibox.api.apidef.model.MethodEx;
import com.darthcloud.apibox.api.apidef.model.MethodExQuery;
import com.darthcloud.join.annotation.Provider;
import com.darthcloud.join.annotation.QueryAll;
import com.darthcloud.join.annotation.QueryOne;

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

    /**
    * 查找用户
    * @param id
    * @return
    */
    @QueryOne
    MethodEx findMethod(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @QueryAll
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
    Pagination<List<MethodEx>> findMethodPage(MethodExQuery methodExQuery);

}