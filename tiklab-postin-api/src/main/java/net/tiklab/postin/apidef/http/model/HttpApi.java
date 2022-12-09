package net.tiklab.postin.apidef.http.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.postin.apidef.apix.model.Apix;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.dss.annotation.Index;
import net.tiklab.dss.annotation.IndexField;
import net.tiklab.dss.annotation.IndexId;
import net.tiklab.join.annotation.Join;
import net.tiklab.join.annotation.JoinQuery;

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
    private Apix apix;

    @ApiProperty(name="path",desc="路径",required = true)
    private String path;

    @ApiProperty(name="methodType",desc="请求类型",required = true)
    private String methodType;

    @ApiProperty(name = "headerList")
    private List<RequestHeader> headerList;

    @ApiProperty(name = "queryList")
    private List<QueryParam> queryList;

    @ApiProperty(name = "requestBody")
    private ApiRequest request;

    @ApiProperty(name = "formParamList")
    private List<FormParam> formList;

    @ApiProperty(name = "formUrlencodedList")
    private List<FormUrlencoded> urlencodedList;

    @ApiProperty(name = "jsonParamList")
    private List<JsonParam> jsonList;

    @ApiProperty(name = "raw")
    private RawParam rawParam;

    @ApiProperty(name = "responseHeaderList")
    private List<ResponseHeader> responseHeaderList;

    @ApiProperty(name = "responseResultList")
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
