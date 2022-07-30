package com.tiklab.postin.apimock.http.model;

import com.tiklab.postin.annotation.ApiModel;
import com.tiklab.postin.annotation.ApiProperty;
import com.tiklab.beans.annotation.Mapper;
import com.tiklab.beans.annotation.Mapping;
import com.tiklab.beans.annotation.Mappings;
import com.tiklab.core.BaseModel;
import com.tiklab.join.annotation.Join;
import com.tiklab.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
@Mapper(targetAlias = "JsonResponseMockEntity")
public class JsonResponseMock extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="mock",desc="所属mock",required = true)
    @Mappings({
            @Mapping(source = "mock.id",target = "mockId")
    })
    @JoinQuery(key = "id")
    private Mock mock;

    @ApiProperty(name="result",desc="预期返回结果")
    private java.lang.String result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Mock getMock() {
        return mock;
    }

    public void setMock(Mock mock) {
        this.mock = mock;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
