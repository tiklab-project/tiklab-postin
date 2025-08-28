package io.tiklab.postin.autotest.http.unit.cases.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 前置 实体
 */
@Entity
@Table(name="autotest_action_pre")
public class PreParamUnitEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    //所属用例
    @Column(name = "api_unit_id")
    private String apiUnitId;

    //参数名称
    @Column(name = "name")
    private String name;

    //类型
    @Column(name = "type")
    private String type;

    //是否启用
    @Column(name = "enabled")
    private Integer enabled;

    //排序
    @Column(name = "sort")
    private Integer sort;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiUnitId() {
        return apiUnitId;
    }

    public void setApiUnitId(String apiUnitId) {
        this.apiUnitId = apiUnitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
