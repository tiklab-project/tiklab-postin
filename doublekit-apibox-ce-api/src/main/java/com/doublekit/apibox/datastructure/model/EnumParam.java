package com.doublekit.apibox.datastructure.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.beans.annotation.Mapper;
import com.doublekit.common.BaseModel;

import javax.validation.constraints.NotNull;

@ApiModel
@Mapper
public class EnumParam extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="paramName",desc="paramName",required = true)
    private java.lang.String paramName;

    @ApiProperty(name="subjectId",desc="subjectId")
    private java.lang.String subjectId;

    @ApiProperty(name="dataType",desc="dataType")
    private java.lang.String dataType;

    @ApiProperty(name="required",desc="required")
    private java.lang.Integer required;

    @ApiProperty(name="description",desc="description")
    private java.lang.String description;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getParamName() {
        return paramName;
    }

    public void setParamName(java.lang.String paramName) {
        this.paramName = paramName;
    }
    public java.lang.String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(java.lang.String subjectId) {
        this.subjectId = subjectId;
    }
    public java.lang.String getDataType() {
        return dataType;
    }

    public void setDataType(java.lang.String dataType) {
        this.dataType = dataType;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
