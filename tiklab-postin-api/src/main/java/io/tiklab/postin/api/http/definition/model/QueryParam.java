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

/**
 * query 模型
 */
@ApiModel
@Join
@Mapper(targetAlias = "QueryParamEntity")
public class QueryParam extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @ApiProperty(name="http",desc="所属接口")
    @Mappings({
            @Mapping(source = "http.id",target = "httpId")
    })
    @JoinQuery(key = "id")
    private HttpApi http;

    @NotNull
    @ApiProperty(name="paramName",desc="参数名称",eg="@text32",required = true)
    private java.lang.String paramName;

    @NotNull
    @ApiProperty(name="required",desc="是否必须,0:非必须;1:必须",eg="@int16",required = true)
    private java.lang.Integer required = 0;

    @ApiProperty(name="desc",desc="描述",eg="@text32")
    private java.lang.String desc;

    @ApiProperty(name="value",desc="示例值",eg="@text32")
    private java.lang.String value;

    @ApiProperty(name="sort",desc="排序",eg="@int16")
    private java.lang.Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HttpApi getHttp() {
        return http;
    }

    public void setHttp(HttpApi http) {
        this.http = http;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
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

}
