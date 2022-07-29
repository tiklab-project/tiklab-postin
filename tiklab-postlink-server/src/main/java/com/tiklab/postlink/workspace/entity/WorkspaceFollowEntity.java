package com.tiklab.postlink.workspace.entity;


import com.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity @Table(name="postlink_workspace_follow")
public class WorkspaceFollowEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "workspace_id",length = 32,notNull = true)
    private String workspaceId;

    @Column(name = "user_id",length = 32,notNull = true)
    private String userId;

    @Column(name = "create_time",length = 4)
    private Timestamp createTime;

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
