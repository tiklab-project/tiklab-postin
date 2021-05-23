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
public class FormParamCase {

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
    @ApiProperty(name="paramName",desc="参数名称",required = true)
    private java.lang.String paramName;

    @NotNull
    @ApiProperty(name="dataType",desc="数据类型",required = true)
    private java.lang.String dataType;

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

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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
