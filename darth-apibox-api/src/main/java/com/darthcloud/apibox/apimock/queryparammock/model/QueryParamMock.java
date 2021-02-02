package com.darthcloud.apibox.apimock.queryparammock.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.apibox.apimock.mock.model.Mock;
import com.darthcloud.beans.annotation.Mapping;
import com.darthcloud.beans.annotation.Mappings;
import com.darthcloud.join.annotation.Join;
import com.darthcloud.join.annotation.JoinField;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
public class QueryParamMock {

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
    @ApiProperty(name="paramName",desc="参数名称",required = true)
    private java.lang.String paramName;

    @ApiProperty(name="value",desc="参数值")
    private java.lang.String value;

    @ApiProperty(name="sort",desc="排序")
    private java.lang.Integer sort;

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

    public java.lang.String getParamName() {
        return paramName;
    }

    public void setParamName(java.lang.String paramName) {
        this.paramName = paramName;
    }
    public java.lang.String getValue() {
        return value;
    }

    public void setValue(java.lang.String value) {
        this.value = value;
    }
    public java.lang.Integer getSort() {
        return sort;
    }

    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
    }
}
