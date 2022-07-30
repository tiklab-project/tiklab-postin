package com.tiklab.postin.apidef.http.model;

import com.tiklab.postin.annotation.ApiProperty;
import com.tiklab.postin.annotation.ApiModel;

@ApiModel
public class ResponseResultQuery {

    @ApiProperty(name ="methodId",desc = "接口ID，精确匹配")
    private String httpId;

    public String getHttpId() {
        return httpId;
    }

    public void setHttpId(String httpId) {
        this.httpId = httpId;
    }
}