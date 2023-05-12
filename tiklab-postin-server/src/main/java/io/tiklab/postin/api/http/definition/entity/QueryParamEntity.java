package io.tiklab.postin.api.http.definition.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * 定义
 * http协议
 * query 实体
 */
@Entity
@Table(name="postin_http_request_query")
public class QueryParamEntity implements Serializable {

    @Id
     @GeneratorValue(length = 12)
    @Column(name = "id",length = 40)
    private String id;

    //所属接口
    @Column(name = "http_id",length = 40)
    private String httpId;

    //参数名称
    @Column(name = "param_name",length = 64,notNull = true)
    private String paramName;

    //是否必选
    @Column(name = "required",length = 2,notNull = true)
    private Integer required;

    //描述说明
    @Column(name = "description",length = 128)
    private String desc;

    //示例值
    @Column(name = "value",length = 128)
    private String value;

    //排序
    @Column(name = "sort",length = 4)
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHttpId() {
        return httpId;
    }

    public void setHttpId(String httpId) {
        this.httpId = httpId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
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
