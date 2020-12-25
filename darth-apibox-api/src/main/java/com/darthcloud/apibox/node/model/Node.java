package com.darthcloud.apibox.node.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

@ApiModel
public class Node {

    @ApiProperty(name="id",desc="id",eg="@text32")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="name",desc="name",eg="@text32",required = true)
    private java.lang.String name;

    @ApiProperty(name="workspaceId",desc="workspaceId",eg="@text32")
    private java.lang.String workspaceId;

    @ApiProperty(name="parentCategoryId",desc="parentCategoryId",eg="@text32")
    private java.lang.String parentCategoryId;

    @ApiProperty(name="sort",desc="sort",eg="@int16")
    private java.lang.Integer sort;

    @ApiProperty(name="nodeType",desc="nodeType",eg="@int16")
    private java.lang.Integer nodeType;

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
    public java.lang.String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(java.lang.String workspaceId) {
        this.workspaceId = workspaceId;
    }
    public java.lang.String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(java.lang.String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
    public java.lang.Integer getSort() {
        return sort;
    }

    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
    }
    public java.lang.Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(java.lang.Integer nodeType) {
        this.nodeType = nodeType;
    }
}
