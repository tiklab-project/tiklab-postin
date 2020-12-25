package com.darthcloud.apibox.apxmethod.service;

import com.darthcloud.common.Pagination;

import com.darthcloud.apibox.apxmethod.model.ApxMethod;
import com.darthcloud.apibox.apxmethod.model.ApxMethodQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface ApxMethodService {

    /**
    * 创建用户
    * @param apxMethod
    * @return
    */
    String createApxMethod(@NotNull @Valid ApxMethod apxMethod);

    /**
    * 更新用户
    * @param apxMethod
    */
    void updateApxMethod(@NotNull @Valid ApxMethod apxMethod);

    /**
    * 删除用户
    * @param id
    */
    void deleteApxMethod(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    ApxMethod findApxMethod(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<ApxMethod> findAllApxMethod();

    /**
    * 查询列表
    * @param apxMethodQuery
    * @return
    */
    List<ApxMethod> findApxMethodList(ApxMethodQuery apxMethodQuery);

    /**
    * 按分页查询
    * @param apxMethodQuery
    * @return
    */
    Pagination<List<ApxMethod>> findApxMethodPage(ApxMethodQuery apxMethodQuery);

}