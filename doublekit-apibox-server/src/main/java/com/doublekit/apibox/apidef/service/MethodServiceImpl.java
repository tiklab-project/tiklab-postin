package com.doublekit.apibox.apidef.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.doublekit.apibox.apidef.dao.*;
import com.doublekit.apibox.apidef.entity.MethodEntity;
import com.doublekit.apibox.apidef.model.MethodEx;
import com.doublekit.apibox.apidef.model.MethodExQuery;
import com.doublekit.apibox.apidef.support.MessageTemplateConstant;
import com.doublekit.beans.BeanMapper;
import com.doublekit.common.Pagination;
import com.doublekit.common.PaginationBuilder;
import com.doublekit.dal.jpa.builder.deletelist.condition.DeleteCondition;
import com.doublekit.dal.jpa.builder.deletelist.conditionbuilder.DeleteBuilders;
import com.doublekit.dss.client.DssClient;
import com.doublekit.eam.common.Ticket;
import com.doublekit.eam.common.TicketContext;
import com.doublekit.eam.common.TicketHolder;
import com.doublekit.join.JoinTemplate;
import com.doublekit.message.message.model.Message;
import com.doublekit.message.message.model.MessageReceiver;
import com.doublekit.message.message.model.MessageTemplate;
import com.doublekit.message.message.service.MessageService;
import com.doublekit.user.user.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
* 用户服务业务处理
*/
@Service
public class MethodServiceImpl implements MethodService {

    @Autowired
    MethodDao methodDao;

    @Autowired
    JoinTemplate joinQuery;

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
        MethodEntity methodEntity = BeanMapper.map(methodEx, MethodEntity.class);

        //添加创建人
        String creatUserId = findCreatUser();
        methodEntity.setCreateUser(creatUserId);
        String id = methodDao.createMethod(methodEntity);

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
        MethodEntity methodEntity = BeanMapper.map(method, MethodEntity.class);

        methodDao.updateMethod(methodEntity);

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

        joinQuery.queryOne(methodEx);
        return methodEx;
    }

    @Override
    public List<MethodEx> findAllMethod() {
        List<MethodEntity> methodEntityList =  methodDao.findAllMethod();

        List<MethodEx> methodExList = BeanMapper.mapList(methodEntityList, MethodEx.class);

        joinQuery.queryList(methodExList);

        return methodExList;
        }

    @Override
    public List<MethodEx> findMethodList(MethodExQuery methodExQuery) {
        List<MethodEntity> methodEntityList = methodDao.findMethodList(methodExQuery);

        List<MethodEx> methodExList = BeanMapper.mapList(methodEntityList, MethodEx.class);

        joinQuery.queryList(methodExList);

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

        joinQuery.queryList(methodExList);

        return PaginationBuilder.build(pagination,methodExList);
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