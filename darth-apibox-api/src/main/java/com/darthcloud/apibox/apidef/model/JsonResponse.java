package com.darthcloud.apibox.apidef.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.dsl.beans.annotation.Mapping;
import com.darthcloud.dsl.beans.annotation.Mappings;
import com.darthcloud.dsl.join.annotation.Join;
import com.darthcloud.dsl.join.annotation.JoinField;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel
@Join
public class JsonResponse {

    @ApiProperty(name="id",desc="唯一ID")
    private java.lang.String id;

    @ApiProperty(name="parent",desc="上级属性")
    @Mappings({
            @Mapping(source = "id",target = "parentId")
    })
    @JoinField(id = "id")
    private JsonResponse parent;

    @NotNull
    @ApiProperty(name="method",desc="所属接口",eg="@selectOne",required = true)
    @Mappings({
            @Mapping(source = "id",target = "methodId")
    })
    @JoinField(id = "id")
    private MethodEx method;

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

    @ApiProperty(name="children",desc="下级属性列表")
    private List<JsonResponse> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MethodEx getMethod() {
        return method;
    }

    public void setMethod(MethodEx method) {
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

    public JsonResponse getParent() {
        return parent;
    }

    public void setParent(JsonResponse parent) {
        this.parent = parent;
    }

    public List<JsonResponse> getChildren() {
        return children;
    }

    public void setChildren(List<JsonResponse> children) {
        this.children = children;
    }
}
