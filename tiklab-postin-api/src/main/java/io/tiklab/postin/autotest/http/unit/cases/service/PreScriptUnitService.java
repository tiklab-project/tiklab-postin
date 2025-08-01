package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.autotest.http.unit.cases.model.PreScriptUnit;
import io.tiklab.postin.autotest.http.unit.cases.model.PreScriptUnitQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 前置脚本 服务接口
*/
public interface PreScriptUnitService {

    /**
    * 创建前置脚本
    * @param preScriptUnit
    * @return
    */
    String createPreScript(@NotNull @Valid PreScriptUnit preScriptUnit);

    /**
    * 更新前置脚本
    * @param preScriptUnit
    */
    void updatePreScript(@NotNull @Valid PreScriptUnit preScriptUnit);

    /**
    * 删除前置脚本
    * @param id
    */
    void deletePreScript(@NotNull String id);

    PreScriptUnit findOne(@NotNull String id);

    List<PreScriptUnit> findList(List<String> idList);

    /**
    * 根据id查找前置脚本
    * @param id
    * @return
    */
    PreScriptUnit findPreScript(@NotNull String id);

    /**
    * 查找所有前置脚本
    * @return
    */
    List<PreScriptUnit> findAllPreScript();

    /**
    * 根据查询参数查询前置脚本列表
    * @param preScriptUnitQuery
    * @return
    */
    List<PreScriptUnit> findPreScriptList(PreScriptUnitQuery preScriptUnitQuery);

    /**
    * 根据查询参数按分页查询前置脚本
    * @param preScriptUnitQuery
    * @return
    */
    Pagination<PreScriptUnit> findPreScriptPage(PreScriptUnitQuery preScriptUnitQuery);

}