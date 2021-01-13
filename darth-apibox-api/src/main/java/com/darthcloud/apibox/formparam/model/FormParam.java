package com.darthcloud.apibox.formparam.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.apibox.apxmethod.model.ApxMethod;
import com.darthcloud.beans.annotation.Mapping;
import com.darthcloud.beans.annotation.Mappings;
import com.darthcloud.join.annotation.Join;
import com.darthcloud.join.annotation.JoinField;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
public class FormParam {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="method",desc="所属接口",required = true)
    @Mappings({
            @Mapping(source = "id",target = "methodId")
    })
    @JoinField(id = "id")
    private ApxMethod method;

    @NotNull
    @ApiProperty(name="paramName",desc="参数名称",eg="@text32",required = true)
    private java.lang.String paramName;

    @NotNull
    @ApiProperty(name="dataType",desc="数据类型",eg="@text32",required = true)
    private java.lang.String dataType;

    @NotNull
    @ApiProperty(name="required",desc="是否必须",eg="@int16",required = true)
    private java.lang.Integer required;

    @ApiProperty(name="desc",desc="描述",eg="@text32")
    private java.lang.String desc;

    @ApiProperty(name="eg",desc="示例值",eg="@text32")
    private java.lang.String eg;

    @ApiProperty(name="sort",desc="排序",eg="@int16")
    private java.lang.Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ApxMethod getMethod() {
        return method;
    }

    public void setMethod(ApxMethod method) {
        this.method = method;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEg() {
        return eg;
    }

    public void setEg(String eg) {
        this.eg = eg;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
