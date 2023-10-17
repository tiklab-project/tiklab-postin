package io.tiklab.postin.api.ws.ws.model;

import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.api.apix.model.*;

import java.util.List;

/**
 * ws 特有字段模型
 */
@ApiModel
@Join
@Mapper
public class WSApi extends BaseModel {

    @ApiProperty(name="apix",desc="所属接口公共定义")
    @Mappings({
            @Mapping(source = "apix.id",target = "apixId")
    })
    @JoinQuery(key = "id")
    private Apix apix;


    @ApiProperty(name = "headerList",desc="请求头列表")
    private List<RequestHeader> headerList;

    @ApiProperty(name = "queryList",desc="查询参数列表")
    private List<QueryParam> queryList;

    @ApiProperty(name = "request",desc="请求")
    private ApiRequest request;

    @ApiProperty(name = "jsonParam",desc="json")
    private JsonParam jsonParam;

    @ApiProperty(name = "raw",desc="raw")
    private RawParam rawParam;


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

    public JsonParam getJsonParam() {
        return jsonParam;
    }

    public void setJsonParam(JsonParam jsonParam) {
        this.jsonParam = jsonParam;
    }

    public RawParam getRawParam() {
        return rawParam;
    }

    public void setRawParam(RawParam rawParam) {
        this.rawParam = rawParam;
    }
}
