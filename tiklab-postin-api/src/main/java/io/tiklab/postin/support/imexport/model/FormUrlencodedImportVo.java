package io.tiklab.postin.support.imexport.model;

import io.tiklab.postin.annotation.ApiProperty;

/**
 * 导入
 * form-url 模型
 */
public class FormUrlencodedImportVo {
    @ApiProperty(name="methodId",desc="接口Id")
    private String methodId;

    @ApiProperty(name="name",desc="参数名")
    private String name;

    @ApiProperty(name="value",desc="示例值")
    private String value;

    @ApiProperty(name="type",desc="数据类型")
    private String dataType;

    @ApiProperty(name="desc",desc="描述")
    private int required;

    @ApiProperty(name="required",desc="必选")
    private String desc;

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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getRequired() {
        return required;
    }

    public void setRequired(int required) {
        this.required = required;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
