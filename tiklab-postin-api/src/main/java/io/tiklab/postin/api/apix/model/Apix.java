package io.tiklab.postin.api.apix.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.api.http.definition.model.HttpApi;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.support.apistatus.model.ApiStatus;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;

import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.user.user.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;


@ApiModel
@Join
@Mapper
public class Apix extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="category",desc="所属分类",eg="@selectOne",required = true)
    @Mappings({
            @Mapping(source = "category.id",target = "categoryId")
    })
    @JoinQuery(key = "id")
    private Category category;

    @ApiProperty(name="name",desc="接口名称",required = true)
    private java.lang.String name;

    @ApiProperty(name="path",desc="路径",required = true)
    private java.lang.String path;

    @ApiProperty(name="protocolType",desc="协议类型",required = true)
    private java.lang.String protocolType;

    @ApiProperty(name="methodType",desc="请求类型",required = true)
    private java.lang.String methodType;

    @ApiProperty(name="createUser",desc="创建人")
    @Mappings({
            @Mapping(source = "createUser.id",target = "createUser")
    })
    @JoinQuery(key = "id")
    private User createUser;

    @ApiProperty(name="updateUser",desc="更新者")
    @Mappings({
            @Mapping(source = "updateUser.id",target = "updateUser")
    })
    @JoinQuery(key = "id")
    private User updateUser;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.sql.Timestamp createTime;

    @ApiProperty(name="updateTime",desc="更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.sql.Timestamp updateTime;

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

    @ApiProperty(name="workspaceId",desc="所属空间")
    private String workspaceId;

    @ApiProperty(name="version",desc="版本")
    private java.lang.String version;

    @ApiProperty(name="apiUid",desc="绑定api的id")
    private java.lang.String apiUid;

    @ApiProperty(name="httpApi",desc="httpApi")
    private HttpApi httpApi;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public User getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
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
    public java.lang.String getDesc() {
        return desc;
    }

    public void setDesc(java.lang.String desc) {
        this.desc = desc;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
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


    public HttpApi getHttpApi() {
        return httpApi;
    }

    public void setHttpApi(HttpApi httpApi) {
        this.httpApi = httpApi;
    }
}
