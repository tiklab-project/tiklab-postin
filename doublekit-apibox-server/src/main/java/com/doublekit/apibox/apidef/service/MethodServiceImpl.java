package com.doublekit.apibox.apidef.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.doublekit.apibox.apidef.dao.*;
import com.doublekit.apibox.apidef.entity.*;
import com.doublekit.apibox.apidef.model.*;
import com.doublekit.apibox.apidef.support.MessageTemplateConstant;
import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.dal.jpa.builder.deletelist.condition.DeleteCondition;
import com.doublekit.dal.jpa.builder.deletelist.conditionbuilder.DeleteBuilders;
import com.doublekit.join.join.JoinQuery;
import com.doublekit.dss.client.DssClient;
import com.doublekit.message.message.model.Message;
import com.doublekit.message.message.model.MessageReceiver;
import com.doublekit.message.message.model.MessageTemplate;
import com.doublekit.message.message.service.MessageService;

import com.doublekit.user.auth.passport.context.TicketHolder;
import com.doublekit.user.user.model.User;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
* 用户服务业务处理
*/
@Service
public class MethodServiceImpl implements MethodService {

    @Autowired
    MethodDao methodDao;

    @Autowired
    JoinQuery joinQuery;

    @Autowired
    MessageService messageService;

    @Autowired
    DssClient dssClient;

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
    public String createMethod(@NotNull @Valid MethodEx methodEx) {
        //添加版本号  默认初始版本号为current
        methodEx.setVersionCode("current");
        //创建接口
        MethodPo methodPo = BeanMapper.map(methodEx, MethodPo.class);

        String id = methodDao.createMethod(methodPo);

        //添加索引
        MethodEx entity = findMethod(id);
        dssClient.save(entity);

        //发送消息
        sendMessageForCreate(entity);
        return id;
    }

    /**
     * 发送消息提醒
     * @param methodEx
     */
    void sendMessageForCreate(MethodEx methodEx){
        Message message = new Message();
        //设置模板ID
        message.setMessageTemplate(new MessageTemplate().setId(MessageTemplateConstant.TEMPLATE_ID_API_CREATE));
        //设置发送数据
        String data = JSON.toJSONString(methodEx, SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteDateUseDateFormat);
        message.setData(data);
        //设置接收人
        List<MessageReceiver> messageReceiverList = new ArrayList<>();
        MessageReceiver messageReceiver = new MessageReceiver()
                .setReceiver(new User().setId(TicketHolder.get()));
        messageReceiverList.add(messageReceiver);
        message.setMessageReceiverList(messageReceiverList);

        messageService.sendMessage(message);
    }

    @Override
    public void updateMethod(@NotNull @Valid MethodEx method) {
        //更新接口
        MethodPo methodPo = BeanMapper.map(method, MethodPo.class);

        methodDao.updateMethod(methodPo);

        //更新索引
        MethodEx entity = findMethod(method.getId());
        dssClient.update(entity);

        //发送更新消息提醒
        sendMessageForUpdate(entity);
    }

    /**
     * 发送消息提醒
     * @param methodEx
     */
    void sendMessageForUpdate(MethodEx methodEx){
        Message message = new Message();
        //设置模板ID
        message.setMessageTemplate(new MessageTemplate().setId(MessageTemplateConstant.TEMPLATE_ID_API_UPDATE));
        //设置发送数据
        String data = JSON.toJSONString(methodEx,SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteDateUseDateFormat);
        message.setData(data);
        //设置接收人
        List<MessageReceiver> messageReceiverList = new ArrayList<>();
        MessageReceiver messageReceiver = new MessageReceiver()
                .setReceiver(new User().setId(TicketHolder.get()));
        messageReceiverList.add(messageReceiver);
        message.setMessageReceiverList(messageReceiverList);

        messageService.sendMessage(message);
    }

    @Override
    public void deleteMethod(@NotNull String id) {
        //删除方法
        methodDao.deleteMethod(id);

        DeleteCondition deleteCondition = DeleteBuilders.instance().eq("methodId", id).get();
        //删除后置脚本数据
        afterScriptDao.deleteAfterScriptList(deleteCondition);

        //删除form类型请求体
        formParamDao.deleteFormParamLsit(deleteCondition);

        //删除json类型请求体
        jsonParamDao.deleteJsonParamList(deleteCondition);

        //删除json类型响应结果
        jsonResponseDao.deleteJsonResponseList(deleteCondition);

        //删除preScrit 前置脚本
        preScriptDao.deletePreScriptList(deleteCondition);

        //删除query类型请求体
        queryParamDao.deleteQueryParamList(deleteCondition);

        //删除rawParam 类型请求体
        rawParamDao.deleteRawParamlist(deleteCondition);

        //删除rawResponse返回类型
        rawResponseDao.deleteRawResponseList(deleteCondition);

        //删除requestBoy
        requestBodyDao.deleteRequestBodyList(deleteCondition);

        //删除requestHeader
        requestHeaderDao.deleteRequestHeaderList(deleteCondition);

        //删除responseHeader
        responseHeaderDao.deleteResponseHeaderList(deleteCondition);

        //删除responseResult
        responseResultDao.deleteResponseResultList(deleteCondition);

        //删除索引
        dssClient.delete(MethodEx.class,id);
    }

    @Override
    public MethodEx findOne(String id) {
        MethodPo methodPo = methodDao.findMethod(id);

        MethodEx methodEx = BeanMapper.map(methodPo, MethodEx.class);
        return methodEx;
    }

    @Override
    public List<MethodEx> findList(List<String> idList) {
        List<MethodPo> methodPoList =  methodDao.findMethodList(idList);

        List<MethodEx> methodExList = BeanMapper.mapList(methodPoList, MethodEx.class);

        return methodExList;
    }

    @Override
    public MethodEx findMethod(@NotNull String id) {
        MethodEx methodEx = findOne(id);

        joinQuery.queryOne(methodEx);
        return methodEx;
    }

    @Override
    public List<MethodEx> findAllMethod() {
        List<MethodPo> methodPoList =  methodDao.findAllMethod();

        List<MethodEx> methodExList = BeanMapper.mapList(methodPoList, MethodEx.class);

        joinQuery.queryList(methodExList);

        return methodExList;
        }

    @Override
    public List<MethodEx> findMethodList(MethodExQuery methodExQuery) {
        List<MethodPo> methodPoList = methodDao.findMethodList(methodExQuery);

        List<MethodEx> methodExList = BeanMapper.mapList(methodPoList, MethodEx.class);

        joinQuery.queryList(methodExList);

        return methodExList;
    }

    @Override
    public Pagination<MethodEx> findMethodPage(MethodExQuery methodExQuery) {
        Pagination<MethodEx> pg = new Pagination<>();

        //添加当前版本
        methodExQuery.setVersionCode("current");


        return  findMethodPage(pg,methodExQuery);
    }
    @Override
    public Pagination<MethodEx> findMethodVersionPage(MethodExQuery methodExQuery) {
        Pagination<MethodEx> pg = new Pagination<>();

        return  findMethodPage(pg,methodExQuery);
    }

    @Override
    public VersionMethod queryVersiondetail(String versionId) {
        return findMethodVersiondetails(versionId);
    }


    @Override
    @Transactional
    public String addHistoryVersion(MethodEx methodEx) {
        //添加方法
        MethodPo method = methodDao.findMethod(methodEx.getId());
        //method.setCreateUser(methodEx.getCreateUser());
        //第一次添加版本  上一个版本id为null
       if (ObjectUtils.isEmpty(method.getOnVersionId())){
           method.setOnVersionId(method.getId());
           methodDao.updateMethod(method);
       }
        //添加初始版本id
        method.setVersionCode(methodEx.getVersionCode());
       method.setOnVersionId(method.getOnVersionId());
        method.setId(null);
        String id = methodDao.createMethod(method);

        //添加后置脚本
        List<AfterScriptPo> afterScriptList = findAfterScript(methodEx.getId());
        if (CollectionUtils.isNotEmpty(afterScriptList)){
            //后置脚本的id 就是方法的id
            afterScriptList.get(0).setId(id);
            afterScriptList.get(0).setMethodId(id);
            afterScriptDao.createAfterScript(afterScriptList.get(0));
        }
        //添加form参数
        List<FormParamPo> formParamList = findformParam(methodEx.getId());
        if (CollectionUtils.isNotEmpty(formParamList)){
            for (FormParamPo formParamPo:formParamList){
                formParamPo.setId(null);
                formParamPo.setMethodId(id);
                formParamDao.createFormParam(formParamPo);
            }
        }
        //添加jsonParam参数
        List<JsonParamPo> jsonParamList = findJsonParam(methodEx.getId(),0);
        if (CollectionUtils.isNotEmpty(jsonParamList)){
            //查询出父集
            List<JsonParamPo> jsonParamCollet = jsonParamList.stream().filter(jsonParamPo -> ObjectUtils.isEmpty(jsonParamPo.getParentId())).collect(Collectors.toList());
            //子集
            List<JsonParamPo> jsonchildParamCollet = jsonParamList.stream().filter(jsonParamPo -> !ObjectUtils.isEmpty(jsonParamPo.getParentId())).collect(Collectors.toList());
           //先创建所有的父级
            for (JsonParamPo jsonParamPo:jsonParamCollet){
                jsonParamPo.setPreVersionId(jsonParamPo.getId());
                jsonParamPo.setId(null);
                jsonParamPo.setMethodId(id);
                jsonParamDao.createJsonParam(jsonParamPo);
            }
            //存在子集
            if (CollectionUtils.isNotEmpty(jsonchildParamCollet)){
                //创建子集
                for (JsonParamPo jsonParamPo:jsonchildParamCollet){
                    jsonParamPo.setPreVersionId(jsonParamPo.getId());
                    jsonParamPo.setId(null);
                    jsonParamPo.setMethodId(id);
                    List<JsonParamPo> jsonParam = findJsonParam(jsonParamPo.getParentId(), 1);
                    if (CollectionUtils.isNotEmpty(jsonParam)){
                        String parentJsonParamId = jsonParam.get(0).getId();
                        jsonParamPo.setParentId(parentJsonParamId);
                    }
                    jsonParamDao.createJsonParam(jsonParamPo);
                }
            }
        }
        //添加jsionRespon参数
        List<JsonResponsePo> jsonResponseList = findjsonResponse(methodEx.getId(),0);
        if (CollectionUtils.isNotEmpty(jsonResponseList)){
            //查询出父集
            List<JsonResponsePo> JsonResponseCollet = jsonResponseList.stream().filter(JsonResponsePo -> ObjectUtils.isEmpty(JsonResponsePo.getParentId())).collect(Collectors.toList());
            //子集
            List<JsonResponsePo> jsonResponChildParamCollet = jsonResponseList.stream().filter(JsonResponsePo -> !ObjectUtils.isEmpty(JsonResponsePo.getParentId())).collect(Collectors.toList());
            //先创建所有的父级
            for (JsonResponsePo jsonResponsePo:JsonResponseCollet){
                jsonResponsePo.setPreVersionId(jsonResponsePo.getId());
                jsonResponsePo.setId(null);
                jsonResponsePo.setMethodId(id);
                jsonResponseDao.createJsonResponse(jsonResponsePo);
            }
            //存在子集
            if (CollectionUtils.isNotEmpty(jsonResponChildParamCollet)){
                //创建子集
                for (JsonResponsePo jsonResponsePo:jsonResponChildParamCollet){
                    jsonResponsePo.setPreVersionId(jsonResponsePo.getId());
                    jsonResponsePo.setId(null);
                    jsonResponsePo.setMethodId(id);
                    List<JsonResponsePo> jsonResponsePos = findjsonResponse(jsonResponsePo.getParentId(), 1);
                    if (CollectionUtils.isNotEmpty(jsonResponsePos)){
                        String parentJsonParamId = jsonResponsePos.get(0).getId();
                        jsonResponsePo.setParentId(parentJsonParamId);
                    }
                    jsonResponseDao.createJsonResponse(jsonResponsePo);
                }
            }
        }
        //添加preScript参数
        List<PreScriptPo> preScriptList = findPreScript(methodEx.getId());
        if (CollectionUtils.isNotEmpty(preScriptList)){
            for (PreScriptPo preScriptPo:preScriptList){
                //preScript的id取的method的id
                preScriptPo.setId(id);
                preScriptPo.setMethodId(id);
                preScriptDao.createPreScript(preScriptPo);
            }
        }
        //添加queryParam参数
        List<QueryParamPo> queryParamList = findQueryParam(methodEx.getId());
        if (CollectionUtils.isNotEmpty(queryParamList)){
            for (QueryParamPo qeryParamPo:queryParamList){
                qeryParamPo.setId(null);
                qeryParamPo.setMethodId(id);
                queryParamDao.createQueryParam(qeryParamPo);
            }
        }
        //添加rawParam 参数
        List<RawParamPo> rawParamList = findRawParam(methodEx.getId());
        if (CollectionUtils.isNotEmpty(rawParamList)){
            for (RawParamPo rawParamPo:rawParamList){
                //rawParam 参数的id 取的method的id
                rawParamPo.setId(id);
                rawParamPo.setMethodId(id);
                rawParamDao.createRawParam(rawParamPo);
            }
        }
        //添加rawRespon 参数
        List<RawResponsePo> rawResponseList = findRawResponse(methodEx.getId());
        if (CollectionUtils.isNotEmpty(rawResponseList)){
            for (RawResponsePo rawResponsePo:rawResponseList){
                //rawRespon参数的id 取的method的id
                rawResponsePo.setId(id);
                rawResponsePo.setMethodId(id);
                rawResponseDao.createRawResponse(rawResponsePo);
            }
        }
        //添加requestBody参数
        List<RequestBodyPo> requestBodyList = findRequestBody(methodEx.getId());
        if (CollectionUtils.isNotEmpty(requestBodyList)){
            for (RequestBodyPo requestBodyPo:requestBodyList){
                //requestBody的id取的method 的id
                requestBodyPo.setId(id);
                requestBodyPo.setMethodId(id);
                requestBodyDao.createRequestBody(requestBodyPo);
            }
        }
        //添加requestHeader参数
        List<RequestHeaderPo> requestHeaderList = findRequestHeader(methodEx.getId());
        if (CollectionUtils.isNotEmpty(requestHeaderList)){
            for (RequestHeaderPo requestHeaderPo:requestHeaderList){
                requestHeaderPo.setId(null);
                requestHeaderPo.setMethodId(id);
                requestHeaderDao.createRequestHeader(requestHeaderPo);
            }
        }
        //添加responseHeader参数
        List<ResponseHeaderPo> responseHeaderList = findResponseHeader(methodEx.getId());
        if (CollectionUtils.isNotEmpty(responseHeaderList)){
            for (ResponseHeaderPo responseHeaderPo:responseHeaderList){
                responseHeaderPo.setId(null);
                responseHeaderPo.setMethodId(id);
                responseHeaderDao.createResponseHeader(responseHeaderPo);
            }
        }
        //添加responseResult参数
        List<ResponseResultPo> responseResultList = findResponseResult(methodEx.getId());
        if (CollectionUtils.isNotEmpty(responseResultList)){
            for (ResponseResultPo responseResultPo:responseResultList){
                responseResultPo.setId(id);
                responseResultPo.setMethodId(id);
                responseResultDao.createResponseResult(responseResultPo);
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
    public List<RequestHeaderPo> findRequestHeader(String id) {
        RequestHeaderQuery requestHeaderQuery = new RequestHeaderQuery();
        requestHeaderQuery.setMethodId(id);
        List<RequestHeaderPo> requestHeaderList = requestHeaderDao.findRequestHeaderList(requestHeaderQuery);
        return requestHeaderList;
    }

    /**
     * 查询 请求体参数类型
     * @param id
     */
    public List<RequestBodyPo> findRequestBody(String id) {
        RequestBodyExQuery requestBodyExQuery = new RequestBodyExQuery();
        requestBodyExQuery.setMethodId(id);
        List<RequestBodyPo> requestBodyList = requestBodyDao.findRequestBodyList(requestBodyExQuery);
        return requestBodyList;
    }

    /**
     * 查询 请求体from类型的参数
     * @param id
     */
    public List<FormParamPo> findformParam(String id) {
        FormParamQuery formParamQuery = new FormParamQuery();
        formParamQuery.setMethodId(id);
        List<FormParamPo> formParamList = formParamDao.findFormParamList(formParamQuery);
        return formParamList;
    }

    /**
     * 查询 请求体json类型的参数
     * @param id
     */
    public List<JsonParamPo> findJsonParam(String id,Integer type) {
        JsonParamQuery jsonParamQuery = new JsonParamQuery();
        if (type==0){
            jsonParamQuery.setMethodId(id);
        }
        if (type==1){
            jsonParamQuery.setPreVersionId(id);
        }
        List<JsonParamPo> jsonParamList = jsonParamDao.findJsonParamList(jsonParamQuery);
        return jsonParamList;
    }
    /**
     * 查询 请求体raw类型的参数
     * @param id
     */
    public List<RawParamPo> findRawParam(String id) {
        RawParamQuery rawParamQuery = new RawParamQuery();
        rawParamQuery.setMethodId(id);
        List<RawParamPo> rawParamList = rawParamDao.findRawParamList(rawParamQuery);
        return rawParamList;
    }
    /**
     * 查询 查询参数
     * @param id
     */
    public List<QueryParamPo> findQueryParam(String id) {

        QueryParamQuery queryParamQuery = new QueryParamQuery();
        queryParamQuery.setMethodId(id);
        List<QueryParamPo> queryParamList = queryParamDao.findQueryParamList(queryParamQuery);
        return queryParamList;
    }

    /**
     * 查询 前置脚本参数
     * @param id
     */
    public List<PreScriptPo> findPreScript(String id) {

        PreScriptQuery preScriptQuery = new PreScriptQuery();
        preScriptQuery.setMethodId(id);
        List<PreScriptPo> preScriptList = preScriptDao.findPreScriptList(preScriptQuery);
        return preScriptList;
    }
    /**
     * 查询 后置脚本参数
     * @param id
     */
    public List<AfterScriptPo> findAfterScript(String id) {

        AfterScriptQuery afterScriptQuery = new AfterScriptQuery();
        afterScriptQuery.setMethodId(id);
        List<AfterScriptPo> afterScriptList = afterScriptDao.findAfterScriptList(afterScriptQuery);
        return afterScriptList;
    }
    /**
     * 查询 响应头部数据
     * @param id
     */
    public List<ResponseHeaderPo> findResponseHeader(String id) {

        ResponseHeaderQuery responseHeaderQuery = new ResponseHeaderQuery();
        responseHeaderQuery.setMethodId(id);
        List<ResponseHeaderPo> responseHeaderList = responseHeaderDao.findResponseHeaderList(responseHeaderQuery);
        return responseHeaderList;
    }

    /**
     * 查询 响应类型
     * @param id
     */
    public List<ResponseResultPo> findResponseResult(String id) {

        ResponseResultQuery responseResultQuery = new ResponseResultQuery();
        responseResultQuery.setMethodId(id);
        List<ResponseResultPo> responseResultList = responseResultDao.findResponseResultList(responseResultQuery);
        return responseResultList;
    }
    /**
     * 查询 raw类型返回结果
     * @param id
     */
    public List<RawResponsePo> findRawResponse(String id) {

        RawResponseQuery rawResponseQuery = new RawResponseQuery();
        rawResponseQuery.setMethodId(id);
        List<RawResponsePo> rawResponseList = rawResponseDao.findRawResponseList(rawResponseQuery);
        return rawResponseList;
    }

    /**
     * 查询 json类型返回参数
     * @param id
     */
    public List<JsonResponsePo> findjsonResponse(String id,Integer type) {

        JsonResponseQuery jsonResponseQuery = new JsonResponseQuery();
        if (type==0){
            jsonResponseQuery.setMethodId(id);
        }
        if (type==1){
            jsonResponseQuery.setPreVersionId(id);
        }
        List<JsonResponsePo> jsonResponseList = jsonResponseDao.findJsonResponseList(jsonResponseQuery);
        return jsonResponseList;
    }


    /**
     * 分页查询
     * @param
     */
    public Pagination<MethodEx> findMethodPage(Pagination pg,MethodExQuery methodExQuery) {

        Pagination<MethodPo>  pagination = methodDao.findMethodPage(methodExQuery);

        BeanUtils.copyProperties(pagination,pg);

        List<MethodEx> methodExList = BeanMapper.mapList(pagination.getDataList(), MethodEx.class);

        joinQuery.queryList(methodExList);

        pg.setDataList(methodExList);
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
        MethodPo method = methodDao.findMethod(id);
        MethodEx methodEx = BeanMapper.map(method, MethodEx.class);
        joinQuery.queryOne(methodEx);
        if(ObjectUtils.isEmpty(method)){
            return null;
        }
        //查询请求头部
        List<RequestHeaderPo> requestHeaderPos = findRequestHeader(method.getId());
        if (CollectionUtils.isNotEmpty(requestHeaderPos)){
            List<RequestHeader> requestHeaderList = BeanMapper.mapList(requestHeaderPos,RequestHeader.class);
            versionRequest.setRequestHeaderList(requestHeaderList);
        }
        //查询 请求参数类型
        List<RequestBodyPo> requestBody = findRequestBody(id);
        if (CollectionUtils.isNotEmpty(requestBody)){
            List<RequestBodyEx> requestBodyExes = BeanMapper.mapList(requestBody, RequestBodyEx.class);
            versionRequest.setRequestBodyExList(requestBodyExes);
            for(RequestBodyPo requestBodyPo:requestBody){
                //请求参数类型为fom
                if (requestBodyPo.getBodyType().equals("form")){
                    List<FormParamPo> formParamPos = findformParam(id);
                    if (CollectionUtils.isNotEmpty(formParamPos)) {
                        List<FormParam> formParams = BeanMapper.mapList(formParamPos, FormParam.class);
                        versionRequest.setFormParamList(formParams);
                    }
                }
                //请求参数为  json
                if (requestBodyPo.getBodyType().equals("json")){
                    JsonParamQuery jsonParamQuery = new JsonParamQuery();
                    jsonParamQuery.setMethodId(id);
                    List<JsonParam> jsonParamListTree = jsonParamService.findJsonParamListTree(jsonParamQuery);
                    versionRequest.setJsonParamList(jsonParamListTree);
                }
                //请求参数为 raw
                if (requestBodyPo.getBodyType().equals("raw")){
                    List<RawParamPo> rawParam = findRawParam(id);
                    if (CollectionUtils.isNotEmpty(rawParam)){
                        List<RawParam> rawParams = BeanMapper.mapList(rawParam, RawParam.class);
                        versionRequest.setRawParamList(rawParams);
                    }
                }
            }
        }
        //查询 查询参数
        List<QueryParamPo> queryParam = findQueryParam(id);
        if(CollectionUtils.isNotEmpty(queryParam)){
            List<QueryParam> queryParams = BeanMapper.mapList(queryParam, QueryParam.class);
            versionRequest.setQueryParamList(queryParams);
        }
        //查询前置脚本
        List<PreScriptPo> preScript = findPreScript(id);
        if (CollectionUtils.isNotEmpty(preScript)){
            List<PreScript> preScripts = BeanMapper.mapList(preScript, PreScript.class);
            versionRequest.setPreScriptList(preScripts);
        }
        //查询后置脚本
        List<AfterScriptPo> afterScript = findAfterScript(id);
        if (CollectionUtils.isNotEmpty(afterScript)){
            List<AfterScript> afterScripts = BeanMapper.mapList(afterScript, AfterScript.class);
            versionRequest.setAfterScriptList(afterScripts);
        }

        //查询响应头部数据
        List<ResponseHeaderPo> responseHeader = findResponseHeader(id);
        if (CollectionUtils.isNotEmpty(responseHeader)){
            List<ResponseHeader> responseHeaders = BeanMapper.mapList(responseHeader, ResponseHeader.class);
            versionRespon.setResponseHeaderList(responseHeaders);
        }
        //查询响应结果类型
        List<ResponseResultPo> responseResult = findResponseResult(id);
        if (CollectionUtils.isNotEmpty(responseResult)){
            List<ResponseResult> responseResults = BeanMapper.mapList(responseResult, ResponseResult.class);
            versionRespon.setResponseResultList(responseResults);
            for (ResponseResultPo responseResultPo:responseResult){
                //查询json 返回类型参数
                if (responseResultPo.getResultType().equals("json")){
                    JsonResponseQuery jsonResponseQuery = new JsonResponseQuery();
                    jsonResponseQuery.setMethodId(id);
                    List<JsonResponse> jsonResponseListTree = jsonResponseService.findJsonResponseListTree(jsonResponseQuery);
                    versionRespon.setJsonResponseList(jsonResponseListTree);

                }
                //查询raw 返回类型参数
                if (responseResultPo.getResultType().equals("raw")){
                    List<RawResponsePo> rawResponse = findRawResponse(id);
                    if (CollectionUtils.isNotEmpty(rawResponse)){
                        List<RawResponse> rawResponses = BeanMapper.mapList(rawResponse, RawResponse.class);
                        versionRespon.setRawResponseList(rawResponses);
                    }
                }
            }
        }
        versionMethod.setMethodEx(methodEx);
        versionMethod.setVersionRequest(versionRequest);
        versionMethod.setVersionRespon(versionRespon);
        return versionMethod;
    }
}