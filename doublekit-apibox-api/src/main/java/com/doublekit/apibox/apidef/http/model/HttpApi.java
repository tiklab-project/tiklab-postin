package com.doublekit.apibox.apidef.http.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.apibox.apidef.apix.model.Apix;
import com.doublekit.beans.annotation.Mapper;
import com.doublekit.beans.annotation.Mapping;
import com.doublekit.beans.annotation.Mappings;
import com.doublekit.common.BaseModel;
import com.doublekit.dss.annotation.Index;
import com.doublekit.dss.annotation.IndexField;
import com.doublekit.dss.annotation.IndexId;
import com.doublekit.join.annotation.Join;
import com.doublekit.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel
@Join
@Index
@Mapper(targetAlias = "HttpApiEntity")
public class HttpApi extends BaseModel {

    @ApiProperty(name="id",desc="唯一ID")
    @IndexId
    @IndexField
    private java.lang.String id;

    @ApiProperty(name="apix",desc="所属定义",eg="@selectOne",required = true)
    @Mappings({
            @Mapping(source = "apix.id",target = "apixId")
    })
    @JoinQuery(key = "id")
    @IndexField
    private Apix apix;

    @ApiProperty(name = "headerList")
    private List<RequestHeader> requestHeaderList;

    @ApiProperty(name = "queryList")
    private List<QueryParam> queryParamList;

    @ApiProperty(name = "requestBody")
    private RequestBodyEx requestBody;

    @ApiProperty(name = "formParamList")
    private List<FormParam> formParamList;

    @ApiProperty(name = "formUrlencodedList")
    private List<FormUrlencoded> formUrlencodedList;

    @ApiProperty(name = "jsonParamList")
    private List<JsonParam> jsonParamList;

    @ApiProperty(name = "raw")
    private RawParam rawParam;

    @ApiProperty(name = "preScript")
    private PreScript preScript;

    @ApiProperty(name = "afterScript")
    private AfterScript afterScript;


    public String getId() {
        return id;
    }

    public HttpApi setId(String id) {
        this.id = id;
        return this;
    }

    public Apix getApix() {
        return apix;
    }

    public void setApix(Apix apix) {
        this.apix = apix;
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

    public RequestBodyEx getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(RequestBodyEx requestBody) {
        this.requestBody = requestBody;
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

    public PreScript getPreScript() {
        return preScript;
    }

    public void setPreScript(PreScript preScript) {
        this.preScript = preScript;
    }

    public AfterScript getAfterScript() {
        return afterScript;
    }

    public void setAfterScript(AfterScript afterScript) {
        this.afterScript = afterScript;
    }
}
