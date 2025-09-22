package io.tiklab.postin.autotest.http.unit.cases.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * path 实体
 */
@Entity
@Table(name="autotest_api_path")
public class PathParamUnitEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    // 所属接口单元用例
    @Column(name = "api_unit_id",length = 32,notNull = true)
    private String apiUnitId;

    //参数名称
    @Column(name = "name")
    private String name;

    //数据类型
    @Column(name = "data_type")
    private String dataType;

    //是否必选
    @Column(name = "required")
    private Integer required;

    //描述说明
    @Column(name = "description")
    private String desc;

    //示例值
    @Column(name = "value")
    private String value;

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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
