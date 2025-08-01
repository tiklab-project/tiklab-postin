package io.tiklab.postin.autotest.http.perf.execute.model;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.autotest.http.perf.cases.model.ApiPerfStep;
import io.tiklab.postin.autotest.http.scene.execute.model.ApiSceneTestRequest;
import io.tiklab.postin.autotest.http.unit.execute.model.ApiUnitTestRequest;

import java.util.List;

/**
 *接口性能测试请求参数 模型
 */
@ApiModel
public class ApiPerfStepTestData extends BaseModel {

    private ApiPerfStep apiPerfStep;

    @ApiProperty(name="apiSceneTestRequestList",desc="api场景测试请求")
    private ApiSceneTestRequest apiSceneTestRequest;

    @ApiProperty(name="apiUnitTestRequest",desc="api单元测试")
    private ApiUnitTestRequest apiUnitTestRequest;

    @ApiProperty(name="当前步骤的测试数据",desc="api单元测试")
    private List<JSONObject> testDataList;

    public ApiPerfStep getApiPerfStep() {
        return apiPerfStep;
    }

    public void setApiPerfStep(ApiPerfStep apiPerfStep) {
        this.apiPerfStep = apiPerfStep;
    }

    public ApiSceneTestRequest getApiSceneTestRequest() {
        return apiSceneTestRequest;
    }

    public void setApiSceneTestRequest(ApiSceneTestRequest apiSceneTestRequest) {
        this.apiSceneTestRequest = apiSceneTestRequest;
    }

    public ApiUnitTestRequest getApiUnitTestRequest() {
        return apiUnitTestRequest;
    }

    public void setApiUnitTestRequest(ApiUnitTestRequest apiUnitTestRequest) {
        this.apiUnitTestRequest = apiUnitTestRequest;
    }

    public List<JSONObject> getTestDataList() {
        return testDataList;
    }

    public void setTestDataList(List<JSONObject> testDataList) {
        this.testDataList = testDataList;
    }
}
