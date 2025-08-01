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

    //请求头
    private Map headerMap;

    //查询参数
    private List<QueryParamUnit> queryParamList;

    //mediaType
    private Map mediaTypeMap;

    //封装请求体数据
    private String body;

    //调用前置脚本
    private String preScript;

    //调用后置脚本
    private String afterScript;

    private List<HashMap<String, Object>> assertList;

    public Map getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map headerMap) {
        this.headerMap = headerMap;
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

    public String getAfterScript() {
        return afterScript;
    }

    public void setAfterScript(String afterScript) {
        this.afterScript = afterScript;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPreScript() {
        return preScript;
    }

    public void setPreScript(String preScript) {
        this.preScript = preScript;
    }

    public List<HashMap<String, Object>> getAssertList() {
        return assertList;
    }

    public void setAssertList(List<HashMap<String, Object>> assertList) {
        this.assertList = assertList;
    }
}
