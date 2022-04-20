package com.doublekit.apiboxpiugin.version.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.apibox.apidef.http.model.JsonResponse;
import com.doublekit.apibox.apidef.http.model.RawResponse;
import com.doublekit.apibox.apidef.http.model.ResponseHeader;
import com.doublekit.apibox.apidef.http.model.ResponseResult;
import com.doublekit.dss.annotation.IndexField;
import com.doublekit.dss.annotation.IndexQueryField;
import com.doublekit.join.annotation.Join;

import java.util.List;


@ApiModel
@Join
public class VersionRespon {

    @ApiProperty(name="responseHeader",desc="响应头部信息",required = true)
    @IndexField
    @IndexQueryField
    private List<ResponseHeader> responseHeaderList;

    @ApiProperty(name="responseResult",desc="响应结果类型",required = true)
    @IndexField
    @IndexQueryField
    private List<ResponseResult> responseResultList;

    @ApiProperty(name="jsonResponse",desc="json响应结果信息",required = true)
    @IndexField
    @IndexQueryField
    private List<JsonResponse> jsonResponseList;

    @ApiProperty(name="rawResponse",desc="raw响应结果信息",required = true)
    @IndexField
    @IndexQueryField
    private List<RawResponse> rawResponseList;

    public List<ResponseHeader> getResponseHeaderList() {
        return responseHeaderList;
    }

    public void setResponseHeaderList(List<ResponseHeader> responseHeaderList) {
        this.responseHeaderList = responseHeaderList;
    }

    public List<ResponseResult> getResponseResultList() {
        return responseResultList;
    }

    public void setResponseResultList(List<ResponseResult> responseResultList) {
        this.responseResultList = responseResultList;
    }

    public List<JsonResponse> getJsonResponseList() {
        return jsonResponseList;
    }

    public void setJsonResponseList(List<JsonResponse> jsonResponseList) {
        this.jsonResponseList = jsonResponseList;
    }

    public List<RawResponse> getRawResponseList() {
        return rawResponseList;
    }

    public void setRawResponseList(List<RawResponse> rawResponseList) {
        this.rawResponseList = rawResponseList;
    }
}
