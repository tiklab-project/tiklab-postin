package io.tiklab.postin.api.http.test.instance.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.api.http.test.cases.model.HttpTestcase;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;

import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.user.user.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * http实例 模型
 */
@ApiModel
@Join
@Mapper
public class HttpInstance extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="httpCase",desc="所属接口用例",required = true)
    @Mappings({
            @Mapping(source = "httpCase.id",target = "httpCaseId")
    })
    @JoinQuery(key = "id")
    private HttpTestcase httpCase;

    @ApiProperty(name="workspaceId",desc="空间ID")
    private String workspaceId;

    @ApiProperty(name="user",desc="所属人")
    @Mappings({
            @Mapping(source = "user.id",target = "userId")
    })
    private User user;

    @ApiProperty(name="createTime",desc="创建时间")
    //@IndexField
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="statusCode",desc="状态码")
    private java.lang.Integer statusCode;

    @ApiProperty(name="result",desc="测试结果")
    private java.lang.Integer result;

    @ApiProperty(name="time",desc="时间")
    private java.lang.Integer time;

    @ApiProperty(name="size",desc="大小")
    private java.lang.Integer size;

    @ApiProperty(name="errorMessage",desc="错误信息")
    private String errorMessage;

    @ApiProperty(name="requestInstance",desc="实例-请求部分")
    private RequestInstances requestInstances;

    @ApiProperty(name="responseInstance",desc="实例-响应部分")
    private ResponseInstances responseInstances;

    @ApiProperty(name="assertInstanceList",desc="实例-断言列表")
    private List<AssertInstances> assertInstancesList = new ArrayList<>();



    public String getId() {
        return id;
    }

    public HttpInstance setId(String id) {
        this.id = id;
        return this;
    }

    public HttpTestcase getHttpCase() {
        return httpCase;
    }

    public void setHttpCase(HttpTestcase httpCase) {
        this.httpCase = httpCase;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public RequestInstances getRequestInstance() {
        return requestInstances;
    }

    public void setRequestInstance(RequestInstances requestInstances) {
        this.requestInstances = requestInstances;
    }

    public ResponseInstances getResponseInstance() {
        return responseInstances;
    }

    public void setResponseInstance(ResponseInstances responseInstances) {
        this.responseInstances = responseInstances;
    }

    public List<AssertInstances> getAssertInstanceList() {
        return assertInstancesList;
    }

    public void setAssertInstanceList(List<AssertInstances> assertInstancesList) {
        this.assertInstancesList = assertInstancesList;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

  }
