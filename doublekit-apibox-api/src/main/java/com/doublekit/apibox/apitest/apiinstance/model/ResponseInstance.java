package com.doublekit.apibox.apitest.apiinstance.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.apibox.apitest.apiinstance.model.TestInstance;
import com.doublekit.beans.annotation.Mapper;
import com.doublekit.beans.annotation.Mapping;
import com.doublekit.beans.annotation.Mappings;
import com.doublekit.common.BaseModel;
import com.doublekit.join.annotation.Join;
import com.doublekit.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
@Mapper(targetAlias = "ResponseInstanceEntity")
public class ResponseInstance extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="testInstance",desc="所属测试实例",required = true)
    @Mappings({
            @Mapping(source = "testInstance.id",target = "instanceId")
    })
    @JoinQuery(key = "id")
    private TestInstance testInstance;

    @NotNull
    @ApiProperty(name="responseHeader",desc="响应头",required = true)
    private java.lang.String responseHeader;

    @NotNull
    @ApiProperty(name="responseBody",desc="响应体",required = true)
    private java.lang.String responseBody;

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

    public String getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(String responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}
