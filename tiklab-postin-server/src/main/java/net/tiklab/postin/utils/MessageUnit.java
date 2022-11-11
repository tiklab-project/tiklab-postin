package net.tiklab.postin.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import net.tiklab.message.message.model.Message;
import net.tiklab.message.message.model.MessageReceiver;
import net.tiklab.message.message.model.MessageTemplate;
import net.tiklab.message.message.service.MessageService;
import net.tiklab.postin.apidef.apix.model.Apix;
import net.tiklab.utils.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MessageUnit {

    @Autowired
    MessageService messageService;


    public void sendMessageForCreate(Map<String, String> msg){
        Message message = new Message();
        message.setApplication("postin");

        //设置模板ID
        MessageTemplate messageTemplate = new MessageTemplate();
        messageTemplate.setId(MessageTemplateConstant.TEMPLATE_ID_API_UPDATE);

        message.setMessageTemplate(messageTemplate);

        //设置发送数据
        message.setData(JSONObject.toJSONString(msg));

        //设置接收人
        List<MessageReceiver> messageReceiverList = new ArrayList<>();
        MessageReceiver messageReceiver = new MessageReceiver();
        //去除message->user依賴 zhangzh
        messageReceiver.setReceiver(LoginContext.getLoginId());
        messageReceiverList.add(messageReceiver);

        message.setMessageReceiverList(messageReceiverList);

        messageService.sendMessage(message);
    }




}
