package io.tiklab.postin.api.ws.ws.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.api.apix.service.ApixService;
import io.tiklab.postin.api.ws.ws.dao.WSApiDao;
import io.tiklab.postin.api.ws.ws.entity.WSApiEntity;
import io.tiklab.postin.api.ws.ws.model.WSApi;
import io.tiklab.postin.api.ws.ws.model.WSApiQuery;
import io.tiklab.postin.support.apistatus.model.ApiStatus;
import io.tiklab.postin.support.apistatus.service.ApiStatusService;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 定义
 * ws协议服务
*/
@Service
@Exporter
public class WSApiServiceImpl implements WSApiService {

    @Autowired
    WSApiDao wsApiDao;

    @Autowired
    ApixService apixService;

    @Autowired
    JoinTemplate joinTemplate;

//    @Autowired
//    RequestHeaderService requestHeaderService;
//
//    @Autowired
//    QueryParamService queryParamService;
//
//    @Autowired
//    ApiRequestService apiRequestService;
//
//    @Autowired
//    FormParamService formParamService;
//
//    @Autowired
//    FormUrlencodedService formUrlencodedService;
//
//    @Autowired
//    JsonParamService jsonParamService;
//
//    @Autowired
//    RawParamService rawParamService;
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
        WSApiEntity wsApiEntity = BeanMapper.map(wsApi, WSApiEntity.class);

        //如果没有id自动生成id
        if (ObjectUtils.isEmpty(wsApi.getId())) {
            String uid = UUID.randomUUID().toString();
            String id = uid.trim().replaceAll("-", "");
            wsApiEntity.setId(id);
        }

        String id = wsApiDao.createWSApi(wsApiEntity);

        //创建apix
        Apix apix = wsApi.getApix();
        apix.setId(id);
        apixService.createApix(apix);

        wsApiEntity.setApixId(id);
        wsApiEntity.setId(id);
        wsApiDao.updateWSApi(wsApiEntity);

//        //初始化请求响应中的类型
//        ApiRequest apiRequest = new ApiRequest();
//        apiRequest.setId(id);
//        apiRequest.setWSId(id);
//        apiRequest.setBodyType("none");
//        apiRequestService.createApiRequest(apiRequest);
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


        return  id;
    }


    @Override
    public void updateWSApi(@NotNull @Valid WSApi wsApi) {
        WSApiEntity wsApiEntity = BeanMapper.map(wsApi, WSApiEntity.class);


        wsApiEntity.setApixId(wsApi.getId());
        wsApiDao.updateWSApi(wsApiEntity);

        Apix apix = wsApi.getApix();
        apixService.updateApix(apix);

    }

    @Override
    public void deleteWSApi(@NotNull String wsId) {
        //删除
        wsApiDao.deleteWSApi(wsId);

//        //删除请求头
//        requestHeaderService.deleteAllRequestHeader(wsId);
//
//        //删除query
//        queryParamService.deleteAllQueryParam(wsId);
//
//        //删除formdata
//        formParamService.deleteAllFormParam(wsId);
//
//        //删除formurl
//        formUrlencodedService.deleteAllFormUrlencoded(wsId);
//
//        //删除raw
//        rawParamService.deleteRawParam(wsId);
//
//        //删除请求
//        apiRequestService.deleteApiRequest(wsId);
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
    public WSApi findOne(String id) {
        WSApiEntity wsApiEntity = wsApiDao.findWSApi(id);

        WSApi wsApi = BeanMapper.map(wsApiEntity, WSApi.class);
        return wsApi;
    }

    @Override
    public List<WSApi> findList(List<String> idList) {
        List<WSApiEntity> wsApiEntityList =  wsApiDao.findWSApiList(idList);

        List<WSApi> wsApiList = BeanMapper.mapList(wsApiEntityList, WSApi.class);

        return wsApiList;
    }

    @Override
    public WSApi findWSApi(@NotNull String id) {
        WSApi wsApi = findOne(id);



//        //获取请求头中的数据
//        List<RequestHeader> requestHeaderList = requestHeaderService.findRequestHeaderList(new RequestHeaderQuery().setWSId(wsId));
//        if(CollectionUtils.isNotEmpty(requestHeaderList)){
//            wsApi.setHeaderList(requestHeaderList);
//        }
//
//        //获取查询参数的数据
//        List<QueryParam> queryParamList = queryParamService.findQueryParamList(new QueryParamQuery().setWSId(wsId));
//        if(CollectionUtils.isNotEmpty(queryParamList)){
//            wsApi.setQueryList(queryParamList);
//        }
//
//        //获取请求体的类型
//        ApiRequest apiRequest = apiRequestService.findApiRequest(wsId);
//        wsApi.setRequest(apiRequest);
//        String bodyType = apiRequest.getBodyType();
//
//        if(!ObjectUtils.isEmpty(bodyType)){
//            if("formdata".equals(bodyType)){
//                //获取formdata数据
//                List<FormParam> formParamList = formParamService.findFormParamList(new FormParamQuery().setWSId(wsId));
//                if(CollectionUtils.isNotEmpty(formParamList)){
//                    wsApi.setFormList(formParamList);
//                }
//
//            }else if("formUrlencoded".equals(bodyType)){
//                //获取formurlencoded数据
//                List<FormUrlencoded> formUrlencodedList = formUrlencodedService.findFormUrlencodedList(new FormUrlencodedQuery().setWSId(wsId));
//                if(CollectionUtils.isNotEmpty(formUrlencodedList)){
//                    wsApi.setUrlencodedList(formUrlencodedList);
//                }
//            }
//            else if("json".equals(bodyType)){
//                //获取json数据
//                JsonParam jsonParam = jsonParamService.findJsonParam(wsId);
//                wsApi.setJsonParam(jsonParam);
//            }
//            else if("raw".equals(bodyType)){
//                //获取raw数据
//                RawParam rawParam = rawParamService.findRawParam(wsId);
//                if(!ObjectUtils.isEmpty(rawParam)){
//                    wsApi.setRawParam(rawParam);
//                }
//            }
//        }
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
        joinTemplate.joinQuery(wsApi);

        Apix apix = apixService.findApix(id);
        wsApi.setApix(apix);

        return wsApi;
    }

    @Override
    public List<WSApi> findAllWSApi() {
        List<WSApiEntity> wsApiEntityList =  wsApiDao.findAllWSApi();

        List<WSApi> wsApiList = BeanMapper.mapList(wsApiEntityList, WSApi.class);

        joinTemplate.joinQuery(wsApiList);

        return wsApiList;
        }

    @Override
    public List<WSApi> findWSApiList(WSApiQuery wsApiQuery) {
        List<WSApiEntity> wsApiEntityList = wsApiDao.findWSApiList(wsApiQuery);

        List<WSApi> wsApiList = BeanMapper.mapList(wsApiEntityList, WSApi.class);

        joinTemplate.joinQuery(wsApiList);

        ArrayList<WSApi> newApiList = new ArrayList<>();

        //手动添加未显示的字段
        if(CollectionUtils.isNotEmpty(wsApiList)){
            for(WSApi wsApi:wsApiList){

                //状态
                if( wsApi.getApix().getStatus()!=null){
                    ApiStatus apiStatus = apiStatusService.findApiStatus( wsApi.getApix().getStatus().getId());
                    wsApi.getApix().setStatus(apiStatus);
                }

                //执行人
                if(wsApi.getApix().getExecutor()!=null){
                    User user = userService.findUser(wsApi.getApix().getCreateUser().getId());
                    wsApi.getApix().setExecutor(user);
                }

                newApiList.add(wsApi);
            }
        }

        return newApiList;
    }
    
  
    @Override
    public Pagination<WSApi> findWSApiPage(WSApiQuery wsApiQuery) {

        Pagination<WSApiEntity> wsApiPage = wsApiDao.findWSApiPage(wsApiQuery);

        List<WSApi> wsApis = BeanMapper.mapList(wsApiPage.getDataList(), WSApi.class);

        joinTemplate.joinQuery(wsApis);

        return  PaginationBuilder.build(wsApiPage,wsApis);
    }

    


}