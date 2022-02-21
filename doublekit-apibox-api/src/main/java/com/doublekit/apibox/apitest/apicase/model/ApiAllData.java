package com.doublekit.apibox.apitest.apicase.model;

import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.apibox.apidef.model.*;
import com.doublekit.common.BaseModel;
import java.util.List;

public class ApiAllData extends BaseModel {

    @ApiProperty(name = "requestType",desc = "请求类型",eg = "post,get")
    private String requestType;

    @ApiProperty(name = "path")
    private String path;

    @ApiProperty(name = "headerList")
    private List<RequestHeader> requestHeaderList;

    @ApiProperty(name = "queryList")
    private List<QueryParam> queryParamList;

    @ApiProperty(name = "bodyType")
    private String bodyType;

    @ApiProperty(name = "formParamList")
    private List<FormParam> formParamList;

    @ApiProperty(name = "formUrlencodedList")
    private List<FormUrlencoded> formUrlencodedList;

    @ApiProperty(name = "jsonParamList")
    private List<JsonParam> jsonParamList;

    @ApiProperty(name = "raw")
    private RawParam rawParam;

    @ApiProperty(name = "preScript")
    private String preScript;

    @ApiProperty(name = "afterScript")
    private String afterScript;


    public String getRequestType() {
        return this.requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<RequestHeader> getRequestHeaderList() {
        return requestHeaderList;
    }

    public void setRequestHeaderList(List<RequestHeader> requestHeaderList) {
        this.requestHeaderList = requestHeaderList;
    }

    public List<QueryParam> getQueryParamList() {
        return queryParamList;
    }

    public void setQueryParamList(List<QueryParam> queryParamList) {
        this.queryParamList = queryParamList;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public List<FormParam> getFormParamList() {
        return formParamList;
    }

    public void setFormParamList(List<FormParam> formParamList) {
        this.formParamList = formParamList;
    }

    public List<FormUrlencoded> getFormUrlencodedList() {
        return formUrlencodedList;
    }

    public void setFormUrlencodedList(List<FormUrlencoded> formUrlencodedList) {
        this.formUrlencodedList = formUrlencodedList;
    }

    public List<JsonParam> getJsonParamList() {
        return jsonParamList;
    }

    public void setJsonParamList(List<JsonParam> jsonParamList) {
        this.jsonParamList = jsonParamList;
    }

    public RawParam getRawParam() {
        return rawParam;
    }

    public void setRawParam(RawParam rawParam) {
        this.rawParam = rawParam;
    }

    public String getPreScript() {
        return preScript;
    }

    public void setPreScript(String preScript) {
        this.preScript = preScript;
    }

    public String getAfterScript() {
        return afterScript;
    }

    public void setAfterScript(String afterScript) {
        this.afterScript = afterScript;
    }
}
