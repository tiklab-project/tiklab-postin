package com.doublekit.apibox.apidef.entity;


import com.doublekit.dal.jpa.annotation.mapper.Column;
import com.doublekit.dal.jpa.annotation.mapper.GeneratorValue;
import com.doublekit.dal.jpa.annotation.mapper.Id;
import com.doublekit.dal.jpa.annotation.mapper.Table;

import java.io.Serializable;

@Table(name="apibox_json_param")
public class JsonParamPo implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "parent_id",length = 32)
    private String parentId;

    @Column(name = "method_id",length = 32,notNull = true)
    private String methodId;

    @Column(name = "param_name",length = 64,notNull = true)
    private String paramName;

    @Column(name = "data_type",length = 32,notNull = true)
    private String dataType;

    @Column(name = "required",length = 2,notNull = true)
    private Integer required;

    @Column(name = "description",length = 128)
    private String desc;

    @Column(name = "eg",length = 128)
    private String eg;

    @Column(name = "sort",length = 4)
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getEg() {
        return eg;
    }

    public void setEg(String eg) {
        this.eg = eg;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
