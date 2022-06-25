package com.doublekit.apibox.integration.version.model;


import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.apibox.apidef.http.model.*;
import com.doublekit.core.BaseModel;
import com.doublekit.join.annotation.Join;

import java.util.List;


@ApiModel
@Join
public class RequestInfo extends BaseModel {


    @ApiProperty(name="requestHeaderList",desc="请求头部",required = true)
    private List<RequestHeader> headers;

    @ApiProperty(name="queryParamList",desc="查询参数",required = true)
    private List<QueryParam> query;

    @ApiProperty(name="requestBodyEx",desc="请求体类型参数",required = true)
    private RequestBodyEx body;

    @ApiProperty(name="formParamList",desc="form类型请求体参数",required = true)
    private List<FormParam> form;

    @ApiProperty(name="formUrlencodedList",desc="form类型请求体参数",required = true)
    private List<FormUrlencoded> formUrlencoded;

    @ApiProperty(name="jsonParamList",desc="json类型请求体参数",required = true)
    private List<JsonParam> json;

    @ApiProperty(name="rawParamList",desc="raw类型请求体参数",required = true)
    private RawParam raw;

    @ApiProperty(name="afterScript",desc="后置脚本参数",required = true)
    private AfterScript afterScript;

    @ApiProperty(name="preScript",desc="前置脚本参数",required = true)
    private PreScript preScript;

    public List<RequestHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(List<RequestHeader> headers) {
        this.headers = headers;
    }

    public List<QueryParam> getQuery() {
        return query;
    }

    public void setQuery(List<QueryParam> query) {
        this.query = query;
    }

    public RequestBodyEx getBody() {
        return body;
    }

    public void setBody(RequestBodyEx body) {
        this.body = body;
    }

    public List<FormParam> getForm() {
        return form;
    }

    public void setForm(List<FormParam> form) {
        this.form = form;
    }

    public List<FormUrlencoded> getFormUrlencoded() {
        return formUrlencoded;
    }

    public void setFormUrlencoded(List<FormUrlencoded> formUrlencoded) {
        this.formUrlencoded = formUrlencoded;
    }

    public List<JsonParam> getJson() {
        return json;
    }

    public void setJson(List<JsonParam> json) {
        this.json = json;
    }

    public RawParam getRaw() {
        return raw;
    }

    public void setRaw(RawParam raw) {
        this.raw = raw;
    }

    public AfterScript getAfterScript() {
        return afterScript;
    }

    public void setAfterScript(AfterScript afterScript) {
        this.afterScript = afterScript;
    }

    public PreScript getPreScript() {
        return preScript;
    }

    public void setPreScript(PreScript preScript) {
        this.preScript = preScript;
    }
}
