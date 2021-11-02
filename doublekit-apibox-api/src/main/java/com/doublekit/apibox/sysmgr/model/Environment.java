package com.doublekit.apibox.sysmgr.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.beans.annotation.Mapper;
import com.doublekit.common.BaseModel;
import com.doublekit.join.annotation.Join;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel
@Join
public class Environment extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="name",desc="环境名称",required = true)
    private java.lang.String name;

    @NotNull
    @ApiProperty(name="url",desc="环境地址",required = true)
    private java.lang.String url;

    @ApiProperty(name="sort",desc="排序")
    private java.lang.Integer sort;

    @ApiProperty(name="createTime",desc="创建时间，服务端生成")
    private java.util.Date createTime;

    @ApiProperty(name="updateTime",desc="更新时间，服务端生成")
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
    public java.lang.String getUrl() {
        return url;
    }

    public void setUrl(java.lang.String url) {
        this.url = url;
    }
    public java.lang.Integer getSort() {
        return sort;
    }

    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
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
}
