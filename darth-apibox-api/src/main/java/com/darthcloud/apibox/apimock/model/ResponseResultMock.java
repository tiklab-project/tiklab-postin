package com.darthcloud.apibox.apimock.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.dsl.beans.annotation.Mapping;
import com.darthcloud.dsl.beans.annotation.Mappings;
import com.darthcloud.dsl.join.annotation.Join;
import com.darthcloud.dsl.join.annotation.JoinField;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
public class ResponseResultMock {

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
    @ApiProperty(name="resultType",desc="返回结果类型，json/raw",required = true)
    private java.lang.String resultType;

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

    public java.lang.String getResultType() {
        return resultType;
    }

    public void setResultType(java.lang.String resultType) {
        this.resultType = resultType;
    }
}
