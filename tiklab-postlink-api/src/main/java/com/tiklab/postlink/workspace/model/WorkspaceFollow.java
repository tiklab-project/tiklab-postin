package com.tiklab.postlink.workspace.model;

import com.tiklab.postlink.annotation.ApiModel;
import com.tiklab.postlink.annotation.ApiProperty;
import com.tiklab.beans.annotation.Mapper;
import com.tiklab.core.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;

@ApiModel
@Mapper(targetAlias = "WorkspaceFollowEntity")
public class WorkspaceFollow extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="workspaceId",desc="workspaceId",required = true)
    private java.lang.String workspaceId;

    @NotNull
    @ApiProperty(name="userId",desc="userId")
    private java.lang.String userId;

    @ApiProperty(name="createTime",desc="createTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.sql.Timestamp createTime;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(java.lang.String workspaceId) {
        this.workspaceId = workspaceId;
    }
    public java.lang.String getUserId() {
        return userId;
    }

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }
    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }
}
