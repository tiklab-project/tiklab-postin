package io.tiklab.postin.support.dbconnect.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 数据库链接管理配置 实体
 */
@Entity
@Table(name="postin_db_connect_config")
public class DatabaseConnectConfigEntity implements Serializable {

    @Id
//    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;


    //所属连接
    @Column(name = "db_connect_id")
    private String dbConnectId;

    // ip host
    @Column(name = "host")
    private String host;

    //端口
    @Column(name = "port")
    private Integer port;

    //用户名
    @Column(name = "user_name")
    private String userName;

    //密码
    @Column(name = "password")
    private String password;

    //数据库名
    @Column(name = "db_name")
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
