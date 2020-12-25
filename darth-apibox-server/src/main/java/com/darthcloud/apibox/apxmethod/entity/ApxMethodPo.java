package com.darthcloud.apibox.apxmethod.entity;


import com.darthcloud.dal.jpa.annotation.Column;
import com.darthcloud.dal.jpa.annotation.Id;
import com.darthcloud.dal.jpa.annotation.Table;

import java.io.Serializable;

@Table(name="apibox_method")
public class ApxMethodPo implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "name",length = 64,notNull = true)
    private String name;

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
