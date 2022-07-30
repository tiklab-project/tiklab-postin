package com.tiklab.postin.apidef.http.service;

import com.tiklab.core.page.Pagination;

import com.tiklab.postin.apidef.http.model.PreScript;
import com.tiklab.postin.apidef.http.model.PreScriptQuery;
import com.tiklab.join.annotation.FindAll;
import com.tiklab.join.annotation.FindOne;
import com.tiklab.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
@JoinProvider(model = PreScript.class)
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
    @FindOne
    PreScript findPreScript(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
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