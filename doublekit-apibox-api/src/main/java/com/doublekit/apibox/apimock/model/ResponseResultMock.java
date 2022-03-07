package com.doublekit.apibox.apimock.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.beans.annotation.Mapper;
import com.doublekit.beans.annotation.Mapping;
import com.doublekit.beans.annotation.Mappings;
import com.doublekit.common.BaseModel;
import com.doublekit.join.annotation.Join;
import com.doublekit.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
@Mapper(targetAlias = "ResponseResultMockEntity")
public class ResponseResultMock extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="mock",desc="所属mock",required = true)
    @Mappings({
            @Mapping(source = "mock.id",target = "mockId")
    })
    @JoinQuery(key = "id")
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
