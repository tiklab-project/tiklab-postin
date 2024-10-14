package io.tiklab.postin.support.imexport.model;

import io.tiklab.postin.annotation.ApiProperty;

/**
 * 接口导入 模型
 */
public class ApixImportVo {

    @ApiProperty(name="methodId",desc="接口id")
    private String methodId;

    @ApiProperty(name="categoryId",desc="目录ID")
    private String categoryId;

    @ApiProperty(name="name",desc="名称")
    private String name;

    @ApiProperty(name="requestType",desc="请求类型")
    private String requestType;

    @ApiProperty(name="path",desc="路径")
    private String path;

    @ApiProperty(name="desc",desc="描述")
    private String desc;

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
