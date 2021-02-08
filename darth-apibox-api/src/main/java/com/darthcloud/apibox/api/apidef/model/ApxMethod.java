package com.darthcloud.apibox.api.apidef.model;

import com.darthcloud.apibox.annotation.ApiModel;
import com.darthcloud.apibox.annotation.ApiProperty;
import com.darthcloud.apibox.category.model.Category;
import com.darthcloud.beans.annotation.Mapping;
import com.darthcloud.beans.annotation.Mappings;
import com.darthcloud.join.annotation.Join;
import com.darthcloud.join.annotation.JoinField;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
public class ApxMethod {

    @ApiProperty(name="id",desc="唯一ID")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="category",desc="所属分类",eg="@selectOne",required = true)
    @Mappings({
            @Mapping(source = "id",target = "categoryId")
    })
    @JoinField(id = "id")
    private Category category;

    @NotNull
    @ApiProperty(name="name",desc="接口名称",eg="@text32",required = true)
    private java.lang.String name;

    @NotNull
    @ApiProperty(name="requestType",desc="请求类型",required = true)
    private java.lang.String requestType;

    @NotNull
    @ApiProperty(name="path",desc="接口路径",required = true)
    private java.lang.String path;

    @ApiProperty(name="desc",desc="描述")
    private java.lang.String desc;

    @ApiProperty(name="sort",desc="排序")
    private java.lang.Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
