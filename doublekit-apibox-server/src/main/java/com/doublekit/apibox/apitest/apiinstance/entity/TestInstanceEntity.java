package com.doublekit.apibox.apitest.apiinstance.entity;


import com.doublekit.dal.jpa.annotation.Column;
import com.doublekit.dal.jpa.annotation.GeneratorValue;
import com.doublekit.dal.jpa.annotation.Id;
import com.doublekit.dal.jpa.annotation.Table;import com.doublekit.dal.jpa.annotation.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity @Table(name="apibox_test_instance")
public class TestInstanceEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 40)
    private String id;

    @Column(name = "testcase_id",length = 40)
    private String testcaseId;

    @Column(name = "testNo",notNull = true)
    private Integer testNo;

    @Column(name = "statusCode",notNull = true)
    private Integer statusCode;

    @Column(name = "result",notNull = true)
    private Integer result;

    @Column(name = "create_time",length = 4)
    private Timestamp createTime;

    @Column(name = "request_type",notNull = true)
    private String requestType;

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

    public Integer getTestNo() {
        return testNo;
    }

    public void setTestNo(Integer testNo) {
        this.testNo = testNo;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
