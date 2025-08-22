package io.tiklab.postin.api.http.definition.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 数据库操作 实体
 */
@Entity
@Table(name="postin_api_request_operate_db")
public class OperateDatabaseEntity implements Serializable {

    @Id
//    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    //所属操作id
    @Column(name = "operation_id")
    private String operationId;

    //数据库连接ID
    @Column(name = "db_connect_id")
    private String dbConnectId;

    //sql_text
    @Column(name = "sql_text")
    private String sqlText;

    //控制台是否打印
    @Column(name = "is_console_print")
    private Integer isConsolePrint;

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
}
