package net.tiklab.postin.apitest.http.httpcase.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.postin.apitest.http.httpcase.model.AfterScriptCase;
import net.tiklab.postin.apitest.http.httpcase.model.AfterScriptCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务接口
*/
public interface AfterScriptCaseService {

    /**
    * 创建用户
    * @param afterScriptCase
    * @return
    */
    String createAfterScriptCase(@NotNull @Valid AfterScriptCase afterScriptCase);

    /**
    * 更新用户
    * @param afterScriptCase
    */
    void updateAfterScriptCase(@NotNull @Valid AfterScriptCase afterScriptCase);

    /**
    * 删除用户
    * @param id
    */
    void deleteAfterScriptCase(@NotNull String id);

    AfterScriptCase findOne(@NotNull String id);

    List<AfterScriptCase> findList(List<String> idList);

    /**
    * 查找用户
    * @param id
    * @return
    */
    AfterScriptCase findAfterScriptCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<AfterScriptCase> findAllAfterScriptCase();

    /**
    * 查询列表
    * @param afterScriptCaseQuery
    * @return
    */
    List<AfterScriptCase> findAfterScriptCaseList(AfterScriptCaseQuery afterScriptCaseQuery);

    /**
    * 按分页查询
    * @param afterScriptCaseQuery
    * @return
    */
    Pagination<AfterScriptCase> findAfterScriptCasePage(AfterScriptCaseQuery afterScriptCaseQuery);

}