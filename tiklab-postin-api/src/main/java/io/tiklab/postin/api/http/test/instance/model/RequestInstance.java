package io.tiklab.postin.api.http.test.instance.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.api.http.test.cases.model.AssertCase;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.user.user.model.User;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * http协议
 * 请求部分实例模型
 */
@ApiModel
@Join
@Mapper(targetName  = "io.tiklab.postin.api.http.test.instance.entity.RequestInstanceEntity")
public class RequestInstance extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="httpInstance",desc="所属测试实例",required = true)
    @Mappings({
            @Mapping(source = "httpInstance.id",target = "httpInstanceId")
    })
    private HttpInstance httpInstance;

    @ApiProperty(name="url",desc="url")
    private java.lang.String url;

    @ApiProperty(name = "methodType",desc = "请求类型")
    private String methodType;

    @ApiProperty(name="headers",desc="请求头")
    private java.lang.String headers;

    @ApiProperty(name="mediaType",desc="类型")
    private java.lang.String mediaType;

    @ApiProperty(name="body",desc="请求参数")
    private java.lang.String body;

    @ApiProperty(name="preScript",desc="请求参数")
    private java.lang.String preScript;

    @ApiProperty(name="afterScript",desc="请求参数")
    private java.lang.String afterScript;

    @ApiProperty(name="asserts",desc="断言")
    private List<AssertCase> assertList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HttpInstance getHttpInstance() {
        return httpInstance;
    }

    public void setHttpInstance(HttpInstance httpInstance) {
        this.httpInstance = httpInstance;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }


    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPreScript() {
        return preScript;
    }

    public void setPreScript(String preScript) {
        this.preScript = preScript;
    }

    public String getAfterScript() {
        return afterScript;
    }

    public void setAfterScript(String afterScript) {
        this.afterScript = afterScript;
    }

    public List<AssertCase> getAssertList() {
        return assertList;
    }

    public void setAssertList(List<AssertCase> assertList) {
        this.assertList = assertList;
    }
}
