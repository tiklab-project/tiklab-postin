package com.doublekit.apibox.apitest.entity;


import com.doublekit.dal.jpa.mapper.annotation.Column;
import com.doublekit.dal.jpa.mapper.annotation.GeneratorValue;
import com.doublekit.dal.jpa.mapper.annotation.Id;
import com.doublekit.dal.jpa.mapper.annotation.Table;

import java.io.Serializable;

@Table(name="apibox_request_header_testcase")
public class RequestHeaderCaseEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "testcase_id",length = 32,notNull = true)
    private String testcaseId;

    @Column(name = "header_name",length = 64,notNull = true)
    private String headerName;

    @Column(name = "value",length = 256)
    private String value;

    @Column(name = "sort",length = 4)
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestcaseId() {
        return testcaseId;
    }

    public void setTestcaseId(String testcaseId) {
        this.testcaseId = testcaseId;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
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