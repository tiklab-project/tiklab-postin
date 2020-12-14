package com.darthcloud.apibox.client.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.darthcloud.apibox.annotation.ApiParam;

import java.lang.reflect.Parameter;

public class ApiParamMeta {

    @JSONField(serialize = false)
    private ApiParam apiParam;

    @JSONField(serialize = false)
    private Parameter parameter;

    private String name;

    private String paramType;

    private boolean required = false;

    private String desc = "";

    private Object def;

    private String textDef = "";

    private Object eg;

    private String textEg = "";

    public ApiParamMeta() {
    }

    public ApiParamMeta(Parameter parameter, ApiParam apiParam) {
        this.parameter = parameter;
        this.apiParam = apiParam;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public ApiParam getApiParam() {
        return apiParam;
    }

    public void setApiParam(ApiParam apiParam) {
        this.apiParam = apiParam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getTextEg() {
        return textEg;
    }

    public void setTextEg(String textEg) {
        this.textEg = textEg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getEg() {
        return eg;
    }

    public void setEg(Object eg) {
        this.eg = eg;
    }

    public Object getDef() {
        return def;
    }

    public void setDef(Object def) {
        this.def = def;
    }

    public String getTextDef() {
        return textDef;
    }

    public void setTextDef(String textDef) {
        this.textDef = textDef;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
