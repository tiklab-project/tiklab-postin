package io.tiklab.postin.autotest.common.forstep.entity;

import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.Entity;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;

import java.io.Serializable;

/**
 * for循环 实体
 */
@Entity
@Table(name="autotest_case_step_for")
public class ForStepEntity implements Serializable {

    @Id
    // @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    // 用例id
    @Column(name = "case_id")
    private String caseId;

    // 重复次数
    @Column(name = "times")
    private Integer times;

    // for 循环条件
    @Column(name = "condition")
    private String condition;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
