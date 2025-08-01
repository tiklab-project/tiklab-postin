package io.tiklab.postin.autotest.testplan.instance.model;

import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfInstance;
import io.tiklab.postin.autotest.http.scene.instance.model.ApiSceneInstance;
import io.tiklab.postin.autotest.http.unit.instance.model.ApiUnitInstance;
import io.tiklab.postin.autotest.test.model.TestCase;
import io.tiklab.postin.autotest.testplan.cases.model.TestPlan;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;

/**
 * 测试计划下用例的实例的中间层 模型
 */
@ApiModel
@Mapper
@Join
public class TestPlanCaseInstanceBind extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="testPlanInstanceId",desc="所属测试计划实例")
    private String testPlanInstanceId;

    @ApiProperty(name="caseInstanceId",desc="用例实例id")
    private String caseInstanceId;

    @ApiProperty(name="name",desc="名称")
    private String name;

    @ApiProperty(name="testType",desc="测试类型")
    private String testType;

    @ApiProperty(name="caseType",desc="用例类型")
    private String caseType;

    @ApiProperty(name="result",desc="结果")
    private Integer result;

    @ApiProperty(name="status",desc="执行状态 状态为 1 说明正在执行")
    private Integer status;

    @ApiProperty(name = "elapsedTime")
    private Integer elapsedTime;

    @ApiProperty(name = "caseId")
    private String caseId;

    @ApiProperty(name = "caseId")
    @Mappings({
            @Mapping(source = "testCase.id",target = "caseId")
    })
    @JoinField(key = "id")
    private TestCase testCase;

    @ApiProperty(name = "testPlanId")
    @Mappings({
            @Mapping(source = "testPlan.id",target = "testPlanId")
    })
    @JoinField(key = "id")
    private TestPlan testPlan;

    private ApiUnitInstance apiUnitInstance;
    private ApiSceneInstance apiSceneInstance;
    private ApiPerfInstance apiPerfInstance;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getTestPlanInstanceId() {
        return testPlanInstanceId;
    }

    public void setTestPlanInstanceId(String testPlanInstanceId) {
        this.testPlanInstanceId = testPlanInstanceId;
    }
    public String getCaseInstanceId() {
        return caseInstanceId;
    }

    public void setCaseInstanceId(String caseInstanceId) {
        this.caseInstanceId = caseInstanceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }
    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }
    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Integer elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public ApiUnitInstance getApiUnitInstance() {
        return apiUnitInstance;
    }

    public void setApiUnitInstance(ApiUnitInstance apiUnitInstance) {
        this.apiUnitInstance = apiUnitInstance;
    }

    public ApiSceneInstance getApiSceneInstance() {
        return apiSceneInstance;
    }

    public void setApiSceneInstance(ApiSceneInstance apiSceneInstance) {
        this.apiSceneInstance = apiSceneInstance;
    }

    public ApiPerfInstance getApiPerfInstance() {
        return apiPerfInstance;
    }

    public void setApiPerfInstance(ApiPerfInstance apiPerfInstance) {
        this.apiPerfInstance = apiPerfInstance;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public TestPlan getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(TestPlan testPlan) {
        this.testPlan = testPlan;
    }
}
