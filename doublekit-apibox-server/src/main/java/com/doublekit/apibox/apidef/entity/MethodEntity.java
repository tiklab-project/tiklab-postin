package com.doublekit.apibox.apidef.entity;


import com.doublekit.dal.jpa.mapper.annotation.Column;
import com.doublekit.dal.jpa.mapper.annotation.GeneratorValue;
import com.doublekit.dal.jpa.mapper.annotation.Id;
import com.doublekit.dal.jpa.mapper.annotation.Table;import com.doublekit.dal.jpa.mapper.annotation.Entity;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="apibox_method")
public class MethodEntity implements Serializable {

    @Id
//    @GeneratorValue
    @Column(name = "id",length = 40)
    private String id;

    @Column(name = "category_id",length = 40)
    private String categoryId;

    @Column(name = "name",length = 64,notNull = true)
    private String name;

    @Column(name = "request_type",length = 32,notNull = true)
    private String requestType;

    @Column(name = "path",length = 256,notNull = true)
    private String path;

    @Column(name = "description",length = 256)
    private String desc;

    @Column(name = "sort",length = 4)
    private Integer sort;

    @Column(name = "version_code",length = 255)
    private String versionCode;

    @Column(name = "on_version_id",length = 255)
    private String onVersionId;

    @Column(name = "create_user",length = 30 )
    private String createUser;

    @Column(name = "update_user",length = 30 )
    private String updateUser;

    @Column(name = "executor_id",length = 32 )
    private String executor;

    @Column(name = "create_time",length = 4)
    private Date createTime;

    @Column(name = "update_time",length = 4)
    private Date updateTime;

    @Column(name = "status_id",length = 8)
    private String statusId;

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getOnVersionId() {
        return onVersionId;
    }

    public void setOnVersionId(String onVersionId) {
        this.onVersionId = onVersionId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
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
}
