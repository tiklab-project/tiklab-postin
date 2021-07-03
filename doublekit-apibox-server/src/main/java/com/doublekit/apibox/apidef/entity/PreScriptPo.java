package com.doublekit.apibox.apidef.entity;


import com.doublekit.dal.jpa.annotation.mapper.Column;
import com.doublekit.dal.jpa.annotation.mapper.Id;
import com.doublekit.dal.jpa.annotation.mapper.Table;

import java.io.Serializable;

@Table(name="apibox_pre_script")
public class PreScriptPo implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "method_id",length = 32,notNull = true)
    private String methodId;

    @Column(name = "script",length = 2048,notNull = true)
    private String scriptex;

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

    public String getScriptex() {
        return scriptex;
    }

    public void setScriptex(String scriptex) {
        this.scriptex = scriptex;
    }
}
