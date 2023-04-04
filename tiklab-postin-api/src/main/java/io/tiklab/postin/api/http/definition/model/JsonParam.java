package io.tiklab.postin.api.http.definition.model;

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
@Mapper(targetAlias = "JsonParamEntity")
public class JsonParam extends BaseModel {

    @ApiProperty(name="id",desc="唯一ID")
    private java.lang.String id;

    @ApiProperty(name="parent",desc="上级参数")
    @Mappings({
            @Mapping(source = "parent.id",target = "parentId")
    })
    @JoinQuery(key = "id")
    private JsonParam parent;

    @NotNull
    @ApiProperty(name="httpId",desc="httpId")
    private String httpId;

    @ApiProperty(name="jsonText",desc="jsonText")
    private java.lang.String jsonText;


    @ApiProperty(name="paramName",desc="参数名称",eg="@text32")
    private java.lang.String paramName;


    @ApiProperty(name="dataType",desc="数据类型,[int,string,boolean]")
    private java.lang.String dataType;


    @ApiProperty(name="required",desc="是否必须,0:非必须;1:必须")
    private java.lang.Integer required = 0;

    @ApiProperty(name="desc",desc="参数说明",eg="@text32")
    private java.lang.String desc;

    @ApiProperty(name="value",desc="示例值")
    private java.lang.String value;

    @ApiProperty(name="sort",desc="排序")
    private java.lang.Integer sort;

    @ApiProperty(name="children",desc="子节点列表")
    private List<JsonParam> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHttpId() {
        return httpId;
    }

    public void setHttpId(String httpId) {
        this.httpId = httpId;
    }

    public String getJsonText() {
        return jsonText;
    }

    public void setJsonText(String jsonText) {
        this.jsonText = jsonText;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public JsonParam getParent() {
        return parent;
    }

    public void setParent(JsonParam parent) {
        this.parent = parent;
    }

    public List<JsonParam> getChildren() {
        return children;
    }

    public void setChildren(List<JsonParam> children) {
        this.children = children;
    }
}
