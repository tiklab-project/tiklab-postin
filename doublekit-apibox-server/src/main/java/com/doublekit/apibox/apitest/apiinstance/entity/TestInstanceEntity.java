package com.doublekit.apibox.apitest.apiinstance.entity;


import com.doublekit.dal.jpa.mapper.annotation.Column;
import com.doublekit.dal.jpa.mapper.annotation.GeneratorValue;
import com.doublekit.dal.jpa.mapper.annotation.Id;
import com.doublekit.dal.jpa.mapper.annotation.Table;import com.doublekit.dal.jpa.mapper.annotation.Entity;

import java.io.Serializable;
import java.util.Date;

@Entity @Table(name="apibox_test_instance")
public class TestInstanceEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 40)
    private String id;

    @Column(name = "testcase_id",length = 40)
    private String testcaseId;

    @Column(name = "method_id",length = 40)
    private String methodId;

    @Column(name = "type",length = 32)
    private String type;

    @Column(name = "testNo",notNull = true)
    private Integer testNo;

    @Column(name = "statusCode",notNull = true)
    private Integer statusCode;

    @Column(name = "result",notNull = true)
    private Integer result;

    @Column(name = "create_time",length = 4)
    private Date createTime;

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

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
