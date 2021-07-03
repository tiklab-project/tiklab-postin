package com.doublekit.apibox.apitest.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.beans.annotation.Mapping;
import com.doublekit.beans.annotation.Mappings;
import com.doublekit.join.annotation.Join;
import com.doublekit.join.annotation.JoinField;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@ApiModel
@Join
public class TestInstance {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="testcase",desc="所属接口用例",required = true)
    @Mappings({
            @Mapping(source = "id",target = "testcaseId")
    })
    @JoinField(id = "id")
    private Testcase testcase;

    @ApiProperty(name="testNo",desc="测试序号")
    private java.lang.Integer testNo;

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

    public Testcase getTestcase() {
        return testcase;
    }

    public void setTestcase(Testcase testcase) {
        this.testcase = testcase;
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
}
