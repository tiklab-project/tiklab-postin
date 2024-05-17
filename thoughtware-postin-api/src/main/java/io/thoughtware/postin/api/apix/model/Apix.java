package io.thoughtware.postin.api.apix.model;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.postin.api.http.definition.model.HttpApi;
import io.thoughtware.postin.category.model.Category;
import io.thoughtware.postin.node.model.Node;
import io.thoughtware.postin.support.apistatus.model.ApiStatus;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;

import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;
import io.thoughtware.user.user.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;


@ApiModel
@Join
@Mapper
public class Apix extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="categoryId",desc="categoryId")
    private String categoryId;

    @ApiProperty(name="workspaceId",desc="workspaceId")
    private String workspaceId;

    @ApiProperty(name="protocolType",desc="协议类型",required = true)
    private java.lang.String protocolType;

    @ApiProperty(name="path",desc="路径",required = true)
    private java.lang.String path;

    @ApiProperty(name="status",desc="状态")
    @Mappings({
            @Mapping(source = "status.id",target = "statusId")
    })
    @JoinQuery(key = "id")
    private ApiStatus status;

    @ApiProperty(name="executor",desc="责任人")
    @Mappings({
            @Mapping(source = "executor.id",target = "executor")
    })
    @JoinQuery(key = "id")
    private User executor;

    @ApiProperty(name="desc",desc="描述")
    private java.lang.String desc;

    @ApiProperty(name="version",desc="版本")
    private java.lang.String version;

    @ApiProperty(name="apiUid",desc="绑定api的id")
    private java.lang.String apiUid;

    @ApiProperty(name="httpApi",desc="httpApi")
    private HttpApi httpApi;

    @ApiProperty(name="node",desc="node")
    private Node node;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public java.lang.String getDesc() {
        return desc;
    }

    public void setDesc(java.lang.String desc) {
        this.desc = desc;
    }

    public ApiStatus getStatus() {
        return status;
    }

    public void setStatus(ApiStatus status) {
        this.status = status;
    }

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
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

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public HttpApi getHttpApi() {
        return httpApi;
    }

    public void setHttpApi(HttpApi httpApi) {
        this.httpApi = httpApi;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
