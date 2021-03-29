package com.darthcloud.apibox.apidef.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.darthcloud.apibox.apidef.dao.MethodDao;
import com.darthcloud.apibox.apidef.entity.MethodPo;
import com.darthcloud.apibox.apidef.model.MethodEx;
import com.darthcloud.apibox.apidef.model.MethodExQuery;

import com.darthcloud.apibox.apidef.support.MessageTemplateConstant;
import com.darthcloud.common.Pagination;
import com.darthcloud.beans.BeanMapper;
import com.darthcloud.join.join.JoinQuery;
import com.darthcloud.message.message.model.Message;
import com.darthcloud.message.message.model.MessageReceiver;
import com.darthcloud.message.message.model.MessageTemplate;
import com.darthcloud.message.message.service.MessageService;
import com.darthcloud.orga.user.model.User;
import com.darthcloud.web.filter.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

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

    @Override
    public String createMethod(@NotNull @Valid MethodEx methodEx) {
        //创建接口
        MethodPo methodPo = BeanMapper.map(methodEx, MethodPo.class);

        String id = methodDao.createMethod(methodPo);

        //发送消息
        sendMessageForCreate(methodEx.setId(id));
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
                .setReceiver(new User().setId(UserContext.getInstance().getTicket()));
        messageReceiverList.add(messageReceiver);
        message.setMessageReceiverList(messageReceiverList);

        messageService.sendMessage(message);
    }

    @Override
    public void updateMethod(@NotNull @Valid MethodEx methodEx) {
        //更新接口
        MethodPo methodPo = BeanMapper.map(methodEx, MethodPo.class);

        methodDao.updateMethod(methodPo);

        //发送更新消息提醒
        methodEx = findOne(methodEx.getId());
        sendMessageForUpdate(methodEx);
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
                .setReceiver(new User().setId(UserContext.getInstance().getTicket()));
        messageReceiverList.add(messageReceiver);
        message.setMessageReceiverList(messageReceiverList);

        messageService.sendMessage(message);
    }

    @Override
    public void deleteMethod(@NotNull String id) {
        methodDao.deleteMethod(id);
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

        Pagination<MethodPo>  pagination = methodDao.findMethodPage(methodExQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<MethodEx> methodExList = BeanMapper.mapList(pagination.getDataList(), MethodEx.class);

        joinQuery.queryList(methodExList);

        pg.setDataList(methodExList);
        return pg;
    }
}