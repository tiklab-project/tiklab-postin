package io.tiklab.postin.api.http.document.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.postin.node.model.Node;
import io.tiklab.postin.workspace.model.Workspace;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 接口文档分享模型
 */
@ApiModel
@Mapper
@Join
public class Share extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="workspaceId",desc="所属空间")
    private String workspaceId;

    @ApiProperty(name="name",desc="名称")
    private java.lang.String name;

    @ApiProperty(name="code",desc="前端生成一个code，用于set Id")
    private java.lang.String code;

    @ApiProperty(name="targetId",desc="接口或目录 ID")
    private java.lang.String targetId;

    @ApiProperty(name="targetId",desc="接口或目录 ID",required = true)
    private Node node;

    @ApiProperty(name="targetId",desc="空间 ID",required = true)
    private Workspace workspace;

    @NotNull
    @ApiProperty(name="targetType",desc="接口，目录",required = true)
    private java.lang.String targetType;

    @NotNull
    @ApiProperty(name="visibility",desc="可见",required = true)
    private java.lang.Integer visibility;

    @ApiProperty(name="password",desc="密码")
    private java.lang.String password;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private java.sql.Timestamp createTime;

    @ApiProperty(name="updateTime",desc="更新时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private java.sql.Timestamp updateTime;

    @ApiProperty(name="nodeIds",desc="节点ID")
    private List<String> nodeIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public java.lang.String getTargetId() {
        return targetId;
    }

    public void setTargetId(java.lang.String targetId) {
        this.targetId = targetId;
    }

    public String getTargetType() {
        return targetType;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public java.lang.Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(java.lang.Integer visibility) {
        this.visibility = visibility;
    }
    public java.lang.String getPassword() {
        return password;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }
    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }
    public java.sql.Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.sql.Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public List<String> getNodeIds() {
        return nodeIds;
    }

    public void setNodeIds(List<String> nodeIds) {
        this.nodeIds = nodeIds;
    }
}
