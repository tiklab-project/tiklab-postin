package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.api.apix.model.RequestHeader;
import io.tiklab.postin.api.http.definition.model.OperateScript;
import io.tiklab.postin.api.http.definition.model.OperateScriptQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 自定义脚本 服务接口
*/
@JoinProvider(model = RequestHeader.class)
public interface OperateScriptService {

    /**
    * 创建自定义脚本
    * @param operateScript
    * @return
    */
    String createOperateScript(@NotNull @Valid OperateScript operateScript);

    /**
    * 更新自定义脚本
    * @param operateScript
    */
    void updateOperateScript(@NotNull @Valid OperateScript operateScript);

    /**
    * 删除自定义脚本
    * @param id
    */
    void deleteOperateScript(@NotNull String id);


    /**
     * 通过operationId删除所有自定义脚本
     * @param operationId
     */
    void deleteAllOperateScript(@NotNull String operationId);


    /**
    * 查找自定义脚本
    * @param id
    * @return
    */
    @FindOne
    OperateScript findOperateScript(@NotNull String id);

    /**
    * 查找自定义脚本
    * @return
    */
    @FindAll
    List<OperateScript> findAllOperateScript();

    /**
    * 查询列表自定义脚本
    * @param operateScriptQuery
    * @return
    */
    List<OperateScript> findOperateScriptList(OperateScriptQuery operateScriptQuery);

    /**
    * 按分页查询自定义脚本
    * @param operateScriptQuery
    * @return
    */
    Pagination<OperateScript> findOperateScriptPage(OperateScriptQuery operateScriptQuery);

}