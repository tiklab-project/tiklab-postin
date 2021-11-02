package com.doublekit.apibox.apitest.model;

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
public class RequestHeaderCase extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="testcase",desc="所属接口用例",required = true)
    @Mappings({
            @Mapping(source = "testcase.id",target = "testcaseId")
    })
    @JoinField(id = "id")
    private Testcase testcase;

    @NotNull
    @ApiProperty(name="headerName",desc="请求头名称",required = true)
    private java.lang.String headerName;

    @ApiProperty(name="value",desc="值")
    private java.lang.String value;

    @ApiProperty(name="sort",desc="排序")
    private java.lang.Integer sort;

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

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
