package net.tiklab.postin.support.imexport.model;

import net.tiklab.postin.api.http.definition.model.JsonParam;

public class JsonParamImportVo {

    private JsonParam parentId;

    private String methodId;

    private String name;

    private String value;

    private String dataType;

    private int required;

    private String desc;

    public JsonParam getParentId() {
        return parentId;
    }

    public void setParentId(JsonParam parentId) {
        this.parentId = parentId;
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getRequired() {
        return required;
    }

    public void setRequired(int required) {
        this.required = required;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
