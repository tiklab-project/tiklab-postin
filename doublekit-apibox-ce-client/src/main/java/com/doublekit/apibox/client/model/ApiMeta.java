package com.doublekit.apibox.client.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.doublekit.apibox.annotation.Api;

import java.util.ArrayList;
import java.util.List;

public class ApiMeta {

    @JSONField(serialize = false)
    private Class<?> cls;

    @JSONField(serialize = false)
    private Api api;

    private String name;

    private String desc;

    private String path;

    @JSONField(name = "method")
    private List<ApiMethodMeta> apiMethodMetaList = new ArrayList<>();

    public ApiMeta() {
    }

    public ApiMeta(Class<?> cls, Api api) {
        this.cls = cls;
        this.api = api;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    public List<ApiMethodMeta> getApiMethodMetaList() {
        return apiMethodMetaList;
    }

    public void setApiMethodMetaList(List<ApiMethodMeta> apiMethodMetaList) {
        this.apiMethodMetaList = apiMethodMetaList;
    }

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
