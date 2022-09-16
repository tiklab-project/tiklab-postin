package net.tiklab.postin.apimock.http.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.join.annotation.JoinQuery;

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
