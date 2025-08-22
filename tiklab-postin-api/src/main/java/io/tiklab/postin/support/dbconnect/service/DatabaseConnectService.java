package io.tiklab.postin.support.dbconnect.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.api.apix.model.RequestHeader;
import io.tiklab.postin.support.dbconnect.model.DatabaseConnect;
import io.tiklab.postin.support.dbconnect.model.DatabaseConnectQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 数据库连接 服务接口
*/
@JoinProvider(model = RequestHeader.class)
public interface DatabaseConnectService {

    /**
    * 创建数据库连接
    * @param databaseConnect
    * @return
    */
    String createDatabaseConnect(@NotNull @Valid DatabaseConnect databaseConnect);

    /**
    * 更新数据库连接
    * @param databaseConnect
    */
    void updateDatabaseConnect(@NotNull @Valid DatabaseConnect databaseConnect);

    /**
    * 删除数据库连接
    * @param id
    */
    void deleteDatabaseConnect(@NotNull String id);


    /**
     * 通过workspaceId删除所有数据库连接
     * @param workspaceId
     */
    void deleteAllDatabaseConnect(@NotNull String workspaceId);


    /**
    * 查找数据库连接
    * @param id
    * @return
    */
    @FindOne
    DatabaseConnect findDatabaseConnect(@NotNull String id);

    /**
    * 查找数据库连接
    * @return
    */
    @FindAll
    List<DatabaseConnect> findAllDatabaseConnect();

    /**
    * 查询列表数据库连接
    * @param pathParamQuery
    * @return
    */
    List<DatabaseConnect> findDatabaseConnectList(DatabaseConnectQuery pathParamQuery);

    /**
    * 按分页查询数据库连接
    * @param pathParamQuery
    * @return
    */
    Pagination<DatabaseConnect> findDatabaseConnectPage(DatabaseConnectQuery pathParamQuery);


    Boolean testDatabaseConnect(@NotNull @Valid DatabaseConnect databaseConnect);
}