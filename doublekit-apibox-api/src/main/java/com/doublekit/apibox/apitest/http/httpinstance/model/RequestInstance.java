package com.doublekit.apibox.apitest.http.httpinstance.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.beans.annotation.Mapper;
import com.doublekit.beans.annotation.Mapping;
import com.doublekit.beans.annotation.Mappings;
import com.doublekit.core.BaseModel;
import com.doublekit.join.annotation.Join;
import com.doublekit.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
@Mapper(targetAlias = "RequestInstanceEntity")
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

    @ApiProperty(name="mediaType",desc="请求参数")
    private java.lang.String mediaType;

    @ApiProperty(name="body",desc="请求参数")
    private java.lang.String body;

    @ApiProperty(name="preScript",desc="请求参数")
    private java.lang.String preScript;

    @ApiProperty(name="afterScript",desc="请求参数")
    private java.lang.String afterScript;

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
}
