package com.tiklab.postlink.apitest.http.httpcase.entity;


import com.tiklab.dal.jpa.annotation.Column;
import com.tiklab.dal.jpa.annotation.Id;
import com.tiklab.dal.jpa.annotation.Table;import com.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

@Entity @Table(name="postlink_after_script_testcase")
public class AfterScriptCaseEntity implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "http_case_id",length = 32,notNull = true)
    private String httpCaseId;

    @Column(name = "script",length = 2048,notNull = true)
    private String scriptex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHttpCaseId() {
        return httpCaseId;
    }

    public void setHttpCaseId(String httpCaseId) {
        this.httpCaseId = httpCaseId;
    }

    public String getScriptex() {
        return scriptex;
    }

    public void setScriptex(String scriptex) {
        this.scriptex = scriptex;
    }
}
