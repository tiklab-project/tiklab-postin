package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.api.apix.model.RequestHeader;
import io.tiklab.postin.api.http.definition.model.OperateDatabaseVariable;
import io.tiklab.postin.api.http.definition.model.OperateDatabaseVariableQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 数据库操作中的环境 服务接口
*/
@JoinProvider(model = RequestHeader.class)
public interface OperateDatabaseVariableService {

    /**
    * 创建数据库操作中的环境
    * @param operateDatabaseVariable
    * @return
    */
    String createOperateDatabaseVariable(@NotNull @Valid OperateDatabaseVariable operateDatabaseVariable);

    /**
    * 更新数据库操作中的环境
    * @param operateDatabaseVariable
    */
    void updateOperateDatabaseVariable(@NotNull @Valid OperateDatabaseVariable operateDatabaseVariable);

    /**
    * 删除数据库操作中的环境
    * @param id
    */
    void deleteOperateDatabaseVariable(@NotNull String id);


    /**
     * 通过operationId删除所有数据库操作中的环境
     * @param operationId
     */
    void deleteAllOperateDatabaseVariable(@NotNull String operationId);


    /**
    * 查找数据库操作中的环境
    * @param id
    * @return
    */
    @FindOne
    OperateDatabaseVariable findOperateDatabaseVariable(@NotNull String id);

    /**
    * 查找数据库操作中的环境
    * @return
    */
    @FindAll
    List<OperateDatabaseVariable> findAllOperateDatabaseVariable();

    /**
    * 查询列表数据库操作中的环境
    * @param operateDatabaseVariableQuery
    * @return
    */
    List<OperateDatabaseVariable> findOperateDatabaseVariableList(OperateDatabaseVariableQuery operateDatabaseVariableQuery);

    /**
    * 按分页查询数据库操作中的环境
    * @param operateDatabaseVariableQuery
    * @return
    */
    Pagination<OperateDatabaseVariable> findOperateDatabaseVariablePage(OperateDatabaseVariableQuery operateDatabaseVariableQuery);

}