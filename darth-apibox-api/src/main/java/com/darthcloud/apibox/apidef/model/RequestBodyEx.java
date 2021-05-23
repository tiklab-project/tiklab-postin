package com.darthcloud.apibox.apidef.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.dsl.beans.annotation.Mapping;
import com.darthcloud.dsl.beans.annotation.Mappings;
import com.darthcloud.dsl.join.annotation.Join;
import com.darthcloud.dsl.join.annotation.JoinField;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
public class RequestBodyEx {

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
    @ApiProperty(name="bodyType",desc="请求体类型，form/json/raw/binary",required = true)
    private java.lang.String bodyType;

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

    public java.lang.String getBodyType() {
        return bodyType;
    }

    public void setBodyType(java.lang.String bodyType) {
        this.bodyType = bodyType;
    }
}
