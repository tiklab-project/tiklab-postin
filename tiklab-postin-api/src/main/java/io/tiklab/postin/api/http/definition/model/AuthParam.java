package io.tiklab.postin.api.http.definition.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;


/**
 * 认证（主） 模型
 */
@ApiModel
@Mapper
public class AuthParam extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private String id;

    @ApiProperty(name="apiId",desc="所属接口")
    private String apiId;

    @ApiProperty(name="type",desc="认证类型")
    private String type;

    private AuthApiKey apiKey;
    private AuthBearer bearer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AuthApiKey getApiKey() {
        return apiKey;
    }

    public void setApiKey(AuthApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public AuthBearer getBearer() {
        return bearer;
    }

    public void setBearer(AuthBearer bearer) {
        this.bearer = bearer;
    }
}
