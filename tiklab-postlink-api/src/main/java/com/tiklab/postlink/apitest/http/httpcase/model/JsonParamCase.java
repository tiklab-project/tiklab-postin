package com.tiklab.postlink.apitest.http.httpcase.model;

import com.tiklab.postlink.annotation.ApiModel;
import com.tiklab.postlink.annotation.ApiProperty;
import com.tiklab.beans.annotation.Mapper;
import com.tiklab.beans.annotation.Mapping;
import com.tiklab.beans.annotation.Mappings;
import com.tiklab.core.BaseModel;
import com.tiklab.join.annotation.Join;
import com.tiklab.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel
@Join
@Mapper(targetAlias = "JsonParamCaseEntity")
public class JsonParamCase extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;


    @ApiProperty(name="parent",desc="上级参数")
    @Mappings({
            @Mapping(source = "parent.id",target = "parentId")
    })
    @JoinQuery(key = "id")
    private JsonParamCase parent;

    @NotNull
    @ApiProperty(name="httpCase",desc="所属接口用例",required = true)
    @Mappings({
            @Mapping(source = "httpCase.id",target = "httpCaseId")
    })
    @JoinQuery(key = "id")
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
