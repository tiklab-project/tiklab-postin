package com.tiklab.postlink.apitest.http.httpcase.entity;

import com.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

@Entity
@Table(name = "postlink_binary_param_testcase")
public class BinaryParamCaseEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id", length = 32)
    private String id;

    @Column(name = "http_case_id",length = 32,notNull = true)
    private String httpCaseId;

    @Column(name = "filename",length = 64, notNull = true)
    private String fileName;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
