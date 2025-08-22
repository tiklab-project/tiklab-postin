package io.tiklab.postin.common;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.message.message.model.SendMessageNotice;
import io.tiklab.message.message.service.SendMessageNoticeService;
import io.tiklab.postin.support.dbconnect.model.DatabaseConnectConfig;
import io.tiklab.security.logging.logging.model.Logging;
import io.tiklab.security.logging.logging.model.LoggingType;
import io.tiklab.security.logging.logging.service.LoggingByTempService;
import io.tiklab.user.user.model.User;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.user.user.service.UserProcessor;
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
    UserProcessor userService;

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
        try {
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
        }catch (Exception e){

        }
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


    public String buildJdbcUrl(String type, DatabaseConnectConfig config) {
        String host = config.getHost();
        int port = config.getPort();
        String dbName = config.getDbName();

        switch (type.toLowerCase()) {
            case MagicValue.DATABASE_CONNECT_TYPE_POSTGRESQL:
                return String.format("jdbc:postgresql://%s:%d/%s", host, port, dbName);
            case MagicValue.DATABASE_CONNECT_TYPE_MYSQL:
                return String.format("jdbc:mysql://%s:%d/%s?useSSL=false&serverTimezone=UTC", host, port, dbName);
            default:
                throw new IllegalArgumentException("不支持的数据库类型: " + type);
        }
    }


}
