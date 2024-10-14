package io.tiklab.postin.api.apix.entity;

import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.Entity;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 接口公共实体
 */
@Entity
public class ApiListEntity implements Serializable {

    @Column(name = "id")
    private String id;

    //接口名称
    @Column(name = "name")
    private String name;

    //路径
    @Column(name = "path")
    private String path;

    //协议类型
    @Column(name = "protocol_type")
    private String protocolType;

    //请求类型
    @Column(name = "method_type")
    private String methodType;

    //创建人
    @Column(name = "create_user" )
    private String createUser;

    //更新人
    @Column(name = "update_user",length = 32 )
    private String updateUser;

    //创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    //更新时间
    @Column(name = "update_time",length = 4)
    private Timestamp updateTime;

    //状态
    @Column(name = "status_id")
    private String statusId;

    //负责人
    @Column(name = "executor_id")
    private String executor;

    @Column(name = "description",length = 256)
    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
