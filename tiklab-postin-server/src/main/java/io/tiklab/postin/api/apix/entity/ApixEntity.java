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

    //路径
    @Column(name = "path",length = 256,notNull = true)
    private String path;

    //所属分类
    @Column(name = "category_id",length = 40)
    private String categoryId;

    //协议类型
    @Column(name = "protocol_type")
    private String protocolType;

    //状态
    @Column(name = "status_id")
    private String statusId;

    //负责人
    @Column(name = "executor_id")
    private String executor;

    @Column(name = "description")
    private String desc;

    //所属空间
    @Column(name = "workspace_id",length = 32)
    private String workspaceId;

    //版本号
    @Column(name = "version_id",length = 32)
    private String versionId;

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


    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }


}
