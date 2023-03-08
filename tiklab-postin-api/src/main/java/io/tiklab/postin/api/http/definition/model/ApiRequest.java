package io.tiklab.postin.api.http.definition.model;


import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

/**
 * 接口定义中请求区的模型
 */
@ApiModel
@Mapper(targetAlias = "ApiRequestEntity")
public class ApiRequest extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="httpId",desc="httpId")
    private java.lang.String httpId;

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
    public java.lang.String getHttpId() {
        return httpId;
    }

    public void setHttpId(java.lang.String httpId) {
        this.httpId = httpId;
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
