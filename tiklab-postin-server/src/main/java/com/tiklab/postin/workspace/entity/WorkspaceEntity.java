package com.tiklab.postin.workspace.entity;


import com.tiklab.dal.jpa.annotation.Column;
import com.tiklab.dal.jpa.annotation.GeneratorValue;
import com.tiklab.dal.jpa.annotation.Id;
import com.tiklab.dal.jpa.annotation.Table;import com.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

@Entity @Table(name="postin_workspace")
public class WorkspaceEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "workspace_name",length = 64,notNull = true)
    private String workspaceName;

    @Column(name = "description",length = 64)
    private String desc;

    @Column(name = "user_id",length = 32)
    private String userId;

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
}