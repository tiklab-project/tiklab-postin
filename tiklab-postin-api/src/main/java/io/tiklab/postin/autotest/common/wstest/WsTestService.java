package io.tiklab.postin.autotest.common.wstest;

import com.alibaba.fastjson.JSONObject;

public interface WsTestService {
    /**
     * 用例执行，发送信息
     * @param agentId
     * @param params
     * @param futureId
     */
    void sendMessageExe(String agentId, JSONObject params, String futureId);

    /**
     * 接收到不同的用例消息并处理
     * @param type
     * @param jsonMsg
     */
    void processDiffCaseAgentMessage(String type,JSONObject jsonMsg);
}
