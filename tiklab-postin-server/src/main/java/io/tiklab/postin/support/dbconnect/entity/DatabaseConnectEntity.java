package io.tiklab.postin.support.dbconnect.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 数据库链接 实体
 */
@Entity
@Table(name="postin_db_connect")
public class DatabaseConnectEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    //所属空间
    @Column(name = "workspace_id")
    private String workspaceId;

    //所属 用户
    @Column(name = "user_id")
    private String userId;

    //名称
    @Column(name = "name")
    private String name;

    //类型
    @Column(name = "type")
    private String type;

    //描述
    @Column(name = "description")
    private String desc;


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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
