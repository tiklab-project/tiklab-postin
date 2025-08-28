package io.tiklab.postin.autotest.http.perf.execute.model;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.autotest.http.perf.cases.model.ApiPerfCase;
import io.tiklab.postin.autotest.http.scene.execute.model.ApiSceneTestRequest;
import io.tiklab.postin.autotest.http.unit.execute.model.ApiUnitTestRequest;

import java.util.List;

/**
 *接口性能测试请求参数 模型
 */
@ApiModel
public class ApiPerfTestRequest extends BaseModel {

    @ApiProperty(name="apiPerfId",desc="性能测试用例")
    private String apiPerfId;

    @ApiProperty(name="apiEnvId",desc="接口环境id")
    private String apiEnvId;

    @ApiProperty(name="apiPreUrl",desc="接口前置地址")
    private String apiPreUrl;


    @ApiProperty(name="apiPerfCase",desc="性能测试用例")
    private ApiPerfCase apiPerfCase;

    @ApiProperty(name="apiSceneTestRequest",desc="api场景测试请求")
    private ApiSceneTestRequest apiSceneTestRequest;

    @ApiProperty(name="apiUnitTestRequest",desc="api单元测试")
    private ApiUnitTestRequest apiUnitTestRequest;

    @ApiProperty(name="当前步骤的测试数据",desc="api单元测试")
    private List<JSONObject> testDataList;


    @ApiProperty(name="apiSceneTestRequestList",desc="api 场景测试请求列表")
    private List<ApiPerfStepTestData> apiPerfStepTestData;

    @ApiProperty(name="exeNum",desc="执行次数")
    private Integer exeNum;

    public String getApiPerfId() {
        return apiPerfId;
    }

    public void setApiPerfId(String apiPerfId) {
        this.apiPerfId = apiPerfId;
    }

    public String getApiEnvId() {
        return apiEnvId;
    }

    public void setApiEnvId(String apiEnvId) {
        this.apiEnvId = apiEnvId;
    }

    public String getApiPreUrl() {
        return apiPreUrl;
    }

    public void setApiPreUrl(String apiPreUrl) {
        this.apiPreUrl = apiPreUrl;
    }

    public List<ApiPerfStepTestData> getApiPerfStepTestData() {
        return apiPerfStepTestData;
    }

    public void setApiPerfStepTestData(List<ApiPerfStepTestData> apiPerfStepTestData) {
        this.apiPerfStepTestData = apiPerfStepTestData;
    }

    public Integer getExeNum() {
        return exeNum;
    }

    public void setExeNum(Integer exeNum) {
        this.exeNum = exeNum;
    }


    public ApiPerfCase getApiPerfCase() {
        return apiPerfCase;
    }

    public void setApiPerfCase(ApiPerfCase apiPerfCase) {
        this.apiPerfCase = apiPerfCase;
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
