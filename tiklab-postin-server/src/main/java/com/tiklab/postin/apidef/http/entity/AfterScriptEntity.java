package com.tiklab.postin.apidef.http.entity;


import com.tiklab.dal.jpa.annotation.Column;
import com.tiklab.dal.jpa.annotation.Id;
import com.tiklab.dal.jpa.annotation.Table;import com.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

@Entity
@Table(name="postin_after_script")
public class AfterScriptEntity implements Serializable {

    @Id
    @Column(name = "id",length = 40)
    private String id;

    @Column(name = "http_id",length = 40,notNull = true)
    private String httpId;

    @Column(name = "script",length = 2048,notNull = true)
    private String scriptex;

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

    public String getScriptex() {
        return scriptex;
    }

    public void setScriptex(String scriptex) {
        this.scriptex = scriptex;
    }

    @Override
    public String toString() {
        return "AfterScriptEntity{" +
                "id='" + id + '\'' +
                ", httpId='" + httpId + '\'' +
                ", scriptex='" + scriptex + '\'' +
                '}';
    }
}
