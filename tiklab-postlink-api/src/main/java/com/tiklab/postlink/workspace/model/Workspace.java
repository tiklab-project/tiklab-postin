package com.tiklab.postlink.workspace.model;

import com.tiklab.postlink.annotation.ApiModel;
import com.tiklab.postlink.annotation.ApiProperty;
import com.tiklab.beans.annotation.Mapper;
import com.tiklab.beans.annotation.Mapping;
import com.tiklab.beans.annotation.Mappings;
import com.tiklab.core.BaseModel;
import com.tiklab.dss.annotation.Index;
import com.tiklab.dss.annotation.IndexField;
import com.tiklab.dss.annotation.IndexId;
import com.tiklab.dss.annotation.IndexQueryField;
import com.tiklab.join.annotation.Join;
import com.tiklab.join.annotation.JoinQuery;
import com.tiklab.user.user.model.User;

import javax.validation.constraints.NotNull;

@ApiModel
@Index
@Join
@Mapper(targetAlias = "WorkspaceEntity")
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
    @JoinQuery(key = "id")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
