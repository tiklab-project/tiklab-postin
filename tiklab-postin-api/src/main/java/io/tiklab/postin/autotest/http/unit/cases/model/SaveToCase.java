package io.tiklab.postin.autotest.http.unit.cases.model;

import io.tiklab.postin.annotation.ApiModel;

import java.util.List;

@ApiModel
public class SaveToCase {

    private ApiUnitCase apiUnitCase;

    private List<RequestHeaderUnit> headerList;

    private List<QueryParamUnit> queryList;

    private String bodyType;

    private List<FormParamUnit> formDataList;

    private List<FormUrlEncodedUnit> formUrlList;

    private String json;

    private RawParamUnit rawParam;

    private List<PreParamUnit> preParamList;

    private List<AfterParamUnit> afterParamList;

    private List<AssertUnit> assertList;


    public ApiUnitCase getApiUnitCase() {
        return apiUnitCase;
    }

    public void setApiUnitCase(ApiUnitCase apiUnitCase) {
        this.apiUnitCase = apiUnitCase;
    }

    public List<RequestHeaderUnit> getHeaderList() {
        return headerList;
    }

    public void setHeaderList(List<RequestHeaderUnit> headerList) {
        this.headerList = headerList;
    }

    public List<QueryParamUnit> getQueryList() {
        return queryList;
    }

    public void setQueryList(List<QueryParamUnit> queryList) {
        this.queryList = queryList;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public List<FormParamUnit> getFormDataList() {
        return formDataList;
    }

    public void setFormDataList(List<FormParamUnit> formDataList) {
        this.formDataList = formDataList;
    }

    public List<FormUrlEncodedUnit> getFormUrlList() {
        return formUrlList;
    }

    public void setFormUrlList(List<FormUrlEncodedUnit> formUrlList) {
        this.formUrlList = formUrlList;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public RawParamUnit getRawParam() {
        return rawParam;
    }

    public void setRawParam(RawParamUnit rawParam) {
        this.rawParam = rawParam;
    }

    public List<PreParamUnit> getPreParamList() {
        return preParamList;
    }

    public void setPreParamList(List<PreParamUnit> preParamList) {
        this.preParamList = preParamList;
    }

    public List<AfterParamUnit> getAfterParamList() {
        return afterParamList;
    }

    public void setAfterParamList(List<AfterParamUnit> afterParamList) {
        this.afterParamList = afterParamList;
    }

    public List<AssertUnit> getAssertList() {
        return assertList;
    }

    public void setAssertList(List<AssertUnit> assertList) {
        this.assertList = assertList;
    }
}
