package net.tiklab.postin.workspace.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.GeneratorValue;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;import net.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * 空间 实体
 */
@Entity @Table(name="postin_workspace")
public class WorkspaceEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    // 空间名称
    @Column(name = "workspace_name",length = 64,notNull = true)
    private String workspaceName;

    // 描述
    @Column(name = "description",length = 64)
    private String desc;

    // 所属用户
    @Column(name = "user_id",length = 32)
    private String userId;

    // 是否可见
    @Column(name = "visibility")
    private Integer visibility;

    // icon路径
    @Column(name = "icon_url",length = 256)
    private String iconUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkspaceName() {
        return workspaceName;
    }

    public void setWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
