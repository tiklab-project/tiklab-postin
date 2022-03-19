package com.doublekit.apibox.apidef.http.entity;


import com.doublekit.dal.jpa.annotation.*;

import java.io.Serializable;


@Entity
@Table(name="apibox_api_http")
public class HttpApiEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 40)
    private String id;

    @Column(name = "apix_id",length = 40)
    private String apixId;

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
}
