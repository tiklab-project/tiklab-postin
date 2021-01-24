package com.darthcloud.apibox.client.definer;

import com.alibaba.fastjson.annotation.JSONField;

public class PropertyDef {

    private String fieldType;

    private String notNull;

    private Boolean required;

    private String desc;

    private Object mark;

    @JSONField(serialize = false)
    private String eg;

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getNotNull() {
        return notNull;
    }

    public void setNotNull(String notNull) {
        this.notNull = notNull;
    }

    public String getEg() {
        return eg;
    }

    public void setEg(String eg) {
        this.eg = eg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Object getMark() {
        return mark;
    }

    public void setMark(Object mark) {
        this.mark = mark;
    }
}
