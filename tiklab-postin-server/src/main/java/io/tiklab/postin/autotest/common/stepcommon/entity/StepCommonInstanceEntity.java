package io.tiklab.postin.autotest.common.stepcommon.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 步骤公共历史 实体
 */
@Entity
@Table(name="autotest_case_step_instance_common")
public class StepCommonInstanceEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    // 所属断言
    @Column(name = "instance_id")
    private String instanceId;

    // 父级
    @Column(name = "parent_id")
    private String parentId;

    // 类型
    @Column(name = "type")
    private String type;

    // 排序
    @Column(name = "result")
    private Integer result;

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

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
