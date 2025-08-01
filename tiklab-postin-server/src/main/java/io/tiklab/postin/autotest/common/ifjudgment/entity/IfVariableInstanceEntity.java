package io.tiklab.postin.autotest.common.ifjudgment.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 变量断言 实体
 */
@Entity
@Table(name="autotest_case_step_instance_if_variable")
public class IfVariableInstanceEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 步骤历史id
    @Column(name = "step_instance_id")
    private String stepInstanceId;

    // 变量
    @Column(name = "variable",length = 128)
    private String variable;

    // 比较
    @Column(name = "compare",length = 12)
    private String compare;

    // 变量值
    @Column(name = "expect",length = 128)
    private String expect;

    // 变量值
    @Column(name = "result")
    private int result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStepInstanceId() {
        return stepInstanceId;
    }

    public void setStepInstanceId(String stepInstanceId) {
        this.stepInstanceId = stepInstanceId;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getCompare() {
        return compare;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
