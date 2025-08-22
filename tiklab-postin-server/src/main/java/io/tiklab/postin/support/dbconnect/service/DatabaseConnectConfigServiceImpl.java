package io.tiklab.postin.support.dbconnect.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.support.dbconnect.dao.DatabaseConnectConfigDao;
import io.tiklab.postin.support.dbconnect.entity.DatabaseConnectConfigEntity;
import io.tiklab.postin.support.dbconnect.model.DatabaseConnectConfig;
import io.tiklab.postin.support.dbconnect.model.DatabaseConnectConfigQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 数据库连接配置 服务
 */
@Service
public class DatabaseConnectConfigServiceImpl implements DatabaseConnectConfigService {

    @Autowired
    DatabaseConnectConfigDao databaseConnectConfigDao;

    @Autowired
    JoinTemplate joinTemplate;


    @Override
    public String createDatabaseConnectConfig(@NotNull @Valid DatabaseConnectConfig databaseConnectConfig) {
        DatabaseConnectConfigEntity databaseConnectConfigEntity = BeanMapper.map(databaseConnectConfig, DatabaseConnectConfigEntity.class);
        String id = databaseConnectConfigDao.createDatabaseConnectConfig(databaseConnectConfigEntity);

        return id;
    }

    @Override
    public void updateDatabaseConnectConfig(@NotNull @Valid DatabaseConnectConfig databaseConnectConfig) {
        DatabaseConnectConfigEntity databaseConnectConfigEntity = BeanMapper.map(databaseConnectConfig, DatabaseConnectConfigEntity.class);

        databaseConnectConfigDao.updateDatabaseConnectConfig(databaseConnectConfigEntity);
    }

    @Override
    public void deleteDatabaseConnectConfig(@NotNull String id) {
        databaseConnectConfigDao.deleteDatabaseConnectConfig(id);
    }

    @Override
    public void deleteAllDatabaseConnectConfig(String dbConnectId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(DatabaseConnectConfigEntity.class)
                .eq("dbConnectId", dbConnectId)
                .get();
        databaseConnectConfigDao.deleteDatabaseConnectConfigList(deleteCondition);
    }

    @Override
    public DatabaseConnectConfig findDatabaseConnectConfig(@NotNull String id) {
        DatabaseConnectConfigEntity databaseConnectConfigEntity = databaseConnectConfigDao.findDatabaseConnectConfig(id);
        DatabaseConnectConfig databaseConnectConfig = BeanMapper.map(databaseConnectConfigEntity, DatabaseConnectConfig.class);
        return databaseConnectConfig;
    }

    @Override
    public List<DatabaseConnectConfig> findAllDatabaseConnectConfig() {
        List<DatabaseConnectConfigEntity> databaseConnectConfigEntityList =  databaseConnectConfigDao.findAllDatabaseConnectConfig();

        List<DatabaseConnectConfig> databaseConnectConfigList = BeanMapper.mapList(databaseConnectConfigEntityList, DatabaseConnectConfig.class);

        return databaseConnectConfigList;
    }

    @Override
    public List<DatabaseConnectConfig> findDatabaseConnectConfigList(DatabaseConnectConfigQuery databaseConnectConfigQuery) {
        List<DatabaseConnectConfigEntity> databaseConnectConfigEntityList = databaseConnectConfigDao.findDatabaseConnectConfigList(databaseConnectConfigQuery);

        List<DatabaseConnectConfig> databaseConnectConfigList = BeanMapper.mapList(databaseConnectConfigEntityList, DatabaseConnectConfig.class);

        return databaseConnectConfigList;
    }

    @Override
    public Pagination<DatabaseConnectConfig> findDatabaseConnectConfigPage(DatabaseConnectConfigQuery databaseConnectConfigQuery) {

        Pagination<DatabaseConnectConfigEntity>  pagination = databaseConnectConfigDao.findDatabaseConnectConfigPage(databaseConnectConfigQuery);

        List<DatabaseConnectConfig> databaseConnectConfigList = BeanMapper.mapList(pagination.getDataList(), DatabaseConnectConfig.class);

        return PaginationBuilder.build(pagination, databaseConnectConfigList);
    }
}