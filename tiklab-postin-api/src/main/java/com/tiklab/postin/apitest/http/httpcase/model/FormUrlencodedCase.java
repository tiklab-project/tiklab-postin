package com.tiklab.postin.apitest.http.httpcase.model;

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
@Mapper(targetAlias = "FormUrlencodedCaseEntity")
public class FormUrlencodedCase extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="httpCase",desc="所属接口用例",required = true)
    @Mappings({
            @Mapping(source = "httpCase.id",target = "httpCaseId")
    })
    @JoinQuery(key = "id")
    private HttpTestcase httpCase;


    @NotNull
    @ApiProperty(name="paramName",desc="paramName",required = true)
    private java.lang.String paramName;

    @NotNull
    @ApiProperty(name="dataType",desc="dataType",required = true)
    private java.lang.String dataType;

    @ApiProperty(name="value",desc="value")
    private java.lang.String value;

    @ApiProperty(name="sort",desc="sort")
    private java.lang.Integer sort;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public HttpTestcase getHttpCase() {
        return httpCase;
    }

    public void setHttpCase(HttpTestcase httpCase) {
        this.httpCase = httpCase;
    }

    public java.lang.String getParamName() {
        return paramName;
    }

    public void setParamName(java.lang.String paramName) {
        this.paramName = paramName;
    }
    public java.lang.String getDataType() {
        return dataType;
    }

    public void setDataType(java.lang.String dataType) {
        this.dataType = dataType;
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
