package io.tiklab.postin.quicktest.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.api.http.definition.model.*;

import java.util.List;

@ApiModel
public class SaveToApi extends BaseModel {

    @ApiProperty(name="httpApi",desc="接口名")
    private HttpApi httpApi;

    @ApiProperty(name = "headerList",desc="请求头列表")
    private List<RequestHeaders> headerList;

    @ApiProperty(name = "queryList",desc="查询参数列表")
    private List<QueryParam> queryList;

    @ApiProperty(name = "request",desc="请求")
    private ApiRequest request;

    @ApiProperty(name = "formParamList",desc="form-data列表")
    private List<FormParam> formList;

    @ApiProperty(name = "formUrlList",desc="form-url列表")
    private List<FormUrlencoded> formUrlList;

    @ApiProperty(name = "raw",desc="raw")
    private RawParams raw;



    public HttpApi getHttpApi() {
        return httpApi;
    }

    public void setHttpApi(HttpApi httpApi) {
        this.httpApi = httpApi;
    }


    public List<RequestHeaders> getHeaderList() {
        return headerList;
    }

    public void setHeaderList(List<RequestHeaders> headerList) {
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

    public List<FormUrlencoded> getFormUrlList() {
        return formUrlList;
    }

    public void setFormUrlList(List<FormUrlencoded> formUrlList) {
        this.formUrlList = formUrlList;
    }

    public RawParams getRaw() {
        return raw;
    }

    public void setRaw(RawParams raw) {
        this.raw = raw;
    }
}
