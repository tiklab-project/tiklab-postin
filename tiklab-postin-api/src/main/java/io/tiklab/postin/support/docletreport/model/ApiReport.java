package io.tiklab.postin.support.docletreport.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.api.apix.model.ApiRequest;
import io.tiklab.postin.api.apix.model.RawParam;
import io.tiklab.postin.api.http.definition.model.*;

import java.util.List;

@ApiModel
public class ApiReport {

    private String apiId;

    private HttpApi apiBase;

    private ApiRequest request;

    private List<FormParam> formList;

    private List<FormUrlencoded> formUrlList;

    private RawParam raw;

    private ApiResponse response;

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public HttpApi getApiBase() {
        return apiBase;
    }

    public void setApiBase(HttpApi apiBase) {
        this.apiBase = apiBase;
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

    public RawParam getRaw() {
        return raw;
    }

    public void setRaw(RawParam raw) {
        this.raw = raw;
    }

    public ApiResponse getResponse() {
        return response;
    }

    public void setResponse(ApiResponse response) {
        this.response = response;
    }
}

