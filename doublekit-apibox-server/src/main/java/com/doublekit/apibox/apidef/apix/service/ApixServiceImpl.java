package com.doublekit.apibox.apidef.apix.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.doublekit.apibox.apidef.apix.dao.ApixDao;
import com.doublekit.apibox.apidef.apix.entity.ApixEntity;
import com.doublekit.apibox.apidef.apix.model.Apix;
import com.doublekit.apibox.apidef.apix.model.ApixQuery;
import com.doublekit.apibox.integration.dynamic.model.Dynamic;
import com.doublekit.apibox.integration.dynamic.service.DynamicService;
import com.doublekit.apibox.sysmgr.support.MessageTemplateConstant;
import com.doublekit.beans.BeanMapper;
import com.doublekit.core.page.Pagination;
import com.doublekit.core.page.PaginationBuilder;
import com.doublekit.dss.client.DssClient;
import com.doublekit.join.JoinTemplate;
import com.doublekit.message.message.model.Message;
import com.doublekit.message.message.model.MessageReceiver;
import com.doublekit.message.message.model.MessageTemplate;
import com.doublekit.message.message.service.MessageService;
import com.doublekit.rpc.annotation.Exporter;
import com.doublekit.user.user.model.User;
import com.doublekit.utils.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
* BasedefServiceImpl
*/
@Service
@Exporter
public class ApixServiceImpl implements ApixService {

    @Autowired
    ApixDao apixDao;

    @Autowired
    DynamicService dynamicService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    DssClient disClient;

    @Autowired
    MessageService messageService;

    @Override
    public String createApix(@NotNull @Valid Apix apix) {
        ApixEntity apixEntity = BeanMapper.map(apix, ApixEntity.class);


        //初始化项目成员
        String userId = LoginContext.getLoginId();
        apixEntity.setCreateUser(userId);

        apixEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        String id = apixDao.createApix(apixEntity);

        //添加索引
        Apix entity = findApix(id);
        disClient.save(entity);

        //发送消息
        sendMessageForCreate(entity);

       //动态
        Dynamic dynamic = new Dynamic();
        dynamic.setWorkspaceId(apix.getWorkspaceId());
        dynamic.setUser(new User().setId(userId));
        dynamic.setName(apix.getName());
        dynamic.setDynamicType("add");
        dynamic.setModel("api");
        dynamic.setModelId(id);
        dynamic.setOperationTime(new Timestamp(System.currentTimeMillis()));
        dynamicService.createDynamic(dynamic);

        return id;
    }

    @Override
    public void updateApix(@NotNull @Valid Apix apix) {
        ApixEntity apixEntity = BeanMapper.map(apix, ApixEntity.class);

        apixEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        String userId = LoginContext.getLoginId();
        apixEntity.setUpdateUser(userId);

        apixDao.updateApix(apixEntity);

        //更新索引
        Apix entity = findApix(apix.getId());
        disClient.update(entity);

        //发送消息
        sendMessageForCreate(entity);

        //动态
        ApixEntity apix1 = apixDao.findApix(apix.getId());
        Dynamic dynamic = new Dynamic();
        dynamic.setWorkspaceId(apix1.getWorkspaceId());
        dynamic.setUser(new User().setId(LoginContext.getLoginId()));
        dynamic.setName(apix1.getName());
        dynamic.setDynamicType("edit");
        dynamic.setModel("api");
        dynamic.setModelId(apix1.getId());
        dynamic.setOperationTime(new Timestamp(System.currentTimeMillis()));
        dynamicService.createDynamic(dynamic);

    }

    @Override
    public void deleteApix(@NotNull String id) {
        ApixEntity apix = apixDao.findApix(id);

        Dynamic dynamic = new Dynamic();
        dynamic.setWorkspaceId(apix.getWorkspaceId());
        dynamic.setUser(new User().setId(LoginContext.getLoginId()));
        dynamic.setName(apix.getName());
        dynamic.setDynamicType("delete");
        dynamic.setModel("api");
        dynamic.setModelId(apix.getId());
        dynamic.setOperationTime(new Timestamp(System.currentTimeMillis()));
        dynamicService.createDynamic(dynamic);

        apixDao.deleteApix(id);

        //删除索引
        disClient.delete(Apix.class,id);
    }

    @Override
    public Apix findOne(String id) {
        ApixEntity apixEntity = apixDao.findApix(id);

        Apix apix = BeanMapper.map(apixEntity, Apix.class);
        return apix;
    }

    @Override
    public List<Apix> findList(List<String> idList) {
        List<ApixEntity> apixEntityList =  apixDao.findApixList(idList);

        List<Apix> apixList =  BeanMapper.mapList(apixEntityList, Apix.class);
        return apixList;
    }

    @Override
    public Apix findApix(@NotNull String id) {
        Apix apix = findOne(id);

        joinTemplate.joinQuery(apix);

        return apix;
    }

    @Override
    public List<Apix> findAllApix() {
        List<ApixEntity> apixEntityList =  apixDao.findAllApix();

        List<Apix> apixList =  BeanMapper.mapList(apixEntityList, Apix.class);

        joinTemplate.joinQuery(apixList);

        return apixList;
    }

    @Override
    public List<Apix> findApixList(ApixQuery apixQuery) {
        List<ApixEntity> apixEntityList = apixDao.findApixList(apixQuery);

        List<Apix> apixList = BeanMapper.mapList(apixEntityList, Apix.class);

        joinTemplate.joinQuery(apixList);

        return apixList;
    }

    @Override
    public Pagination<Apix> findApixPage(ApixQuery apixQuery) {
        Pagination<ApixEntity>  pagination = apixDao.findApixPage(apixQuery);

        List<Apix> apixList = BeanMapper.mapList(pagination.getDataList(), Apix.class);

        joinTemplate.joinQuery(apixList);

        return PaginationBuilder.build(pagination, apixList);
    }

    /**
     * 发送消息提醒
     * @param apix
     */
    private void sendMessageForCreate(Apix apix){
        Message message = new Message();
        //设置模板ID
        message.setMessageTemplate(new MessageTemplate().setId(MessageTemplateConstant.TEMPLATE_ID_API_UPDATE));
        //设置发送数据
        String data = JSON.toJSONString(apix, SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteDateUseDateFormat);
        message.setData(data);
        message.setApplication("apibox");
        //设置接收人
        List<MessageReceiver> messageReceiverList = new ArrayList<>();
        MessageReceiver messageReceiver = new MessageReceiver();
        messageReceiver.setReceiver(LoginContext.getLoginId());//去除message->user依賴 zhangzh
        messageReceiverList.add(messageReceiver);
        message.setMessageReceiverList(messageReceiverList);

        messageService.sendMessage(message);
    }


}