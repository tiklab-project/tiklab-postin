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
@Mapper(targetAlias = "RawParamCaseEntity")
public class RawParamCase extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="testcase",desc="所属接口用例",required = true)
    @Mappings({
            @Mapping(source = "httpTestcase.id",target = "testcaseId")
    })
    @JoinQuery(key = "id")
    private HttpTestcase httpTestcase;

    @ApiProperty(name="raw",desc="自定义文本")
    private java.lang.String raw;

    @NotNull
    @ApiProperty(name = "type",desc = "raw中类型" , required = true)
    private java.lang.String type;

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

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
