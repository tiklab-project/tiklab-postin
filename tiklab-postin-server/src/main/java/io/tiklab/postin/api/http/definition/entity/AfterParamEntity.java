package io.tiklab.postin.api.http.definition.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 实体实体
 */
@Entity
@Table(name="postin_api_request_after")
public class AfterParamEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    //所属接口
    @Column(name = "api_id")
    private String apiId;

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

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
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
