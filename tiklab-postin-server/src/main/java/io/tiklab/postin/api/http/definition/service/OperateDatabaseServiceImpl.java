package io.tiklab.postin.api.http.definition.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.http.definition.dao.OperateDatabaseDao;
import io.tiklab.postin.api.http.definition.entity.OperateDatabaseEntity;
import io.tiklab.postin.api.http.definition.model.OperateDatabase;
import io.tiklab.postin.api.http.definition.model.OperateDatabaseQuery;
import io.tiklab.postin.api.http.definition.model.OperateDatabaseVariable;
import io.tiklab.postin.api.http.definition.model.OperateDatabaseVariableQuery;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.common.PostInUnit;
import io.tiklab.postin.support.dbconnect.model.DatabaseConnect;
import io.tiklab.postin.support.dbconnect.model.DatabaseConnectConfig;
import io.tiklab.postin.support.dbconnect.service.DatabaseConnectService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.*;
import java.util.*;

/**
 * 数据库操作 服务
 */
@Service
public class OperateDatabaseServiceImpl implements OperateDatabaseService {

    @Autowired
    OperateDatabaseDao operateDatabaseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    OperateDatabaseVariableService operateDatabaseVariableService;

    @Autowired
    PostInUnit postInUnit;

    @Autowired
    DatabaseConnectService databaseConnectService;

    @Override
    public String createOperateDatabase(@NotNull @Valid OperateDatabase operateDatabase) {
        OperateDatabaseEntity operateDatabaseEntity = BeanMapper.map(operateDatabase, OperateDatabaseEntity.class);
        String id = operateDatabaseDao.createOperateDatabase(operateDatabaseEntity);
        return id;
    }

    @Override
    public void updateOperateDatabase(@NotNull @Valid OperateDatabase operateDatabase) {
        OperateDatabaseEntity operateDatabaseEntity = BeanMapper.map(operateDatabase, OperateDatabaseEntity.class);

        operateDatabaseDao.updateOperateDatabase(operateDatabaseEntity);

    }

    @Override
    public void deleteOperateDatabase(@NotNull String id) {
        operateDatabaseDao.deleteOperateDatabase(id);

        operateDatabaseVariableService.deleteAllOperateDatabaseVariable(id);
    }

    @Override
    public void deleteAllOperateDatabase(String id) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(OperateDatabaseEntity.class)
                .eq("apiId", id)
                .get();
        operateDatabaseDao.deleteOperateDatabaseList(deleteCondition);
    }

    @Override
    public OperateDatabase findOperateDatabase(@NotNull String id) {
        OperateDatabaseEntity operateDatabaseEntity = operateDatabaseDao.findOperateDatabase(id);
        OperateDatabase operateDatabase = BeanMapper.map(operateDatabaseEntity, OperateDatabase.class);

        String dbConnectId = operateDatabase.getDbConnectId();
        if(dbConnectId != null){
            DatabaseConnect databaseConnect = databaseConnectService.findDatabaseConnect(dbConnectId);
            operateDatabase.setDbConnect(databaseConnect);

            // 变量
            OperateDatabaseVariableQuery operateDatabaseVariableQuery = new OperateDatabaseVariableQuery();
            operateDatabaseVariableQuery.setOperationId(operateDatabase.getOperationId());
            List<OperateDatabaseVariable> operateDatabaseVariableList = operateDatabaseVariableService.findOperateDatabaseVariableList(operateDatabaseVariableQuery);
            if(operateDatabaseVariableList != null){
                operateDatabase.setVariableList(operateDatabaseVariableList);
            }
        }

        return operateDatabase;
    }

    @Override
    public List<OperateDatabase> findAllOperateDatabase() {
        List<OperateDatabaseEntity> operateDatabaseEntityList =  operateDatabaseDao.findAllOperateDatabase();

        List<OperateDatabase> operateDatabaseList = BeanMapper.mapList(operateDatabaseEntityList, OperateDatabase.class);

        return operateDatabaseList;
    }

    @Override
    public List<OperateDatabase> findOperateDatabaseList(OperateDatabaseQuery operateDatabaseQuery) {
        List<OperateDatabaseEntity> operateDatabaseEntityList = operateDatabaseDao.findOperateDatabaseList(operateDatabaseQuery);

        List<OperateDatabase> operateDatabaseList = BeanMapper.mapList(operateDatabaseEntityList, OperateDatabase.class);


        if(operateDatabaseList == null){
            return Collections.emptyList();
        }

        for(OperateDatabase operateDatabase : operateDatabaseList){
            String dbConnectId = operateDatabase.getDbConnectId();
            if(dbConnectId != null){
                DatabaseConnect databaseConnect = databaseConnectService.findDatabaseConnect(dbConnectId);
                operateDatabase.setDbConnect(databaseConnect);
            }

            // 变量
            OperateDatabaseVariableQuery operateDatabaseVariableQuery = new OperateDatabaseVariableQuery();
            operateDatabaseVariableQuery.setOperationId(operateDatabase.getOperationId());
            List<OperateDatabaseVariable> operateDatabaseVariableList = operateDatabaseVariableService.findOperateDatabaseVariableList(operateDatabaseVariableQuery);
            if(operateDatabaseVariableList != null){
                operateDatabase.setVariableList(operateDatabaseVariableList);
            }
        }


        return operateDatabaseList;
    }

    @Override
    public Pagination<OperateDatabase> findOperateDatabasePage(OperateDatabaseQuery operateDatabaseQuery) {

        Pagination<OperateDatabaseEntity>  pagination = operateDatabaseDao.findOperateDatabasePage(operateDatabaseQuery);

        List<OperateDatabase> operateDatabaseList = BeanMapper.mapList(pagination.getDataList(), OperateDatabase.class);

        return PaginationBuilder.build(pagination, operateDatabaseList);
    }

    @Override
    public Map<String, Object> executeSql(OperateDatabase operateDatabase) {
        // 参数验证
        String errorMsg = validateParams(operateDatabase);
        if (errorMsg != null) {
            return buildErrorResult(errorMsg);
        }

        DatabaseConnect dbConnect = operateDatabase.getDbConnect();
        DatabaseConnectConfig config = dbConnect.getConfig();
        String url = postInUnit.buildJdbcUrl(dbConnect.getType(), config);

        try (Connection conn = DriverManager.getConnection(
                url, config.getUserName(), config.getPassword());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(operateDatabase.getSqlText())) {

            // 只处理查询结果
            return handleQuery(rs);

        } catch (SQLException e) {
            return buildErrorResult("SQL执行失败: " + e.getMessage());
        } catch (Exception e) {
            return buildErrorResult("系统错误: " + e.getMessage());
        }
    }

    /**
     * 参数验证
     */
    private String validateParams(OperateDatabase operateDatabase) {
        if (operateDatabase == null) return "操作对象为空";
        if (operateDatabase.getDbConnect() == null) return "数据库连接为空";
        if (operateDatabase.getDbConnect().getConfig() == null) return "连接配置为空";
        if (isBlank(operateDatabase.getSqlText())) return "SQL语句为空";
        if (isBlank(operateDatabase.getDbConnect().getType())) return "数据库类型为空";

        DatabaseConnectConfig config = operateDatabase.getDbConnect().getConfig();
        if (isBlank(config.getHost())) return "主机地址为空";
        if (isBlank(config.getDbName())) return "数据库名为空";
        if (isBlank(config.getUserName())) return "用户名为空";

        return null;
    }

    /**
     * 处理查询结果
     */
    private Map<String, Object> handleQuery(ResultSet rs) throws SQLException {
        List<Map<String, Object>> rows = new ArrayList<>();
        ResultSetMetaData meta = rs.getMetaData();
        int colCount = meta.getColumnCount();

        while (rs.next()) {
            Map<String, Object> row = new LinkedHashMap<>();
            for (int i = 1; i <= colCount; i++) {
                row.put(meta.getColumnLabel(i), rs.getObject(i));
            }
            rows.add(row);
        }

        return buildSuccessResult("query", "data", rows);
    }

    /**
     * 构建成功结果
     */
    private Map<String, Object> buildSuccessResult(String type, String dataKey, Object dataValue) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", "success");
        result.put("type", type);
        result.put(dataKey, dataValue);
        return result;
    }

    /**
     * 构建错误结果
     */
    private Map<String, Object> buildErrorResult(String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", "error");
        result.put("type", "error");
        result.put("message", message);
        return result;
    }

    /**
     * 判断字符串是否为空
     */
    private boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }


}