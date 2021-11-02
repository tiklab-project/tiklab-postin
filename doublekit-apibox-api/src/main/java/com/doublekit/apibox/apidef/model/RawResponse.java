package com.doublekit.apibox.apidef.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.beans.annotation.Mapper;
import com.doublekit.beans.annotation.Mapping;
import com.doublekit.beans.annotation.Mappings;
import com.doublekit.common.BaseModel;
import com.doublekit.join.annotation.Join;
import com.doublekit.join.annotation.JoinField;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
public class RawResponse extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识，非自动生成")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="method",desc="所属接口",required = true)
    @Mappings({
            @Mapping(source = "method.id",target = "methodId")
    })
    @JoinField(id = "id")
    private MethodEx method;

    @NotNull
    @ApiProperty(name="raw",desc="raw自定义文本",required = true)
    private java.lang.String raw;

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

    public java.lang.String getRaw() {
        return raw;
    }

    public void setRaw(java.lang.String raw) {
        this.raw = raw;
    }
}
