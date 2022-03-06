package com.doublekit.apibox.apidef.entity;


import com.doublekit.dal.jpa.annotation.Column;
import com.doublekit.dal.jpa.annotation.Id;
import com.doublekit.dal.jpa.annotation.Table;import com.doublekit.dal.jpa.annotation.Entity;

import java.io.Serializable;

@Entity
@Table(name="apibox_request_body")
public class RequestBodyEntity implements Serializable {

    @Id
    @Column(name = "id",length = 40)
    private String id;

    @Column(name = "method_id",length = 40,notNull = true)
    private String methodId;

    @Column(name = "body_type",length = 32,notNull = true)
    private String bodyType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}
