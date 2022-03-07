package com.doublekit.apibox.apidef.model;

import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.apibox.annotation.ApiModel;

@ApiModel
public class ResponseResultQuery {

    @ApiProperty(name ="methodId",desc = "接口ID，精确匹配")
    private String methodId;

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }


}