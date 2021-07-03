package com.doublekit.apibox.apidef.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.beans.annotation.Mapping;
import com.doublekit.beans.annotation.Mappings;
import com.doublekit.join.annotation.Join;
import com.doublekit.join.annotation.JoinField;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel
@Join
public class JsonParam {

    @ApiProperty(name="id",desc="唯一ID")
    private java.lang.String id;

    @ApiProperty(name="parent",desc="上级参数")
    @Mappings({
            @Mapping(source = "id",target = "parentId")
    })
    @JoinField(id = "id")
    private JsonParam parent;

    @NotNull
    @ApiProperty(name="method",desc="所属接口",eg="@selectOne",required = true)
    @Mappings({
            @Mapping(source = "id",target = "methodId")
    })
    @JoinField(id = "id")
    private MethodEx method;

    @NotNull
    @ApiProperty(name="paramName",desc="参数名称",eg="@text32",required = true)
    private java.lang.String paramName;

    @NotNull
    @ApiProperty(name="dataType",desc="数据类型,[int,string,boolean]",required = true)
    private java.lang.String dataType;

    @NotNull
    @ApiProperty(name="required",desc="是否必须,0:非必须;1:必须",required = true)
    private java.lang.Integer required = 0;

    @ApiProperty(name="desc",desc="参数说明",eg="@text32")
    private java.lang.String desc;

    @ApiProperty(name="eg",desc="示例值")
    private java.lang.String eg;

    @ApiProperty(name="sort",desc="排序")
    private java.lang.Integer sort;

    @ApiProperty(name="children",desc="子节点列表")
    private List<JsonParam> children;

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

    public JsonParam getParent() {
        return parent;
    }

    public void setParent(JsonParam parent) {
        this.parent = parent;
    }

    public List<JsonParam> getChildren() {
        return children;
    }

    public void setChildren(List<JsonParam> children) {
        this.children = children;
    }
}
