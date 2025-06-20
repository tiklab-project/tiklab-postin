package io.tiklab.postin.support.environment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 接口环境 模型
 */
@ApiModel
@Join
@Mapper
public class Environment extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @ApiProperty(name="workspaceId",desc="空间Id")
    private String workspaceId;

    @NotNull
    @ApiProperty(name="name",desc="环境名称",required = true)
    private java.lang.String name;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date createTime;

    @ApiProperty(name="updateTime",desc="更新时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
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

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
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
