package com.darthcloud.apibox.api.apidef.entity;


import com.darthcloud.dal.jpa.annotation.Column;
import com.darthcloud.dal.jpa.annotation.GeneratorValue;
import com.darthcloud.dal.jpa.annotation.Id;
import com.darthcloud.dal.jpa.annotation.Table;

import java.io.Serializable;

@Table(name="apibox_response_header")
public class ResponseHeaderPo implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "method_id",length = 32,notNull = true)
    private String methodId;

    @Column(name = "header_name",length = 64,notNull = true)
    private String headerName;

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

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
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

    public String getEg() {
        return eg;
    }

    public void setEg(String eg) {
        this.eg = eg;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
