package net.tiklab.postin.support.environment.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.GeneratorValue;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;
import net.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 接口环境 实体
 */
@Entity
@Table(name="postin_environment")
public class EnvironmentEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    // 名称
    @Column(name = "name",length = 64,notNull = true)
    private String name;

    // 环境url
    @Column(name = "url",length = 256,notNull = true)
    private String url;

    // 创建时间
    @Column(name = "create_time")
    private Date createTime;

    // 更新时间
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

}
