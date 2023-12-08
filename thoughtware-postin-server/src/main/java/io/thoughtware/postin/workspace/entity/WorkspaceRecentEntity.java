package io.thoughtware.postin.workspace.entity;


import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 最近访问空间 实体
 */
@Entity @Table(name="postin_workspace_recent")
public class WorkspaceRecentEntity implements Serializable {

    @Id
     @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    //  所属空间
    @Column(name = "workspace_id",length = 64,notNull = true)
    private String workspaceId;

    // 所属用户
    @Column(name = "user_id",length = 32)
    private String userId;

    // 更新时间
    @Column(name = "update_time",length = 4)
    private Timestamp updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
