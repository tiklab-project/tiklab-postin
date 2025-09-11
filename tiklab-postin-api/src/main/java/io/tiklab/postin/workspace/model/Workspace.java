package io.tiklab.postin.workspace.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;

import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.privilege.role.model.PatchUser;
import io.tiklab.user.user.model.User;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
 * 空间 模型
 * @pi.model: io.tiklab.postin.workspace.model.Workspace
 */
@ApiModel
@Join
//@Index
@Mapper
public class Workspace extends BaseModel {
    /**
     * @pi.name: id
     * @pi.dataType:string
     * @pi.desc: ID
     * @pi.value: workspaceIddfdfdfdfdf
     */
    @ApiProperty(name="id",desc="空间ID")
    //@IndexId
    //@IndexField
    private java.lang.String id;

    /**
     * @pi.name: workspaceName
     * @pi.dataType:string
     * @pi.desc: 空间名称
     * @pi.value: workspaceName
     */
    @NotNull
    @ApiProperty(name="workspaceName",desc="空间名称",eg="@text32",required = true)
    //@IndexField
    //@IndexQueryField
    private java.lang.String workspaceName;

    /**
     * @pi.name: desc
     * @pi.dataType:string
     * @pi.desc: 描述
     * @pi.value: desc
     */
    @ApiProperty(name="desc",desc="描述",eg="@text32")
    //@IndexField
    private java.lang.String desc;

    /**
     * @pi.model: user
     * @pi.desc: 所属用户
     */
    @ApiProperty(name="user",desc="所属用户",eg="user111")
    @Mappings({
            @Mapping(source = "user.id",target = "userId")
    })
    @JoinField(key = "id")
    private User user;

    /**
     * @pi.name: visibility
     * @pi.dataType:Integer
     * @pi.desc: 可见范围
     * @pi.value: 1
     */
    @ApiProperty(name="visibility",desc="可见范围",eg = "0: 公共， 1：私密")
    private Integer visibility;
    /**
     * @pi.name: iconUrl
     * @pi.dataType:String
     * @pi.desc: 图标地址
     * @pi.value: a.png
     */
    @ApiProperty(name="iconUrl",desc="图标地址",eg = "a.png")
    private String iconUrl;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Timestamp createTime;

    private Integer apiNum;
    private Integer caseNum;

    private Integer isFollow;
    private List<PatchUser> userList;

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getApiNum() {
        return apiNum;
    }

    public void setApiNum(Integer apiNum) {
        this.apiNum = apiNum;
    }

    public Integer getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(Integer caseNum) {
        this.caseNum = caseNum;
    }
}
