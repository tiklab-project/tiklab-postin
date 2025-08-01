package io.tiklab.postin.autotest.common.ifjudgment.entity;

import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.Entity;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;

import java.io.Serializable;

/**
 * 变量断言 实体
 */
@Entity
@Table(name="autotest_case_step_if")
public class IfJudgmentEntity implements Serializable {

    @Id
    // @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    // 用例id
    @Column(name = "case_id")
    private String caseId;

    // 条件关系
    @Column(name = "relation")
    private String relation;


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

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
