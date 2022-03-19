package com.doublekit.apibox.apidef.http.entity;

import com.doublekit.dal.jpa.annotation.*;

import java.io.Serializable;

@Entity
@Table(name = "apibox_binary_param")
public class BinaryParamEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id", length = 40)
    private String id;

    @Column(name = "http_id",length = 40,notNull = true)
    private String httpId;

    @Column(name = "filename",length = 64, notNull = true)
    private String fileName;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
