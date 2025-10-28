package io.tiklab.postin.autotest.http.scene.execute.service;


import com.alibaba.fastjson.JSONObject;

import io.tiklab.postin.autotest.http.scene.execute.model.ApiSceneTestRequest;
import io.tiklab.postin.autotest.http.scene.execute.model.ApiSceneTestResponse;
import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneInstance;

/**
 * 接口场景测试调度 服务接口
 */
public interface ApiSceneExecuteDispatchService {

    /**
     * 测试
     * @return
     */
    ApiSceneTestResponse execute(ApiSceneTestRequest apiSceneTestRequest);

    void executeStart(ApiSceneTestRequest apiSceneTestRequest);

    /**
     * 获取场景测试结果
     * @param apiSceneId
     * @return
     */
    ApiSceneInstance result(String apiSceneId);

    /**
     * 获取测试结果
     * @param apiSceneId
     * @return
     */
    ApiSceneTestResponse getResult(String apiSceneId);

    /**
     * agent定时上报的数据
     * @param message
     */
    void getAgentTestData(JSONObject message);

}
