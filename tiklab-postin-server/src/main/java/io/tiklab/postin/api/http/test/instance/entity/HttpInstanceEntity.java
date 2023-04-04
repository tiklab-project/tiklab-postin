package io.tiklab.postin.api.http.test.instance.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;
import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity @Table(name="postin_instance")
public class HttpInstanceEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 40)
    private String id;

    @Column(name = "http_case_id",length = 40)
    private String httpCaseId;

    @Column(name = "workspace_id",length = 32)
    private String workspaceId;

    @Column(name = "user_id",length = 32)
    private String userId;

    @Column(name = "status_code")
    private Integer statusCode;

    @Column(name = "result")
    private Integer result;

    @Column(name = "time")
    private Integer time;

    @Column(name = "size")
    private Integer size;

    @Column(name = "error_message")
    private String errorMessage;


    @Column(name = "create_time",length = 4)
    private Timestamp createTime;

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

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
