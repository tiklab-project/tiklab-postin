package io.tiklab.postin.api.http.definition.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.support.dbconnect.model.DatabaseConnect;
import io.tiklab.toolkit.beans.annotation.Mapper;

import java.util.List;


/**
 *  数据库操作 模型
 */
@ApiModel
@Mapper
public class OperateDatabase extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private String id;

    @ApiProperty(name="operationId",desc="所属操作id")
    private String operationId;

    @ApiProperty(name="dbConnectId",desc="数据库连接id")
    private String dbConnectId;

    @ApiProperty(name="sqlText",desc="sql_text")
    private String sqlText;

    @ApiProperty(name="isConsolePrint",desc="控制台是否打印")
    private Integer isConsolePrint;

    private DatabaseConnect dbConnect;
    private List<OperateDatabaseVariable> variableList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getDbConnectId() {
        return dbConnectId;
    }

    public void setDbConnectId(String dbConnectId) {
        this.dbConnectId = dbConnectId;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public Integer getIsConsolePrint() {
        return isConsolePrint;
    }

    public void setIsConsolePrint(Integer isConsolePrint) {
        this.isConsolePrint = isConsolePrint;
    }

    public DatabaseConnect getDbConnect() {
        return dbConnect;
    }

    public void setDbConnect(DatabaseConnect dbConnect) {
        this.dbConnect = dbConnect;
    }

    public List<OperateDatabaseVariable> getVariableList() {
        return variableList;
    }

    public void setVariableList(List<OperateDatabaseVariable> variableList) {
        this.variableList = variableList;
    }
}
