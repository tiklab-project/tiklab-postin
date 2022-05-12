package com.doublekit.apiboxpiugin.version.service;


import com.doublekit.apibox.apidef.apix.dao.ApixDao;
import com.doublekit.apibox.apidef.apix.entity.ApixEntity;
import com.doublekit.apibox.apidef.apix.model.Apix;
import com.doublekit.apibox.apidef.apix.model.ApixQuery;
import com.doublekit.apibox.apidef.http.dao.*;
import com.doublekit.apibox.apidef.http.entity.*;
import com.doublekit.apibox.apidef.http.model.*;
import com.doublekit.apibox.apidef.http.service.JsonParamService;
import com.doublekit.apibox.apidef.http.service.JsonResponseService;
import com.doublekit.apiboxpiugin.version.model.VersionMethod;
import com.doublekit.apiboxpiugin.version.model.VersionMethodQuery;
import com.doublekit.apiboxpiugin.version.model.VersionRequest;
import com.doublekit.apiboxpiugin.version.model.VersionRespon;
import com.doublekit.beans.BeanMapper;
import com.doublekit.core.page.Pagination;
import com.doublekit.dis.client.DisClient;
import com.doublekit.eam.common.Ticket;
import com.doublekit.eam.common.TicketContext;
import com.doublekit.eam.common.TicketHolder;
import com.doublekit.join.JoinTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* 用户服务业务处理
*/
@Service
public class MethodVersionServiceImpl implements MethodVersionService {

    @Autowired
    ApixDao apixDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    DisClient disClient;

    @Autowired
    AfterScriptDao afterScriptDao;

    @Autowired
    FormParamDao formParamDao;

    @Autowired
    JsonParamDao jsonParamDao;

    @Autowired
    JsonResponseDao jsonResponseDao;

    @Autowired
    PreScriptDao preScriptDao;

    @Autowired
    QueryParamDao queryParamDao;

    @Autowired
    RawParamDao rawParamDao;

    @Autowired
    RawResponseDao rawResponseDao;

    @Autowired
    RequestBodyDao requestBodyDao;

    @Autowired
    RequestHeaderDao requestHeaderDao;

    @Autowired
    ResponseHeaderDao responseHeaderDao;

    @Autowired
    ResponseResultDao responseResultDao;

    @Autowired
    JsonParamService jsonParamService;

    @Autowired
    JsonResponseService jsonResponseService;

    @Override
    public Pagination<Apix> findMethodVersionPage(ApixQuery methodExQuery) {
        Pagination<Apix> pg = new Pagination<>();

        return  findMethodPage(pg,methodExQuery);
    }

    @Override
    public VersionMethod queryVersiondetail(String versionId) {
        return findMethodVersiondetails(versionId);
    }


    @Override
    @Transactional
    public String addHistoryVersion(Apix methodEx) {
        //添加方法
        ApixEntity method = apixDao.findApix(methodEx.getId());
        //第一次添加版本  上一个版本id为null
        /*
       if (ObjectUtils.isEmpty(method.getOnVersionId())){
           method.setOnVersionId(method.getId());
           methodDao.updateMethod(method);
       }
         */
        //添加初始版本id
        //method.setVersionCode(methodEx.getVersionCode());
        //method.setOnVersionId(method.getOnVersionId());
        //添加创建人
        String creatUserId = findCreatUser();
        method.setCreateUser(creatUserId);
        method.setId(null);
        String id = apixDao.createApix(method);

        //添加后置脚本
        List<AfterScriptEntity> afterScriptList = findAfterScript(methodEx.getId());
        if (CollectionUtils.isNotEmpty(afterScriptList)){
            //后置脚本的id 就是方法的id
            afterScriptList.get(0).setId(id);
            //afterScriptList.get(0).setMethodId(id);
            afterScriptDao.createAfterScript(afterScriptList.get(0));
        }
        //添加form参数
        List<FormParamEntity> formParamList = findformParam(methodEx.getId());
        if (CollectionUtils.isNotEmpty(formParamList)){
            for (FormParamEntity formParamEntity:formParamList){
                formParamEntity.setId(null);
                //formParamEntity.setMethodId(id);
                formParamDao.createFormParam(formParamEntity);
            }
        }
        //添加jsonParam参数
        List<JsonParamEntity> jsonParamList = findJsonParam(methodEx.getId(),0);
        if (CollectionUtils.isNotEmpty(jsonParamList)){
            //查询出父集
            List<JsonParamEntity> jsonParamCollet = jsonParamList.stream().filter(jsonParamPo -> ObjectUtils.isEmpty(jsonParamPo.getParentId())).collect(Collectors.toList());
            //子集
            List<JsonParamEntity> jsonchildParamCollet = jsonParamList.stream().filter(jsonParamPo -> !ObjectUtils.isEmpty(jsonParamPo.getParentId())).collect(Collectors.toList());
           //先创建所有的父级
            for (JsonParamEntity jsonParamPo:jsonParamCollet){
                //jsonParamPo.setPreVersionId(jsonParamPo.getId());
                jsonParamPo.setId(null);
                //jsonParamPo.setMethodId(id);
                jsonParamDao.createJsonParam(jsonParamPo);
            }
            //存在子集
            if (CollectionUtils.isNotEmpty(jsonchildParamCollet)){
                //创建子集
                for (JsonParamEntity jsonParamPo:jsonchildParamCollet){
                    //jsonParamPo.setPreVersionId(jsonParamPo.getId());
                    jsonParamPo.setId(null);
                    //jsonParamPo.setMethodId(id);
                    List<JsonParamEntity> jsonParam = findJsonParam(jsonParamPo.getParentId(), 1);
                    if (CollectionUtils.isNotEmpty(jsonParam)){
                        String parentJsonParamId = jsonParam.get(0).getId();
                        jsonParamPo.setParentId(parentJsonParamId);
                    }
                    jsonParamDao.createJsonParam(jsonParamPo);
                }
            }
        }
        //添加jsionRespon参数
        List<JsonResponseEntity> jsonResponseList = findjsonResponse(methodEx.getId(),0);
        if (CollectionUtils.isNotEmpty(jsonResponseList)){
            //查询出父集
            List<JsonResponseEntity> JsonResponseCollet = jsonResponseList.stream().filter(JsonResponseEntity -> ObjectUtils.isEmpty(JsonResponseEntity.getParentId())).collect(Collectors.toList());
            //子集
            List<JsonResponseEntity> jsonResponChildParamCollet = jsonResponseList.stream().filter(JsonResponseEntity -> !ObjectUtils.isEmpty(JsonResponseEntity.getParentId())).collect(Collectors.toList());
            //先创建所有的父级
            for (JsonResponseEntity jsonResponseEntity:JsonResponseCollet){
                //jsonResponseEntity.setPreVersionId(jsonResponseEntity.getId());
                jsonResponseEntity.setId(null);
                //jsonResponseEntity.setMethodId(id);
                jsonResponseDao.createJsonResponse(jsonResponseEntity);
            }
            //存在子集
            if (CollectionUtils.isNotEmpty(jsonResponChildParamCollet)){
                //创建子集
                for (JsonResponseEntity jsonResponseEntity:jsonResponChildParamCollet){
                    //jsonResponseEntity.setPreVersionId(jsonResponseEntity.getId());
                    jsonResponseEntity.setId(null);
                    //jsonResponseEntity.setMethodId(id);
                    List<JsonResponseEntity> jsonResponseEntitys = findjsonResponse(jsonResponseEntity.getParentId(), 1);
                    if (CollectionUtils.isNotEmpty(jsonResponseEntitys)){
                        String parentJsonParamId = jsonResponseEntitys.get(0).getId();
                        jsonResponseEntity.setParentId(parentJsonParamId);
                    }
                    jsonResponseDao.createJsonResponse(jsonResponseEntity);
                }
            }
        }
        //添加preScript参数
        List<PreScriptEntity> preScriptList = findPreScript(methodEx.getId());
        if (CollectionUtils.isNotEmpty(preScriptList)){
            for (PreScriptEntity preScriptEntity:preScriptList){
                //preScript的id取的method的id
                preScriptEntity.setId(id);
                //preScriptEntity.setMethodId(id);
                preScriptDao.createPreScript(preScriptEntity);
            }
        }
        //添加queryParam参数
        List<QueryParamEntity> queryParamList = findQueryParam(methodEx.getId());
        if (CollectionUtils.isNotEmpty(queryParamList)){
            for (QueryParamEntity qeryParamPo:queryParamList){
                qeryParamPo.setId(null);
                //qeryParamPo.setMethodId(id);
                queryParamDao.createQueryParam(qeryParamPo);
            }
        }
        //添加rawParam 参数
        List<RawParamEntity> rawParamList = findRawParam(methodEx.getId());
        if (CollectionUtils.isNotEmpty(rawParamList)){
            for (RawParamEntity rawParamEntity:rawParamList){
                //rawParam 参数的id 取的method的id
                rawParamEntity.setId(id);
                //rawParamEntity.setMethodId(id);
                rawParamDao.createRawParam(rawParamEntity);
            }
        }
        //添加rawRespon 参数
        List<RawResponseEntity> rawResponseList = findRawResponse(methodEx.getId());
        if (CollectionUtils.isNotEmpty(rawResponseList)){
            for (RawResponseEntity rawResponseEntity:rawResponseList){
                //rawRespon参数的id 取的method的id
                rawResponseEntity.setId(id);
                //rawResponseEntity.setMethodId(id);
                rawResponseDao.createRawResponse(rawResponseEntity);
            }
        }
        //添加requestBody参数
        List<RequestBodyEntity> requestBodyList = findRequestBody(methodEx.getId());
        if (CollectionUtils.isNotEmpty(requestBodyList)){
            for (RequestBodyEntity requestBodyEntity:requestBodyList){
                //requestBody的id取的method 的id
                requestBodyEntity.setId(id);
                //requestBodyEntity.setMethodId(id);
                requestBodyDao.createRequestBody(requestBodyEntity);
            }
        }
        //添加requestHeader参数
        List<RequestHeaderEntity> requestHeaderList = findRequestHeader(methodEx.getId());
        if (CollectionUtils.isNotEmpty(requestHeaderList)){
            for (RequestHeaderEntity requestHeaderEntity:requestHeaderList){
                requestHeaderEntity.setId(null);
                //requestHeaderEntity.setMethodId(id);
                requestHeaderDao.createRequestHeader(requestHeaderEntity);
            }
        }
        //添加responseHeader参数
        List<ResponseHeaderEntity> responseHeaderList = findResponseHeader(methodEx.getId());
        if (CollectionUtils.isNotEmpty(responseHeaderList)){
            for (ResponseHeaderEntity responseHeaderEntity:responseHeaderList){
                responseHeaderEntity.setId(null);
                //responseHeaderEntity.setMethodId(id);
                responseHeaderDao.createResponseHeader(responseHeaderEntity);
            }
        }
        //添加responseResult参数
        List<ResponseResultEntity> responseResultList = findResponseResult(methodEx.getId());
        if (CollectionUtils.isNotEmpty(responseResultList)){
            for (ResponseResultEntity responseResultEntity:responseResultList){
                responseResultEntity.setId(id);
                //responseResultEntity.setMethodId(id);
                responseResultDao.createResponseResult(responseResultEntity);
            }
        }
        return null;
    }



    @Override
    public Map contrastVersion(VersionMethodQuery versionMethodQuery) {
        List different = new ArrayList<>();
        Map versionMap = new HashMap<>();
        //当前版本
        VersionMethod currentVersion = findMethodVersiondetails(versionMethodQuery.getCurrentId());
        //历史版本
        VersionMethod oldVersionde = findMethodVersiondetails(versionMethodQuery.getOldId());
        versionMap.put("currentVersion",currentVersion);
        versionMap.put("oldVersion",oldVersionde);
        versionMap.put("different",different);
        return versionMap;
    }

    /**
     * 查询请求头参数
     * @param id
     */
    public List<RequestHeaderEntity> findRequestHeader(String id) {
        RequestHeaderQuery requestHeaderQuery = new RequestHeaderQuery();
        //requestHeaderQuery.setMethodId(id);
        List<RequestHeaderEntity> requestHeaderList = requestHeaderDao.findRequestHeaderList(requestHeaderQuery);
        return requestHeaderList;
    }

    /**
     * 查询 请求体参数类型
     * @param id
     */
    public List<RequestBodyEntity> findRequestBody(String id) {
        RequestBodyExQuery requestBodyExQuery = new RequestBodyExQuery();
        //requestBodyExQuery.setMethodId(id);
        List<RequestBodyEntity> requestBodyList = requestBodyDao.findRequestBodyList(requestBodyExQuery);
        return requestBodyList;
    }

    /**
     * 查询 请求体from类型的参数
     * @param id
     */
    public List<FormParamEntity> findformParam(String id) {
        FormParamQuery formParamQuery = new FormParamQuery();
        //formParamQuery.setMethodId(id);
        List<FormParamEntity> formParamList = formParamDao.findFormParamList(formParamQuery);
        return formParamList;
    }

    /**
     * 查询 请求体json类型的参数
     * @param id
     */
    public List<JsonParamEntity> findJsonParam(String id,Integer type) {
        JsonParamQuery jsonParamQuery = new JsonParamQuery();
        if (type==0){
            //jsonParamQuery.setMethodId(id);
        }
        if (type==1){
            //jsonParamQuery.setPreVersionId(id);
        }
        List<JsonParamEntity> jsonParamList = jsonParamDao.findJsonParamList(jsonParamQuery);
        return jsonParamList;
    }
    /**
     * 查询 请求体raw类型的参数
     * @param id
     */
    public List<RawParamEntity> findRawParam(String id) {
        RawParamQuery rawParamQuery = new RawParamQuery();
        //rawParamQuery.setMethodId(id);
        List<RawParamEntity> rawParamList = rawParamDao.findRawParamList(rawParamQuery);
        return rawParamList;
    }
    /**
     * 查询 查询参数
     * @param id
     */
    public List<QueryParamEntity> findQueryParam(String id) {
        QueryParamQuery queryParamQuery = new QueryParamQuery();
        //queryParamQuery.setMethodId(id);
        List<QueryParamEntity> queryParamList = queryParamDao.findQueryParamList(queryParamQuery);
        return queryParamList;
    }

    /**
     * 查询 前置脚本参数
     * @param id
     */
    public List<PreScriptEntity> findPreScript(String id) {
        PreScriptQuery preScriptQuery = new PreScriptQuery();
        //preScriptQuery.setMethodId(id);
        List<PreScriptEntity> preScriptList = preScriptDao.findPreScriptList(preScriptQuery);
        return preScriptList;
    }
    /**
     * 查询 后置脚本参数
     * @param id
     */
    public List<AfterScriptEntity> findAfterScript(String id) {
        AfterScriptQuery afterScriptQuery = new AfterScriptQuery();
        //afterScriptQuery.setMethodId(id);
        List<AfterScriptEntity> afterScriptList = afterScriptDao.findAfterScriptList(afterScriptQuery);
        return afterScriptList;
    }
    /**
     * 查询 响应头部数据
     * @param id
     */
    public List<ResponseHeaderEntity> findResponseHeader(String id) {
        ResponseHeaderQuery responseHeaderQuery = new ResponseHeaderQuery();
        //responseHeaderQuery.setMethodId(id);
        List<ResponseHeaderEntity> responseHeaderList = responseHeaderDao.findResponseHeaderList(responseHeaderQuery);
        return responseHeaderList;
    }

    /**
     * 查询 响应类型
     * @param id
     */
    public List<ResponseResultEntity> findResponseResult(String id) {
        ResponseResultQuery responseResultQuery = new ResponseResultQuery();
        //responseResultQuery.setMethodId(id);
        List<ResponseResultEntity> responseResultList = responseResultDao.findResponseResultList(responseResultQuery);
        return responseResultList;
    }
    /**
     * 查询 raw类型返回结果
     * @param id
     */
    public List<RawResponseEntity> findRawResponse(String id) {
        RawResponseQuery rawResponseQuery = new RawResponseQuery();
        //rawResponseQuery.setMethodId(id);
        List<RawResponseEntity> rawResponseList = rawResponseDao.findRawResponseList(rawResponseQuery);
        return rawResponseList;
    }

    /**
     * 查询 json类型返回参数
     * @param id
     */
    public List<JsonResponseEntity> findjsonResponse(String id,Integer type) {
        JsonResponseQuery jsonResponseQuery = new JsonResponseQuery();
        if (type==0){
            //jsonResponseQuery.setMethodId(id);
        }
        if (type==1){
            //jsonResponseQuery.setPreVersionId(id);
        }
        List<JsonResponseEntity> jsonResponseList = jsonResponseDao.findJsonResponseList(jsonResponseQuery);
        return jsonResponseList;
    }


    /**
     * 分页查询
     * @param
     */
    public Pagination<Apix> findMethodPage(Pagination pg, ApixQuery apixQuery) {
//        Pagination<ApixEntity> pagination = apixDao.findApixPage(apixQuery);
//
//        BeanUtils.copyProperties(pagination,pg);
//
//        List<Apix> methodExList = BeanMapper.mapList(pagination.getDataList(), Apix.class);
//
//        joinTemplate.joinQuery(methodExList);
//
//        pg.setDataList(methodExList);
        return pg;
    }
    /**
     * 查询版本详情
     * @param
     */
    public VersionMethod findMethodVersiondetails(String id) {
        VersionMethod versionMethod = new VersionMethod();
        VersionRequest versionRequest = new VersionRequest();
        VersionRespon versionRespon = new VersionRespon();
        //查询方法数据
        ApixEntity method = apixDao.findApix(id);
        Apix methodEx = BeanMapper.map(method, Apix.class);
        joinTemplate.joinQuery(methodEx);
        if(ObjectUtils.isEmpty(method)){
            return null;
        }
        //查询请求头部
        List<RequestHeaderEntity> requestHeaderEntitys = findRequestHeader(method.getId());
        if (CollectionUtils.isNotEmpty(requestHeaderEntitys)){
            List<RequestHeader> requestHeaderList = BeanMapper.mapList(requestHeaderEntitys,RequestHeader.class);
            versionRequest.setRequestHeaderList(requestHeaderList);
        }
        //查询 请求参数类型
        List<RequestBodyEntity> requestBody = findRequestBody(id);
        if (CollectionUtils.isNotEmpty(requestBody)){
            List<RequestBodyEx> requestBodyExes = BeanMapper.mapList(requestBody, RequestBodyEx.class);
            versionRequest.setRequestBodyExList(requestBodyExes);
            for(RequestBodyEntity requestBodyEntity:requestBody){
                //请求参数类型为fom
                if (requestBodyEntity.getBodyType().equals("form")){
                    List<FormParamEntity> formParamEntitys = findformParam(id);
                    if (CollectionUtils.isNotEmpty(formParamEntitys)) {
                        List<FormParam> formParams = BeanMapper.mapList(formParamEntitys, FormParam.class);
                        versionRequest.setFormParamList(formParams);
                    }
                }
                //请求参数为  json
                if (requestBodyEntity.getBodyType().equals("json")){
                    JsonParamQuery jsonParamQuery = new JsonParamQuery();
                    //jsonParamQuery.setMethodId(id);
                    List<JsonParam> jsonParamListTree = jsonParamService.findJsonParamListTree(jsonParamQuery);
                    versionRequest.setJsonParamList(jsonParamListTree);
                }
                //请求参数为 raw
                if (requestBodyEntity.getBodyType().equals("raw")){
                    List<RawParamEntity> rawParam = findRawParam(id);
                    if (CollectionUtils.isNotEmpty(rawParam)){
                        List<RawParam> rawParams = BeanMapper.mapList(rawParam, RawParam.class);
                        versionRequest.setRawParamList(rawParams);
                    }
                }
            }
        }
        //查询 查询参数
        List<QueryParamEntity> queryParam = findQueryParam(id);
        if(CollectionUtils.isNotEmpty(queryParam)){
            List<QueryParam> queryParams = BeanMapper.mapList(queryParam, QueryParam.class);
            versionRequest.setQueryParamList(queryParams);
        }
        //查询前置脚本
        List<PreScriptEntity> preScript = findPreScript(id);
        if (CollectionUtils.isNotEmpty(preScript)){
            List<PreScript> preScripts = BeanMapper.mapList(preScript, PreScript.class);
            versionRequest.setPreScriptList(preScripts);
        }
        //查询后置脚本
        List<AfterScriptEntity> afterScript = findAfterScript(id);
        if (CollectionUtils.isNotEmpty(afterScript)){
            List<AfterScript> afterScripts = BeanMapper.mapList(afterScript, AfterScript.class);
            versionRequest.setAfterScriptList(afterScripts);
        }

        //查询响应头部数据
        List<ResponseHeaderEntity> responseHeader = findResponseHeader(id);
        if (CollectionUtils.isNotEmpty(responseHeader)){
            List<ResponseHeader> responseHeaders = BeanMapper.mapList(responseHeader, ResponseHeader.class);
            versionRespon.setResponseHeaderList(responseHeaders);
        }
        //查询响应结果类型
        List<ResponseResultEntity> responseResult = findResponseResult(id);
        if (CollectionUtils.isNotEmpty(responseResult)){
            List<ResponseResult> responseResults = BeanMapper.mapList(responseResult, ResponseResult.class);
            versionRespon.setResponseResultList(responseResults);
            for (ResponseResultEntity responseResultEntity:responseResult){
                //查询json 返回类型参数
                if (responseResultEntity.getResultType().equals("json")){
                    JsonResponseQuery jsonResponseQuery = new JsonResponseQuery();
                    jsonResponseQuery.setHttpId(id);
                    List<JsonResponse> jsonResponseListTree = jsonResponseService.findJsonResponseListTree(jsonResponseQuery);
                    versionRespon.setJsonResponseList(jsonResponseListTree);

                }
                //查询raw 返回类型参数
                if (responseResultEntity.getResultType().equals("raw")){
                    List<RawResponseEntity> rawResponse = findRawResponse(id);
                    if (CollectionUtils.isNotEmpty(rawResponse)){
                        List<RawResponse> rawResponses = BeanMapper.mapList(rawResponse, RawResponse.class);
                        versionRespon.setRawResponseList(rawResponses);
                    }
                }
            }
        }
        versionMethod.setApix(methodEx);
        versionMethod.setVersionRequest(versionRequest);
        versionMethod.setVersionRespon(versionRespon);
        return versionMethod;
    }
    /**
     * 查询用户（创建人）id
     * @param
     */
    public String findCreatUser(){
        String ticketId = TicketHolder.get();
        Ticket ticket = TicketContext.get(ticketId);
        return ticket.getUserId();
    }
}