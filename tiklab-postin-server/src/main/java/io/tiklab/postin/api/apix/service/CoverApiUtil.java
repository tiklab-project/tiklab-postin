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
import java.util.Objects;

@Service
public class CoverApiUtil {

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

    public void coverApi(String willCoverApiId,String originApiId) {
        if (willCoverApiId == null || originApiId==null) {
            return;
        }

        Node node = nodeService.findNode(willCoverApiId);
        if (node == null) {
            return;
        }

        String nodeType = node.getType();

        if (MagicValue.PROTOCOL_TYPE_HTTP.equals(nodeType)) {
             coverHttpApi(willCoverApiId, originApiId);
        } else if (MagicValue.PROTOCOL_TYPE_WS.equals(nodeType)) {
             coverWsApi(willCoverApiId, originApiId);
        }

    }

    private void coverHttpApi(String willCoverApiId, String originApiId) {
        HttpApi originalApi = httpApiService.findHttpApi(originApiId);

        coverBaseHttpApi(originalApi,willCoverApiId);

        coverRequestHeader(originalApi.getHeaderList(),willCoverApiId);
        coverQueryParam(originalApi.getQueryList(),willCoverApiId);
        coverPathParam(originalApi.getPathList(),willCoverApiId);

        coverApiRequest(originalApi,willCoverApiId);

        coverResponseHeader(originalApi.getResponseHeaderList(),willCoverApiId);
        coverApiResponse(originalApi.getResponseResultList(),willCoverApiId);

    }

    private void coverWsApi(String willCoverApiId, String originApiId) {
        WSApi originalApi = wsApiService.findWSApi(originApiId);

        coverBaseWsApi(originalApi,willCoverApiId);

        coverRequestHeader(originalApi.getHeaderList(),willCoverApiId);
        coverQueryParam(originalApi.getQueryList(),willCoverApiId);

        coverWsApiRequest(originalApi,willCoverApiId);

    }

    /**
     * 覆盖基本的HTTP API结构
     * @param originalApi 原始API
     * @return 新创建的HTTP API
     */
    private void coverBaseHttpApi(HttpApi originalApi,String willCoverApiId) {
        HttpApi coverApi = new HttpApi();
        coverApi.setId(willCoverApiId);


        // 复制并重置Node ID
        Node node = originalApi.getNode();
        node.setId(willCoverApiId);

        // 复制并重置Apix ID
        Apix apix = originalApi.getApix();
        apix.setId(willCoverApiId);
        apix.setNode(node);
        apix.setVersionId("nullstring");


        coverApi.setApix(apix);

        // 更新API
         httpApiService.updateHttpApi(coverApi);
    }

    private void coverBaseWsApi(WSApi originalApi,String willCoverApiId) {
        WSApi coverApi = new WSApi();

        // 复制并重置Node ID
        Node node = originalApi.getNode();
        node.setId(willCoverApiId);

        // 复制并重置Apix ID
        Apix apix = originalApi.getApix();
        apix.setId(willCoverApiId);
        apix.setNode(node);
        apix.setVersionId("nullstring");

        coverApi.setApix(apix);

        // 更新API
        wsApiService.updateWSApi(coverApi);
    }


    private void coverRequestHeader(List<RequestHeader> headerList, String willCoverApiId) {
        // 需要先删除所有
        requestHeaderService.deleteAllRequestHeader(willCoverApiId);
        if (headerList == null || headerList.isEmpty()) {
            return;
        }

        for (RequestHeader header : headerList) {
            RequestHeader requestHeader = new RequestHeader();
            requestHeader.setApiId(willCoverApiId);
            requestHeader.setHeaderName(header.getHeaderName());
            requestHeader.setRequired(header.getRequired());
            requestHeader.setValue(header.getValue());
            requestHeader.setDesc(header.getDesc());
            requestHeader.setSort(header.getSort());
            requestHeader.setWorkspaceId(header.getWorkspaceId());
            requestHeaderService.createRequestHeader(requestHeader);
        }
    }


    private void coverQueryParam(List<QueryParam> queryList, String willCoverApiId) {
        queryParamService.deleteAllQueryParam(willCoverApiId);
        if (queryList == null || queryList.isEmpty()) {
            return;
        }

        for (QueryParam query : queryList) {
            QueryParam queryParam = new QueryParam();
            queryParam.setApiId(willCoverApiId);
            queryParam.setParamName(query.getParamName());
            queryParam.setRequired(query.getRequired());
            queryParam.setValue(query.getValue());
            queryParam.setDesc(query.getDesc());
            queryParam.setSort(query.getSort());
            queryParamService.createQueryParam(queryParam);
        }
    }

    private void coverPathParam(List<PathParam> pathList, String willCoverApiId) {
        pathParamService.deleteAllPathParam(willCoverApiId);
        if (pathList == null || pathList.isEmpty()) {
            return;
        }

        for (PathParam path : pathList) {
            PathParam pathParam = new PathParam();
            pathParam.setApiId(willCoverApiId);
            pathParam.setName(path.getName());
            pathParam.setDataType(path.getDataType());
            pathParam.setRequired(path.getRequired());
            pathParam.setValue(path.getValue());
            pathParam.setDesc(path.getDesc());
            pathParam.setSort(path.getSort());
            pathParam.setWorkspaceId(path.getWorkspaceId());
            pathParamService.createPathParam(path);
        }
    }


    private void coverApiRequest(HttpApi originalApi, String willCoverApiId) {
        ApiRequest request = originalApi.getRequest();
        request.setId(willCoverApiId);
        request.setApiId(willCoverApiId);
        apiRequestService.updateApiRequest(request);

        // 根据请求体类型复制对应的请求参数
        String bodyType = request.getBodyType();
        switch (bodyType) {
            case MagicValue.REQUEST_BODY_TYPE_FORMDATA:
                coverFormParam(originalApi.getFormList(), willCoverApiId);
                break;

            case MagicValue.REQUEST_BODY_TYPE_FORM_URLENCODED:
                coverFormUrlencoded(originalApi.getUrlencodedList(),willCoverApiId);
                break;

            case MagicValue.REQUEST_BODY_TYPE_JSON:
                coverJsonParam(originalApi.getJsonParam(), willCoverApiId);
                break;

            case MagicValue.REQUEST_BODY_TYPE_RAW:
                coverRawParam(originalApi.getRawParam(), willCoverApiId);
                break;
        }
    }


    private void coverWsApiRequest(WSApi originalApi, String willCoverApiId) {
        ApiRequest request = originalApi.getRequest();
        request.setId(willCoverApiId);
        request.setApiId(willCoverApiId);
        apiRequestService.updateApiRequest(request);

        // 根据请求体类型复制对应的请求参数
        switch (request.getBodyType()) {
            case MagicValue.REQUEST_BODY_TYPE_JSON:
                coverJsonParam(originalApi.getJsonParam(), willCoverApiId);
                break;

            case MagicValue.REQUEST_BODY_TYPE_RAW:
                coverRawParam(originalApi.getRawParam(), willCoverApiId);
                break;
        }
    }


    private void coverFormParam(List<FormParam> formList, String willCoverApiId) {
        formParamService.deleteAllFormParam(willCoverApiId);

        if (formList == null || formList.isEmpty()) {
            return;
        }

        for (FormParam form : formList) {
            FormParam formParam = new FormParam();
            HttpApi httpApi = new HttpApi();
            httpApi.setId(willCoverApiId);
            formParam.setHttp(httpApi);
            formParam.setParamName(form.getParamName());
            formParam.setDataType(form.getDataType());
            formParam.setRequired(form.getRequired());
            formParam.setValue(form.getValue());
            formParam.setDesc(form.getDesc());
            formParam.setSort(form.getSort());
            formParamService.createFormParam(formParam);
        }
    }

    private void coverFormUrlencoded(List<FormUrlencoded> formList, String willCoverApiId) {
        formUrlencodedService.deleteAllFormUrlencoded(willCoverApiId);

        if(formList == null || formList.isEmpty()){
            return;
        }

        for(FormUrlencoded encoded : formList){
            FormUrlencoded urlencoded = new FormUrlencoded();
            HttpApi httpApi = new HttpApi();
            httpApi.setId(willCoverApiId);
            urlencoded.setHttp(httpApi);
            urlencoded.setParamName(encoded.getParamName());
            urlencoded.setDataType(encoded.getDataType());
            urlencoded.setRequired(encoded.getRequired());
            urlencoded.setValue(encoded.getValue());
            urlencoded.setDesc(encoded.getDesc());
            urlencoded.setSort(encoded.getSort());
            formUrlencodedService.createFormUrlencoded(urlencoded);
        }
    }


    private void coverJsonParam(JsonParam jsonParam, String willCoverApiId) {
        if (jsonParam == null) {
            return;
        }

        jsonParam.setApiId(willCoverApiId);
        jsonParam.setId(willCoverApiId);
        jsonParamService.updateJsonParam(jsonParam);
    }

    private void coverRawParam(RawParam rawParam, String willCoverApiId) {
        if (rawParam == null) {
            return;
        }

        rawParam.setApiId(willCoverApiId);
        rawParam.setId(willCoverApiId);
        rawParamService.updateRawParam(rawParam);
    }

    private void coverResponseHeader(List<ResponseHeader> headerList, String willCoverApiId) {
        // 需要先删除
        responseHeaderService.deleteAllResponseHeader(willCoverApiId);

        if (headerList == null || headerList.isEmpty()) {
            return;
        }

        for (ResponseHeader header : headerList) {
            ResponseHeader responseHeader = new ResponseHeader();
            HttpApi httpApi = new HttpApi();
            httpApi.setId(willCoverApiId);
            responseHeader.setHttp(httpApi);
            responseHeader.setHeaderName(header.getHeaderName());
            responseHeader.setRequired(header.getRequired());
            responseHeader.setValue(header.getValue());
            responseHeader.setDesc(header.getDesc());
            responseHeader.setSort(header.getSort());
            responseHeaderService.createResponseHeader(responseHeader);
        }
    }

    private void coverApiResponse(List<ApiResponse> responseResultList, String willCoverApiId) {
        apiResponseService.deleteAllApiResponse(willCoverApiId);
        if (responseResultList == null || responseResultList.isEmpty()) {
            return;
        }
        // 创建新的API响应
        for (ApiResponse response : responseResultList) {
            ApiResponse targetResponse = new ApiResponse();
            targetResponse.setHttpId(willCoverApiId);
            targetResponse.setHttpCode(response.getHttpCode());
            targetResponse.setName(response.getName());
            targetResponse.setDataType(response.getDataType());
            if(Objects.equals(response.getDataType(), "json")){
                targetResponse.setJsonText(response.getJsonText());
            }else {
                targetResponse.setRawText(response.getRawText());
            }
            apiResponseService.createApiResponse(targetResponse);
        }

    }


}
