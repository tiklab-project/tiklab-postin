package io.tiklab.postin.autotest.common.ifjudgment.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 变量断言 实体
 */
@Entity
@Table(name="autotest_case_step_if_variable")
public class IfVariableEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 所属断言
    @Column(name = "step_id",length = 32)
    private String stepId;

    // 变量
    @Column(name = "variable",length = 128)
    private String variable;

    // 比较
    @Column(name = "compare",length = 12)
    private String compare;

    // 变量值
    @Column(name = "expect",length = 128)
    private String expect;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
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
}
