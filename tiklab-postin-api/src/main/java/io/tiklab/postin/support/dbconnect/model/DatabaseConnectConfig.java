package io.tiklab.postin.support.dbconnect.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;


/**
 *  数据库连接配置 模型
 */
@ApiModel
@Mapper
public class DatabaseConnectConfig extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private String id;

    @ApiProperty(name="dbConnectId",desc="所属连接")
    private String dbConnectId;

    @ApiProperty(name="host",desc="host")
    private String host;

    @ApiProperty(name="port",desc="端口")
    private Integer port;

    @ApiProperty(name="userName",desc="用户名")
    private String userName;

    @ApiProperty(name="password",desc="password")
    private String password;

    @ApiProperty(name="dbName",desc="数据库名称")
    private String dbName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDbConnectId() {
        return dbConnectId;
    }

    public void setDbConnectId(String dbConnectId) {
        this.dbConnectId = dbConnectId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
