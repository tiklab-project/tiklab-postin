package com.doublekit.apibox.apitest.apicase.model;

import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.common.BaseModel;

import java.util.List;

public class CaseApiAllData extends BaseModel {
    @ApiProperty(name = "name")
    private String name;

    @ApiProperty(name = "requestType",desc = "请求类型",eg = "post,get")
    private String requestType;

    @ApiProperty(name = "path")
    private String path;

    @ApiProperty(name = "headerList")
    private List<RequestHeaderCase> requestHeaderList;

    @ApiProperty(name = "queryList")
    private List<QueryParamCase> queryParamList;

    @ApiProperty(name = "bodyType")
    private String bodyType;

    @ApiProperty(name = "formParamList")
    private List<FormParamCase> formParamList;

    @ApiProperty(name = "formUrlencodedList")
    private List<FormUrlencodedCase> formUrlencodedList;

    @ApiProperty(name = "jsonParamList")
    private List<JsonParamCase> jsonParamList;

    @ApiProperty(name = "raw")
    private RawParamCase rawParam;

    @ApiProperty(name = "preScript")
    private PreScriptCase preScript;

    @ApiProperty(name = "afterScript")
    private AfterScriptCase afterScript;

    public String getRequestType() {
        return requestType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<RequestHeaderCase> getRequestHeaderList() {
        return requestHeaderList;
    }

    public void setRequestHeaderList(List<RequestHeaderCase> requestHeaderList) {
        this.requestHeaderList = requestHeaderList;
    }

    public List<QueryParamCase> getQueryParamList() {
        return queryParamList;
    }

    public void setQueryParamList(List<QueryParamCase> queryParamList) {
        this.queryParamList = queryParamList;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public List<FormParamCase> getFormParamList() {
        return formParamList;
    }

    public void setFormParamList(List<FormParamCase> formParamList) {
        this.formParamList = formParamList;
    }

    public List<FormUrlencodedCase> getFormUrlencodedList() {
        return formUrlencodedList;
    }

    public void setFormUrlencodedList(List<FormUrlencodedCase> formUrlencodedList) {
        this.formUrlencodedList = formUrlencodedList;
    }

    public List<JsonParamCase> getJsonParamList() {
        return jsonParamList;
    }

    public void setJsonParamList(List<JsonParamCase> jsonParamList) {
        this.jsonParamList = jsonParamList;
    }

    public RawParamCase getRawParam() {
        return rawParam;
    }

    public void setRawParam(RawParamCase rawParam) {
        this.rawParam = rawParam;
    }

    public PreScriptCase getPreScript() {
        return preScript;
    }

    public void setPreScript(PreScriptCase preScript) {
        this.preScript = preScript;
    }

    public AfterScriptCase getAfterScript() {
        return afterScript;
    }

    public void setAfterScript(AfterScriptCase afterScript) {
        this.afterScript = afterScript;
    }
}
