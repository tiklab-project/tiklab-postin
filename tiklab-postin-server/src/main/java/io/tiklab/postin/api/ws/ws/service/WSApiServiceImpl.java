package io.tiklab.postin.api.ws.ws.service;

import io.tiklab.postin.api.apix.model.*;
import io.tiklab.postin.api.apix.service.*;
import io.tiklab.postin.api.ws.ws.model.WSApi;
import io.tiklab.postin.common.PostInUnit;
import io.tiklab.postin.support.apistatus.service.ApiStatusService;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.user.user.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 定义
 * ws协议服务
*/
@Service
@Exporter
public class WSApiServiceImpl implements WSApiService {

    @Autowired
    ApixService apixService;


    @Autowired
    RequestHeaderService requestHeaderService;

    @Autowired
    QueryParamService queryParamService;

    @Autowired
    ApiRequestService apiRequestService;


    @Autowired
    JsonParamService jsonParamService;

    @Autowired
    RawParamService rawParamService;
//
//    @Autowired
//    ResponseHeaderService responseHeaderService;
//
//    @Autowired
//    ApiResponseService apiResponseService;

//    @Autowired
//    DssClient disClient;

    @Autowired
    ApiStatusService apiStatusService;

    @Autowired
    UserService userService;

//    @Autowired
//    MockService mockService;

    @Override
    public String createWSApi(@NotNull @Valid WSApi wsApi) {

        String apiId = apixService.createApix(wsApi.getApix());

        //初始化请求响应中的类型
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setId(apiId);
        apiRequest.setApiId(apiId);
        apiRequest.setBodyType("raw");
        apiRequestService.createApiRequest(apiRequest);

        //初始化raw
        RawParam rawParam = new RawParam();
        rawParam.setApiId(apiId);
        rawParam.setId(apiId);
        rawParam.setType("text/plain");
        rawParam.setRaw("");
        rawParamService.createRawParam(rawParam);

//
//        //初始化一个返回结果 数据类型为json， 所以设置值为jsonSchema结构的值
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setId(id);
//        apiResponse.setWSId(id);
//        apiResponse.setWSCode(200);
//        apiResponse.setName("成功");
//        apiResponse.setDataType("json");
//        apiResponse.setJsonText("{\"type\": \"object\",\"properties\": {}}");
//        apiResponseService.createApiResponse(apiResponse);


        return  apiId;
    }


    @Override
    public void updateWSApi(@NotNull @Valid WSApi wsApi) {


    }

    @Override
    public void deleteWSApi(@NotNull String wsId) {
        //删除

        //删除请求头
        requestHeaderService.deleteAllRequestHeader(wsId);

        //删除query
        queryParamService.deleteAllQueryParam(wsId);

        //删除请求
        apiRequestService.deleteApiRequest(wsId);

        //删除raw
        rawParamService.deleteRawParam(wsId);

        jsonParamService.deleteJsonParam(wsId);

//
//        //删除响应部分
//        List<ApiResponse> apiResponseList = apiResponseService.findApiResponseList(new ApiResponseQuery().setWSId(wsId));
//        for(ApiResponse apiResponse: apiResponseList){
//            apiResponseService.deleteApiResponse(apiResponse.getId());
//        }
//
//        //删除响应头
//        responseHeaderService.deleteAllResponseHeader(wsId);
//
//        //删除mock
//        mockService.deleteAllMock(wsId);


        //删除索引
//        disClient.delete(WSApi.class,id);
    }


    @Override
    public WSApi findWSApi(@NotNull String id) {
        WSApi wsApi = new WSApi();

        Apix apix = apixService.findApix(id);
        wsApi.setApix(apix);

        //获取请求头中的数据
        List<RequestHeader> requestHeaderList = requestHeaderService.findRequestHeaderList(new RequestHeaderQuery().setApiId(id));
        if(CollectionUtils.isNotEmpty(requestHeaderList)){
            wsApi.setHeaderList(requestHeaderList);
        }

        //获取查询参数的数据
        List<QueryParam> queryParamList = queryParamService.findQueryParamList(new QueryParamQuery().setApiId(id));
        if(CollectionUtils.isNotEmpty(queryParamList)){
            wsApi.setQueryList(queryParamList);
        }

        //获取请求体的类型
        ApiRequest apiRequest = apiRequestService.findApiRequest(id);
        wsApi.setRequest(apiRequest);
        String bodyType = apiRequest.getBodyType();

        if(!ObjectUtils.isEmpty(bodyType)){
            if("json".equals(bodyType)){
                //获取json数据
                JsonParam jsonParam = jsonParamService.findJsonParam(id);
                wsApi.setJsonParam(jsonParam);
            } else if("raw".equals(bodyType)){
                //获取raw数据
                RawParam rawParam = rawParamService.findRawParam(id);
                if(!ObjectUtils.isEmpty(rawParam)){
                    wsApi.setRawParam(rawParam);
                }
            }
        }
//
//        //获取响应里的参数
//        //响应头
//        List<ResponseHeader> responseHeaderList = responseHeaderService.findResponseHeaderList(new ResponseHeaderQuery().setWSId(wsId));
//        if(CollectionUtils.isNotEmpty(responseHeaderList)){
//            wsApi.setResponseHeaderList(responseHeaderList);
//        }
//
//        //响应示例
//        List<ApiResponse> apiResponseList = apiResponseService.findApiResponseList(new ApiResponseQuery().setWSId(wsId));
//        if(CollectionUtils.isNotEmpty(apiResponseList)){
//            wsApi.setResponseResultList(apiResponseList);
//        }
//


        return wsApi;
    }




}