package com.darthcloud.apibox.api.apimock.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.beans.annotation.Mapping;
import com.darthcloud.beans.annotation.Mappings;
import com.darthcloud.join.annotation.Join;
import com.darthcloud.join.annotation.JoinField;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
public class ResponseMock {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="mock",desc="所属mock",required = true)
    @Mappings({
            @Mapping(source = "id",target = "mockId")
    })
    @JoinField(id = "id")
    private Mock mock;

    @NotNull
    @ApiProperty(name="httpCode",desc="返回http状态码，默认200为正常",required = true)
    private java.lang.String httpCode;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public Mock getMock() {
        return mock;
    }

    public void setMock(Mock mock) {
        this.mock = mock;
    }

    public java.lang.String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(java.lang.String httpCode) {
        this.httpCode = httpCode;
    }
}
