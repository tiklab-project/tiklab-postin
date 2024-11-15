package io.tiklab.postin.api.http.mock.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;

import javax.validation.constraints.NotNull;

/**
 * mock
 * 响应区模型
 */
@ApiModel
@Join
@Mapper
public class ResponseMock extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="mockId",desc="所属mock",required = true)
    private String mockId;

    @ApiProperty(name="bodyType",desc="选项类型")
    private java.lang.String bodyType;

    @ApiProperty(name="httpCode",desc="返回http状态码，默认200为正常")
    private java.lang.String httpCode;

    @ApiProperty(name="time",desc="响应延迟时间")
    private Integer time;

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

    public java.lang.String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(java.lang.String httpCode) {
        this.httpCode = httpCode;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
