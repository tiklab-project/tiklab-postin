package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.api.apix.model.RequestHeader;
import io.tiklab.postin.api.http.definition.model.OperateDatabase;
import io.tiklab.postin.api.http.definition.model.OperateDatabaseQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
* 数据库操作 服务接口
*/
@JoinProvider(model = RequestHeader.class)
public interface OperateDatabaseService {

    /**
    * 创建数据库操作
    * @param operateDatabase
    * @return
    */
    String createOperateDatabase(@NotNull @Valid OperateDatabase operateDatabase);

    /**
    * 更新数据库操作
    * @param operateDatabase
    */
    void updateOperateDatabase(@NotNull @Valid OperateDatabase operateDatabase);

    /**
    * 删除数据库操作
    * @param id
    */
    void deleteOperateDatabase(@NotNull String id);


    /**
     * 通过接口Id删除所有数据库操作
     * @param id
     */
    void deleteAllOperateDatabase(@NotNull String id);


    /**
    * 查找数据库操作
    * @param id
    * @return
    */
    @FindOne
    OperateDatabase findOperateDatabase(@NotNull String id);

    /**
    * 查找数据库操作
    * @return
    */
    @FindAll
    List<OperateDatabase> findAllOperateDatabase();

    /**
    * 查询列表数据库操作
    * @param operateDatabaseQuery
    * @return
    */
    List<OperateDatabase> findOperateDatabaseList(OperateDatabaseQuery operateDatabaseQuery);

    /**
    * 按分页查询数据库操作
    * @param operateDatabaseQuery
    * @return
    */
    Pagination<OperateDatabase> findOperateDatabasePage(OperateDatabaseQuery operateDatabaseQuery);


    Map<String, Object> executeSql(@NotNull @Valid OperateDatabase operateDatabase);

}