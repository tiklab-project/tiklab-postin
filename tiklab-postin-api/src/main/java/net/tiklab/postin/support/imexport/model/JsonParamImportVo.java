package net.tiklab.postin.support.imexport.model;

import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.postin.api.http.definition.model.JsonParam;

/**
 * 导入
 * json 模型
 */
public class JsonParamImportVo {

    @ApiProperty(name="parentId",desc="上级Id")
    private JsonParam parentId;

    @ApiProperty(name="methodId",desc="接口Id")
    private String methodId;

    @ApiProperty(name="name",desc="参数名")
    private String name;

    @ApiProperty(name="value",desc="示例值")
    private String value;

    @ApiProperty(name="type",desc="数据类型")
    private String dataType;

    @ApiProperty(name="required",desc="必选")
    private int required;

    @ApiProperty(name="desc",desc="描述")
    private String desc;

    public JsonParam getParentId() {
        return parentId;
    }

    public void setParentId(JsonParam parentId) {
        this.parentId = parentId;
    }

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
