package io.tiklab.postin.api.apix.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;

import javax.validation.constraints.NotNull;

/**
 * raw 模型
 */
@ApiModel
@Join
@Mapper
public class RawParam extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识，非自动生成")
    private java.lang.String id;

    @ApiProperty(name="apiId",desc="所属接口")
    private String apiId;

    @NotNull
    @ApiProperty(name = "type",desc = "raw中类型",required = true)
    private java.lang.String type;

    @NotNull
    @ApiProperty(name="raw",desc="raw自定义文本",required = true)
    private java.lang.String raw;

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

    public java.lang.String getRaw() {
        return raw;
    }

    public void setRaw(java.lang.String raw) {
        this.raw = raw;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
