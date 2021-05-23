package com.darthcloud.apibox.apimock.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.apibox.apidef.model.MethodEx;
import com.darthcloud.dsl.beans.annotation.Mapping;
import com.darthcloud.dsl.beans.annotation.Mappings;
import com.darthcloud.dsl.join.annotation.JoinField;

import javax.validation.constraints.NotNull;

@ApiModel
public class Mock {

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
    @ApiProperty(name="name",desc="mock名称",required = true)
    private java.lang.String name;

    @ApiProperty(name="desc",desc="描述")
    private java.lang.String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MethodEx getMethod() {
        return method;
    }

    public void setMethod(MethodEx method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
