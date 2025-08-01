package io.tiklab.postin.autotest.testplan.instance.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 测试计划实例 实体
 */
@Entity
@Table(name="autotest_test_plan_instance")
public class TestPlanInstanceEntity implements Serializable {
    @Id
     @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 所属计划
    @Column(name = "test_plan_id")
    private String testPlanId;

    // 所属仓库
    @Column(name = "workspace_id")
    private String workspaceId;

    // 状态，0：未执行，1：正在执行
    @Column(name = "status")
    private Integer status;

    // 结果
    @Column(name = "result")
    private Integer result;

    // 总用例
    @Column(name = "total")
    private Integer total;

    // 可执行用例
    @Column(name = "executable_case_num")
    private Integer executableCaseNum;

    // 通过数
    @Column(name = "pass_num")
    private Integer passNum;

    // 错误数
    @Column(name = "fail_num")
    private Integer failNum;

    // 通过率
    @Column(name = "pass_rate")
    private String passRate;

    // 错误率
    @Column(name = "error_rate")
    private String errorRate;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    // 执行人
    @Column(name = "create_user")
    private String createUser;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getExecutableCaseNum() {
        return executableCaseNum;
    }

    public void setExecutableCaseNum(Integer executableCaseNum) {
        this.executableCaseNum = executableCaseNum;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }



}
