package com.doublekit.apibox.apitest.http.httpinstance.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.apibox.apitest.http.httpcase.model.HttpTestcase;
import com.doublekit.beans.annotation.Mapper;
import com.doublekit.beans.annotation.Mapping;
import com.doublekit.beans.annotation.Mappings;
import com.doublekit.common.BaseModel;
import com.doublekit.dss.annotation.IndexField;
import com.doublekit.join.annotation.Join;
import com.doublekit.join.annotation.JoinQuery;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ApiModel
@Join
@Mapper(targetAlias = "TestInstanceEntity")
public class TestInstance extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="testcase",desc="所属接口用例",required = true)
    @Mappings({
            @Mapping(source = "testcase.id",target = "testcaseId")
    })
    @JoinQuery(key = "id")
    private HttpTestcase httpTestcase;

    @ApiProperty(name="createTime",desc="创建时间")
    @IndexField
    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @ApiProperty(name="testNo",desc="测试序号")
    private Integer testNo;

    @ApiProperty(name = "requestType",desc = "请求类型")
    private String requestType;

    @NotNull
    @ApiProperty(name="statusCode",desc="状态码",required = true)
    private java.lang.Integer statusCode;

    @NotNull
    @ApiProperty(name="result",desc="测试结果",required = true)
    private java.lang.Integer result;

    @ApiProperty(name="requestInstance",desc="实例-请求部分")
    private RequestInstance requestInstance;

    @ApiProperty(name="responseInstance",desc="实例-响应部分")
    private ResponseInstance responseInstance;

    @ApiProperty(name="assertInstanceList",desc="实例-断言列表")
    private List<AssertInstance> assertInstanceList = new ArrayList<>();

    public String getId() {
        return id;
    }

    public TestInstance setId(String id) {
        this.id = id;
        return this;
    }

    public HttpTestcase getTestcase() {
        return httpTestcase;
    }

    public void setTestcase(HttpTestcase httpTestcase) {
        this.httpTestcase = httpTestcase;
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

    public RequestInstance getRequestInstance() {
        return requestInstance;
    }

    public void setRequestInstance(RequestInstance requestInstance) {
        this.requestInstance = requestInstance;
    }

    public ResponseInstance getResponseInstance() {
        return responseInstance;
    }

    public void setResponseInstance(ResponseInstance responseInstance) {
        this.responseInstance = responseInstance;
    }

    public List<AssertInstance> getAssertInstanceList() {
        return assertInstanceList;
    }

    public void setAssertInstanceList(List<AssertInstance> assertInstanceList) {
        this.assertInstanceList = assertInstanceList;
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
