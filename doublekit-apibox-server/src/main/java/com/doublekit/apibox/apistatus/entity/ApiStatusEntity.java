package com.doublekit.apibox.apistatus.entity;


import com.doublekit.dal.jpa.mapper.annotation.*;

import java.io.Serializable;

@Entity
@Table(name="apibox_method_status")
public class ApiStatusEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name="id",length = 32)
    private String id;

    @Column(name = "code",length = 32)
    private String code;

    @Column(name = "name",length = 32)
    private String name;

    @Column(name = "type",length = 32)
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

