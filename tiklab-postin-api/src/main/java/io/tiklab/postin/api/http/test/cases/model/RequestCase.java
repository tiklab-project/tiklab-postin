package io.tiklab.postin.api.http.test.cases.model;


import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

@ApiModel
@Mapper
public class RequestCase extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="httpCaseId",desc="httpCaseId",required = true)
    private java.lang.String httpCaseId;

    @ApiProperty(name="bodyType",desc="bodyType")
    private java.lang.String bodyType;

    @ApiProperty(name="preScript",desc="前置")
    private java.lang.String preScript;

    @ApiProperty(name="afterScript",desc="后置")
    private java.lang.String afterScript;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getHttpCaseId() {
        return httpCaseId;
    }

    public void setHttpCaseId(java.lang.String httpCaseId) {
        this.httpCaseId = httpCaseId;
    }
    public java.lang.String getBodyType() {
        return bodyType;
    }

    public void setBodyType(java.lang.String bodyType) {
        this.bodyType = bodyType;
    }
    public java.lang.String getPreScript() {
        return preScript;
    }

    public void setPreScript(java.lang.String preScript) {
        this.preScript = preScript;
    }
    public java.lang.String getAfterScript() {
        return afterScript;
    }

    public void setAfterScript(java.lang.String afterScript) {
        this.afterScript = afterScript;
    }
}
