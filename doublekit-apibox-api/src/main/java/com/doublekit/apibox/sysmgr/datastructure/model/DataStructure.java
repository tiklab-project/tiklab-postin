package com.doublekit.apibox.sysmgr.datastructure.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.beans.annotation.Mapper;
import com.doublekit.common.BaseModel;

import javax.validation.constraints.NotNull;

@ApiModel
@Mapper(targetAlias = "DataStructureEntity")
public class DataStructure extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="name",desc="名字",required = true)
    private java.lang.String name;

    @ApiProperty(name="coding",desc="编码")
    private java.lang.String coding;

    @ApiProperty(name="dataType",desc="数据类型")
    private java.lang.String dataType;

    @ApiProperty(name="createUser",desc="创建人")
    private java.lang.String createUser;

    @ApiProperty(name="createTime",desc="创建时间")
    private java.util.Date createTime;

    @ApiProperty(name="updateTime",desc="更新时间")
    private java.util.Date updateTime;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }
    public java.lang.String getCoding() {
        return coding;
    }

    public void setCoding(java.lang.String coding) {
        this.coding = coding;
    }
    public java.lang.String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(java.lang.String createUser) {
        this.createUser = createUser;
    }
    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }
    public java.util.Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
