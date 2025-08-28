package io.tiklab.postin.autotest.http.unit.execute.model;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCase;
import io.tiklab.postin.autotest.http.unit.cases.model.ApiUnitCaseDataConstruction;

/**
 * 接口单元测试执行数据构造 模型
 */
@ApiModel
public class ApiUnitTestRequest extends BaseModel {

    @ApiProperty(name="apiUnitCase",desc="测试用例")
    private ApiUnitCase apiUnitCase;

    @ApiProperty(name="workspaceId",desc="项目Id")
    private String workspaceId;

    @ApiProperty(name="apiEnvId",desc="接口环境id")
    private String apiEnvId;

    @ApiProperty(name="apiPreUrl",desc="接口前置地址")
    private String apiPreUrl;

    @ApiProperty(name="apiUnitCaseDataConstruction",desc="接口数据")
    private ApiUnitCaseDataConstruction apiUnitCaseDataConstruction;

    @ApiProperty(name="variableJson",desc="环境变量")
    private JSONObject variableJson;


    public ApiUnitCase getApiUnitCase() {
        return apiUnitCase;
    }

    public void setApiUnitCase(ApiUnitCase apiUnitCase) {
        this.apiUnitCase = apiUnitCase;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
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

    public ApiUnitCaseDataConstruction getApiUnitCaseDataConstruction() {
        return apiUnitCaseDataConstruction;
    }

    public void setApiUnitCaseDataConstruction(ApiUnitCaseDataConstruction apiUnitCaseDataConstruction) {
        this.apiUnitCaseDataConstruction = apiUnitCaseDataConstruction;
    }

    public JSONObject getVariableJson() {
        return variableJson;
    }

    public void setVariableJson(JSONObject variableJson) {
        this.variableJson = variableJson;
    }

    // Convert this object to JSONObject
    public JSONObject toJSONObject() {
        return (JSONObject) JSONObject.toJSON(this);
    }
}
