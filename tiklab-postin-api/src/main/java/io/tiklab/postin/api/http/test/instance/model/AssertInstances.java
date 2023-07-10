package io.tiklab.postin.api.http.test.instance.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;

import javax.validation.constraints.NotNull;

/**
 * http
 * 断言实例 模型
 */
@ApiModel
@Join
@Mapper
public class AssertInstances extends BaseModel {

    @ApiProperty(name="id",desc="唯一ID")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="httpInstance",desc="所属测试实例",required = true)
    @Mappings({
            @Mapping(source = "httpInstance.id",target = "httpInstanceId")
    })
    private HttpInstance httpInstance;

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

    public HttpInstance getHttpInstance() {
        return httpInstance;
    }

    public void setHttpInstance(HttpInstance httpInstance) {
        this.httpInstance = httpInstance;
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
