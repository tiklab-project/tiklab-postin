package com.tiklab.postlink.apimock.http.model;

import com.tiklab.postlink.annotation.ApiModel;
import com.tiklab.postlink.annotation.ApiProperty;
import com.tiklab.beans.annotation.Mapper;
import com.tiklab.beans.annotation.Mapping;
import com.tiklab.beans.annotation.Mappings;
import com.tiklab.core.BaseModel;
import com.tiklab.join.annotation.Join;
import com.tiklab.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
@Mapper(targetAlias = "JsonParamMockEntity")
public class JsonParamMock extends BaseModel {

    @ApiProperty(name="id",desc="唯一ID")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="mock",desc="所属mock",required = true)
    @Mappings({
            @Mapping(source = "mock.id",target = "mockId")
    })
    @JoinQuery(key = "id")
    private Mock mock;

    @NotNull
    @ApiProperty(name="exp",desc="表达式",required = true)
    private java.lang.String exp;

    @ApiProperty(name="value",desc="值")
    private java.lang.String value;

    @ApiProperty(name="sort",desc="排序")
    private java.lang.Integer sort;

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

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
