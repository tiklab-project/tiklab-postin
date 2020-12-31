package com.darthcloud.apibox.responseresult.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.apibox.apxmethod.model.ApxMethod;
import com.darthcloud.beans.annotation.Mapper;
import com.darthcloud.beans.annotation.Mapping;
import com.darthcloud.beans.annotation.Mappings;
import com.darthcloud.join.annotation.Join;
import com.darthcloud.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

@ApiModel
@Mapper(targetClass = "com.darthcloud.apibox.responseresult.entity.ResponseResultPo")
@Join
public class ResponseResult {

    @ApiProperty(name="id",desc="唯一ID")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="method",desc="所属接口",eg="@selectOne",required = true)
    @Mappings({
            @Mapping(source = "id",target = "methodId")
    })
    @JoinQuery(id = "id")
    private ApxMethod method;

    @NotNull
    @ApiProperty(name="propertyName",desc="属性名称",required = true)
    private java.lang.String propertyName;

    @NotNull
    @ApiProperty(name="dataType",desc="数据类型,[int,string,boolean]",required = true)
    private java.lang.String dataType;

    @NotNull
    @ApiProperty(name="required",desc="是否必须,0:非必须;1:必须",required = true)
    private java.lang.Integer required;

    @ApiProperty(name="desc",desc="描述",eg="@text32")
    private java.lang.String desc;

    @ApiProperty(name="eg",desc="示例值")
    private java.lang.String eg;

    @ApiProperty(name="sort",desc="排序")
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

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getEg() {
        return eg;
    }

    public void setEg(String eg) {
        this.eg = eg;
    }
}
