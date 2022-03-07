package com.doublekit.apibox.apitest.apicase.service;

import com.doublekit.common.page.Pagination;

import com.doublekit.apibox.apitest.apicase.model.PreScriptCase;
import com.doublekit.apibox.apitest.apicase.model.PreScriptCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface PreScriptCaseService {

    /**
    * 创建用户
    * @param preScriptCase
    * @return
    */
    String createPreScriptCase(@NotNull @Valid PreScriptCase preScriptCase);

    /**
    * 更新用户
    * @param preScriptCase
    */
    void updatePreScriptCase(@NotNull @Valid PreScriptCase preScriptCase);

    /**
    * 删除用户
    * @param id
    */
    void deletePreScriptCase(@NotNull String id);

    PreScriptCase findOne(@NotNull String id);

    List<PreScriptCase> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    PreScriptCase findPreScriptCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<PreScriptCase> findAllPreScriptCase();

    /**
    * 查询列表
    * @param preScriptCaseQuery
    * @return
    */
    List<PreScriptCase> findPreScriptCaseList(PreScriptCaseQuery preScriptCaseQuery);

    /**
    * 按分页查询
    * @param preScriptCaseQuery
    * @return
    */
    Pagination<PreScriptCase> findPreScriptCasePage(PreScriptCaseQuery preScriptCaseQuery);

}