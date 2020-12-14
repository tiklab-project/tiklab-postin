package com.darthcloud.apibox.client.model;

import com.darthcloud.apibox.annotation.ApiModel;

import java.util.ArrayList;
import java.util.List;

public class ApiModelMeta {

    private Class<?> cls;

    private ApiModel apiModel;

    private List<ApiPropertyMeta> apiPropertyMetaList = new ArrayList<>();

    public ApiModelMeta(Class<?> cls, ApiModel apiModel) {
        this.cls = cls;
        this.apiModel = apiModel;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }

    public ApiModel getApiModel() {
        return apiModel;
    }

    public void setApiModel(ApiModel apiModel) {
        this.apiModel = apiModel;
    }

    public List<ApiPropertyMeta> getApiPropertyMetaList() {
        return apiPropertyMetaList;
    }

    public void setApiPropertyMetaList(List<ApiPropertyMeta> apiPropertyMetaList) {
        this.apiPropertyMetaList = apiPropertyMetaList;
    }
}
