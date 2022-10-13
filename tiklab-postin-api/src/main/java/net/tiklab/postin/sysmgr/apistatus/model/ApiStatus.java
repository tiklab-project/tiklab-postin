package net.tiklab.postin.sysmgr.apistatus.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.core.BaseModel;

@ApiModel
@Mapper(targetAlias = "ApiStatusEntity")
public class ApiStatus extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="color",desc="code")
    private java.lang.String color;

    @ApiProperty(name="name",desc="name")
    private java.lang.String name;

    @ApiProperty(name="type",desc="类型")
    private java.lang.String type;

    @ApiProperty(name="workspaceId",desc="空间Id")
    private String workspaceId;

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
