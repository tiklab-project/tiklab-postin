package net.tiklab.postin.integration.sendrequest.model;

import net.tiklab.postin.annotation.ApiModel;
import org.springframework.http.HttpHeaders;

@ApiModel
public class HttpRequest {

    String url;

    String method;

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
