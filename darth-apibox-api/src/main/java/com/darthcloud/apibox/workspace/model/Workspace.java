package com.darthcloud.apibox.workspace.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.dss.store.annotation.Index;
import com.darthcloud.dss.store.annotation.IndexField;
import com.darthcloud.dss.store.annotation.IndexId;
import com.darthcloud.dss.store.annotation.IndexQueryField;

import javax.validation.constraints.NotNull;

@ApiModel
@Index
public class Workspace implements java.io.Serializable{

    @ApiProperty(name="id",desc="空间ID")
    @IndexId
    @IndexField
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="workspaceName",desc="空间名称",eg="@text32",required = true)
    @IndexField
    @IndexQueryField
    private java.lang.String workspaceName;

    @ApiProperty(name="desc",desc="描述",eg="@text32")
    @IndexField
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
