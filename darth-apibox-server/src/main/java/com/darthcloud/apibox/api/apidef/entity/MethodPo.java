package com.darthcloud.apibox.api.apidef.entity;


import com.darthcloud.dal.jpa.annotation.Column;
import com.darthcloud.dal.jpa.annotation.GeneratorValue;
import com.darthcloud.dal.jpa.annotation.Id;
import com.darthcloud.dal.jpa.annotation.Table;

import java.io.Serializable;

@Table(name="apibox_method")
public class MethodPo implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "category_id",length = 32)
    private String categoryId;

    @Column(name = "name",length = 64,notNull = true)
    private String name;

    @Column(name = "request_type",length = 32,notNull = true)
    private String requestType;

    @Column(name = "path",length = 256,notNull = true)
    private String path;

    @Column(name = "description",length = 256)
    private String desc;

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
