package com.doublekit.apibox.datastructure.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.apibox.apidef.model.JsonParam;
import com.doublekit.beans.annotation.Mapper;
import com.doublekit.beans.annotation.Mapping;
import com.doublekit.beans.annotation.Mappings;
import com.doublekit.common.BaseModel;
import com.doublekit.join.annotation.Join;
import com.doublekit.join.annotation.JoinField;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel
@Mapper
@Join
public class JsonParamDS extends BaseModel{

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

    @ApiProperty(name="parent",desc="上级参数")
    @Mappings({
            @Mapping(source = "parent.id",target = "parentId")
    })
    @JoinField(id = "id")
    private JsonParamDS parent;

    @ApiProperty(name="children",desc="子节点列表")
    private List<JsonParamDS> children;

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

    public JsonParamDS getParent() {
        return parent;
    }

    public void setParent(JsonParamDS parent) {
        this.parent = parent;
    }

    public List<JsonParamDS> getChildren() {
        return children;
    }

    public void setChildren(List<JsonParamDS> children) {
        this.children = children;
    }
}
