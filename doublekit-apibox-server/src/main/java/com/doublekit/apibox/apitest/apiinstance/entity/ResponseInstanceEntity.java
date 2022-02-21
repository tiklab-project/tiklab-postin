package com.doublekit.apibox.apitest.apiinstance.entity;


import com.doublekit.dal.jpa.mapper.annotation.Column;
import com.doublekit.dal.jpa.mapper.annotation.Id;
import com.doublekit.dal.jpa.mapper.annotation.Table;import com.doublekit.dal.jpa.mapper.annotation.Entity;

import java.io.Serializable;

@Entity @Table(name="apibox_response_instance")
public class ResponseInstanceEntity implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "instance_id",length = 32,notNull = true)
    private String instanceId;

    @Column(name = "response_header",length = 2048,notNull = true)
    private String responseHeader;

    @Column(name = "response_body",length = 2048,notNull = true)
    private String responseBody;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(String responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}
