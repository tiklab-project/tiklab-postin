package com.doublekit.apibox.apidef.entity;

import com.doublekit.dal.jpa.mapper.annotation.*;

import java.io.Serializable;

@Entity
@Table(name = "apibox_binary_param")
public class BinaryParamEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id", length = 32)
    private String id;

    @Column(name = "method_id",length = 32,notNull = true)
    private String methodId;

    @Column(name = "filename",length = 64, notNull = true)
    private String fileName;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
