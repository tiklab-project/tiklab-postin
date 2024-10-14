package io.tiklab.postin.support.datastructure.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据结构 实体
 */
@Entity @Table(name="postin_model")
public class DataStructureEntity implements Serializable {

    @Id
     @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 所属空间
    @Column(name = "workspace_id",length = 32)
    private String workspaceId;

    // 名称
    @Column(name = "name",length = 64,notNull = true)
    private String name;

    // 编码
    @Column(name = "coding",length = 32)
    private String coding;

    // 数据类型
    @Column(name = "data_type",length = 32)
    private String dataType;

    //  创建人
    @Column(name = "create_user",length = 32)
    private String createUser;

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

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoding() {
        return coding;
    }

    public void setCoding(String coding) {
        this.coding = coding;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
