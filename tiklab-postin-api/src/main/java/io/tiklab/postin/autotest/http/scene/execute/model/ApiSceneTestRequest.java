package io.tiklab.postin.autotest.http.scene.execute.model;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.autotest.common.stepcommon.model.StepCommon;
import io.tiklab.postin.autotest.http.scene.cases.model.ApiSceneCase;

import java.util.List;

/**
 * 接口场景发送请求数据 模型
 */
@ApiModel
public class ApiSceneTestRequest extends BaseModel {

    @ApiProperty(name="workspaceId",desc="仓库id")
    private String workspaceId;

    @ApiProperty(name="apiSceneCase",desc="测试用例id")
    private ApiSceneCase apiSceneCase;

    @ApiProperty(name="apiEnvId",desc="接口环境id")
    private String apiEnvId;

    @ApiProperty(name="apiPreUrl",desc="接口前置地址")
    private String apiPreUrl;

    @ApiProperty(name="variableJson",desc="环境变量")
    private JSONObject variableJson;

    @ApiProperty(name="exeType",desc="当前执行的类型，用于测试计划中")
    private String exeType;

    private List<StepCommon> stepCommonList;

    public ApiSceneCase getApiSceneCase() {
        return apiSceneCase;
    }

    public void setApiSceneCase(ApiSceneCase apiSceneCase) {
        this.apiSceneCase = apiSceneCase;
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

    public List<StepCommon> getStepCommonList() {
        return stepCommonList;
    }

    public void setStepCommonList(List<StepCommon> stepCommonList) {
        this.stepCommonList = stepCommonList;
    }

    public String getExeType() {
        return exeType;
    }

    public void setExeType(String exeType) {
        this.exeType = exeType;
    }

    public JSONObject getVariableJson() {
        return variableJson;
    }

    public void setVariableJson(JSONObject variableJson) {
        this.variableJson = variableJson;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }
}
