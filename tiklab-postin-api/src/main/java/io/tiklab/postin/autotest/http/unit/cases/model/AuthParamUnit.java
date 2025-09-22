package io.tiklab.postin.autotest.http.unit.cases.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.api.http.definition.model.AuthApiKey;
import io.tiklab.postin.api.http.definition.model.AuthBearer;
import io.tiklab.toolkit.beans.annotation.Mapper;


/**
 * 认证（主） 模型
 */
@ApiModel
@Mapper
public class AuthParamUnit extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private String id;

    @ApiProperty(name="apiUnitId",desc="所属接口")
    private String apiUnitId;

    @ApiProperty(name="type",desc="认证类型")
    private String type;

    private AuthApiKeyUnit apiKey;
    private AuthBearerUnit bearer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiUnitId() {
        return apiUnitId;
    }

    public void setApiUnitId(String apiUnitId) {
        this.apiUnitId = apiUnitId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AuthApiKeyUnit getApiKey() {
        return apiKey;
    }

    public void setApiKey(AuthApiKeyUnit apiKey) {
        this.apiKey = apiKey;
    }

    public AuthBearerUnit getBearer() {
        return bearer;
    }

    public void setBearer(AuthBearerUnit bearer) {
        this.bearer = bearer;
    }
}
