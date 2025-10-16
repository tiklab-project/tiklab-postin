package io.tiklab.postin.autotest.common.stepcommon.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 变量断言 实体
 */
@Entity
@Table(name="autotest_case_step_common")
public class StepCommonEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    // 关联的用例
    @Column(name = "case_id")
    private String caseId;

    // 父级
    @Column(name = "parent_id")
    private String parentId;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    // 类型
    @Column(name = "type")
    private String type;

    // 排序
    @Column(name = "sort")
    private Integer sort;

    @Column(name = "name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
