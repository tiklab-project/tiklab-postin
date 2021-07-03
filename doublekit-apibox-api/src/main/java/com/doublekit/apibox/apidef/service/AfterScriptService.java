package com.doublekit.apibox.apidef.service;

import com.doublekit.common.Pagination;

import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apidef.model.AfterScriptQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface AfterScriptService {

    /**
    * 创建用户
    * @param afterScript
    * @return
    */
    String createAfterScript(@NotNull @Valid AfterScript afterScript);

    /**
    * 更新用户
    * @param afterScript
    */
    void updateAfterScript(@NotNull @Valid AfterScript afterScript);

    /**
    * 删除用户
    * @param id
    */
    void deleteAfterScript(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    AfterScript findAfterScript(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<AfterScript> findAllAfterScript();

    /**
    * 查询列表
    * @param afterScriptQuery
    * @return
    */
    List<AfterScript> findAfterScriptList(AfterScriptQuery afterScriptQuery);

    /**
    * 按分页查询
    * @param afterScriptQuery
    * @return
    */
    Pagination<AfterScript> findAfterScriptPage(AfterScriptQuery afterScriptQuery);

}