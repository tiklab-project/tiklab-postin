package io.tiklab.postin.autotest.common.wsTest.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.postin.autotest.common.wstest.WsTestService;
import io.tiklab.postin.common.MagicValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class WebSocketServiceImpl implements WebSocketHandler {

    private static Logger logger = LoggerFactory.getLogger(WebSocketServiceImpl.class);

    static WsTestCommonFn wsTestCommonFn;
    public  WsTestCommonFn getWsTestCommonFn() {
        return wsTestCommonFn;
    }
    @Autowired
    public  void setWsTestCommonFn(WsTestCommonFn wsTestCommonFn) {
        WebSocketServiceImpl.wsTestCommonFn = wsTestCommonFn;
    }

    static WsTestService wsTestService;
    public WsTestService getWsTestService() {
        return wsTestService;
    }
    @Autowired
    public void setWsTestService(WsTestService wsTestService) {
        WebSocketServiceImpl.wsTestService = wsTestService;
    }


    public static Map<String, CompletableFuture<JSONObject>> futureMap = new ConcurrentHashMap<>();

    /**
     * agentSessionMap:使用线程安全map存储用户连接webscoket信息
     */
    public final static Map<String, WebSocketSession> agentSessionMap = new ConcurrentHashMap<>();

    /**
     * 建立websocket连接时调用该方法
     * 内存中存储当前agentId
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 设置会话属性，如果需要
        session.setTextMessageSizeLimit(50 * 1024 * 1024); // 50 MB
        session.setBinaryMessageSizeLimit(50 * 1024 * 1024); // 50 MB

        JSONObject agentInfo = wsTestCommonFn.getAgentInfoFromQuery(session);
        String agentId = agentInfo.getString("agentId");
        logger.info("SERVER:  {} --- has successfully connected to the server.", agentId);
        agentSessionMap.put(agentId, session);

    }


    /**
     * 接收到客户端发来的信息进行处理
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String agentId = wsTestCommonFn.getAgentIdFromAgentSessionMap(session, agentSessionMap);
        if(agentId==null){return;}

        String msg = message.getPayload().toString();
        JSONObject jsonMsg = JSONObject.parseObject(msg);

        if(jsonMsg.containsKey("type")){
            String type = jsonMsg.getString("type");

            //接收到不同的用例消息并处理
            wsTestService.processDiffCaseAgentMessage(type,jsonMsg);

            // 从Map中取出CompletableFuture实例并设置结果
            String futureId = agentId + "_" + type + "_" + jsonMsg.getString("caseId");
            CompletableFuture<JSONObject> future = futureMap.remove(futureId);
            if (future != null) {
                future.complete(jsonMsg);
            }
        }
    }


    /**
     * 关闭websocket时调用该方法
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String agentId = wsTestCommonFn.getAgentIdFromAgentSessionMap(session, agentSessionMap);

        if (agentId != null) {
            if(!agentId.equals(MagicValue.AGENT_DEFAULT)){
                wsTestCommonFn.updateAgentStatusClose(agentId);
            }
            agentSessionMap.remove(agentId);
            logger.info("AgentId -- {}  closed!", agentId);
        } else {
            logger.error("关闭时，获取AgentId为空");
        }

        logger.info("AgentId closed because-- {}  closed!",status);
    }

    /**
     * 传输过程出现异常时，调用该方法
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
        String agentId = wsTestCommonFn.getAgentIdFromAgentSessionMap(session, agentSessionMap);

        logger.info("AgentId -- " + agentId + " 异常信息：" + e.getMessage());
    }

    /**
     * 用于确定 WebSocket 处理器是否支持处理部分消息
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


}
