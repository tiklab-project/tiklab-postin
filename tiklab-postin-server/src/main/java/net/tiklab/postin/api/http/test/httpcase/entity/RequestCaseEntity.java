package net.tiklab.postin.api.http.test.httpcase.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.Entity;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;

import java.io.Serializable;

@Entity
@Table(name="postin_http_case_request")
public class RequestCaseEntity implements Serializable {

    @Id
    @Column(name = "id",length = 40)
    private String id;

    @Column(name = "http_case_id",length = 32,notNull = true)
    private String httpCaseId;

    @Column(name = "body_type",length = 32)
    private String bodyType;

    @Column(name = "pre_script",length = 2048)
    private String preScript;

    @Column(name = "after_script",length = 2048)
    private String afterScript;

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

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getPreScript() {
        return preScript;
    }

    public void setPreScript(String preScript) {
        this.preScript = preScript;
    }

    public String getAfterScript() {
        return afterScript;
    }

    public void setAfterScript(String afterScript) {
        this.afterScript = afterScript;
    }
}
