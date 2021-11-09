package com.doublekit.apibox.workspace.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.beans.annotation.Mapper;
import com.doublekit.beans.annotation.Mapping;
import com.doublekit.beans.annotation.Mappings;
import com.doublekit.common.BaseModel;
import com.doublekit.dss.annotation.Index;
import com.doublekit.dss.annotation.IndexField;
import com.doublekit.dss.annotation.IndexId;
import com.doublekit.dss.annotation.IndexQueryField;
import com.doublekit.join.annotation.JoinField;

import javax.validation.constraints.NotNull;

@ApiModel
@Index
public class Workspace extends BaseModel {

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

    @ApiProperty(name="user",desc="所属用户",eg="@selectOne")
    @Mappings({
            @Mapping(source = "user.id",target = "userId")
    })
    @JoinField(id = "id")
    private Workspace user;

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

    public Workspace getUser() {
        return user;
    }

    public void setUser(Workspace user) {
        this.user = user;
    }
}
