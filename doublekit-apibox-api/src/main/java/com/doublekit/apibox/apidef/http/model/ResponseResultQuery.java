package com.doublekit.apibox.apidef.http.model;

import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.apibox.annotation.ApiModel;

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