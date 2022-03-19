package com.doublekit.apibox.apitest.http.httpcase.model;

import com.doublekit.apibox.annotation.ApiModel;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.beans.annotation.Mapper;
import com.doublekit.beans.annotation.Mapping;
import com.doublekit.beans.annotation.Mappings;
import com.doublekit.common.BaseModel;
import com.doublekit.join.annotation.Join;
import com.doublekit.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
@Mapper(targetAlias = "RequestBodyCaseEntity")
public class RequestBodyCase extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="testcase",desc="所属接口用例",required = true)
    @Mappings({
            @Mapping(source = "testcase.id",target = "testcaseId")
    })
    @JoinQuery(key = "id")
    private HttpTestcase httpTestcase;

    @NotNull
    @ApiProperty(name="bodyType",desc="请求体类型none/formdata/formxurlcoded/json/raw/binary",required = true)
    private java.lang.String bodyType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HttpTestcase getTestcase() {
        return httpTestcase;
    }

    public void setTestcase(HttpTestcase httpTestcase) {
        this.httpTestcase = httpTestcase;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}
