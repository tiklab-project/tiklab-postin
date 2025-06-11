package io.tiklab.postin.client.openapi;

import java.util.HashMap;

public class ProcessParamConfig {

    private String prePath;

    private HashMap<String,String> headers = new HashMap<>();

    public String getPrePath() {
        return prePath;
    }

    public void setPrePath(String prePath) {
        this.prePath = prePath;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }
}
