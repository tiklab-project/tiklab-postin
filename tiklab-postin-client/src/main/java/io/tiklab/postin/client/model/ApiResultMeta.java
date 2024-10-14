package io.tiklab.postin.client.model;

import com.alibaba.fastjson.annotation.JSONField;

public class ApiResultMeta extends TypeMeta {

    private Object def;

    @JSONField(serialize = false)
    private String textDef="";

    private Object eg;

    @JSONField(serialize = false)
    private String textEg = "";

    //返回类型，字符串
    private String returnTypeText;

    public String getTextEg() {
        return textEg;
    }

    public void setTextEg(String textEg) {
        this.textEg = textEg;
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

    public String getReturnTypeText() {
        return returnTypeText;
    }

    public void setReturnTypeText(String returnTypeText) {
        this.returnTypeText = returnTypeText;
    }
}
