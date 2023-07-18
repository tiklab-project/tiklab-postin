package io.tiklab.postin.api.http.definition.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;


import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;

import java.util.List;

/**
 * http 特有字段模型
 */
@ApiModel
@Join
@Mapper
public class HttpApi extends BaseModel {

    @ApiProperty(name="id",desc="唯一ID")
    //@IndexId
    //@IndexField
    private java.lang.String id;

    @ApiProperty(name="apix",desc="所属接口公共定义",eg="@selectOne",required = true)
    @Mappings({
            @Mapping(source = "apix.id",target = "apixId")
    })
    @JoinQuery(key = "id")
    private Apix apix;

    @ApiProperty(name="path",desc="路径",required = true)
    private String path;

    @ApiProperty(name="methodType",desc="请求类型",required = true)
    private String methodType;

    @ApiProperty(name = "headerList",desc="请求头列表")
    private List<RequestHeader> headerList;

    @ApiProperty(name = "queryList",desc="查询参数列表")
    private List<QueryParam> queryList;

    @ApiProperty(name = "request",desc="请求")
    private ApiRequest request;

    @ApiProperty(name = "formParamList",desc="form-data列表")
    private List<FormParam> formList;

    @ApiProperty(name = "formUrlencodedList",desc="form-url列表")
    private List<FormUrlencoded> urlencodedList;

    @ApiProperty(name = "jsonParamList",desc="json列表")
    private List<JsonParam> jsonList;

    @ApiProperty(name = "raw",desc="raw")
    private RawParam rawParam;

    @ApiProperty(name = "responseHeaderList",desc="响应头列表")
    private List<ResponseHeader> responseHeaderList;

    @ApiProperty(name = "responseResultList",desc="响应结果列表")
    private List<ApiResponse> responseResultList;


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

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

    public List<RequestHeader> getHeaderList() {
        return headerList;
    }

    public void setHeaderList(List<RequestHeader> headerList) {
        this.headerList = headerList;
    }

    public List<QueryParam> getQueryList() {
        return queryList;
    }

    public void setQueryList(List<QueryParam> queryList) {
        this.queryList = queryList;
    }

    public ApiRequest getRequest() {
        return request;
    }

    public void setRequest(ApiRequest request) {
        this.request = request;
    }

    public List<FormParam> getFormList() {
        return formList;
    }

    public void setFormList(List<FormParam> formList) {
        this.formList = formList;
    }

    public List<FormUrlencoded> getUrlencodedList() {
        return urlencodedList;
    }

    public void setUrlencodedList(List<FormUrlencoded> urlencodedList) {
        this.urlencodedList = urlencodedList;
    }

    public List<JsonParam> getJsonList() {
        return jsonList;
    }

    public void setJsonList(List<JsonParam> jsonList) {
        this.jsonList = jsonList;
    }

    public RawParam getRawParam() {
        return rawParam;
    }

    public void setRawParam(RawParam rawParam) {
        this.rawParam = rawParam;
    }

    public List<ResponseHeader> getResponseHeaderList() {
        return responseHeaderList;
    }

    public void setResponseHeaderList(List<ResponseHeader> responseHeaderList) {
        this.responseHeaderList = responseHeaderList;
    }

    public List<ApiResponse> getResponseResultList() {
        return responseResultList;
    }

    public void setResponseResultList(List<ApiResponse> responseResultList) {
        this.responseResultList = responseResultList;
    }
}
