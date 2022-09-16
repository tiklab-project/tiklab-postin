package net.tiklab.postin.apidef.http.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.postin.apidef.http.model.PreScript;
import net.tiklab.postin.apidef.http.model.PreScriptQuery;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;

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