package io.tiklab.postin.api.apix.service;

import io.tiklab.postin.api.apix.model.*;
import io.tiklab.postin.api.http.definition.model.*;
import io.tiklab.postin.api.http.definition.service.*;
import io.tiklab.postin.api.ws.ws.model.WSApi;
import io.tiklab.postin.api.ws.ws.service.WSApiService;
import io.tiklab.postin.common.MagicValue;
import io.tiklab.postin.node.model.Node;
import io.tiklab.postin.node.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyCurrentApiUtil {

    @Autowired
    NodeService nodeService;

    @Autowired
    HttpApiService httpApiService;

    @Autowired
    ApiRequestService apiRequestService;

    @Autowired
    RequestHeaderService requestHeaderService;

    @Autowired
    QueryParamService queryParamService;

    @Autowired
    PathParamService pathParamService;

    @Autowired
    FormParamService formParamService;

    @Autowired
    FormUrlencodedService formUrlencodedService;

    @Autowired
    JsonParamService jsonParamService;

    @Autowired
    RawParamService rawParamService;

    @Autowired
    ResponseHeaderService responseHeaderService;

    @Autowired
    ApiResponseService apiResponseService;

    @Autowired
    WSApiService wsApiService;

    /**
     * 复制当前API，创建一个结构相同但拥有新ID的API副本
     * @param apiId 需要复制的API的ID
     * @return 新创建的API的ID，如果复制失败则返回null
     */
    public String copyCurrentApi(String apiId) {
        if (apiId == null || apiId.isEmpty()) {
            return null;
        }

        Node node = nodeService.findNode(apiId);
        if (node == null) {
            return null;
        }

        String nodeType = node.getType();

        if (MagicValue.PROTOCOL_TYPE_HTTP.equals(nodeType)) {
            return copyHttpApi(apiId);
        } else if (MagicValue.PROTOCOL_TYPE_WS.equals(nodeType)) {
            return copyWsApi(apiId);
        }

        return null;
    }

    /**
     * 复制HTTP类型的API
     * @param apiId 原API的ID
     * @return 新API的ID
     */
    private String copyHttpApi(String apiId) {
        HttpApi originalApi = httpApiService.findHttpApi(apiId);
        if (originalApi == null) {
            return null;
        }

        // 创建基本API结构
        HttpApi newApi = createBaseHttpApi(originalApi);
        String newApiId = newApi.getId();

        // 复制API的各组成部分
        copyRequestHeaders(originalApi.getHeaderList(), newApiId);
        copyQueryParams(originalApi.getQueryList(), newApiId);
        copyPathParams(originalApi.getPathList(), newApiId);

        // 复制并更新请求体
        copyApiRequest(originalApi, newApi, newApiId);

        // 复制响应相关部分
        copyResponseHeaders(originalApi.getResponseHeaderList(), newApi);
        copyApiResponses(originalApi.getResponseResultList(), newApiId);

        return newApiId;
    }

    /**
     * 复制WebSocket类型的API
     * @param apiId 原API的ID
     * @return 新API的ID
     */
    private String copyWsApi(String apiId) {
        WSApi originalApi = wsApiService.findWSApi(apiId);
        if (originalApi == null) {
            return null;
        }

        // 创建基本API结构
        String newApiId  = createBaseWsApi(originalApi);

        // 复制API的各组成部分
        copyRequestHeaders(originalApi.getHeaderList(), newApiId);
        copyQueryParams(originalApi.getQueryList(), newApiId);

        // 复制并更新请求体
        copyWsApiRequest(originalApi, newApiId);

        return newApiId;
    }

    /**
     * 创建基本的HTTP API结构
     * @param originalApi 原始API
     * @return 新创建的HTTP API
     */
    private HttpApi createBaseHttpApi(HttpApi originalApi) {
        HttpApi newApi = new HttpApi();

        // 复制并重置Apix ID
        Apix apix = originalApi.getApix();
        apix.setId(null);
        newApi.setApix(apix);

        // 复制并重置Node ID
        Node node = originalApi.getNode();
        node.setId(null);
        newApi.setNode(node);

        // 创建新API并获取ID
        String newApiId = httpApiService.createHttpApi(newApi);
        newApi.setId(newApiId);

        return newApi;
    }

    /**
     * 创建基本的WebSocket API结构
     * @param originalApi 原始API
     * @return 新创建的WebSocket API
     */
    private String createBaseWsApi(WSApi originalApi) {
        WSApi newApi = new WSApi();

        // 复制并重置Apix ID
        Apix apix = originalApi.getApix();
        apix.setId(null);
        newApi.setApix(apix);

        // 复制并重置Node ID
        Node node = originalApi.getNode();
        node.setId(null);
        newApi.setNode(node);

        // 创建新API并获取ID
        String newApiId = wsApiService.createWSApi(newApi);

        return newApiId;
    }

    /**
     * 复制请求头列表
     * @param headerList 原请求头列表
     * @param newApiId 新API的ID
     */
    private void copyRequestHeaders(List<RequestHeader> headerList, String newApiId) {
        if (headerList == null || headerList.isEmpty()) {
            return;
        }

        for (RequestHeader header : headerList) {
            header.setApiId(newApiId);
            requestHeaderService.createRequestHeader(header);
        }
    }

    /**
     * 复制查询参数列表
     * @param queryList 原查询参数列表
     * @param newApiId 新API的ID
     */
    private void copyQueryParams(List<QueryParam> queryList, String newApiId) {
        if (queryList == null || queryList.isEmpty()) {
            return;
        }

        for (QueryParam param : queryList) {
            param.setApiId(newApiId);
            queryParamService.createQueryParam(param);
        }
    }

    /**
     * 复制路径参数列表
     * @param pathList 原路径参数列表
     * @param newApiId 新API的ID
     */
    private void copyPathParams(List<PathParam> pathList, String newApiId) {
        if (pathList == null || pathList.isEmpty()) {
            return;
        }

        for (PathParam param : pathList) {
            param.setApiId(newApiId);
            pathParamService.createPathParam(param);
        }
    }

    /**
     * 复制API请求体
     * @param originalApi 原API
     * @param newApi 新API
     * @param newApiId 新API的ID
     */
    private void copyApiRequest(HttpApi originalApi, HttpApi newApi, String newApiId) {
        ApiRequest request = originalApi.getRequest();
        request.setId(newApiId);
        request.setApiId(newApiId);
        apiRequestService.updateApiRequest(request);

        // 根据请求体类型复制对应的请求参数
        String bodyType = request.getBodyType();
        switch (bodyType) {
            case MagicValue.REQUEST_BODY_TYPE_FORMDATA:
                copyFormDataParams(originalApi.getFormList(), newApi);
                break;

            case MagicValue.REQUEST_BODY_TYPE_FORM_URLENCODED:
                copyFormUrlencodedParams(originalApi.getUrlencodedList(), newApi);
                break;

            case MagicValue.REQUEST_BODY_TYPE_JSON:
                copyJsonParam(originalApi.getJsonParam(), newApiId);
                break;

            case MagicValue.REQUEST_BODY_TYPE_RAW:
                copyRawParam(originalApi.getRawParam(), newApiId);
                break;
        }
    }

    /**
     * 复制WebSocket API请求体
     * @param originalApi 原API
     * @param newApiId 新API的ID
     */
    private void copyWsApiRequest(WSApi originalApi, String newApiId) {
        ApiRequest request = originalApi.getRequest();
        request.setId(newApiId);
        request.setApiId(newApiId);
        apiRequestService.updateApiRequest(request);

        // 根据请求体类型复制对应的请求参数
        switch (request.getBodyType()) {
            case MagicValue.REQUEST_BODY_TYPE_JSON:
                copyJsonParam(originalApi.getJsonParam(), newApiId);
                break;

            case MagicValue.REQUEST_BODY_TYPE_RAW:
                copyRawParam(originalApi.getRawParam(), newApiId);
                break;
        }
    }

    /**
     * 复制Form表单数据参数
     * @param formList 原表单数据列表
     * @param newApi 新API
     */
    private void copyFormDataParams(List<FormParam> formList, HttpApi newApi) {
        if (formList == null || formList.isEmpty()) {
            return;
        }

        for (FormParam param : formList) {
            param.setHttp(newApi);
            formParamService.createFormParam(param);
        }
    }

    /**
     * 复制URL编码的表单参数
     * @param urlencodedList 原URL编码表单列表
     * @param newApi 新API
     */
    private void copyFormUrlencodedParams(List<FormUrlencoded> urlencodedList, HttpApi newApi) {
        if (urlencodedList == null || urlencodedList.isEmpty()) {
            return;
        }

        for (FormUrlencoded param : urlencodedList) {
            param.setHttp(newApi);
            formUrlencodedService.createFormUrlencoded(param);
        }
    }

    /**
     * 复制JSON参数
     * @param jsonParam 原JSON参数
     * @param newApiId 新API的ID
     */
    private void copyJsonParam(JsonParam jsonParam, String newApiId) {
        if (jsonParam == null) {
            return;
        }

        jsonParam.setApiId(newApiId);
        jsonParam.setId(newApiId);
        jsonParamService.updateJsonParam(jsonParam);
    }

    /**
     * 复制Raw参数
     * @param rawParam 原Raw参数
     * @param newApiId 新API的ID
     */
    private void copyRawParam(RawParam rawParam, String newApiId) {
        if (rawParam == null) {
            return;
        }

        rawParam.setApiId(newApiId);
        rawParam.setId(newApiId);
        rawParamService.updateRawParam(rawParam);
    }

    /**
     * 复制响应头
     * @param responseHeaderList 原响应头列表
     * @param newApi 新API
     */
    private void copyResponseHeaders(List<ResponseHeader> responseHeaderList, HttpApi newApi) {
        if (responseHeaderList == null || responseHeaderList.isEmpty()) {
            return;
        }

        for (ResponseHeader header : responseHeaderList) {
            header.setHttp(newApi);
            responseHeaderService.createResponseHeader(header);
        }
    }

    /**
     * 复制API响应
     * @param responseResultList 原API响应列表
     * @param newApiId 新API的ID
     */
    private void copyApiResponses(List<ApiResponse> responseResultList, String newApiId) {
        // 创建接口的时候会自动生成一条，所以需要删除
        List<ApiResponse> apiResponseList = apiResponseService.findApiResponseList(new ApiResponseQuery().setHttpId(newApiId));
        if(apiResponseList!=null && !apiResponseList.isEmpty()){
            for(ApiResponse apiResponse:apiResponseList){
                apiResponseService.deleteApiResponse(apiResponse.getId());
            }
        }

        if (responseResultList == null || responseResultList.isEmpty()) {
            return;
        }

        for (ApiResponse response : responseResultList) {
            response.setHttpId(newApiId);
            apiResponseService.createApiResponse(response);
        }
    }



}
