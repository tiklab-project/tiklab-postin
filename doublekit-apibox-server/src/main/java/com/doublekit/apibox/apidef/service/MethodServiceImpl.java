package com.doublekit.apibox.apidef.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.doublekit.apibox.apidef.dao.*;
import com.doublekit.apibox.apidef.entity.*;
import com.doublekit.apibox.apidef.model.MethodEx;
import com.doublekit.apibox.apidef.model.MethodExQuery;
import com.doublekit.apibox.apidef.support.MessageTemplateConstant;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.page.Pagination;
import com.doublekit.common.page.PaginationBuilder;
import com.doublekit.dal.jpa.criterial.DeleteBuilders;
import com.doublekit.dal.jpa.criterial.model.DeleteCondition;
import com.doublekit.eam.common.Ticket;
import com.doublekit.eam.common.TicketContext;
import com.doublekit.eam.common.TicketHolder;
import com.doublekit.join.JoinTemplate;
import com.doublekit.message.message.model.Message;
import com.doublekit.message.message.model.MessageReceiver;
import com.doublekit.message.message.model.MessageTemplate;
import com.doublekit.message.message.service.MessageService;
import com.doublekit.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
* 用户服务业务处理
*/
@Service
public class MethodServiceImpl implements MethodService {

    @Autowired
    MethodDao methodDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    MessageService messageService;

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


    @Override
    public String createMethod(@NotNull @Valid MethodEx methodEx) {
        //添加版本号  默认初始版本号为current
        methodEx.setVersionCode("current");
        //创建接口
        MethodEntity methodEntity = BeanMapper.map(methodEx, MethodEntity.class);

        //如果没有id自动生成id
        if (StringUtils.isEmpty(methodEx.getId())) {
            UUID uniqueKey = UUID.randomUUID();
            methodEntity.setId(uniqueKey.toString());
        }

        //添加创建人
        String creatUserId = findCreatUser();
        methodEntity.setCreateUser(creatUserId);
        String id = methodDao.createMethod(methodEntity);

        //添加索引
        MethodEx entity = findMethod(id);
//        dssClient.save(entity);

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
        MessageReceiver messageReceiver = new MessageReceiver();
        messageReceiver.setReceiver(TicketHolder.get());//去除message->user依賴 zhangzh
        messageReceiverList.add(messageReceiver);
        message.setMessageReceiverList(messageReceiverList);

        messageService.sendMessage(message);
    }

    @Override
    public void updateMethod(@NotNull @Valid MethodEx method) {
        //更新接口
        MethodEntity methodEntity = BeanMapper.map(method, MethodEntity.class);

        //添加更新人
        String updateUserId = findCreatUser();
        methodEntity.setUpdateUser(updateUserId);
        methodDao.updateMethod(methodEntity);

        //更新索引
        MethodEx entity = findMethod(method.getId());
//        dssClient.update(entity);

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
        MessageReceiver messageReceiver = new MessageReceiver();
        messageReceiver.setReceiver(TicketHolder.get());//去除message->user依賴 zhangzh
        messageReceiverList.add(messageReceiver);
        message.setMessageReceiverList(messageReceiverList);

        messageService.sendMessage(message);
    }

    @Override
    public void deleteMethod(@NotNull String id) {
        //删除方法
        methodDao.deleteMethod(id);

        //删除后置脚本数据
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(AfterScriptEntity.class)
                .eq("methodId", id)
                .get();
        afterScriptDao.deleteAfterScriptList(deleteCondition);

        //删除requestHeader
        deleteCondition = DeleteBuilders.createDelete(RequestHeaderEntity.class)
                .eq("methodId", id)
                .get();
        requestHeaderDao.deleteRequestHeaderList(deleteCondition);

        //删除query类型请求体
        deleteCondition = DeleteBuilders.createDelete(QueryParamEntity.class)
                .eq("methodId", id)
                .get();
        queryParamDao.deleteQueryParamList(deleteCondition);

        //删除requestBoy
        deleteCondition = DeleteBuilders.createDelete(RequestBodyEntity.class)
                .eq("methodId", id)
                .get();
        requestBodyDao.deleteRequestBodyList(deleteCondition);

        //删除form类型请求体
        deleteCondition = DeleteBuilders.createDelete(FormParamEntity.class)
                .eq("methodId", id)
                .get();
        formParamDao.deleteFormParamLsit(deleteCondition);

        //删除formUrlencodedDao
        deleteCondition = DeleteBuilders.createDelete(FormParamEntity.class)
                .eq("methodId", id)
                .get();
        formUrlencodedDao.deleteFormUrlencoded(deleteCondition);

        //删除json类型请求体
        deleteCondition = DeleteBuilders.createDelete(JsonParamEntity.class)
                .eq("methodId", id)
                .get();
        jsonParamDao.deleteJsonParamList(deleteCondition);

        //删除json类型响应结果
        deleteCondition = DeleteBuilders.createDelete(JsonResponseEntity.class)
                .eq("methodId", id)
                .get();
        jsonResponseDao.deleteJsonResponseList(deleteCondition);

        //删除rawParam 类型请求体
        deleteCondition = DeleteBuilders.createDelete(RawParamEntity.class)
                .eq("methodId", id)
                .get();
        rawParamDao.deleteRawParamlist(deleteCondition);

        //删除binaryParam
        deleteCondition = DeleteBuilders.createDelete(RawParamEntity.class)
                .eq("methodId", id)
                .get();
        binaryParamDao.deleteBinaryParam(deleteCondition);

        //删除preScrit 前置脚本
        deleteCondition = DeleteBuilders.createDelete(PreScriptEntity.class)
                .eq("methodId", id)
                .get();
        preScriptDao.deletePreScriptList(deleteCondition);

        //删除rawResponse返回类型
        deleteCondition = DeleteBuilders.createDelete(RawResponseEntity.class)
                .eq("methodId", id)
                .get();
        rawResponseDao.deleteRawResponseList(deleteCondition);

        //删除responseHeader
        deleteCondition = DeleteBuilders.createDelete(ResponseHeaderEntity.class)
                .eq("methodId", id)
                .get();
        responseHeaderDao.deleteResponseHeaderList(deleteCondition);

        //删除responseResult
        deleteCondition = DeleteBuilders.createDelete(ResponseResultEntity.class)
                .eq("methodId", id)
                .get();
        responseResultDao.deleteResponseResultList(deleteCondition);

        //删除索引
//        dssClient.delete(MethodEx.class,id);
    }

    @Override
    public MethodEx findOne(String id) {
        MethodEntity methodEntity = methodDao.findMethod(id);

        MethodEx methodEx = BeanMapper.map(methodEntity, MethodEx.class);
        return methodEx;
    }

    @Override
    public List<MethodEx> findList(List<String> idList) {
        List<MethodEntity> methodEntityList =  methodDao.findMethodList(idList);

        List<MethodEx> methodExList = BeanMapper.mapList(methodEntityList, MethodEx.class);

        return methodExList;
    }

    @Override
    public MethodEx findMethod(@NotNull String id) {
        MethodEx methodEx = findOne(id);

        joinTemplate.joinQuery(methodEx);
        return methodEx;
    }

    @Override
    public List<MethodEx> findAllMethod() {
        List<MethodEntity> methodEntityList =  methodDao.findAllMethod();

        List<MethodEx> methodExList = BeanMapper.mapList(methodEntityList, MethodEx.class);

        joinTemplate.joinQuery(methodExList);

        return methodExList;
        }

    @Override
    public List<MethodEx> findMethodList(MethodExQuery methodExQuery) {
        List<MethodEntity> methodEntityList = methodDao.findMethodList(methodExQuery);

        List<MethodEx> methodExList = BeanMapper.mapList(methodEntityList, MethodEx.class);

        joinTemplate.joinQuery(methodExList);

        return methodExList;
    }

    @Override
    public Pagination<MethodEx> findMethodPage(MethodExQuery methodExQuery) {

        //添加当前版本
        methodExQuery.setVersionCode("current");


        return  findMethod(methodExQuery);
    }
    /**
     * 分页查询
     * @param
     */
    public Pagination<MethodEx> findMethod(MethodExQuery methodExQuery) {

        Pagination<MethodEntity>  pagination = methodDao.findMethodPage(methodExQuery);


        List<MethodEx> methodExList = BeanMapper.mapList(pagination.getDataList(), MethodEx.class);

        joinTemplate.joinQuery(methodExList);

        return PaginationBuilder.build(pagination,methodExList);
    }

    /**
     * 查询用户（创建/更新 人）id
     * @param
     */
    public String findCreatUser(){
        String ticketId = TicketHolder.get();
        Ticket ticket = TicketContext.get(ticketId);
        return ticket.getUserId();
    }
}