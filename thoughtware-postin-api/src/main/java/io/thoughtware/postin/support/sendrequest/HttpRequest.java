package io.thoughtware.postin.support.sendrequest;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import org.springframework.http.HttpHeaders;

/**
 * http发送请求 模型
 */
@ApiModel
public class HttpRequest {

    @ApiProperty(name="url",desc="请求前置url")
    String url;

    @ApiProperty(name="method",desc="请求类型")
    String method;

    @ApiProperty(name="method",desc="请求头")
    HttpHeaders headers;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }
}
