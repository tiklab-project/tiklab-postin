package com.doublekit.apibox.apidef.apix.entity;

import com.doublekit.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "apibox_apix")
public class ApixEntity implements Serializable {
    @Id
    @Column(name = "id",length = 40)
    private String id;

    @Column(name = "name",length = 64)
    private String name;

    @Column(name = "category_id",length = 40)
    private String categoryId;

    @Column(name = "protocol_type",length = 32)
    private String protocolType;

    @Column(name = "create_user",length = 30 )
    private String createUser;

    @Column(name = "update_user",length = 30 )
    private String updateUser;

    @Column(name = "create_time",length = 4)
    private Timestamp createTime;

    @Column(name = "update_time",length = 4)
    private Timestamp updateTime;

    @Column(name = "status_id",length = 8)
    private String statusId;

    @Column(name = "executor_id",length = 32 )
    private String executor;

    @Column(name = "description",length = 256)
    private String desc;

    @Column(name = "workspace_id",length = 4)
    private String workspaceId;

    @Column(name = "version",length = 32)
    private String version;

    @Column(name = "api_uid",length = 32)
    private String apiUid;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getApiUid() {
        return apiUid;
    }

    public void setApiUid(String apiUid) {
        this.apiUid = apiUid;
    }
}
