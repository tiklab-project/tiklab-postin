package com.darthcloud.apibox.client.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.darthcloud.apibox.annotation.ApiParam;

import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ApiParamMeta extends ParamItem{

    @JSONField(serialize = false)
    private Parameter parameter;

    @JSONField(serialize = false)
    private ApiParam apiParam;

    //----------------

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

    public ApiParamMeta(Parameter parameter, ApiParam apiParam,Type type,Type paramType) {
        this.parameter = parameter;
        this.apiParam = apiParam;
        super.setType(type);
        super.setParamType(paramType);
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

    public Object getEg() {
        return eg;
    }

    public void setEg(Object eg) {
        this.eg = eg;
    }

    public String getTextEg() {
        return textEg;
    }

    public void setTextEg(String textEg) {
        this.textEg = textEg;
    }
}
