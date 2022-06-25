package com.doublekit.apibox.integration.version.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.apibox.apidef.http.model.JsonResponse;
import com.doublekit.apibox.apidef.http.model.RawResponse;
import com.doublekit.apibox.apidef.http.model.ResponseHeader;
import com.doublekit.apibox.apidef.http.model.ResponseResult;
import com.doublekit.core.BaseModel;
import com.doublekit.dss.annotation.IndexField;
import com.doublekit.dss.annotation.IndexQueryField;
import com.doublekit.join.annotation.Join;

import java.util.List;


@ApiModel
@Join
public class ResponseInfo extends BaseModel {

    @ApiProperty(name="responseHeader",desc="响应头部信息",required = true)
    private List<ResponseHeader> headers;

    @ApiProperty(name="responseResult",desc="响应结果类型",required = true)
    private ResponseResult body;

    @ApiProperty(name="jsonResponse",desc="json响应结果信息",required = true)
    private List<JsonResponse> json;

    @ApiProperty(name="rawResponse",desc="raw响应结果信息",required = true)
    private RawResponse  raw;

    public List<ResponseHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(List<ResponseHeader> headers) {
        this.headers = headers;
    }

    public ResponseResult getBody() {
        return body;
    }

    public void setBody(ResponseResult body) {
        this.body = body;
    }

    public List<JsonResponse> getJson() {
        return json;
    }

    public void setJson(List<JsonResponse> json) {
        this.json = json;
    }

    public RawResponse getRaw() {
        return raw;
    }

    public void setRaw(RawResponse raw) {
        this.raw = raw;
    }
}
