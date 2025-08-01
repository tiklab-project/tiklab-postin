package io.tiklab.postin.autotest.common.ifjudgment.entity;

import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.Entity;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;

import java.io.Serializable;

/**
 * 变量断言历史 实体
 */
@Entity
@Table(name="autotest_case_step_instance_if")
public class IfJudgmentInstanceEntity implements Serializable {

    @Id
    // @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    // 步骤历史id
    @Column(name = "step_instance_id")
    private String stepInstanceId;

    // 条件关系
    @Column(name = "relation")
    private String relation;


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

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
