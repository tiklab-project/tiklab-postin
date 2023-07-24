package io.tiklab.postin.client.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.lang.reflect.Type;
import java.util.List;

public class TypeMeta {

    //名称
    @JSONField(ordinal = 1)
    private String name;

    //描述
    @JSONField(ordinal = 2)
    private String desc = "";

    //数据类型
    @JSONField(serialize = false)
    private Type type;

    //参数化类型
    @JSONField(serialize = false)
    private Type paramType;

    //数据类型，字符串
    @JSONField(ordinal = 3)
    private String dataType;

    //是否必须
    @JSONField(ordinal = 4)
    private boolean required = false;

    //子节点列表
    @JSONField(name = "io.tiklab.postin.test.model",ordinal = 5)
    private List<ApiPropertyMeta> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
        this.dataType = type.getTypeName();
    }

    public Type getParamType() {
        return paramType;
    }

    public void setParamType(Type paramType) {
        this.paramType = paramType;
    }

    public List<ApiPropertyMeta> getChildren() {
        return children;
    }

    public void setChildren(List<ApiPropertyMeta> children) {
        this.children = children;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
