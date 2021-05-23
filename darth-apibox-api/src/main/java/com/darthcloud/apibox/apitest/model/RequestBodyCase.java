package com.darthcloud.apibox.apitest.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.dsl.beans.annotation.Mapping;
import com.darthcloud.dsl.beans.annotation.Mappings;
import com.darthcloud.dsl.join.annotation.Join;
import com.darthcloud.dsl.join.annotation.JoinField;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
public class RequestBodyCase {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="mock",desc="所属接口用例",required = true)
    @Mappings({
            @Mapping(source = "id",target = "testcaseId")
    })
    @JoinField(id = "id")
    private Testcase testcase;

    @NotNull
    @ApiProperty(name="bodyType",desc="请求体类型",required = true)
    private java.lang.String bodyType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Testcase getTestcase() {
        return testcase;
    }

    public void setTestcase(Testcase testcase) {
        this.testcase = testcase;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}
