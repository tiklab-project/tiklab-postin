package io.tiklab.postin.api.apix.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * json 模型
 */
@ApiModel
@Join
@Mapper
public class JsonParam extends BaseModel {

    @ApiProperty(name="id",desc="唯一ID")
    private java.lang.String id;

    @ApiProperty(name="apiId",desc="所属接口")
    private String apiId;

    @ApiProperty(name="jsonText",desc="jsonText")
    private java.lang.String jsonText;

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

    public String getJsonText() {
        return jsonText;
    }

    public void setJsonText(String jsonText) {
        this.jsonText = jsonText;
    }

}
