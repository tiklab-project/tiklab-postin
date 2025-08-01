package io.tiklab.postin.autotest.testplan.execute.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 测试数据构造 模型
 */
@ApiModel
public class TestPlanTestData extends BaseModel{

    @ApiProperty(name="testPlanId",desc="当前测试计划Id")
    private String testPlanId;

    @ApiProperty(name="workspaceId",desc="仓库Id")
    private String workspaceId;

    @ApiProperty(name="apiEnvId",desc="接口环境id")
    private String apiEnvId;

    @ApiProperty(name="apiPreUrl",desc="接口前置地址")
    private String apiPreUrl;

    @ApiProperty(name="webEnv",desc="WEB环境")
    private String webEnv;

    @ApiProperty(name="appEnv",desc="APP环境")
    private String appEnv;

    @ApiProperty(name="appEnvList",desc="APP环境list")
    private List<String> appEnvList;

    public String getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(String testPlanId) {
        this.testPlanId = testPlanId;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getApiPreUrl() {
        return apiPreUrl;
    }

    public void setApiPreUrl(String apiPreUrl) {
        this.apiPreUrl = apiPreUrl;
    }

    public String getApiEnvId() {
        return apiEnvId;
    }

    public void setApiEnvId(String apiEnvId) {
        this.apiEnvId = apiEnvId;
    }

    public String getWebEnv() {
        return webEnv;
    }

    public void setWebEnv(String webEnv) {
        this.webEnv = webEnv;
    }

    public String getAppEnv() {
        return appEnv;
    }

    public void setAppEnv(String appEnv) {
        this.appEnv = appEnv;
    }

    public List<String> getAppEnvList() {
        return appEnvList;
    }

    public void setAppEnvList(List<String> appEnvList) {
        this.appEnvList = appEnvList;
    }
}
