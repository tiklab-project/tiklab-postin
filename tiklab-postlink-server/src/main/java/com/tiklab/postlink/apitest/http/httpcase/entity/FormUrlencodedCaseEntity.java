package com.tiklab.postlink.apitest.http.httpcase.entity;

import com.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

@Entity
@Table(name="postlink_form_urlencoded_testcase")
public class FormUrlencodedCaseEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "http_case_id",length = 32,notNull = true)
    private String httpCaseId;

    @Column(name = "param_name",length = 64,notNull = true)
    private String paramName;

    @Column(name = "data_type",length = 32,notNull = true)
    private String dataType;

    @Column(name = "value",length = 128)
    private String value;

    @Column(name = "sort",length = 4)
    private Integer sort;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHttpCaseId() {
        return httpCaseId;
    }

    public void setHttpCaseId(String httpCaseId) {
        this.httpCaseId = httpCaseId;
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
