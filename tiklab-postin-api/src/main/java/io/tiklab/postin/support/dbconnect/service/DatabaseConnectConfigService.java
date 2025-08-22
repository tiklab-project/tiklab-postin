package io.tiklab.postin.support.dbconnect.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.postin.api.apix.model.RequestHeader;
import io.tiklab.postin.support.dbconnect.model.DatabaseConnectConfig;
import io.tiklab.postin.support.dbconnect.model.DatabaseConnectConfigQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 数据库连接配置 服务接口
*/
@JoinProvider(model = RequestHeader.class)
public interface DatabaseConnectConfigService {

    /**
    * 创建数据库连接配置
    * @param databaseConnectConfig
    * @return
    */
    String createDatabaseConnectConfig(@NotNull @Valid DatabaseConnectConfig databaseConnectConfig);

    /**
    * 更新数据库连接配置
    * @param databaseConnectConfig
    */
    void updateDatabaseConnectConfig(@NotNull @Valid DatabaseConnectConfig databaseConnectConfig);

    /**
    * 删除数据库连接配置
    * @param id
    */
    void deleteDatabaseConnectConfig(@NotNull String id);


    /**
     * 通过dbConnectId删除所有数据库连接配置
     * @param dbConnectId
     */
    void deleteAllDatabaseConnectConfig(@NotNull String dbConnectId);


    /**
    * 查找数据库连接配置
    * @param id
    * @return
    */
    @FindOne
    DatabaseConnectConfig findDatabaseConnectConfig(@NotNull String id);

    /**
    * 查找数据库连接配置
    * @return
    */
    @FindAll
    List<DatabaseConnectConfig> findAllDatabaseConnectConfig();

    /**
    * 查询列表数据库连接配置
    * @param databaseConnectConfigQuery
    * @return
    */
    List<DatabaseConnectConfig> findDatabaseConnectConfigList(DatabaseConnectConfigQuery databaseConnectConfigQuery);

    /**
    * 按分页查询数据库连接配置
    * @param databaseConnectConfigQuery
    * @return
    */
    Pagination<DatabaseConnectConfig> findDatabaseConnectConfigPage(DatabaseConnectConfigQuery databaseConnectConfigQuery);

}