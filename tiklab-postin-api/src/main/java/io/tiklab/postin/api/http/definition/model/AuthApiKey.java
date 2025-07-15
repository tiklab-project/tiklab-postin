package io.tiklab.postin.api.http.definition.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;


/**
 * ApiKey自定义认证 模型
 */
@ApiModel
@Mapper
public class AuthApiKey extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private String id;

    @ApiProperty(name="apiId",desc="所属接口")
    private String apiId;

    @ApiProperty(name="name",desc="自定义的属性名")
    private String name;

    @ApiProperty(name="value",desc="属性值")
    private String value;

    @ApiProperty(name="apikeyIn",desc="添加的位置：header/query")
    private String apikeyIn;

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

    public String getApikeyIn() {
        return apikeyIn;
    }

    public void setApikeyIn(String apikeyIn) {
        this.apikeyIn = apikeyIn;
    }
}
