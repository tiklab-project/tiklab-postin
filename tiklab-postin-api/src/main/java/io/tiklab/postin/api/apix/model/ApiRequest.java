package io.tiklab.postin.api.apix.model;


import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

/**
 * 接口定义中请求区的模型
 */
@ApiModel
@Mapper
public class ApiRequest extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="apiId",desc="所属接口")
    private String apiId;

    @ApiProperty(name="bodyType",desc="请求体类型")
    private java.lang.String bodyType;

    @ApiProperty(name="preScript",desc="前置脚本")
    private java.lang.String preScript;

    @ApiProperty(name="afterScript",desc="后置脚本")
    private java.lang.String afterScript;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public java.lang.String getBodyType() {
        return bodyType;
    }

    public void setBodyType(java.lang.String bodyType) {
        this.bodyType = bodyType;
    }
    public java.lang.String getPreScript() {
        return preScript;
    }

    public void setPreScript(java.lang.String preScript) {
        this.preScript = preScript;
    }
    public java.lang.String getAfterScript() {
        return afterScript;
    }

    public void setAfterScript(java.lang.String afterScript) {
        this.afterScript = afterScript;
    }
}
