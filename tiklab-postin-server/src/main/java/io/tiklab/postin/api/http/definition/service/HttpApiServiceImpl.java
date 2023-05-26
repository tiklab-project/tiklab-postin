package io.tiklab.postin.api.http.definition.service;

import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.api.apix.model.ApixQuery;
import io.tiklab.postin.api.apix.service.ApixService;
import io.tiklab.postin.api.http.definition.dao.HttpApiDao;
import io.tiklab.postin.api.http.definition.entity.HttpApiEntity;
import io.tiklab.postin.api.http.definition.model.*;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dss.client.DssClient;
import io.tiklab.join.JoinTemplate;
import io.tiklab.postin.api.http.mock.service.MockService;
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
 * http协议服务
*/
@Service
@Exporter
public class HttpApiServiceImpl implements HttpApiService {

    @Autowired
    HttpApiDao httpApiDao;

    @Autowired
    ApixService apixService;

    @Autowired
    JoinTemplate joinTemplate;

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
    JsonParamService jsonParamService;

    @Autowired
    RawParamService rawParamService;

    @Autowired
    ResponseHeaderService responseHeaderService;

    @Autowired
    ApiResponseService apiResponseService;

    @Autowired
    DssClient disClient;

    @Autowired
    ApiStatusService apiStatusService;

    @Autowired
    UserService userService;

    @Autowired
    MockService mockService;


    @Override
    public String createHttpApi(@NotNull @Valid HttpApi httpApi) {
        HttpApiEntity httpApiEntity = BeanMapper.map(httpApi, HttpApiEntity.class);

        //如果没有id自动生成id
        if (ObjectUtils.isEmpty(httpApi.getId())) {
            String uid = UUID.randomUUID().toString();
            String id = uid.trim().replaceAll("-", "");
            httpApiEntity.setId(id);
        }

        String id = httpApiDao.createHttpApi(httpApiEntity);

        httpApiEntity.setApixId(id);
        httpApiEntity.setId(id);
        httpApiDao.updateHttpApi(httpApiEntity);

        //初始化请求响应中的类型
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setId(id);
        apiRequest.setHttpId(id);
        apiRequest.setBodyType("none");
        apiRequestService.createApiRequest(apiRequest);

        JsonParam jsonParam = new JsonParam();
        jsonParam.setId(id);
        jsonParam.setHttpId(id);
        jsonParam.setJsonText(
                "{\n" +
                "    \"type\": \"object\",\n" +
                "    \"title\": \"title\",\n" +
                "    \"properties\": {}\n" +
                "}"
        );
        jsonParamService.createJsonParam(jsonParam);

        //初始化一个返回结果 数据类型为json， 所以设置值为jsonSchema结构的值
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setId(id);
        apiResponse.setHttpId(id);
        apiResponse.setHttpCode(200);
        apiResponse.setName("成功");
        apiResponse.setDataType("json");
        apiResponse.setJsonText(
                "{\n" +
                "    \"type\": \"object\",\n" +
                "    \"title\": \"title\",\n" +
                "    \"properties\": {}\n" +
                "}"
        );
        apiResponseService.createApiResponse(apiResponse);

        //创建apix
        Apix apix = httpApi.getApix();
        apix.setId(id);
        apix.setMethodType(httpApi.getMethodType());
        apixService.createApix(apix);


        return  id;
    }


    @Override
    public void updateHttpApi(@NotNull @Valid HttpApi httpApi) {
        HttpApiEntity httpApiEntity = BeanMapper.map(httpApi, HttpApiEntity.class);


        httpApiEntity.setApixId(httpApi.getId());
        httpApiDao.updateHttpApi(httpApiEntity);

        Apix apix = httpApi.getApix();
        apix.setMethodType(httpApi.getMethodType());
        apixService.updateApix(apix);

    }

    @Override
    public void deleteHttpApi(@NotNull String httpId) {
        //删除
        httpApiDao.deleteHttpApi(httpId);

        //删除请求头
        requestHeaderService.deleteAllRequestHeader(httpId);

        //删除query
        queryParamService.deleteAllQueryParam(httpId);

        //删除formdata
        formParamService.deleteAllFormParam(httpId);

        //删除formurl
        formUrlencodedService.deleteAllFormUrlencoded(httpId);

        //删除raw
        rawParamService.deleteRawParam(httpId);

        //删除请求
        apiRequestService.deleteApiRequest(httpId);

        //删除响应部分
        apiResponseService.deleteApiResponse(httpId);

        //删除响应头
        responseHeaderService.deleteAllResponseHeader(httpId);

        //删除mock
        mockService.deleteAllMock(httpId);


        //删除索引
//        disClient.delete(HttpApi.class,id);
    }

    @Override
    public HttpApi findOne(String id) {
        HttpApiEntity httpApiEntity = httpApiDao.findHttpApi(id);

        HttpApi httpApi = BeanMapper.map(httpApiEntity, HttpApi.class);
        return httpApi;
    }

    @Override
    public List<HttpApi> findList(List<String> idList) {
        List<HttpApiEntity> httpApiEntityList =  httpApiDao.findHttpApiList(idList);

        List<HttpApi> httpApiList = BeanMapper.mapList(httpApiEntityList, HttpApi.class);

        return httpApiList;
    }

    @Override
    public HttpApi findHttpApi(@NotNull String id) {
        HttpApi httpApi = findOne(id);
        String httpId = httpApi.getId();



        //获取请求头中的数据
        List<RequestHeaders> requestHeadersList = requestHeaderService.findRequestHeaderList(new RequestHeaderQuery().setHttpId(httpId));
        if(CollectionUtils.isNotEmpty(requestHeadersList)){
            httpApi.setHeaderList(requestHeadersList);
        }

        //获取查询参数的数据
        List<QueryParam> queryParamList = queryParamService.findQueryParamList(new QueryParamQuery().setHttpId(httpId));
        if(CollectionUtils.isNotEmpty(queryParamList)){
            httpApi.setQueryList(queryParamList);
        }

        //获取请求体的类型
        ApiRequest apiRequest = apiRequestService.findApiRequest(httpId);
        httpApi.setRequest(apiRequest);
        String bodyType = apiRequest.getBodyType();

        if(!ObjectUtils.isEmpty(bodyType)){
            if(bodyType.equals("formdata")){
                //获取formdata数据
                List<FormParam> formParamList = formParamService.findFormParamList(new FormParamQuery().setHttpId(httpId));
                if(CollectionUtils.isNotEmpty(formParamList)){
                    httpApi.setFormList(formParamList);
                }

            }else if(bodyType.equals("formUrlencoded")){
                //获取formurlencoded数据
                List<FormUrlencoded> formUrlencodedList = formUrlencodedService.findFormUrlencodedList(new FormUrlencodedQuery().setHttpId(httpId));
                if(CollectionUtils.isNotEmpty(formUrlencodedList)){
                    httpApi.setUrlencodedList(formUrlencodedList);
                }

            }
//            else if(bodyType.equals("json")){
//                //获取json数据
//                List<JsonParam> jsonParamList = jsonParamService.findJsonParamListTree(new JsonParamQuery().setHttpId(httpId));
//                if(CollectionUtils.isNotEmpty(jsonParamList)){
//                    httpApi.setJsonList(jsonParamList);
//                }
//
//            }
            else if(bodyType.equals("raw")){
                //获取raw数据
                RawParam rawParam = rawParamService.findRawParam(httpId);
                if(!ObjectUtils.isEmpty(rawParam)){
                    httpApi.setRawParam(rawParam);
                }
            }
        }

        //获取响应里的参数
        //响应头
        List<ResponseHeaders> responseHeadersList = responseHeaderService.findResponseHeaderList(new ResponseHeaderQuery().setHttpId(httpId));
        if(CollectionUtils.isNotEmpty(responseHeadersList)){
            httpApi.setResponseHeaderList(responseHeadersList);
        }

        //响应示例
        List<ApiResponse> apiResponseList = apiResponseService.findApiResponseList(new ApiResponseQuery().setHttpId(httpId));
        if(CollectionUtils.isNotEmpty(apiResponseList)){
            httpApi.setResponseResultList(apiResponseList);
        }

        joinTemplate.joinQuery(httpApi);

        Apix apix = apixService.findApix(id);
        httpApi.setApix(apix);

        return httpApi;
    }

    @Override
    public List<HttpApi> findAllHttpApi() {
        List<HttpApiEntity> httpApiEntityList =  httpApiDao.findAllHttpApi();

        List<HttpApi> httpApiList = BeanMapper.mapList(httpApiEntityList, HttpApi.class);

        joinTemplate.joinQuery(httpApiList);

        return httpApiList;
        }

    @Override
    public List<HttpApi> findHttpApiList(HttpApiQuery httpApiQuery) {
        List<HttpApiEntity> httpApiEntityList = httpApiDao.findHttpApiList(httpApiQuery);

        List<HttpApi> httpApiList = BeanMapper.mapList(httpApiEntityList, HttpApi.class);

        joinTemplate.joinQuery(httpApiList);

        ArrayList<HttpApi> newApiList = new ArrayList<>();

        //手动添加未显示的字段
        if(CollectionUtils.isNotEmpty(httpApiList)){
            for(HttpApi httpApi:httpApiList){

                //状态
                if( httpApi.getApix().getStatus()!=null){
                    ApiStatus apiStatus = apiStatusService.findApiStatus( httpApi.getApix().getStatus().getId());
                    httpApi.getApix().setStatus(apiStatus);
                }

                //执行人
                if(httpApi.getApix().getExecutor()!=null){
                    User user = userService.findUser(httpApi.getApix().getCreateUser().getId());
                    httpApi.getApix().setExecutor(user);
                }

                newApiList.add(httpApi);
            }
        }

        return newApiList;
    }

    @Override
    public Pagination<HttpApi> findHttpApiPage(HttpApiQuery httpApiQuery) {

        Pagination<HttpApiEntity> httpApiPage = httpApiDao.findHttpApiPage(httpApiQuery);

        List<HttpApi> httpApis = BeanMapper.mapList(httpApiPage.getDataList(), HttpApi.class);

        joinTemplate.joinQuery(httpApis);

        return  PaginationBuilder.build(httpApiPage,httpApis);
    }

    @Override
    public List<HttpApi> findHttpApiListByApix(ApixQuery apixQuery) {
        List<Apix> apixList = apixService.findApixList(apixQuery);

        ArrayList<HttpApi> arrayList = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(apixList)){
            for (Apix apix : apixList) {
                //去除带版本的api，因为跟随初始的api，没有设置apiUid的就是初始api
                if(!ObjectUtils.isEmpty(apix.getApiUid())){
                    continue;
                }

                //通过初始api，查询下面所有版本，拿到最新版本的api
                List<Apix> versionList = apixService.findApixList(new ApixQuery().setApiUid(apix.getId()));
                if(CollectionUtils.isNotEmpty(versionList)){
                    Apix recentApi = versionList.get(0);

                    //通过最新版本api的id，获取httpApi的值
                    List<HttpApi> httpApiList = findHttpApiList(new HttpApiQuery().setApixId(recentApi.getId()));
                    if (CollectionUtils.isNotEmpty(httpApiList)) {
                        arrayList.addAll(httpApiList);
                    }
                }else {
                    //如果没有版本，获取初始api中的httpApi
                    List<HttpApi> httpApiList = findHttpApiList(new HttpApiQuery().setApixId(apix.getId()));
                    arrayList.addAll(httpApiList);
                }
            }
        }

        return arrayList;
    }

    /**
     * 分页查询
     * @param
     */
    public Pagination<HttpApi> findHttpApi(HttpApiQuery httpApiQuery) {

        Pagination<HttpApiEntity>  pagination = httpApiDao.findHttpApiPage(httpApiQuery);

        List<HttpApi> httpApiList = BeanMapper.mapList(pagination.getDataList(), HttpApi.class);

        joinTemplate.joinQuery(httpApiList);

        return PaginationBuilder.build(pagination, httpApiList);
    }




}