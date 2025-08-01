package io.tiklab.postin.autotest.testplan.instance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.postin.autotest.instance.model.Instance;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.autotest.testplan.cases.model.TestPlan;

/**
 * 测试计划实例 模型
 */
@ApiModel
@Mapper
@Join
public class TestPlanInstance extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="testPlan",desc="所属模块")
    @Mappings({
            @Mapping(source = "testPlan.id",target = "testPlanId")
    })
    @JoinField(key = "id")
    private TestPlan testPlan;

    @ApiProperty(name="workspaceId",desc="所属仓库")
    private String workspaceId;

    @ApiProperty(name="status",desc="状态，0：未执行，1：正在执行")
    private Integer status;

    @ApiProperty(name="result",desc="结果")
    private Integer result;

    @ApiProperty(name="total",desc="总用例")
    private Integer total;

    @ApiProperty(name="total",desc="可执行用例")
    private Integer executableCaseNum;

    @ApiProperty(name="passNum",desc="通过数")
    private Integer passNum;

    @ApiProperty(name="failNum",desc="错误数")
    private Integer failNum;

    @ApiProperty(name="passRate",desc="通过率")
    private String passRate;

    @ApiProperty(name="errorRate",desc="错误率")
    private String errorRate;

    @ApiProperty(name = "elapsedTime")
    private Integer elapsedTime;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private java.sql.Timestamp createTime;

    @ApiProperty(name="createUser",desc="执行人")
    private String createUser;

    private TestPlanCaseInstanceBind bindCase;

    private Instance instance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TestPlan getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(TestPlan testPlan) {
        this.testPlan = testPlan;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
    public Integer getPassNum() {
        return passNum;
    }

    public void setPassNum(Integer passNum) {
        this.passNum = passNum;
    }
    public Integer getFailNum() {
        return failNum;
    }

    public void setFailNum(Integer failNum) {
        this.failNum = failNum;
    }
    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }
    public String getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(String errorRate) {
        this.errorRate = errorRate;
    }

    public Integer getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Integer elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public TestPlanCaseInstanceBind getBindCase() {
        return bindCase;
    }

    public void setBindCase(TestPlanCaseInstanceBind bindCase) {
        this.bindCase = bindCase;
    }

    public Integer getExecutableCaseNum() {
        return executableCaseNum;
    }

    public void setExecutableCaseNum(Integer executableCaseNum) {
        this.executableCaseNum = executableCaseNum;
    }

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }
}
