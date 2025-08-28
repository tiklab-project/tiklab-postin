package io.tiklab.postin.autotest.http.unit.cases.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接口单元用例数据封装 模型
 */
@ApiModel
public class ApiUnitCaseDataConstruction extends BaseModel{

    private List<RequestHeaderUnit> headerList;

    //查询参数
    private List<QueryParamUnit> queryParamList;

    // 请求体类型
    private RequestBodyUnit requestBodyUnit;
    //mediaType
    private Map mediaTypeMap;

    //封装请求体数据
    private List<FormParamUnit> formList;
    private List<FormUrlEncodedUnit> urlencodedList;
    private JsonParamUnit jsonParam;
    private String jsonStr;
    private RawParamUnit rawParam;

    //调用前置脚本
    private List<PreParamUnit> preParamUnitList;

    //调用后置脚本
    private List<AfterParamUnit> afterParamUnitList;

    private List<HashMap<String, Object>> assertList;

    public List<RequestHeaderUnit> getHeaderList() {
        return headerList;
    }

    public void setHeaderList(List<RequestHeaderUnit> headerList) {
        this.headerList = headerList;
    }

    public List<QueryParamUnit> getQueryParamList() {
        return queryParamList;
    }

    public void setQueryParamList(List<QueryParamUnit> queryParamList) {
        this.queryParamList = queryParamList;
    }

    public Map getMediaTypeMap() {
        return mediaTypeMap;
    }

    public void setMediaTypeMap(Map mediaTypeMap) {
        this.mediaTypeMap = mediaTypeMap;
    }

    public RequestBodyUnit getRequestBodyUnit() {
        return requestBodyUnit;
    }

    public void setRequestBodyUnit(RequestBodyUnit requestBodyUnit) {
        this.requestBodyUnit = requestBodyUnit;
    }

    public JsonParamUnit getJsonParam() {
        return jsonParam;
    }

    public void setJsonParam(JsonParamUnit jsonParam) {
        this.jsonParam = jsonParam;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public RawParamUnit getRawParam() {
        return rawParam;
    }

    public void setRawParam(RawParamUnit rawParam) {
        this.rawParam = rawParam;
    }

    public List<FormParamUnit> getFormList() {
        return formList;
    }

    public void setFormList(List<FormParamUnit> formList) {
        this.formList = formList;
    }

    public List<FormUrlEncodedUnit> getUrlencodedList() {
        return urlencodedList;
    }

    public void setUrlencodedList(List<FormUrlEncodedUnit> urlencodedList) {
        this.urlencodedList = urlencodedList;
    }



    public List<PreParamUnit> getPreParamUnitList() {
        return preParamUnitList;
    }

    public void setPreParamUnitList(List<PreParamUnit> preParamUnitList) {
        this.preParamUnitList = preParamUnitList;
    }

    public List<AfterParamUnit> getAfterParamUnitList() {
        return afterParamUnitList;
    }

    public void setAfterParamUnitList(List<AfterParamUnit> afterParamUnitList) {
        this.afterParamUnitList = afterParamUnitList;
    }

    public List<HashMap<String, Object>> getAssertList() {
        return assertList;
    }

    public void setAssertList(List<HashMap<String, Object>> assertList) {
        this.assertList = assertList;
    }
}
