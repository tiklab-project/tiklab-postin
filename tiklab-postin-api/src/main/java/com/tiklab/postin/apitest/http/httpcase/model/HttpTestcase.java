package com.tiklab.postin.apitest.http.httpcase.model;

import com.tiklab.postin.annotation.ApiModel;
import com.tiklab.postin.annotation.ApiProperty;
import com.tiklab.postin.apidef.http.model.HttpApi;
import com.tiklab.beans.annotation.Mapper;
import com.tiklab.beans.annotation.Mapping;
import com.tiklab.beans.annotation.Mappings;
import com.tiklab.core.BaseModel;
import com.tiklab.join.annotation.Join;
import com.tiklab.join.annotation.JoinQuery;

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

    @ApiProperty(name="requestHeaderCaseList",desc="用例-请求头列表")
    private List<RequestHeaderCase> requestHeaderCaseList;

    @ApiProperty(name="queryParamCaseList",desc="用例-查询参数列表")
    private List<QueryParamCase> queryParamCaseList;

    @ApiProperty(name="requestBodyCase",desc="用例-请求体")
    private RequestBodyCase requestBodyCase;

    @ApiProperty(name="formParamCaseList",desc="用例-请求体form参数")
    private List<FormParamCase> formParamCaseList;

    @ApiProperty(name = "form")
    private List<FormUrlencodedCase> formUrlencodedCaseList;

    @ApiProperty(name="jsonParamCaseList",desc="用例-请求体json参数")
    private List<JsonParamCase> jsonParamCaseList;

    @ApiProperty(name="rawParamCase",desc="用例-请求体raw参数")
    private RawParamCase rawParamCase;

    @ApiProperty(name="preScriptCase",desc="用例-前置脚本参数")
    private PreScriptCase preScriptCase;

    @ApiProperty(name="afterScriptCase",desc="用例-后置脚本参数")
    private AfterScriptCase afterScriptCase;

    @ApiProperty(name="assertCaseList",desc="用例-断言列表")
    private List<AssertCase> assertCaseList;

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

    public List<RequestHeaderCase> getRequestHeaderCaseList() {
        return requestHeaderCaseList;
    }

    public void setRequestHeaderCaseList(List<RequestHeaderCase> requestHeaderCaseList) {
        this.requestHeaderCaseList = requestHeaderCaseList;
    }

    public List<QueryParamCase> getQueryParamCaseList() {
        return queryParamCaseList;
    }

    public void setQueryParamCaseList(List<QueryParamCase> queryParamCaseList) {
        this.queryParamCaseList = queryParamCaseList;
    }

    public RequestBodyCase getRequestBodyCase() {
        return requestBodyCase;
    }

    public void setRequestBodyCase(RequestBodyCase requestBodyCase) {
        this.requestBodyCase = requestBodyCase;
    }

    public List<FormParamCase> getFormParamCaseList() {
        return formParamCaseList;
    }

    public void setFormParamCaseList(List<FormParamCase> formParamCaseList) {
        this.formParamCaseList = formParamCaseList;
    }

    public List<FormUrlencodedCase> getFormUrlencodedCaseList() {
        return formUrlencodedCaseList;
    }

    public void setFormUrlencodedCaseList(List<FormUrlencodedCase> formUrlencodedCaseList) {
        this.formUrlencodedCaseList = formUrlencodedCaseList;
    }

    public List<JsonParamCase> getJsonParamCaseList() {
        return jsonParamCaseList;
    }

    public void setJsonParamCaseList(List<JsonParamCase> jsonParamCaseList) {
        this.jsonParamCaseList = jsonParamCaseList;
    }

    public RawParamCase getRawParamCase() {
        return rawParamCase;
    }

    public void setRawParamCase(RawParamCase rawParamCase) {
        this.rawParamCase = rawParamCase;
    }

    public PreScriptCase getPreScriptCase() {
        return preScriptCase;
    }

    public void setPreScriptCase(PreScriptCase preScriptCase) {
        this.preScriptCase = preScriptCase;
    }

    public AfterScriptCase getAfterScriptCase() {
        return afterScriptCase;
    }

    public void setAfterScriptCase(AfterScriptCase afterScriptCase) {
        this.afterScriptCase = afterScriptCase;
    }

    public List<AssertCase> getAssertCaseList() {
        return assertCaseList;
    }

    public void setAssertCaseList(List<AssertCase> assertCaseList) {
        this.assertCaseList = assertCaseList;
    }
}
