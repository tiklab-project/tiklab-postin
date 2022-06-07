package com.doublekit.apibox.apitest.http.httpinstance.entity;


import com.doublekit.dal.jpa.annotation.Column;
import com.doublekit.dal.jpa.annotation.Id;
import com.doublekit.dal.jpa.annotation.Table;
import com.doublekit.dal.jpa.annotation.Entity;

import java.io.Serializable;

@Entity @Table(name="apibox_request_instance")
public class RequestInstanceEntity implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "http_instance_id",length = 32,notNull = true)
    private String httpInstanceId;

    @Column(name = "URL",length = 64)
    private String URL;

    @Column(name = "headers",length = 2048)
    private String headers;

    @Column(name = "mediaType",length = 32)
    private String mediaType;

    @Column(name = "body",length = 2048)
    private String body;

    @Column(name = "preScript",length = 2048)
    private String preScript;

    @Column(name = "afterScript",length = 2048)
    private String afterScript;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHttpInstanceId() {
        return httpInstanceId;
    }

    public void setHttpInstanceId(String httpInstanceId) {
        this.httpInstanceId = httpInstanceId;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
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

    public String getAfterScript() {
        return afterScript;
    }

    public void setAfterScript(String afterScript) {
        this.afterScript = afterScript;
    }
}
