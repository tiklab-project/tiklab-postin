package net.tiklab.postin.utils;

import com.alibaba.fastjson.JSONObject;
import net.tiklab.oplog.log.modal.OpLog;
import net.tiklab.oplog.log.modal.OpLogTemplate;
import net.tiklab.oplog.log.modal.OpLogTemplateQuery;
import net.tiklab.oplog.log.service.OpLogService;
import net.tiklab.oplog.log.service.OpLogTemplateService;
import net.tiklab.user.user.model.User;
import net.tiklab.utils.context.LoginContext;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class LogUnit {

    @Autowired
    OpLogService opLogService;

    @Autowired
    OpLogTemplateService opLogTemplateService;

    public void log(String type, String module, Map<String,String> map){

        //通过编码找模板ID
        OpLogTemplateQuery opLogTemplateQuery = new OpLogTemplateQuery();
        opLogTemplateQuery.setCode("LOG_CODE");
        List<OpLogTemplate> logTemplateList = opLogTemplateService.findLogTemplateList(opLogTemplateQuery);
        if(CollectionUtils.isEmpty(logTemplateList)){
            try{
                throw new Exception("No Template！！！");
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

        OpLogTemplate opLogTemplate = new OpLogTemplate();
        opLogTemplate.setId(logTemplateList.get(0).getId());
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
