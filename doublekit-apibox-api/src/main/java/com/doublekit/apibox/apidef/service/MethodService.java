package com.doublekit.apibox.apidef.service;

import com.doublekit.apibox.apidef.model.VersionMethod;
import com.doublekit.apibox.apidef.model.VersionMethodQuery;
import com.doublekit.common.Pagination;

import com.doublekit.apibox.apidef.model.MethodEx;
import com.doublekit.apibox.apidef.model.MethodExQuery;
import com.doublekit.join.annotation.FindList;
import com.doublekit.join.annotation.Provider;
import com.doublekit.join.annotation.FindAll;
import com.doublekit.join.annotation.FindOne;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

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
    /**
     * 添加历史版本
     * @param methodEx
     * @return
     */
    String addHistoryVersion(MethodEx methodEx);
    /**
     * 版本对比
     * @param versionMethodQuery
     * @return
     */
    Map contrastVersion(VersionMethodQuery versionMethodQuery);
    /**
     * 版本查询
     * @param methodExQuery
     * @return
     */
    Pagination<MethodEx> findMethodVersionPage(MethodExQuery methodExQuery);
    /**
     * 版本详情查询
     * @param versionId
     * @return
     */
    VersionMethod queryVersiondetail(String versionId);
}