package com.darthcloud.apibox.apitest.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.beans.annotation.Mapping;
import com.darthcloud.beans.annotation.Mappings;
import com.darthcloud.join.annotation.Join;
import com.darthcloud.join.annotation.JoinField;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
public class RequestInstance {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="testInstance",desc="所属测试实例",required = true)
    @Mappings({
            @Mapping(source = "id",target = "instanceId")
    })
    @JoinField(id = "id")
    private TestInstance testInstance;

    @NotNull
    @ApiProperty(name="requestBase",desc="请求基本信息",required = true)
    private java.lang.String requestBase;

    @NotNull
    @ApiProperty(name="requestHeader",desc="请求头",required = true)
    private java.lang.String requestHeader;

    @NotNull
    @ApiProperty(name="requestParam",desc="请求参数",required = true)
    private java.lang.String requestParam;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TestInstance getTestInstance() {
        return testInstance;
    }

    public void setTestInstance(TestInstance testInstance) {
        this.testInstance = testInstance;
    }

    public String getRequestBase() {
        return requestBase;
    }

    public void setRequestBase(String requestBase) {
        this.requestBase = requestBase;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }
}
