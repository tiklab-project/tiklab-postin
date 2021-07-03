package com.doublekit.apibox.client.builder;

import com.doublekit.apibox.client.model.ApiMeta;

import java.util.ArrayList;
import java.util.List;

public class ApiData {

    List<ApiMeta> apis = new ArrayList<>();

    public List<ApiMeta> getApis() {
        return apis;
    }

    public void setApis(List<ApiMeta> apis) {
        this.apis = apis;
    }
}
