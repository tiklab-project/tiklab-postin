package io.tiklab.postin.workspace.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.dss.annotation.Index;
import io.tiklab.dss.annotation.IndexField;
import io.tiklab.dss.annotation.IndexId;
import io.tiklab.dss.annotation.IndexQueryField;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.privilege.role.model.PatchUser;
import io.tiklab.user.user.model.User;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 空间 模型
 */
@ApiModel
@Join
@Index
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

    @ApiProperty(name="visibility",desc="可见范围",eg = "0: 公共， 1：私密")
    private Integer visibility;

    @ApiProperty(name="iconUrl",desc="图标地址",eg = "a.png")
    private String iconUrl;


    private Integer isFollow;
    private List<PatchUser> userList;
    private Integer categoryNum;
    private Integer apiNum;


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

    public Integer getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Integer isFollow) {
        this.isFollow = isFollow;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public List<PatchUser> getUserList() {
        return userList;
    }

    public void setUserList(List<PatchUser> userList) {
        this.userList = userList;
    }

    public Integer getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(Integer categoryNum) {
        this.categoryNum = categoryNum;
    }

    public Integer getApiNum() {
        return apiNum;
    }

    public void setApiNum(Integer apiNum) {
        this.apiNum = apiNum;
    }
}
