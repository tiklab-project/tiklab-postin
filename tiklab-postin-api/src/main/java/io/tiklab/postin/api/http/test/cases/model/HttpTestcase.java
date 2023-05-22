package io.tiklab.postin.api.http.test.cases.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.api.http.definition.model.HttpApi;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel
@Join
@Mapper(targetAlias = "HttpTestcaseEntity")
public class HttpTestcase extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="http",desc="所属接口",required = true)
    @Mappings({
            @Mapping(source = "http.id",target = "httpId")
    })
    @JoinQuery(key = "id")
    private HttpApi http;

    @NotNull
    @ApiProperty(name="name",desc="用例名称",required = true)
    private java.lang.String name;

    @ApiProperty(name="result",desc="最近一次测试的结果")
    private Integer result;

    @ApiProperty(name="headerList",desc="用例-请求头列表")
    private List<RequestHeaderCase> headerList;

    @ApiProperty(name="queryList",desc="用例-查询参数列表")
    private List<QueryParamCase> queryList;

    @ApiProperty(name="request",desc="用例-请求参数")
    private RequestCase request;

    @ApiProperty(name="formList",desc="用例-请求体form参数")
    private List<FormParamCase> formList;

    @ApiProperty(name = "urlencodedList")
    private List<FormUrlencodedCase> urlencodedList;

    @ApiProperty(name="jsonList",desc="用例-请求体json参数")
    private List<JsonParamCases> jsonList;

    @ApiProperty(name="rawParamCase",desc="用例-请求体raw参数")
    private RawParamCase rawParamCase;

    @ApiProperty(name="assertList",desc="用例-断言列表")
    private List<AssertCases> assertList;

    public String getId() {
        return id;
    }

    public HttpTestcase setId(String id) {
        this.id = id;
        return this;
    }

    public HttpApi getHttp() {
        return http;
    }

    public void setHttp(HttpApi http) {
        this.http = http;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public List<RequestHeaderCase> getHeaderList() {
        return headerList;
    }

    public void setHeaderList(List<RequestHeaderCase> headerList) {
        this.headerList = headerList;
    }

    public List<QueryParamCase> getQueryList() {
        return queryList;
    }

    public void setQueryList(List<QueryParamCase> queryList) {
        this.queryList = queryList;
    }

    public RequestCase getRequest() {
        return request;
    }

    public void setRequest(RequestCase request) {
        this.request = request;
    }

    public List<FormParamCase> getFormList() {
        return formList;
    }

    public void setFormList(List<FormParamCase> formList) {
        this.formList = formList;
    }

    public List<FormUrlencodedCase> getUrlencodedList() {
        return urlencodedList;
    }

    public void setUrlencodedList(List<FormUrlencodedCase> urlencodedList) {
        this.urlencodedList = urlencodedList;
    }

    public List<JsonParamCases> getJsonList() {
        return jsonList;
    }

    public void setJsonList(List<JsonParamCases> jsonList) {
        this.jsonList = jsonList;
    }

    public RawParamCase getRawParamCase() {
        return rawParamCase;
    }

    public void setRawParamCase(RawParamCase rawParamCase) {
        this.rawParamCase = rawParamCase;
    }

    public List<AssertCases> getAssertList() {
        return assertList;
    }

    public void setAssertList(List<AssertCases> assertList) {
        this.assertList = assertList;
    }
}
