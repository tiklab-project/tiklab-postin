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
public class AssertInstance {

    @ApiProperty(name="id",desc="唯一ID")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="testInstance",desc="所属测试实例",required = true)
    @Mappings({
            @Mapping(source = "id",target = "instanceId")
    })
    @JoinField(id = "id")
    private TestInstance testInstance;

    @ApiProperty(name="source",desc="断言来源类型")
    private java.lang.Integer source;

    @ApiProperty(name="propertyName",desc="属性名称")
    private java.lang.String propertyName;

    @ApiProperty(name="dataType",desc="数据类型")
    private java.lang.String dataType;

    @NotNull
    @ApiProperty(name="comparator",desc="比较符",required = true)
    private java.lang.String comparator;

    @ApiProperty(name="value",desc="值")
    private java.lang.String value;

    @ApiProperty(name="result",desc="断言结果,1:成功;-1:失败")
    private java.lang.Integer result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TestInstance getTestInstance() {
        return testInstance;
    }

    public void setTestInstance(TestInstance testInstance) {
        this.testInstance = testInstance;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getComparator() {
        return comparator;
    }

    public void setComparator(String comparator) {
        this.comparator = comparator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
