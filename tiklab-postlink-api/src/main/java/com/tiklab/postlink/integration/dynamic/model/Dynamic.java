package com.tiklab.postlink.integration.dynamic.model;

import com.tiklab.postlink.annotation.ApiModel;
import com.tiklab.postlink.annotation.ApiProperty;
import com.tiklab.beans.annotation.Mapper;
import com.tiklab.beans.annotation.Mapping;
import com.tiklab.beans.annotation.Mappings;
import com.tiklab.core.BaseModel;
import com.tiklab.join.annotation.Join;
import com.tiklab.join.annotation.JoinQuery;
import com.tiklab.user.user.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
@Mapper(targetAlias = "DynamicEntity")
public class Dynamic extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="workspaceId",desc="workspaceId",required = true)
    private java.lang.String workspaceId;

    @NotNull
    @ApiProperty(name="name",desc="name",required = true)
    private java.lang.String name;

    @NotNull
    @ApiProperty(name="modelId",desc="modelId",required = true)
    private java.lang.String modelId;

    @NotNull
    @ApiProperty(name="user",desc="所属用户",eg="@selectOne")
    @Mappings({
            @Mapping(source = "user.id",target = "userId")
    })
    @JoinQuery(key = "id")
    private User user;

    @NotNull
    @ApiProperty(name="model",desc="model",required = true)
    private java.lang.String model;

    @NotNull
    @ApiProperty(name="dynamicType",desc="dynamicType",required = true)
    private java.lang.String dynamicType;

    @NotNull
    @ApiProperty(name="operationTime",desc="operationTime",required = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.sql.Timestamp operationTime;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }
    public java.lang.String getModelId() {
        return modelId;
    }

    public void setModelId(java.lang.String modelId) {
        this.modelId = modelId;
    }
    public java.lang.String getModel() {
        return model;
    }

    public void setModel(java.lang.String model) {
        this.model = model;
    }
    public java.lang.String getDynamicType() {
        return dynamicType;
    }

    public void setDynamicType(java.lang.String dynamicType) {
        this.dynamicType = dynamicType;
    }
    public java.sql.Timestamp getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(java.sql.Timestamp operationTime) {
        this.operationTime = operationTime;
    }
}
