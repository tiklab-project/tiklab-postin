package com.darthcloud.apibox.sysmgr.entity;


import com.darthcloud.dal.jpa.annotation.mapper.Column;
import com.darthcloud.dal.jpa.annotation.mapper.GeneratorValue;
import com.darthcloud.dal.jpa.annotation.mapper.Id;
import com.darthcloud.dal.jpa.annotation.mapper.Table;

import java.io.Serializable;
import java.util.Date;

@Table(name="apibox_environment")
public class EnvironmentPo implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "name",length = 64,notNull = true)
    private String name;

    @Column(name = "url",length = 256,notNull = true)
    private String url;

    @Column(name = "sort",length = 4)
    private Integer sort;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
