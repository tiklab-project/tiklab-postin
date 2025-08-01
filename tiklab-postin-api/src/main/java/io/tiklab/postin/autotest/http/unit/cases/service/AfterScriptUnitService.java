package io.tiklab.postin.autotest.http.unit.cases.service;


import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.model.AfterScriptUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.AfterScriptUnitQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 后置脚本 服务接口
*/
public interface AfterScriptUnitService {

    /**
    * 创建后置脚本
    * @param afterScriptUnit
    * @return
    */
    String createAfterScript(@NotNull @Valid AfterScriptUnit afterScriptUnit);

    /**
    * 更新后置脚本
    * @param afterScriptUnit
    */
    void updateAfterScript(@NotNull @Valid AfterScriptUnit afterScriptUnit);

    /**
    * 删除后置脚本
    * @param id
    */
    void deleteAfterScript(@NotNull String id);

    AfterScriptUnit findOne(@NotNull String id);

    List<AfterScriptUnit> findList(List<String> idList);

    /**
    * 根据id查找后置脚本
    * @param id
    * @return
    */
    AfterScriptUnit findAfterScript(@NotNull String id);

    /**
    * 查找所有后置脚本
    * @return
    */
    List<AfterScriptUnit> findAllAfterScript();

    /**
    * 通过查询参数查询后置脚本列表
    * @param afterScriptUnitQuery
    * @return
    */
    List<AfterScriptUnit> findAfterScriptList(AfterScriptUnitQuery afterScriptUnitQuery);

    /**
    * 通过查询参数按分页查询后置脚本
    * @param afterScriptUnitQuery
    * @return
    */
    Pagination<AfterScriptUnit> findAfterScriptPage(AfterScriptUnitQuery afterScriptUnitQuery);

}