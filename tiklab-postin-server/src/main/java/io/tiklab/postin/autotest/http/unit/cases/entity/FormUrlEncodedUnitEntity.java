package io.tiklab.postin.autotest.http.unit.cases.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * form-url 实体
 */
@Entity
@Table(name="autotest_api_form_urlencoded")
public class FormUrlEncodedUnitEntity implements Serializable {

    @Id
     @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

     // 所属接口
    @Column(name = "api_unit_id",length = 32,notNull = true)
    private String apiUnitId;

    // 名称
    @Column(name = "param_name",length = 64,notNull = true)
    private String paramName;

    // 数据类型
    @Column(name = "data_type",length = 32,notNull = true)
    private String dataType;

     // 是否必选
//    @Column(name = "required",length = 2,notNull = true)
//    private Integer required;

    // 说明描述
    @Column(name = "description",length = 128)
    private String desc;

     // 示例值
    @Column(name = "value",length = 128)
    private String value;

    // 排序
    @Column(name = "sort",length = 4)
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

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
