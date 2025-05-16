package io.tiklab.postin.api.http.document.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 分享 实体
 */
@Entity
@Table(name="postin_share")
public class ShareEntity implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "name")
    private String name;

    // 所属空间
    @Column(name = "workspace_id")
    private String workspaceId;

    //目标id
    @Column(name = "target_Id")
    private String targetId;

    //目标类型  接口或目录
    @Column(name = "target_type")
    private String targetType;

    //是否可见
    @Column(name = "visibility")
    private Integer visibility;

    //密码
    @Column(name = "password")
    private String password;

    //创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    //更新时间
    @Column(name = "update_time")
    private Timestamp updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
