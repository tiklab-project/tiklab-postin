package com.darthcloud.apibox.category.entity;


import com.darthcloud.dal.jpa.annotation.mapper.Column;
import com.darthcloud.dal.jpa.annotation.mapper.GeneratorValue;
import com.darthcloud.dal.jpa.annotation.mapper.Id;
import com.darthcloud.dal.jpa.annotation.mapper.Table;

import java.io.Serializable;

@Table(name="apibox_category")
public class CategoryPo implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "name",length = 64,notNull = true)
    private String name;

    @Column(name = "workspace_id",length = 32)
    private String workspaceId;

    @Column(name = "parent_category_id",length = 32)
    private String parentCategoryId;

    @Column(name = "sort",length = 4)
    private Integer sort;

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

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
