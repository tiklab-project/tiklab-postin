package com.doublekit.apibox.apidef.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.apibox.category.model.Category;
import com.doublekit.beans.annotation.Mapping;
import com.doublekit.beans.annotation.Mappings;
import com.doublekit.common.BaseModel;
import com.doublekit.dss.annotation.Index;
import com.doublekit.dss.annotation.IndexField;
import com.doublekit.dss.annotation.IndexId;
import com.doublekit.dss.annotation.IndexQueryField;
import com.doublekit.join.annotation.Join;
import com.doublekit.join.annotation.JoinField;
import com.doublekit.user.user.model.User;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@ApiModel
//@Mapper(target = "com.doublekit.apibox.apidef.entity.MethodEntity")
@Join
@Index
public class MethodEx extends BaseModel {

    @ApiProperty(name="id",desc="唯一ID")
    @IndexId
    @IndexField
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="category",desc="所属分类",eg="@selectOne",required = true)
    @Mappings({
            @Mapping(source = "category.id",target = "categoryId")
    })
    @JoinField(id = "id")
    @IndexField
    private Category category;

    @NotNull
    @ApiProperty(name="name",desc="接口名称",eg="@text32",required = true)
    @IndexField
    @IndexQueryField
    private java.lang.String name;

    @NotNull
    @ApiProperty(name="requestType",desc="请求类型",required = true)
    @IndexField
    private java.lang.String requestType;

    @NotNull
    @ApiProperty(name="path",desc="接口路径",required = true)
    @IndexField
    private java.lang.String path;

    @ApiProperty(name="desc",desc="描述")
    @IndexField
    private java.lang.String desc;

    @ApiProperty(name="sort",desc="排序")
    private java.lang.Integer sort;

    @ApiProperty(name="versionCode",desc="版本号")
    @IndexField
    private java.lang.String versionCode;
    @ApiProperty(name="onVersionId",desc="上一个版本id")
    @IndexField
    private java.lang.String onVersionId;

    @ApiProperty(name="createUser",desc="创建人")
    @Mappings({
            @Mapping(source = "createUser.id",target = "createUser")
    })
    @JoinField(id = "id")
    private User createUser;

    @ApiProperty(name="updateUser",desc="更新者")
    @Mappings({
            @Mapping(source = "updateUser.id",target = "updateUser")
    })
    @JoinField(id = "id")
    private User updateUser;

    @ApiProperty(name="executor",desc="创建人")
    @Mappings({
            @Mapping(source = "executor.id",target = "executor")
    })
    @JoinField(id = "id")
    private User executor;

    @ApiProperty(name="createTime",desc="创建时间")
    @IndexField
    private  java.sql.Date createTime;

    @ApiProperty(name="updateTime",desc="更新时间")
    @IndexField
    private java.sql.Date updateTime;

    @ApiProperty(name="status",desc="状态")
    @IndexField
    private java.lang.String status;



    public String getId() {
        return id;
    }

    public MethodEx setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
    }
}
