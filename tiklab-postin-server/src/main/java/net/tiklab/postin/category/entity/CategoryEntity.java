package net.tiklab.postin.category.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;import net.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

@Entity
@Table(name="postin_category")
public class CategoryEntity implements Serializable {

    @Id
//    @GeneratorValue
    @Column(name = "id",length = 40)
    private String id;

    @Column(name = "name",length = 64,notNull = true)
    private String name;

    @Column(name = "workspace_id",length = 32)
    private String workspaceId;

    @Column(name = "parent_category_id",length = 32)
    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
