package net.tiklab.postin.api.http.test.httpinstance.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;import net.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

@Entity @Table(name="postin_http_case_instance_response")
public class ResponseInstanceEntity implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "http_instance_id",length = 32,notNull = true)
    private String httpInstanceId;

    @Column(name = "headers")
    private String headers;

    @Column(name = "body")
    private String body;

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

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
