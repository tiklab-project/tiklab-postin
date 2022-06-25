package com.doublekit.apibox.apidef.http.entity;


import com.doublekit.dal.jpa.annotation.*;

import java.io.Serializable;


@Entity
@Table(name="apibox_api_http")
public class HttpApiEntity implements Serializable {

    @Id
    @Column(name = "id",length = 40)
    private String id;

    @Column(name = "apix_id",length = 40)
    private String apixId;

    @Column(name = "path",length = 256,notNull = true)
    private String path;

    @Column(name = "request_type",length = 32,notNull = true)
    private String requestType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApixId() {
        return apixId;
    }

    public void setApixId(String apixId) {
        this.apixId = apixId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
