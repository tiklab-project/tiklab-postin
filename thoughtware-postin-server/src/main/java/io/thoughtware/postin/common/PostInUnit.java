package io.thoughtware.postin.common;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.message.message.model.SendMessageNotice;
import io.thoughtware.message.message.service.SendMessageNoticeService;
import io.thoughtware.security.logging.model.Logging;
import io.thoughtware.security.logging.model.LoggingType;
import io.thoughtware.security.logging.service.LoggingByTempService;
import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import io.thoughtware.eam.common.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

/**
 * postin 工具类
 */
@Service
public class PostInUnit {

    @Autowired
    UserService userService;

    @Value("${base.url:null}")
    String baseUrl;

    @Autowired
    LoggingByTempService opLogByTemplService;

    @Autowired
    SendMessageNoticeService sendMessageNoticeService;

    /**
     * 日志
     * @param type
     * @param module
     * @param map
     */
    public void log(String type, String module, Map<String,String> map){

        Logging log = new Logging();
        LoggingType opLogType = new LoggingType();
        opLogType.setId(type);
        opLogType.setBgroup("postin");
        log.setActionType(opLogType);

        User user = new User();
        user.setId(LoginContext.getLoginId());
        log.setUser(user);

        log.setModule(module);
        log.setAction(map.get("workspaceName"));

        log.setLink(map.get("link"));
        log.setBgroup("postin");
        log.setData(JSONObject.toJSONString(map));
        log.setBaseUrl(baseUrl);

        opLogByTemplService.createLog(log);
    }

    public void message(Map<String, String> map){
        SendMessageNotice sendMessageNotice = new SendMessageNotice();
        String jsonString = JSONObject.toJSONString(map);

        sendMessageNotice.setSendId(LoginContext.getLoginId());
        sendMessageNotice.setSiteData(jsonString);
        sendMessageNotice.setEmailData(jsonString);
        sendMessageNotice.setDingdingData(jsonString);
        sendMessageNotice.setQywechatData(jsonString);

        sendMessageNotice.setId("MESSAGE_NOTICE_ID");
        sendMessageNotice.setBaseUrl(baseUrl);

        sendMessageNotice.setLink(map.get("link"));
        sendMessageNotice.setAction(map.get("workspaceName"));

        sendMessageNoticeService.sendMessageNotice(sendMessageNotice);
    }


    /**
     * 获取当前用户的信息
     * @return
     */
    public User getUser(){
        String userId = LoginContext.getLoginId();

        return userService.findUser(userId);
    }

    /**
     * 生成12位id
     * @return
     */
    public String generateId() {
        String uuid = UUID.randomUUID().toString();
        String id = uuid.trim().replaceAll("-", "");

        // 获取rid中的前12位
        return id.substring(0,12);
    }

}
