package io.tiklab.postin.quicktest.quicktest.service;

import io.tiklab.postin.api.apix.model.ApiRequest;
import io.tiklab.postin.api.apix.model.QueryParam;
import io.tiklab.postin.api.apix.model.RawParam;
import io.tiklab.postin.api.apix.model.RequestHeader;
import io.tiklab.postin.api.apix.service.ApiRequestService;
import io.tiklab.postin.api.apix.service.QueryParamService;
import io.tiklab.postin.api.apix.service.RawParamService;
import io.tiklab.postin.api.apix.service.RequestHeaderService;
import io.tiklab.postin.api.http.definition.model.*;
import io.tiklab.postin.api.http.definition.service.*;
import io.tiklab.postin.quicktest.model.SaveToApi;
import io.tiklab.postin.quicktest.service.SaveToApiService;
import io.tiklab.rpc.annotation.Exporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Exporter
@Service
public class SaveToApiServicelmpl implements SaveToApiService {

    @Autowired
    private HttpApiService httpApiService;

    @Autowired
    private RequestHeaderService requestHeaderService;

    @Autowired
    private QueryParamService queryParamService;

    @Autowired
    private ApiRequestService apiRequestService;

    @Autowired
    private FormParamService formParamService;

    @Autowired
    private FormUrlencodedService formUrlencodedService;

    @Autowired
    private RawParamService rawParamService;

    @Override
    @Transactional
    public String saveToApi(SaveToApi saveToApi) {
        try {
            // 创建接口并获取ID
            String httpApiId = httpApiService.createHttpApi(saveToApi.getHttpApi());

            // 处理请求头
            processRequestHeaders(saveToApi.getHeaderList(), httpApiId);

            // 处理查询参数
            processQueryParams(saveToApi.getQueryList(), httpApiId);

            // 更新API请求（由于创建接口时已初始化）
            updateApiRequest(saveToApi.getRequest(), httpApiId);

            //  处理表单参数
            processFormParams(saveToApi.getFormList(), httpApiId);

            // 处理表单URL编码参数
            processFormUrlEncodedParams(saveToApi.getFormUrlList(), httpApiId);

            // 处理原始参数
            processRawParam(saveToApi.getRaw(), httpApiId);

            return httpApiId;
        } catch (Exception e) {
            throw new RuntimeException("保存API过程中发生错误: " + e.getMessage(), e);
        }
    }

    private void processRequestHeaders(List<RequestHeader> headers, String apiId) {
        if (isEmpty(headers)) {
            return;
        }

        headers.forEach(header -> {
            header.setApiId(apiId);
            requestHeaderService.createRequestHeader(header);
        });
    }

    private void processQueryParams(List<QueryParam> queryParams, String apiId) {
        if (isEmpty(queryParams)) {
            return;
        }

        queryParams.forEach(param -> {
            param.setApiId(apiId);
            queryParamService.createQueryParam(param);
        });
    }

    private void updateApiRequest(ApiRequest request, String apiId) {
        if (request == null) {
            return;
        }

        request.setId(apiId);
        request.setApiId(apiId);
        apiRequestService.updateApiRequest(request);
    }

    private void processFormParams(List<FormParam> formParams, String apiId) {
        if (isEmpty(formParams)) {
            return;
        }

        HttpApi httpApi = new HttpApi().setId(apiId);
        formParams.forEach(param -> {
            param.setHttp(httpApi);
            if(param.getDataType()==null){
                param.setDataType("text");
            }
            formParamService.createFormParam(param);
        });
    }

    private void processFormUrlEncodedParams(List<FormUrlencoded> formUrlParams, String apiId) {
        if (isEmpty(formUrlParams)) {
            return;
        }

        HttpApi httpApi = new HttpApi().setId(apiId);
        formUrlParams.forEach(param -> {
            param.setHttp(httpApi);
            if(param.getDataType()==null){
                param.setDataType("string");
            }
            formUrlencodedService.createFormUrlencoded(param);
        });
    }

    private void processRawParam(RawParam rawParam, String apiId) {
        if (rawParam == null || rawParam.getRaw() == null) {
            return;
        }

        rawParam.setApiId(apiId);
        rawParam.setId(apiId);
        rawParamService.createRawParam(rawParam);
    }

    private <T> boolean isEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }
}
