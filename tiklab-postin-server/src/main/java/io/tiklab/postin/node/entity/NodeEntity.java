package io.tiklab.postin.node.entity;

import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.Entity;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 公共实体
 */
@Entity
@Table(name = "postin_node")
public class NodeEntity implements Serializable {
    @Id
    @Column(name = "id",length = 12)
    private String id;

    //接口名称
    @Column(name = "name",length = 64)
    private String name;

    //所属空间
    @Column(name = "workspace_id",length = 32)
    private String workspaceId;

    //所属分类
    @Column(name = "parent_id",length = 12)
    private String parentId;

    //请求类型
    @Column(name = "type")
    private String type;

    @Column(name = "tree_path")
    private String treePath;

    @Column(name = "sort")
    private Integer sort;

    //创建人
    @Column(name = "create_user",length = 32 )
    private String createUser;

    //更新人
    @Column(name = "update_user",length = 32 )
    private String updateUser;

    //创建时间
    @Column(name = "create_time",length = 4)
    private Timestamp createTime;

    //更新时间
    @Column(name = "update_time",length = 4)
    private Timestamp updateTime;

    @Column(name = "method_type",length = 32)
    private String methodType;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }
}
