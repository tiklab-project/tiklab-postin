package io.tiklab.postin.support.datastructure.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

/**
 * 枚举结构 模型
 */
@ApiModel
@Join
@Mapper(targetAlias = "EnumParamEntity")
public class EnumParam extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="paramName",desc="名字",required = true)
    private java.lang.String paramName;

    @ApiProperty(name="dataStructure",desc="主表")
    @Mappings({
            @Mapping(source = "dataStructure.id",target = "dataStructureId")
    })
    @JoinQuery(key = "id")
    private DataStructure dataStructure;

    @ApiProperty(name="dataType",desc="类型")
    private java.lang.String dataType;

    @ApiProperty(name="required",desc="是否必须")
    private java.lang.Integer required;

    @ApiProperty(name="description",desc="说明")
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

    public DataStructure getDataStructure() {
        return dataStructure;
    }

    public void setDataStructure(DataStructure dataStructure) {
        this.dataStructure = dataStructure;
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
