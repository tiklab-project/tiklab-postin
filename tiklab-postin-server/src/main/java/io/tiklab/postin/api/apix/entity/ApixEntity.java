package io.tiklab.postin.api.apix.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 接口公共实体
 */
@Entity
@Table(name = "postin_apix")
public class ApixEntity implements Serializable {
    @Id
    @Column(name = "id",length = 40)
    private String id;

    //接口名称
    @Column(name = "name",length = 64)
    private String name;

    //所属分类
    @Column(name = "category_id",length = 40)
    private String categoryId;

    //协议类型
    @Column(name = "protocol_type",length = 32)
    private String protocolType;

    //请求类型
    @Column(name = "method_type",length = 32)
    private String methodType;

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

    //状态
    @Column(name = "status_id",length = 8)
    private String statusId;

    //负责人
    @Column(name = "executor_id",length = 32 )
    private String executor;

    @Column(name = "description",length = 256)
    private String desc;

    //所属空间
    @Column(name = "workspace_id",length = 32)
    private String workspaceId;

    //版本号
    @Column(name = "version",length = 32)
    private String version;

    //所属接口id
    @Column(name = "api_uid",length = 32)
    private String apiUid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getApiUid() {
        return apiUid;
    }

    public void setApiUid(String apiUid) {
        this.apiUid = apiUid;
    }

}
