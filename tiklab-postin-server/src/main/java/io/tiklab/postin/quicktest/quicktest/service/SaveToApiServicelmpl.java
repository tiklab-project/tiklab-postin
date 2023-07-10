package io.tiklab.postin.quicktest.quicktest.service;

import io.tiklab.postin.api.http.definition.model.*;
import io.tiklab.postin.api.http.definition.service.*;
import io.tiklab.postin.quicktest.model.SaveToApi;
import io.tiklab.postin.quicktest.service.SaveToApiService;
import io.tiklab.rpc.annotation.Exporter;
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
               requestHeader.setHttp(new HttpApi().setId(httpApiId));
               requestHeaderService.createRequestHeader(requestHeader);
           }
        }

        //创建查询参数
        List<QueryParam> queryList = saveToApi.getQueryList();
        if(queryList!=null){
            for(QueryParam queryParam:queryList){
                queryParam.setHttp(new HttpApi().setId(httpApiId));
                queryParamService.createQueryParam(queryParam);
            }
        }

        //由于创建接口的时候初始化了，所有这里只需要更新
        ApiRequest request = saveToApi.getRequest();
        request.setId(httpApiId);
        request.setHttpId(httpApiId);
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
            raw.setHttp(new HttpApi().setId(httpApiId));
            raw.setId(httpApiId);
            rawParamService.createRawParam(raw);
        }


        return httpApiId;
    }



}
