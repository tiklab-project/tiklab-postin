package com.tiklab.postlink.client.builder;

import com.tiklab.postlink.client.model.ApiMeta;

import java.util.List;
import java.util.Map;

public class ApiMetaContext {

    public static List<ApiMeta> apiMetaList;

    public static Map<String,ApiMeta> apiMetaMap;

    public static List<ApiMeta> getApiMetaList() {
        return apiMetaList;
    }

    public static void setApiMetaList(List<ApiMeta> apiMetaList) {
        ApiMetaContext.apiMetaList = apiMetaList;
    }

    public static Map<String, ApiMeta> getApiMetaMap() {
        return apiMetaMap;
    }

    public static void setApiMetaMap(Map<String, ApiMeta> apiMetaMap) {
        ApiMetaContext.apiMetaMap = apiMetaMap;
    }
}
