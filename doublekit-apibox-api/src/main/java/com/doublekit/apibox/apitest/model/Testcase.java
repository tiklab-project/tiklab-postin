package com.doublekit.apibox.apitest.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.apibox.apidef.model.MethodEx;
import com.doublekit.beans.annotation.Mapping;
import com.doublekit.beans.annotation.Mappings;
import com.doublekit.join.annotation.Join;
import com.doublekit.join.annotation.JoinField;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel
@Join
public class Testcase {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="method",desc="所属接口",required = true)
    @Mappings({
            @Mapping(source = "id",target = "methodId")
    })
    @JoinField(id = "id")
    private MethodEx method;

    @NotNull
    @ApiProperty(name="name",desc="用例名称",required = true)
    private java.lang.String name;

    @ApiProperty(name="baseUrl",desc="路径前缀")
    private java.lang.String baseUrl;

    @ApiProperty(name="requestHeaderCaseList",desc="用例-请求头列表")
    private List<RequestHeaderCase> requestHeaderCaseList;

    @ApiProperty(name="queryParamCaseList",desc="用例-查询参数列表")
    private List<QueryParamCase> queryParamCaseList;

    @ApiProperty(name="requestBodyCase",desc="用例-请求体")
    private RequestBodyCase requestBodyCase;

    @ApiProperty(name="formParamCaseList",desc="用例-请求体form参数")
    private List<FormParamCase> formParamCaseList;

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

    public Testcase setId(String id) {
        this.id = id;
        return this;
    }

    public MethodEx getMethod() {
        return method;
    }

    public void setMethod(MethodEx method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
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
