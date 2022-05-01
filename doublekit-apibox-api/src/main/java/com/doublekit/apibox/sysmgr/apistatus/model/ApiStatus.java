package com.doublekit.apibox.sysmgr.apistatus.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.beans.annotation.Mapper;
import com.doublekit.core.BaseModel;

@ApiModel
@Mapper(targetAlias = "ApiStatusEntity")
public class ApiStatus extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="code",desc="code")
    private java.lang.String code;

    @ApiProperty(name="name",desc="name")
    private java.lang.String name;

    @ApiProperty(name="type",desc="类型")
    private java.lang.String type;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public java.lang.String getCode() {
        return code;
    }

    public void setCode(java.lang.String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
