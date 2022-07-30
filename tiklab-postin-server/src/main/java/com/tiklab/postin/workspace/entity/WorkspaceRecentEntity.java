package com.tiklab.postin.workspace.entity;


import com.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity @Table(name="postin_workspace_recent")
public class WorkspaceRecentEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "workspace_id",length = 64,notNull = true)
    private String workspaceId;

    @Column(name = "user_id",length = 32)
    private String userId;

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
