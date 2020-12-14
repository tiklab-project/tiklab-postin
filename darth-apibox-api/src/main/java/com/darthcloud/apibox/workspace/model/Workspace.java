package com.darthcloud.apibox.workspace.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

@ApiModel
public class Workspace {

    @ApiProperty(name="id",desc="空间ID")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="workspaceName",desc="空间名称",eg="@text32",required = true)
    private java.lang.String workspaceName;

    @ApiProperty(name="desc",desc="描述",eg="@text32")
    private java.lang.String desc;

    public java.lang.String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public java.lang.String getWorkspaceName() {
        return workspaceName;
    }

    public void setWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
    }
    public java.lang.String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
