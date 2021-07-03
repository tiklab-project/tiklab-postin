package com.doublekit.apibox.workspace.entity;


import com.doublekit.dal.jpa.annotation.mapper.Column;
import com.doublekit.dal.jpa.annotation.mapper.GeneratorValue;
import com.doublekit.dal.jpa.annotation.mapper.Id;
import com.doublekit.dal.jpa.annotation.mapper.Table;

import java.io.Serializable;

@Table(name="apibox_workspace")
public class WorkspacePo implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "workspace_name",length = 64,notNull = true)
    private String workspaceName;

    @Column(name = "description",length = 64)
    private String desc;

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
}
