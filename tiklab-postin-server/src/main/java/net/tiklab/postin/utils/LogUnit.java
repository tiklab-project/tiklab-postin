package net.tiklab.postin.utils;

import com.alibaba.fastjson.JSONObject;
import net.tiklab.oplog.log.modal.OpLog;
import net.tiklab.oplog.log.modal.OpLogTemplate;
import net.tiklab.oplog.log.service.OpLogService;
import net.tiklab.user.user.model.User;
import net.tiklab.utils.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;


@Service
public class LogUnit {

    @Autowired
    OpLogService opLogService;

    public void log(String type, String module, Map<String,String> map){
        OpLogTemplate opLogTemplate = new OpLogTemplate();
        opLogTemplate.setId("c072fe18d74bc3893ac323cf8319d8b3");
        User user = new User();
        user.setId( LoginContext.getLoginId());

        OpLog log = new OpLog();

        log.setActionType(type);
        log.setModule(module);
        log.setOpLogTemplate(opLogTemplate);
        log.setTimestamp(new Timestamp(System.currentTimeMillis()));
        log.setUser(user);
        log.setBgroup("postin");
        log.setContent(JSONObject.toJSONString(map));

        opLogService.createLog(log);
    }
}
