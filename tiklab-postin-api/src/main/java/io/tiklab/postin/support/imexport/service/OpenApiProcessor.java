package io.tiklab.postin.support.imexport.service;

import com.alibaba.fastjson.JSONObject;

/**
 * OpenAPI处理器基类，定义通用的处理逻辑和数据结构
 */

public interface OpenApiProcessor {
    void process(JSONObject allJson, String workspaceId);
}
