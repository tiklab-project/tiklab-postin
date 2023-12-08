package io.thoughtware.postin.support.imexport.model;

import io.thoughtware.postin.annotation.ApiProperty;

/**
 * 导入
 * form-data 模型
 */
public class FormDataImportVo {

    @ApiProperty(name="methodId",desc="接口Id")
    private String methodId;

    @ApiProperty(name="name",desc="参数名")
    private String name;

    @ApiProperty(name="value",desc="示例值")
    private String value;

    @ApiProperty(name="type",desc="数据类型")
    private String type;

    @ApiProperty(name="desc",desc="描述")
    private String desc;

    @ApiProperty(name="required",desc="必选")
    private Integer required;

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }
}
