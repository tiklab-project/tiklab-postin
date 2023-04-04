package io.tiklab.postin.support.datastructure.model;

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
 * json结构 模型
 */
@ApiModel
@Join
@Mapper(targetAlias = "JsonParamDSEntity")
public class JsonParamDS extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="dataStructureId",desc="dataStructureId")
    private String dataStructureId;

    @ApiProperty(name="jsonText",desc="jsonText")
    private java.lang.String jsonText;

    public String getDataStructureId() {
        return dataStructureId;
    }

    public void setDataStructureId(String dataStructureId) {
        this.dataStructureId = dataStructureId;
    }

    public String getJsonText() {
        return jsonText;
    }

    public void setJsonText(String jsonText) {
        this.jsonText = jsonText;
    }

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }


}
