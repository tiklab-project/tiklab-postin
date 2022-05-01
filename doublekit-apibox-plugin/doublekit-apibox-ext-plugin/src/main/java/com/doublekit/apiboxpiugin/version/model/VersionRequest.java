package com.doublekit.apiboxpiugin.version.model;


import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.apibox.apidef.http.model.*;
import com.doublekit.dis.annotation.IndexField;
import com.doublekit.dis.annotation.IndexQueryField;
import com.doublekit.join.annotation.Join;

import java.util.List;


@ApiModel
@Join
public class VersionRequest {

    @ApiProperty(name="requestHeaderList",desc="请求头部",required = true)
    @IndexField
    @IndexQueryField
    private List<RequestHeader> requestHeaderList;

    @ApiProperty(name="queryParamList",desc="查询参数",required = true)
    @IndexField
    @IndexQueryField
    private List<QueryParam> queryParamList;

    @ApiProperty(name="requestBodyEx",desc="请求体类型参数",required = true)
    @IndexField
    @IndexQueryField
    private List<RequestBodyEx> requestBodyExList;

    @ApiProperty(name="jsonParamList",desc="json类型请求体参数",required = true)
    @IndexField
    @IndexQueryField
    private List<JsonParam> jsonParamList;

    @ApiProperty(name="rawParamList",desc="raw类型请求体参数",required = true)
    @IndexField
    @IndexQueryField
    private List<RawParam> rawParamList;

    @ApiProperty(name="formParamList",desc="form类型请求体参数",required = true)
    @IndexField
    @IndexQueryField
    private List<FormParam> formParamList;

    @ApiProperty(name="afterScript",desc="后置脚本参数",required = true)
    @IndexField
    @IndexQueryField
    private List<AfterScript> afterScriptList;

    @ApiProperty(name="preScript",desc="前置脚本参数",required = true)
    @IndexField
    @IndexQueryField
    private List<PreScript> preScriptList;

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

    public List<RequestBodyEx> getRequestBodyExList() {
        return requestBodyExList;
    }

    public void setRequestBodyExList(List<RequestBodyEx> requestBodyExList) {
        this.requestBodyExList = requestBodyExList;
    }

    public List<JsonParam> getJsonParamList() {
        return jsonParamList;
    }

    public void setJsonParamList(List<JsonParam> jsonParamList) {
        this.jsonParamList = jsonParamList;
    }

    public List<RawParam> getRawParamList() {
        return rawParamList;
    }

    public void setRawParamList(List<RawParam> rawParamList) {
        this.rawParamList = rawParamList;
    }

    public List<FormParam> getFormParamList() {
        return formParamList;
    }

    public void setFormParamList(List<FormParam> formParamList) {
        this.formParamList = formParamList;
    }

    public List<AfterScript> getAfterScriptList() {
        return afterScriptList;
    }

    public void setAfterScriptList(List<AfterScript> afterScriptList) {
        this.afterScriptList = afterScriptList;
    }

    public List<PreScript> getPreScriptList() {
        return preScriptList;
    }

    public void setPreScriptList(List<PreScript> preScriptList) {
        this.preScriptList = preScriptList;
    }
}
