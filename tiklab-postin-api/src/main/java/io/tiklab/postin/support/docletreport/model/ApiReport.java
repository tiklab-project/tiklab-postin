package io.tiklab.postin.support.docletreport.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.api.http.definition.model.*;

import java.util.List;

@ApiModel
public class ApiReport {

    private String apiId;

    private HttpApi httpApi;

    private ApiRequest request;

    private List<FormParam> formList;

    private List<FormUrlencoded> formUrlList;

    private RawParam raw;

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public HttpApi getHttpApi() {
        return httpApi;
    }

    public void setHttpApi(HttpApi httpApi) {
        this.httpApi = httpApi;
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
}
