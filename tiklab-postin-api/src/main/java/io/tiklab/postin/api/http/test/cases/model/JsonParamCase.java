package io.tiklab.postin.api.http.test.cases.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel
@Join
@Mapper
public class JsonParamCase extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;


    @ApiProperty(name="parent",desc="上级参数")
    @Mappings({
            @Mapping(source = "parent.id",target = "parentId")
    })
    @JoinField(key = "id")
    private JsonParamCase parent;

    @NotNull
    @ApiProperty(name="httpCase",desc="所属接口用例",required = true)
    @Mappings({
            @Mapping(source = "httpCase.id",target = "httpCaseId")
    })
    @JoinField(key = "id")
    private HttpTestcase httpCase;

    @NotNull
    @ApiProperty(name="paramName",desc="参数名称",required = true)
    private java.lang.String paramName;

    @NotNull
    @ApiProperty(name="dataType",desc="数据类型",required = true)
    private java.lang.String dataType;

    @ApiProperty(name="value",desc="值")
    private java.lang.String value;

    @ApiProperty(name="sort",desc="排序")
    private java.lang.Integer sort;

    @ApiProperty(name="children",desc="子节点列表")
    private List<JsonParamCase> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HttpTestcase getHttpCase() {
        return httpCase;
    }

    public void setHttpCase(HttpTestcase httpCase) {
        this.httpCase = httpCase;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public JsonParamCase getParent() {
        return parent;
    }

    public void setParent(JsonParamCase parent) {
        this.parent = parent;
    }

    public List<JsonParamCase> getChildren() {
        return children;
    }

    public void setChildren(List<JsonParamCase> children) {
        this.children = children;
    }
}
