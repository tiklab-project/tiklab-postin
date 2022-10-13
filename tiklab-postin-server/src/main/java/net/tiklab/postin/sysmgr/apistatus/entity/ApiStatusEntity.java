package net.tiklab.postin.sysmgr.apistatus.entity;


import net.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

@Entity
@Table(name="postin_api_status")
public class ApiStatusEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name="id",length = 32)
    private String id;

    @Column(name = "color",length = 32)
    private String color;

    @Column(name = "name",length = 32)
    private String name;

    @Column(name = "type",length = 32)
    private String type;

    @Column(name = "workspace_id",length = 32)
    private String workspaceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }
}
