package net.tiklab.postin.apidef.http.service;

import net.tiklab.postin.apidef.apix.model.Apix;
import net.tiklab.postin.apidef.apix.model.ApixQuery;
import net.tiklab.postin.apidef.apix.service.ApixService;
import net.tiklab.postin.apidef.http.dao.*;
import net.tiklab.postin.apidef.http.entity.*;
import net.tiklab.postin.apidef.http.model.*;
import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.dss.client.DssClient;
import net.tiklab.join.JoinTemplate;
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
* 用户服务业务处理
*/
@Service
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
    ApiResponseService apiResponseService;

    @Autowired
    DssClient disClient;



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

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setId(id);
        apiResponse.setHttpId(id);
        apiResponse.setBodyType("json");
        apiResponseService.createApiResponse(apiResponse);

        //创建apix
        Apix apix = httpApi.getApix();
        apix.setId(id);
        apixService.createApix(apix);


        return  id;
    }


    @Override
    public void updateHttpApi(@NotNull @Valid HttpApi httpApi) {
        HttpApiEntity httpApiEntity = BeanMapper.map(httpApi, HttpApiEntity.class);


        httpApiEntity.setApixId(httpApi.getId());
        httpApiDao.updateHttpApi(httpApiEntity);

        apixService.updateApix(httpApi.getApix());

    }

    @Override
    public void deleteHttpApi(@NotNull String id) {
        //删除
        httpApiDao.deleteHttpApi(id);
        //删除apix
        apixService.deleteApix(id);


        //删除索引
        disClient.delete(HttpApi.class,id);
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
        List<RequestHeader> requestHeaderList = requestHeaderService.findRequestHeaderList(new RequestHeaderQuery().setHttpId(httpId));
        if(CollectionUtils.isNotEmpty(requestHeaderList)){
            httpApi.setHeaderList(requestHeaderList);
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

            }else if(bodyType.equals("json")){
                //获取json数据
                List<JsonParam> jsonParamList = jsonParamService.findJsonParamListTree(new JsonParamQuery().setHttpId(httpId));
                if(CollectionUtils.isNotEmpty(jsonParamList)){
                    httpApi.setJsonList(jsonParamList);
                }

            }else if(bodyType.equals("raw")){
                //获取raw数据
                RawParam rawParam = rawParamService.findRawParam(httpId);
                if(!ObjectUtils.isEmpty(rawParam)){
                    httpApi.setRawParam(rawParam);
                }
            }
        }


        joinTemplate.joinQuery(httpApi);
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

        return httpApiList;
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