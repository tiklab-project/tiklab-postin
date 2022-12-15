package net.tiklab.postin.utils;

import com.alibaba.fastjson.JSONObject;
import net.tiklab.oplog.log.modal.OpLog;
import net.tiklab.oplog.log.modal.OpLogTemplate;
import net.tiklab.oplog.log.modal.OpLogType;
import net.tiklab.oplog.log.service.OpLogByTemplService;
import net.tiklab.oplog.log.service.OpLogService;
import net.tiklab.user.user.model.User;
import net.tiklab.utils.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;

import static net.tiklab.postin.utils.MessageTemplateConstant.LOG_TEMPLATE_ID;


@Service
public class LogUnit {

    @Autowired
    OpLogByTemplService opLogByTemplService;

    public void log(String type, String module, Map<String,String> map){

        User user = new User();
        user.setId( LoginContext.getLoginId());

        OpLog log = new OpLog();
        OpLogType opLogType = new OpLogType();
        opLogType.setId(type);
        opLogType.setBgroup("postin");

        log.setActionType(opLogType);
        log.setModule(module);
        log.setOpLogTemplateId(LOG_TEMPLATE_ID);
        log.setTimestamp(new Timestamp(System.currentTimeMillis()));
        log.setUser(user);
        log.setBgroup("postin");
        log.setContent(JSONObject.toJSONString(map));

        opLogByTemplService.createLog(log);
    }
}
