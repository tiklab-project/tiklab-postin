package io.tiklab.postin.autotest.http.perf.execute.service;


import com.alibaba.fastjson.JSONObject;
import io.tiklab.postin.autotest.http.perf.execute.model.ApiPerfTestRequest;
import io.tiklab.postin.autotest.http.perf.execute.model.ApiPerfTestResponse;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfInstance;

/**
 * api性能测试调度服务
 */
public interface ApiPerfExecuteDispatchService {

    /**
     * 性能执行
     * @param
     * @param apiPerfTestRequest
     * @return
     */
    Boolean execute(ApiPerfTestRequest apiPerfTestRequest);

    void executeStart(ApiPerfTestRequest apiPerfTestRequest);

    /**
     * 获取性能测试结果
     * @param
     * @return
     */
    ApiPerfInstance result(String apiPerfId);

    ApiPerfTestResponse getResult(ApiPerfTestRequest apiPerfTestRequest);

    /**
     * 停止执行
     * @param apiPerfId
     */
    void stopTest(String apiPerfId);


    /**
     * 清理数据
     */
    void cleanUpData(String apiPerfId);

    /**
     * agent定时上报的数据
     * @param message
     */
    void getAgentTestData(JSONObject message);

}
