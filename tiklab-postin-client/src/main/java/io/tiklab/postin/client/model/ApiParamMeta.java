package io.tiklab.postin.client.model;

import com.alibaba.fastjson.annotation.JSONField;
import io.tiklab.postin.annotation.ApiParam;

import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

public class ApiParamMeta extends TypeMeta {

    @JSONField(serialize = false)
    private Parameter parameter;

    @JSONField(serialize = false)
    private ApiParam apiParam;

    //----------------

    private Object def;

    @JSONField(serialize = false)
    private String textDef = "";

    private Object eg;

    @JSONField(serialize = false)
    private String textEg = "";

    //请求格式类型，form-data/json等，默认form-data
    private String paramDataType = "form-data";

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

    public String getParamDataType() {
        return paramDataType;
    }

    public void setParamDataType(String paramDataType) {
        this.paramDataType = paramDataType;
    }
}
