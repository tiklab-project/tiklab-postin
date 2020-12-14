package com.darthcloud.apibox.client.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.darthcloud.apibox.annotation.ApiMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ApiMethodMeta {

    @JSONField(serialize = false)
    private ApiMethod apiMethod;

    @JSONField(serialize = false)
    private Method method;

    private String name;

    private String path = "";

    private String desc;

    private String requestType;

    @JSONField(name = "param")
    private List<ApiParamMeta> apiParamMetaList = new ArrayList<>();

    private String paramEg;

    @JSONField(name = "result")
    private ApiResultMeta apiResultMeta;

    public ApiMethodMeta() {
    }

    public ApiMethodMeta(Method method, ApiMethod apiMethod) {
        this.method = method;
        this.apiMethod = apiMethod;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public ApiMethod getApiMethod() {
        return apiMethod;
    }

    public void setApiMethod(ApiMethod apiMethod) {
        this.apiMethod = apiMethod;
    }

    public List<ApiParamMeta> getApiParamMetaList() {
        return apiParamMetaList;
    }

    public void setApiParamMetaList(List<ApiParamMeta> apiParamMetaList) {
        this.apiParamMetaList = apiParamMetaList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ApiResultMeta getApiResultMeta() {
        return apiResultMeta;
    }

    public void setApiResultMeta(ApiResultMeta apiResultMeta) {
        this.apiResultMeta = apiResultMeta;
    }

    public String getParamEg() {
        return paramEg;
    }

    public void setParamEg(String paramEg) {
        this.paramEg = paramEg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
