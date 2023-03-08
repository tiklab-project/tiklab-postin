package io.tiklab.postin.api.http.mock.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;

import javax.validation.constraints.NotNull;

/**
 * mock
 * 请求区 模型
 */
@ApiModel
@Join
@Mapper(targetAlias = "RequestMockEntity")
public class RequestMock extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="mockId",desc="所属mock",required = true)
    private String mockId;

    @ApiProperty(name="bodyType",desc="bodyType",required = true)
    private java.lang.String bodyType;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public String getMockId() {
        return mockId;
    }

    public void setMockId(String mockId) {
        this.mockId = mockId;
    }

    public java.lang.String getBodyType() {
        return bodyType;
    }

    public void setBodyType(java.lang.String bodyType) {
        this.bodyType = bodyType;
    }
}
