package io.tiklab.postin.support.dbconnect.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.common.PostInUnit;
import io.tiklab.postin.support.dbconnect.dao.DatabaseConnectDao;
import io.tiklab.postin.support.dbconnect.entity.DatabaseConnectEntity;
import io.tiklab.postin.support.dbconnect.model.DatabaseConnect;
import io.tiklab.postin.support.dbconnect.model.DatabaseConnectConfig;
import io.tiklab.postin.support.dbconnect.model.DatabaseConnectQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库连接 服务
 */
@Service
public class DatabaseConnectServiceImpl implements DatabaseConnectService {
    private static Logger logger = LoggerFactory.getLogger(DatabaseConnectServiceImpl.class);


    @Autowired
    DatabaseConnectDao databaseConnectDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    DatabaseConnectConfigService databaseConnectConfigService;

    @Autowired
    PostInUnit postInUnit;

    @Override
    public String createDatabaseConnect(@NotNull @Valid DatabaseConnect databaseConnect) {
        String loginId = LoginContext.getLoginId();
        databaseConnect.setUserId(loginId);
        DatabaseConnectEntity databaseConnectEntity = BeanMapper.map(databaseConnect, DatabaseConnectEntity.class);
        String id = databaseConnectDao.createDatabaseConnect(databaseConnectEntity);

        // 创建数据库连接配置
        DatabaseConnectConfig databaseConnectConfig = databaseConnect.getConfig();
        databaseConnectConfig.setId(id);
        databaseConnectConfig.setDbConnectId(id);
        databaseConnectConfigService.createDatabaseConnectConfig(databaseConnectConfig);
  
        return id;
    }

    @Override
    public void updateDatabaseConnect(@NotNull @Valid DatabaseConnect databaseConnect) {
        DatabaseConnectEntity databaseConnectEntity = BeanMapper.map(databaseConnect, DatabaseConnectEntity.class);
        databaseConnectDao.updateDatabaseConnect(databaseConnectEntity);

        DatabaseConnectConfig databaseConnectConfig = databaseConnect.getConfig();
        databaseConnectConfigService.updateDatabaseConnectConfig(databaseConnectConfig);
    }

    @Override
    public void deleteDatabaseConnect(@NotNull String id) {
        databaseConnectDao.deleteDatabaseConnect(id);

        databaseConnectConfigService.deleteDatabaseConnectConfig(id);
    }

    @Override
    public void deleteAllDatabaseConnect(String workspaceId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(DatabaseConnectEntity.class)
                .eq("workspaceId", workspaceId)
                .get();
        databaseConnectDao.deleteDatabaseConnectList(deleteCondition);
    }

    @Override
    public DatabaseConnect findDatabaseConnect(@NotNull String id) {
        DatabaseConnectEntity databaseConnectEntity = databaseConnectDao.findDatabaseConnect(id);
        DatabaseConnect databaseConnect = BeanMapper.map(databaseConnectEntity, DatabaseConnect.class);

        if(databaseConnect == null){
            return null;
        }

        DatabaseConnectConfig databaseConnectConfig = databaseConnectConfigService.findDatabaseConnectConfig(id);
        databaseConnect.setConfig(databaseConnectConfig);


        return databaseConnect;
    }

    @Override
    public List<DatabaseConnect> findAllDatabaseConnect() {
        List<DatabaseConnectEntity> databaseConnectEntityList =  databaseConnectDao.findAllDatabaseConnect();

        List<DatabaseConnect> databaseConnectList = BeanMapper.mapList(databaseConnectEntityList, DatabaseConnect.class);

        return databaseConnectList;
    }

    @Override
    public List<DatabaseConnect> findDatabaseConnectList(DatabaseConnectQuery databaseConnectQuery) {
        List<DatabaseConnectEntity> databaseConnectEntityList = databaseConnectDao.findDatabaseConnectList(databaseConnectQuery);

        List<DatabaseConnect> databaseConnectList = BeanMapper.mapList(databaseConnectEntityList, DatabaseConnect.class);

        if(databaseConnectList != null){
            for (DatabaseConnect databaseConnect : databaseConnectList){
                DatabaseConnectConfig databaseConnectConfig = databaseConnectConfigService.findDatabaseConnectConfig(databaseConnect.getId());
                databaseConnect.setConfig(databaseConnectConfig);
            }
        }

        return databaseConnectList;
    }

    @Override
    public Pagination<DatabaseConnect> findDatabaseConnectPage(DatabaseConnectQuery databaseConnectQuery) {

        Pagination<DatabaseConnectEntity>  pagination = databaseConnectDao.findDatabaseConnectPage(databaseConnectQuery);

        List<DatabaseConnect> databaseConnectList = BeanMapper.mapList(pagination.getDataList(), DatabaseConnect.class);

        return PaginationBuilder.build(pagination, databaseConnectList);
    }

    @Override
    public Boolean testDatabaseConnect( DatabaseConnect databaseConnect) {
        // 仍然建议添加空指针检查
        if (databaseConnect == null || databaseConnect.getConfig() == null) {
            logger.error("数据库连接配置未找到或不完整");
            return false;
        }

        DatabaseConnectConfig config = databaseConnect.getConfig();

        String url = postInUnit.buildJdbcUrl(databaseConnect.getType(), config);

        try (Connection conn = DriverManager.getConnection(url, config.getUserName(), config.getPassword())) {
            return true;
        } catch (SQLException e) {
            logger.error("数据库连接测试失败 - 类型: {}, 主机: {}, 数据库: {}",
                    databaseConnect.getType(), config.getHost(), config.getDbName(), e);
            return false;
        }
    }

}