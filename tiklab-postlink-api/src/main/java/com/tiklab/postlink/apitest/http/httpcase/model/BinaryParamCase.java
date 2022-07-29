package com.tiklab.postlink.apitest.http.httpcase.model;

import com.tiklab.postlink.annotation.ApiModel;
import com.tiklab.postlink.annotation.ApiProperty;
import com.tiklab.beans.annotation.Mapper;
import com.tiklab.beans.annotation.Mapping;
import com.tiklab.beans.annotation.Mappings;
import com.tiklab.core.BaseModel;
import com.tiklab.join.annotation.Join;
import com.tiklab.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
@Mapper(targetAlias = "BinaryParamCaseEntity")
public class BinaryParamCase extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="httpCase",desc="所属接口用例",required = true)
    @Mappings({
            @Mapping(source = "httpCase.id",target = "httpCaseId")
    })
    @JoinQuery(key = "id")
    private HttpTestcase httpCase;

    @NotNull
    @ApiProperty(name="fileName",desc="fileName",required = true)
    private java.lang.String fileName;

    @ApiProperty(name = "streamData", desc = "文件流")
    private String streamData;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public HttpTestcase getHttpCase() {
        return httpCase;
    }

    public void setHttpCase(HttpTestcase httpCase) {
        this.httpCase = httpCase;
    }

    public java.lang.String getFileName() {
        return fileName;
    }

    public void setFileName(java.lang.String fileName) {
        this.fileName = fileName;
    }

    public String getStreamData() {
        return streamData;
    }

    public void setStreamData(String streamData) {
        this.streamData = streamData;
    }
}
