package com.doublekit.apibox.apidef.http.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.doublekit.apibox.apidef.apix.model.Apix;
import com.doublekit.apibox.apidef.apix.model.ApixQuery;
import com.doublekit.apibox.apidef.apix.service.ApixService;
import com.doublekit.apibox.apidef.http.dao.*;
import com.doublekit.apibox.apidef.http.entity.*;
import com.doublekit.apibox.apidef.http.model.*;
import com.doublekit.apibox.sysmgr.support.MessageTemplateConstant;
import com.doublekit.apibox.category.model.Category;
import com.doublekit.beans.BeanMapper;
import com.doublekit.core.page.Pagination;
import com.doublekit.core.page.PaginationBuilder;
import com.doublekit.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import com.doublekit.dal.jpa.criterial.condition.DeleteCondition;
import com.doublekit.dss.client.DssClient;
import com.doublekit.join.JoinTemplate;
import com.doublekit.message.message.model.Message;
import com.doublekit.message.message.model.MessageReceiver;
import com.doublekit.message.message.model.MessageTemplate;
import com.doublekit.message.message.service.MessageService;
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
    AfterScriptDao afterScriptDao;

    @Autowired
    RequestHeaderDao requestHeaderDao;

    @Autowired
    QueryParamDao queryParamDao;

    @Autowired
    RequestBodyDao requestBodyDao;

    @Autowired
    FormParamDao formParamDao;

    @Autowired
    JsonParamDao jsonParamDao;

    @Autowired
    FormUrlencodedDao formUrlencodedDao;

    @Autowired
    RawParamDao rawParamDao;

    @Autowired
    BinaryParamDao binaryParamDao;

    @Autowired
    PreScriptDao preScriptDao;

    @Autowired
    JsonResponseDao jsonResponseDao;

    @Autowired
    RawResponseDao rawResponseDao;

    @Autowired
    ResponseHeaderDao responseHeaderDao;

    @Autowired
    ResponseResultDao responseResultDao;

    @Autowired
    RequestHeaderService requestHeaderService;

    @Autowired
    QueryParamService queryParamService;

    @Autowired
    RequestBodyService requestBodyService;

    @Autowired
    FormParamService formParamService;

    @Autowired
    FormUrlencodedService formUrlencodedService;

    @Autowired
    JsonParamService jsonParamService;

    @Autowired
    RawParamService rawParamService;

    @Autowired
    PreScriptService preScriptService;

    @Autowired
    AfterScriptService afterScriptService;


    @Autowired
    DssClient disClient;

    @Autowired
    MessageService messageService;

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

        //创建apix
        Apix apix = apix(httpApi, id);
        apixService.createApix(apix);

        //初始化请求体类型
        RequestBodyEx requestBodyEx = new RequestBodyEx();
        requestBodyEx.setId(id);
        requestBodyEx.setHttp(new HttpApi().setId(id));
        requestBodyEx.setBodyType("none");
        requestBodyService.createRequestBody(requestBodyEx);

        return  id;
    }


    @Override
    public void updateHttpApi(@NotNull @Valid HttpApi httpApi) {
        HttpApiEntity httpApiEntity = BeanMapper.map(httpApi, HttpApiEntity.class);

        httpApiEntity.setApixId(httpApi.getId());
        httpApiDao.updateHttpApi(httpApiEntity);

        Apix apix = apix(httpApi, httpApi.getId());
        apixService.updateApix(apix);

    }

    public Apix apix(HttpApi httpApi,String id){
        Apix apix = new Apix();

        Category category = new Category();
        category.setId(httpApi.getApix().getCategory().getId());
        apix.setWorkspaceId(httpApi.getApix().getWorkspaceId());
        apix.setCategory(category);
        apix.setId(id);
        apix.setName(httpApi.getApix().getName());
        apix.setDesc(httpApi.getApix().getDesc());
        apix.setProtocolType(httpApi.getApix().getProtocolType());
        apix.setExecutor(httpApi.getApix().getExecutor());
        apix.setStatus(httpApi.getApix().getStatus());
        apix.setApiUid(httpApi.getApix().getApiUid());
        apix.setVersion(httpApi.getApix().getVersion());

        return apix;
    }



    @Override
    public void deleteHttpApi(@NotNull String id) {
        //删除
        httpApiDao.deleteHttpApi(id);
        //删除apix
        apixService.deleteApix(id);

        //删除后置脚本数据
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(AfterScriptEntity.class)
                .eq("httpId", id)
                .get();
        afterScriptDao.deleteAfterScriptList(deleteCondition);

        //删除requestHeader
        deleteCondition = DeleteBuilders.createDelete(RequestHeaderEntity.class)
                .eq("httpId", id)
                .get();
        requestHeaderDao.deleteRequestHeaderList(deleteCondition);

        //删除query类型请求体
        deleteCondition = DeleteBuilders.createDelete(QueryParamEntity.class)
                .eq("httpId", id)
                .get();
        queryParamDao.deleteQueryParamList(deleteCondition);

        //删除requestBoy
        deleteCondition = DeleteBuilders.createDelete(RequestBodyEntity.class)
                .eq("httpId", id)
                .get();
        requestBodyDao.deleteRequestBodyList(deleteCondition);

        //删除form类型请求体
        deleteCondition = DeleteBuilders.createDelete(FormParamEntity.class)
                .eq("httpId", id)
                .get();
        formParamDao.deleteFormParamLsit(deleteCondition);

        //删除formUrlencodedDao
        deleteCondition = DeleteBuilders.createDelete(FormParamEntity.class)
                .eq("httpId", id)
                .get();
        formUrlencodedDao.deleteFormUrlencoded(deleteCondition);

        //删除json类型请求体
        deleteCondition = DeleteBuilders.createDelete(JsonParamEntity.class)
                .eq("httpId", id)
                .get();
        jsonParamDao.deleteJsonParamList(deleteCondition);

        //删除json类型响应结果
        deleteCondition = DeleteBuilders.createDelete(JsonResponseEntity.class)
                .eq("httpId", id)
                .get();
        jsonResponseDao.deleteJsonResponseList(deleteCondition);

        //删除rawParam 类型请求体
        deleteCondition = DeleteBuilders.createDelete(RawParamEntity.class)
                .eq("httpId", id)
                .get();
        rawParamDao.deleteRawParamlist(deleteCondition);

        //删除binaryParam
        deleteCondition = DeleteBuilders.createDelete(RawParamEntity.class)
                .eq("httpId", id)
                .get();
        binaryParamDao.deleteBinaryParam(deleteCondition);

        //删除preScrit 前置脚本
        deleteCondition = DeleteBuilders.createDelete(PreScriptEntity.class)
                .eq("httpId", id)
                .get();
        preScriptDao.deletePreScriptList(deleteCondition);

        //删除rawResponse返回类型
        deleteCondition = DeleteBuilders.createDelete(RawResponseEntity.class)
                .eq("httpId", id)
                .get();
        rawResponseDao.deleteRawResponseList(deleteCondition);

        //删除responseHeader
        deleteCondition = DeleteBuilders.createDelete(ResponseHeaderEntity.class)
                .eq("httpId", id)
                .get();
        responseHeaderDao.deleteResponseHeaderList(deleteCondition);

        //删除responseResult
        deleteCondition = DeleteBuilders.createDelete(ResponseResultEntity.class)
                .eq("httpId", id)
                .get();
        responseResultDao.deleteResponseResultList(deleteCondition);

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
            httpApi.setRequestHeaderList(requestHeaderList);
        }

        //获取查询参数的数据
        List<QueryParam> queryParamList = queryParamService.findQueryParamList(new QueryParamQuery().setHttpId(httpId));
        if(CollectionUtils.isNotEmpty(queryParamList)){
            httpApi.setQueryParamList(queryParamList);
        }

        //获取请求体的类型
        RequestBodyEx requestBody = requestBodyService.findRequestBody(httpId);
        httpApi.setRequestBody(requestBody);
        String bodyType = requestBody.getBodyType();

        if(bodyType.equals("formdata")){
            //获取formdata数据
            List<FormParam> formParamList = formParamService.findFormParamList(new FormParamQuery().setHttpId(httpId));
            if(CollectionUtils.isNotEmpty(formParamList)){
                httpApi.setFormParamList(formParamList);
            }

        }else if(bodyType.equals("formUrlencoded")){
            //获取formurlencoded数据
            List<FormUrlencoded> formUrlencodedList = formUrlencodedService.findFormUrlencodedList(new FormUrlencodedQuery().setHttpId(httpId));
            if(CollectionUtils.isNotEmpty(formUrlencodedList)){
                httpApi.setFormUrlencodedList(formUrlencodedList);
            }

        }else if(bodyType.equals("json")){
            //获取json数据
            List<JsonParam> jsonParamList = jsonParamService.findJsonParamListTree(new JsonParamQuery().setHttpId(httpId));
            if(CollectionUtils.isNotEmpty(jsonParamList)){
                httpApi.setJsonParamList(jsonParamList);
            }

        }else if(bodyType.equals("raw")){
            //获取raw数据
            RawParam rawParam = rawParamService.findRawParam(httpId);
            if(!ObjectUtils.isEmpty(rawParam)){
                httpApi.setRawParam(rawParam);
            }

        }

        //获取前置脚本数据
        PreScript preScript = preScriptService.findPreScript(httpId);
        if(!ObjectUtils.isEmpty(preScript)){
            httpApi.setPreScript(preScript);
        }

        //获取后置脚本数据
        AfterScript afterScript = afterScriptService.findAfterScript(httpId);
        if(!ObjectUtils.isEmpty(afterScript)){
            httpApi.setAfterScript(afterScript);
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