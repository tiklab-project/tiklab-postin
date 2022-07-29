package com.tiklab.postlink.apidef.http.entity;


import com.tiklab.dal.jpa.annotation.Column;
import com.tiklab.dal.jpa.annotation.Id;
import com.tiklab.dal.jpa.annotation.Table;import com.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

@Entity
@Table(name="postlink_request_body")
public class RequestBodyEntity implements Serializable {

    @Id
    @Column(name = "id",length = 40)
    private String id;

    @Column(name = "http_id",length = 40,notNull = true)
    private String httpId;

    @Column(name = "body_type",length = 32,notNull = true)
    private String bodyType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHttpId() {
        return httpId;
    }

    public void setHttpId(String httpId) {
        this.httpId = httpId;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}
