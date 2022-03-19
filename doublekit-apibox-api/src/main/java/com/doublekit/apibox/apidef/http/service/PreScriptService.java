package com.doublekit.apibox.apidef.http.service;

import com.doublekit.common.page.Pagination;

import com.doublekit.apibox.apidef.http.model.PreScript;
import com.doublekit.apibox.apidef.http.model.PreScriptQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface PreScriptService {

    /**
    * 创建用户
    * @param preScript
    * @return
    */
    String createPreScript(@NotNull @Valid PreScript preScript);

    /**
    * 更新用户
    * @param preScript
    */
    void updatePreScript(@NotNull @Valid PreScript preScript);

    /**
    * 删除用户
    * @param id
    */
    void deletePreScript(@NotNull String id);

    /**
    * 查找用户
    * @param id
    * @return
    */
    PreScript findPreScript(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<PreScript> findAllPreScript();

    /**
    * 查询列表
    * @param preScriptQuery
    * @return
    */
    List<PreScript> findPreScriptList(PreScriptQuery preScriptQuery);

    /**
    * 按分页查询
    * @param preScriptQuery
    * @return
    */
    Pagination<PreScript> findPreScriptPage(PreScriptQuery preScriptQuery);

}