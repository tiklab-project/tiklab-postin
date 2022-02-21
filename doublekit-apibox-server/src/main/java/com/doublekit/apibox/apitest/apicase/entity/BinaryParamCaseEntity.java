package com.doublekit.apibox.apitest.apicase.entity;

import com.doublekit.dal.jpa.mapper.annotation.*;

import java.io.Serializable;

@Entity
@Table(name = "apibox_binary_param_testcase")
public class BinaryParamCaseEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id", length = 32)
    private String id;

    @Column(name = "testcase_id",length = 32,notNull = true)
    private String testcaseId;

    @Column(name = "filename",length = 64, notNull = true)
    private String fileName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestcaseId() {
        return testcaseId;
    }

    public void setTestcaseId(String testcaseId) {
        this.testcaseId = testcaseId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
