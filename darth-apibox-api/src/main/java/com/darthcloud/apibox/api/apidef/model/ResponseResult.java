package com.darthcloud.apibox.api.apidef.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.beans.annotation.Mapping;
import com.darthcloud.beans.annotation.Mappings;
import com.darthcloud.join.annotation.Join;
import com.darthcloud.join.annotation.JoinField;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
public class ResponseResult {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="method",desc="所属接口",required = true)
    @Mappings({
            @Mapping(source = "id",target = "methodId")
    })
    @JoinField(id = "id")
    private MethodEx method;

    @NotNull
    @ApiProperty(name="resultType",desc="返回结果类型,json/raw",required = true)
    private java.lang.String resultType;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public MethodEx getMethod() {
        return method;
    }

    public void setMethod(MethodEx method) {
        this.method = method;
    }

    public java.lang.String getResultType() {
        return resultType;
    }

    public void setResultType(java.lang.String resultType) {
        this.resultType = resultType;
    }
}
