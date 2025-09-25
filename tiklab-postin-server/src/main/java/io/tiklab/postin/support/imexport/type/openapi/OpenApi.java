package io.tiklab.postin.support.imexport.type.openapi;


import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.postin.common.ErrorCode;
import io.tiklab.postin.support.imexport.common.FunctionImport;

/**
 * OpenApi3 导入
 */
@Component
public class OpenApi {

    @Autowired
    FunctionImport functionImport;

    @Autowired
    OpenApiCommonFn openApiCommonFn;

    @Autowired
    OpenApi30XImport api30XImport;

    @Autowired
    OpenApi31XImport api31XImport;


    public void analysisOpenApi(String workspaceId, InputStream stream) {
        try {
            JSONObject allJson = functionImport.getJsonData(stream);

            // 验证OpenAPI文档结构
            if (!openApiCommonFn.validateOpenApiDocument(allJson)) {
                throw new ApplicationException(ErrorCode.IMPORT_ERROR, "无效的OpenAPI文档格式");
            }

            // 获取 OpenAPI 版本号
            String openApiVersion = allJson.getString("openapi");

            if (openApiVersion == null) {
                throw new ApplicationException(ErrorCode.IMPORT_ERROR, "无法识别 OpenAPI 版本");
            }

            // 根据版本号调用不同的处理逻辑
            if (openApiVersion.startsWith("3.0")) {
                api30XImport.process(allJson, workspaceId);
            } else if (openApiVersion.startsWith("3.1")) {
                api31XImport.process(allJson, workspaceId);
            } else {
                throw new ApplicationException(ErrorCode.IMPORT_ERROR, "不支持的 OpenAPI 版本: " + openApiVersion);
            }
        } catch (Exception e) {
            throw new ApplicationException(ErrorCode.IMPORT_ERROR, e.getMessage());
        }
    }

}
