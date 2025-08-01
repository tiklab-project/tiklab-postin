package io.tiklab.postin.autotest.testplan.instance.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 测试计划下用例的实例的中间层 实体
 */
@Entity
@Table(name="autotest_test_plan_case_instance_bind")
public class TestPlanCaseInstanceBindEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 所属测试计划实例
    @Column(name = "test_plan_instance_id",length = 32)
    private String testPlanInstanceId;

    // 用例实例id
    @Column(name = "case_instance_id",length = 32)
    private String caseInstanceId;

    // 名称
    @Column(name="name",length = 128)
    private String name;

    // 测试类型
    @Column(name="test_type",length = 32)
    private String testType;

    // 用例类型
    @Column(name="case_type",length = 32)
    private String caseType;

    // 结果
    @Column(name="result")
    private Integer result;


    @Column(name="elapsed_time")
    private Integer elapsedTime;

    @Column(name="case_id")
    private String caseId;

    @Column(name="test_plan_id")
    private String testPlanId;

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

    public Integer getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Integer elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(String testPlanId) {
        this.testPlanId = testPlanId;
    }
}
