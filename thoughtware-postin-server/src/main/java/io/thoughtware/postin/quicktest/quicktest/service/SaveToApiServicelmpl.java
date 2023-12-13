package io.thoughtware.postin.quicktest.quicktest.service;

import io.thoughtware.postin.api.apix.model.ApiRequest;
import io.thoughtware.postin.api.apix.model.QueryParam;
import io.thoughtware.postin.api.apix.model.RawParam;
import io.thoughtware.postin.api.apix.model.RequestHeader;
import io.thoughtware.postin.api.apix.service.ApiRequestService;
import io.thoughtware.postin.api.apix.service.QueryParamService;
import io.thoughtware.postin.api.apix.service.RawParamService;
import io.thoughtware.postin.api.apix.service.RequestHeaderService;
import io.thoughtware.postin.api.http.definition.model.*;
import io.thoughtware.postin.api.http.definition.service.*;
import io.thoughtware.postin.quicktest.model.SaveToApi;
import io.thoughtware.postin.quicktest.service.SaveToApiService;
import io.thoughtware.rpc.annotation.Exporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Exporter
@Service
public class SaveToApiServicelmpl implements SaveToApiService {

    @Autowired
    HttpApiService httpApiService;

    @Autowired
    RequestHeaderService requestHeaderService;

    @Autowired
    QueryParamService queryParamService;

    @Autowired
    ApiRequestService apiRequestService;

    @Autowired
    FormParamService formParamService;

    @Autowired
    FormUrlencodedService formUrlencodedService;

    @Autowired
    RawParamService rawParamService;

    @Override
    public String saveToApi(SaveToApi saveToApi) {


        //创建接口
        String httpApiId = httpApiService.createHttpApi(saveToApi.getHttpApi());

        //创建请求头
        List<RequestHeader> headerList = saveToApi.getHeaderList();
        if(headerList!=null){
           for(RequestHeader requestHeader : headerList){
               requestHeader.setApiId(httpApiId);
               requestHeaderService.createRequestHeader(requestHeader);
           }
        }

        //创建查询参数
        List<QueryParam> queryList = saveToApi.getQueryList();
        if(queryList!=null){
            for(QueryParam queryParam:queryList){
                queryParam.setApiId(httpApiId);
                queryParamService.createQueryParam(queryParam);
            }
        }

        //由于创建接口的时候初始化了，所有这里只需要更新
        ApiRequest request = saveToApi.getRequest();
        request.setId(httpApiId);
        request.setApiId(httpApiId);
        apiRequestService.updateApiRequest(request);



        //form
        List<FormParam> formList = saveToApi.getFormList();
        if(formList!=null){
            for(FormParam formParam:formList){
                formParam.setHttp(new HttpApi().setId(httpApiId));
                formParamService.createFormParam(formParam);
            }
        }


        //formurl
        List<FormUrlencoded> formUrlList = saveToApi.getFormUrlList();
        if(formUrlList!=null){
            for(FormUrlencoded formUrlencoded:formUrlList){
                formUrlencoded.setHttp(new HttpApi().setId(httpApiId));
                formUrlencodedService.createFormUrlencoded(formUrlencoded);
            }
        }

        RawParam raw = saveToApi.getRaw();
        if(raw!=null){
            raw.setApiId(httpApiId);
            raw.setId(httpApiId);
            rawParamService.createRawParam(raw);
        }


        return httpApiId;
    }



}