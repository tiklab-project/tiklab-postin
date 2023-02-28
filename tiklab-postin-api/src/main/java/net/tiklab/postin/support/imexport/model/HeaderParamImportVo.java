package net.tiklab.postin.support.imexport.model;

import net.tiklab.postin.annotation.ApiProperty;

/**
 * 导入
 * 请求头 模型
 */
public class HeaderParamImportVo {

    @ApiProperty(name="methodId",desc="接口Id")
    private String methodId;

    @ApiProperty(name="name",desc="参数名")
    private String name;

    @ApiProperty(name="value",desc="示例值")
    private String value;

    @ApiProperty(name="desc",desc="描述")
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
