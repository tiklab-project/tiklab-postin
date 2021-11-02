package com.doublekit.apibox.apitest.entity;


import com.doublekit.dal.jpa.mapper.annotation.Column;
import com.doublekit.dal.jpa.mapper.annotation.Id;
import com.doublekit.dal.jpa.mapper.annotation.Table;

import java.io.Serializable;

@Table(name="apibox_request_instance")
public class RequestInstanceEntity implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "instance_id",length = 32,notNull = true)
    private String instanceId;

    @Column(name = "request_base",length = 2048,notNull = true)
    private String requestBase;

    @Column(name = "request_header",length = 2048,notNull = true)
    private String requestHeader;

    @Column(name = "request_param",length = 2048,notNull = true)
    private String requestParam;

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

    public String getRequestBase() {
        return requestBase;
    }

    public void setRequestBase(String requestBase) {
        this.requestBase = requestBase;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }
}